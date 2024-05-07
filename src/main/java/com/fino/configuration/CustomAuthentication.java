package com.fino.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.fino.service.UserService;

import exception.BadRequest;


@Component
public class CustomAuthentication implements AuthenticationProvider {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal().toString();
		String password = (String) authentication.getCredentials();

		UserDetails userDetails= this.userService.loadUserByUsername(username);

		if (userDetails == null || !userDetails.getUsername().equalsIgnoreCase(username)) {
			throw new BadRequest("Please check your user Id");
		}

		else if (!this.passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadRequest("Please check your password and try again....... ");
		}

		java.util.Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return true;
	}

}
