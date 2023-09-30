package com.nadeul.ndj.config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadeul.ndj.dto.ApiResponse;
import com.nadeul.ndj.enums.ApiResponseEnum;
import com.nadeul.ndj.repository.TokenRepository;
import com.nadeul.ndj.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;
  
  private final AntPathMatcher antPathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;
  
    
    // white list 통과 로직
	  if (SecurityConfig.whiteListedRoutes != null) {
	      for (String path : SecurityConfig.whiteListedRoutes) {
	          if (antPathMatcher.match(path, request.getServletPath())) {
	              filterChain.doFilter(request, response);
	              return;
	          }
	      }
	  }
    
    // whilteList에 포함 되어 있지 않고  "Authorization" 헤더가 요청에 없거나 "Bearer "로 시작하지 않는 경우 401 반환
    if(authHeader == null || !authHeader.startsWith("Bearer ") && !Arrays.asList(SecurityConfig.whiteListedRoutes).contains(request.getServletPath())) {
    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
    	response.setContentType("application/json");
    	ApiResponse<?> aa = ApiResponse.errorResponse(ApiResponseEnum.UNAUTHORIZED);
    	
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(aa);
	    response.getWriter().write(jsonResponse);
	    
      return;
    }
    
    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);
  
    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
        var isTokenValid = tokenRepository.findByToken(jwt)
            .map(t -> !t.isExpired() && !t.isRevoked())
            .orElse(false);
      
        if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            return;
        }
    } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
        return;
    }
  
    filterChain.doFilter(request, response);
  }
}
