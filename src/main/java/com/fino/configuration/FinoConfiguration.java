package com.fino.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@EnableAsync
public class FinoConfiguration {

	@Autowired
	private CustomAuthentication customAuthentication;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(this.customAuthentication);
		return authenticationManagerBuilder.build();
	}

	@Bean
	 CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*")); // Allow requests from all origins
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP
																									// methods
		configuration.setAllowedHeaders(Arrays.asList("*")); // Allowed
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Apply the configuration to all paths
		return source;
	}

//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
//				.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//						.requestMatchers("/auth/**", "/entry/point/**").authenticated()
//						.requestMatchers("eidiko/internal/userdata/**", "eidiko/internal/authentication/**",
//								"/employee/image/**", "/multithread/**", "/login1", "/eidiko/**", "location/**")
//						.permitAll()
//
//				).exceptionHandling(ex -> ex.authenticationEntryPoint(JwtAuthenticationInitialPoint))
//				.exceptionHandling(ex -> ex.accessDeniedHandler(CustomAccessDeniedHandler))
//				.sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//		return http.build();
//	}

}
