package com.fino.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.fino.dto.FinoUserDetailsDto;

public interface UserService {


	Map<Object, Object> insertFinoUserDetails(FinoUserDetailsDto finoUserDetailsDto);

	Map<Object, Object> deleteFinoUserDetails(Long recieptId);

	Map<Object, Object> updateFinoUserDetails(Long recieptId, FinoUserDetailsDto finoUserDetailsDto);

	Map<Object, Object> getAllFinoUsersDetails();
	UserDetails loadUserByUsername(String username);
	
}
