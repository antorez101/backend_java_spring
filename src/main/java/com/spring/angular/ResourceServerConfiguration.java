package com.spring.angular;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/clientes", "/api/clientes/page/**", "/api/clientes/upload/**", "/api/clientes/download/img/**", "/img/**").permitAll()
		
//		.antMatchers(HttpMethod.GET, "/api/clientes/{id}", "/api/clientes/download/**").hasAnyRole("USER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/clientes/upload/**").hasRole("ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/clientes").hasRole("ADMIN")
//		.antMatchers("/api/clientes/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTION", "PATCH"));
		config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource urlBaseCors = new UrlBasedCorsConfigurationSource();
		urlBaseCors.registerCorsConfiguration("/**", config);
		return urlBaseCors;
		
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> filterRegistration(){
		
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filter;
	}
	
	
	

}
