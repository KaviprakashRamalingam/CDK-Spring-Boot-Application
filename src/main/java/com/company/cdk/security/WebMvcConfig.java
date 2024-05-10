package com.company.cdk.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.company.cdk.Interceptors.LoginInterceptor;

@Configuration
//@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/**") 
        .excludePathPatterns("/", "/login", "/userlogin", "/register", "/useroauth2login", "/styles.css", "/createUser.htm", "/favicon.ico", "/error");
	}
	
	
	
}
