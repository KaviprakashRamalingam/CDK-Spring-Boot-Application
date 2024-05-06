package com.company.cdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(scanBasePackages = {"com.company.cdk.controller",
										   "com.company.cdk.dao",
										   "com.company.cdk.model",
										   "com.company.cdk.Validator",
										   "com.company.cdk.security",
										   "com.company.cdk.security.oauth2",
										   "com.company.cdk.Interceptors",
										   "com.company.cdk.mail",
										   "com.company.service",
										   "com.company.service.Impl"})
public class CdkApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CdkApplication.class, args);
	}

}
