package com.fino.utils;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FinoSecurityContext {
	
	
	
	public String getCurrentLoggedInUser() {
		
		var securityCxtHolder=SecurityContextHolder.getContext();
		
		if(securityCxtHolder.getAuthentication() !=null) {
			return securityCxtHolder.getAuthentication().getName();
		}
		else {
			return null;
		}
		 
	}

}
