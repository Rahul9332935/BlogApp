package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.filter.JwtFilter;
import com.blog.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	
	private JwtFilter jwtFilter;
	
	
	public SecurityConfig(JwtFilter jwtFilter) {
		this.jwtFilter= jwtFilter;
    }
	
	@Bean
	UserDetailsService userDetailsService() {
		return new CustomUserDetailService();		
			
	}
	
	
	 @Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
	      return httpSecurity.csrf(csrf->csrf.disable())
	              .authorizeHttpRequests(auth->auth
	            		  .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
	            			.requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
	            			.requestMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.GET, "/api/users/all").hasRole("ADMIN")
	            			.requestMatchers(HttpMethod.GET, "/api/posts/{postId}").hasRole("ADMIN")
	            			.requestMatchers(HttpMethod.PUT, "/api/posts/{postId}").hasRole("ADMIN")
	            			.requestMatchers(HttpMethod.DELETE, "/api/posts/{postId}").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.POST, "/api/posts").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.POST, "/api/categories/**").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.GET, "/api/categories").hasRole("ADMIN")
	            			.requestMatchers(HttpMethod.PUT, "/api/categories/{categoryId}").hasRole("ADMIN")
	            			.requestMatchers(HttpMethod.GET, "/api/categories/{categoryId}").hasRole("ADMIN")
	            			.requestMatchers(HttpMethod.DELETE, "/api/categories/{categoryId}").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.POST, "/api/comments/post/{postId}").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.PUT, "/api/comments/{postId}/{commentId}").hasAnyRole("ADMIN","USER")
	            			.requestMatchers(HttpMethod.PUT, "/api/comments/post/{postId}").hasAnyRole("ADMIN")
	            			.requestMatchers(HttpMethod.DELETE, "/api/comments//{postId}/{commentId}").hasAnyRole("ADMIN","USER")
	            			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
	            			.anyRequest().hasRole("ADMIN")
	            		  )
	              .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	              .authenticationProvider(authenticationProvider())
	              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	              .build();
	  }
	 	@Bean
	    AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	    }
	

}
