package com.bikash.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bikash.interceptor.SessionInterceptor;

@Configuration
public class MySessionInterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private SessionInterceptor intercept;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(intercept)
				.addPathPatterns("/dashboard","/filterdashboard","/newpost","/comment","/createpost","/editpost","/allcomments","/filtercomments","/deletepost","/deletecomment","");
	}
}
