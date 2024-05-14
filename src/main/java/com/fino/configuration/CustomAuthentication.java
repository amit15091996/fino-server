package com.fino.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fino.entity.FinoUserDetails;
import com.fino.service.UserService;

import com.fino.exception.*;


@Component
public class CustomAuthentication implements AuthenticationProvider {

	private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String mobileNumber = (String)authentication.getName();
		String password = (String) authentication.getCredentials();
		FinoUserDetails finoUserDetails= this.userService.loadUserByUsername(mobileNumber);
		if (finoUserDetails == null || !finoUserDetails.getMobileNumber().equalsIgnoreCase(mobileNumber)) {
			throw new CustomAuthenticationException("Please check your user Id");
		}

		else if (!this.passwordEncoder.matches(password, finoUserDetails.getPassword())) {
			throw new CustomAuthenticationException("Please check your password and try again....... ");
		}
		
		else if (!finoUserDetails.isEnabled()) {
			throw new CustomAuthenticationException("sorry your account is inactive..........");
		}

		java.util.Collection<? extends GrantedAuthority> authorities = finoUserDetails.getAuthorities();

		return new UsernamePasswordAuthenticationToken(finoUserDetails,password, authorities);

	}

	@Override
	public boolean supports(Class<?> authentication) {

		return true;
	}

}
