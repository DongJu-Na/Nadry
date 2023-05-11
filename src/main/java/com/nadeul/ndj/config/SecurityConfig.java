package com.nadeul.ndj.config;

import com.nadeul.ndj.jwt.JwtSecurityConfig;
import com.nadeul.ndj.jwt.JwtAccessDeniedHandler;
import com.nadeul.ndj.jwt.JwtAuthenticationEntryPoint;
import com.nadeul.ndj.jwt.TokenProvider;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

	private final TokenProvider tokenProvider;
	private final CorsFilter corsFilter;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final String[] WHITE_LIST = {
				"/swagger-ui/**",
				"/v3/api-docs/**",
				"/api/v1/tour/**", 
			};

	public SecurityConfig(TokenProvider tokenProvider, CorsFilter corsFilter,
			JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		this.tokenProvider = tokenProvider;
		this.corsFilter = corsFilter;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

	

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
        // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
        .csrf().disable()

        .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

        .exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        .accessDeniedHandler(jwtAccessDeniedHandler)

        // enable h2-console
        .and()
        .headers()
        .frameOptions()
        .sameOrigin()

        // 시큐리티는 기본적으로 세션을 사용
        // 여기서는 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        
        // 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
        .and()
        .authorizeHttpRequests() // HttpServletRequest 사용 요청 접근제한
        .requestMatchers(WHITE_LIST).permitAll()
        .requestMatchers(PathRequest.toH2Console()).permitAll()
        .anyRequest().authenticated()
        
        // JwtFilter 를 addFilterBefore 로 등록했던 JwtSecurityConfig 클래스를 적용
        .and()
        .apply(new JwtSecurityConfig(tokenProvider));

		return httpSecurity.build();
	}

}
