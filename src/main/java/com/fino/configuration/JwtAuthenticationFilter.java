package com.fino.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fino.entity.FinoUserDetails;
import com.fino.service.UserService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	@Autowired
	private UserService userService;
	@Autowired
	private JwtHelpers jwtHelpers;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	final String requestTokenHeader = request.getHeader("Authorization");
	String username = null;
	String jwtToken = null;
	log.info("token:- "+requestTokenHeader.substring(7));
	
	
	if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
		
		jwtToken = requestTokenHeader.substring(7);
		try {
			username = this.jwtHelpers.getUsernameFromToken(jwtToken);
		} catch (IllegalArgumentException e) {
			logger.info("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			logger.info("JWT Token has expired");
		}
	} else {
		logger.warn("JWT Token does not begin with Bearer String");
	}
	if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		FinoUserDetails finoUserDetails = this.userService.loadUserByUsername(username);
		
		if (this.jwtHelpers.validateToken(jwtToken, finoUserDetails)) {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					finoUserDetails, null, finoUserDetails.getAuthorities());
			usernamePasswordAuthenticationToken
					.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		else {
			logger.info("authentication failed");
		}
	}
	filterChain.doFilter(request, response);
	
	
		
	}

}
