package com.company.cdk.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.company.cdk.Interceptors.LoginInterceptor;

@Configuration
//@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer{			//extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> 

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/**").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/userlogin", false)
                        .failureUrl("/login"))
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .failureUrl("/login")
                        .defaultSuccessUrl("/useroauth2login", true))
                .build();
	}
}
