package com.nadeul.ndj.oauth2;


import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.nadeul.ndj.dto.MemberDto;
import com.nadeul.ndj.entity.Member;
import com.nadeul.ndj.repository.MemberRepository;
import com.nadeul.ndj.service.AuthenticationService;
import com.nadeul.ndj.service.JwtService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository userRepository;
//    private final AuthenticationService authenticationService;
//    private final JwtService jwtService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");
        try {
        		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        		log.info("oAuth2User 정보 : {} " , oAuth2User.toString());
        		
        		Map<String, Object> attributes = oAuth2User.getAttributes();
        		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        		String email = (String) kakaoAccount.get("email");
        		
        		
        		Optional<Member> existingMember = userRepository.findByEmail(email);

            // User의 Role이 GUEST일 경우 처음 요청한 회원이므로 회원가입 페이지로 리다이렉트 ((Member) oAuth2User).getRole() == Role.USER
            if(!existingMember.isPresent()) {
                response.sendRedirect("https://www.nadry.shop/login"); // 프론트의 회원가입 추가 정보 입력 폼으로 리다이렉트
            } else {
            	/*
            	String jwtToken = jwtService.generateToken(existingMember.get());
                String refreshToken = jwtService.generateRefreshToken(existingMember.get());
                authenticationService.revokeAllUserTokens(existingMember.get());
                authenticationService.saveUserToken(existingMember.get(), jwtToken);
                
            	MemberDto memberDto = new MemberDto();
			  			  memberDto.setId(existingMember.get().getMemId());
			  			  memberDto.setName(existingMember.get().getName());
			  			  memberDto.setEmail(existingMember.get().getEmail());
			  			  memberDto.setPassword(existingMember.get().getPassword());
			  			  memberDto.setBirthDay(existingMember.get().getBirthDay());
			  			  memberDto.setRole(existingMember.get().getRole());
			  			  memberDto.setProfileUrl(existingMember.get().getProfileUrl());
			*/  			  
            }
        } catch (Exception e) {
            throw e;
        }

    }

    // TODO : 소셜 로그인 시에도 무조건 토큰 생성하지 말고 JWT 인증 필터처럼 RefreshToken 유/무에 따라 다르게 처리해보기
    private void loginSuccess(HttpServletResponse response, OAuth2User oAuth2User) throws IOException {
//    		String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
//        String refreshToken = jwtService.createRefreshToken();
//        response.addHeader(jwtService.getAccessHeader(), "Bearer " + accessToken);
//        response.addHeader(jwtService.getRefreshHeader(), "Bearer " + refreshToken);
//
//        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
//        jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
    }
}