package com.fino.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.fino.dto.FinoUserDetailsDto;
import com.fino.entity.FinoUserDetails;

public interface UserService {


	Map<Object, Object> insertFinoUserDetails(FinoUserDetailsDto finoUserDetailsDto);

	Map<Object, Object> deleteFinoUserDetails(Long recieptId);

	Map<Object, Object> updateFinoUserDetails(Long recieptId, FinoUserDetailsDto finoUserDetailsDto);
	Map<Object, Object> getAllFinoUsersDetails();
	FinoUserDetails loadUserByUsername(String username);
	
}
