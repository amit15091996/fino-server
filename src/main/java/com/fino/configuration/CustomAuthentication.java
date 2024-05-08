package com.fino.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fino.entity.FinoUserDetails;
import com.fino.service.UserService;

import exception.BadRequest;


@Component
public class CustomAuthentication implements AuthenticationProvider {

	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		FinoUserDetails finoUserAuthenticate = (FinoUserDetails)authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		FinoUserDetails finoUserDetails= this.userService.loadUserByUsername(finoUserAuthenticate.getMobileNumber());

		if (finoUserDetails == null || !finoUserDetails.getMobileNumber().equalsIgnoreCase(finoUserAuthenticate.getMobileNumber())) {
			throw new BadRequest("Please check your user Id");
		}

		else if (!this.passwordEncoder.matches(password, finoUserDetails.getPassword())) {
			throw new BadRequest("Please check your password and try again....... ");
		}

		java.util.Collection<? extends GrantedAuthority> authorities = finoUserDetails.getAuthorities();

		return new UsernamePasswordAuthenticationToken(finoUserDetails, password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return true;
	}

}
