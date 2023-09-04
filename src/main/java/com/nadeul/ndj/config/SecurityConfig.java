package com.nadeul.ndj.config;

import static com.nadeul.ndj.model.Permission.ADMIN_CREATE;
import static com.nadeul.ndj.model.Permission.ADMIN_DELETE;
import static com.nadeul.ndj.model.Permission.ADMIN_READ;
import static com.nadeul.ndj.model.Permission.ADMIN_UPDATE;
import static com.nadeul.ndj.model.Permission.MANAGER_CREATE;
import static com.nadeul.ndj.model.Permission.MANAGER_DELETE;
import static com.nadeul.ndj.model.Permission.MANAGER_READ;
import static com.nadeul.ndj.model.Permission.MANAGER_UPDATE;
import static com.nadeul.ndj.model.Role.ADMIN;
import static com.nadeul.ndj.model.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.nadeul.ndj.handler.OAuth2LoginFailureHandler;
import com.nadeul.ndj.handler.OAuth2LoginSuccessHandler;
import com.nadeul.ndj.service.CustomOAuth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {


  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;
  private final CustomOAuth2UserService customOAuth2UserService;
  private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
  private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
  
  public static final String[] whiteListedRoutes = new String[]{
      "/api/v1/**",
      "/api/v2/*/list",
      "/v2/api-docs",
      "/v3/api-docs",
      "/v3/api-docs/**",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui/**",
      "/webjars/**",
      "/swagger-ui.html",
      "/h2-console/**"
  };
  

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // csrf 보안 사용 x
        .formLogin().disable() // FormLogin 사용 x 
        .httpBasic().disable() // httpBasic 사용 x 
        .authorizeHttpRequests()
        .requestMatchers(whiteListedRoutes).permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())


        .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
        .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
        .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
        .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
        .requestMatchers(PathRequest.toH2Console()).permitAll()

       /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
        .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
        .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
        .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
        .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/


        .anyRequest()
        .authenticated()
        .and()
        .headers().frameOptions().sameOrigin()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용하지 않으므로 STATELESS로 설정
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout()
        .logoutUrl("/api/v1/auth/logout")
        .addLogoutHandler(logoutHandler)
        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        .and()
        .oauth2Login()
        .successHandler(oAuth2LoginSuccessHandler)
        .failureHandler(oAuth2LoginFailureHandler)
        .userInfoEndpoint()
        .userService(customOAuth2UserService)
    ;

    return http.build();
  }


}
