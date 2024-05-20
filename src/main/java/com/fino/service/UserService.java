package com.fino.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fino.dto.FinoUserDetailsDto;
import com.fino.entity.FinoUserDetails;

public interface UserService extends UserDetailsService {


	Map<Object, Object> insertFinoUserDetails(FinoUserDetailsDto finoUserDetailsDto);
	Map<Object, Object> deleteFinoUserDetails(Long recieptId);
	Map<Object, Object> updateFinoUserDetails(Long recieptId, FinoUserDetailsDto finoUserDetailsDto);
	Map<Object, Object> getAllFinoUsersDetails();
	FinoUserDetails loadUserByUsername(String mobileNumber);
	Map<Object, Object> onUserlogin(String mobileNumber,String password);
	Map<Object, Object> assignANewRole(String mobileNumber,String newRole);
	Map<Object, Object> deletePreviousAssignedRole(Long roleId);
	Map<Object, Object> resetPassword(String mobileNumber,LocalDate dateOfBirth);
	Map<Object, Object> changePassword(String mobileNumber,String oldPassword,String newPassword);
}
