package br.pro.software.eleicoes2020;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.pro.software.eleicoes2020.interceptor.LoginInterceptor;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/login", "/", "/fazerLogin", 
				"/webjars/**", "/bootstrap/**", "/static/vote.png", "/static/**", "/public/**");
	}
}