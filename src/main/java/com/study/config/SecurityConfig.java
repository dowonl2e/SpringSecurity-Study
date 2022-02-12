package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.study.filter.JwtAuthenticationFilter;
import com.study.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

/**
 * Security 구성 설정
 * @author dowonlee
 *
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	public void init(WebSecurity web) throws Exception {
		super.init(web);
		web.ignoring().antMatchers("/static/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // Rest API : CSRF보안이 필요 없으므로 미사용 처리(Rest 방식의 경우 미사용 처리 필요함)
			.httpBasic().disable() // Rest API : 기본설정 사용안함(기본설정은 비인증시 로그인화면으로 리다이렉트 됨)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 토큰으로 인증 하므로 세션은 필요 없어 생성하지 않음
			.and()
				.authorizeRequests() //다음 Request에 대한 사용 권한 체크
				.antMatchers("/api/signin", "/api/signup").permitAll() // 가입 및 인증 주소는 누구나 접근가능
                // .antMatchers(HttpMethod.GET, "helloworld/**").permitAll() // hellowworld로 시작하는 GET요청 리소스는 누구나 접근가능
                .anyRequest().hasRole("USER") // 그외 나머지 요청은 모두 인증된 회원만 접근 가능
            .and()
            	.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token 필터를 id/password 인증 필터 전에 넣는다
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
	}
}
