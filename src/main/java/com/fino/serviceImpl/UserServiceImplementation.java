package com.fino.serviceImpl;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fino.configuration.JwtHelpers;
import com.fino.dto.FinoUserDetailsDto;
import com.fino.entity.FinoUserDetails;
import com.fino.entity.FinoUserRoles;
import com.fino.helpers.AppConstants;
import com.fino.repository.FinoUserDetailsRepository;
import com.fino.service.UserService;

import exception.BadRequest;
import exception.InternalServerError;
import exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImplementation implements UserService{

	@Autowired
	private FinoUserDetailsRepository finoUserDetailsRepository;
	
	private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	
	@Autowired
	private JwtHelpers jwtHelpers;
	
	@Override
	public Map<Object, Object> insertFinoUserDetails(FinoUserDetailsDto finoUserDetailsDto) {
		Map<Object, Object> userSignUpMap=new HashMap<>();
		var finoUserDetails=new FinoUserDetails();
		var finoUserRoles= new FinoUserRoles();
	
	try {	
		finoUserDetails.setUserName(finoUserDetailsDto.getUserName());finoUserDetails.setDateOfBirth(finoUserDetailsDto.getDateOfBirth());
		finoUserDetails.setEmailId(finoUserDetailsDto.getEmailId());finoUserDetails.setFirstName(finoUserDetailsDto.getFirstName());
		finoUserDetails.setLastName(finoUserDetailsDto.getLastName());finoUserDetails.setMobileNumber(finoUserDetailsDto.getMobileNumber());
		finoUserDetails.setPassword(this.passwordEncoder.encode(finoUserDetailsDto.getPassword()));
		finoUserRoles.setRoleName(AppConstants.USER_ROLE);finoUserRoles.setRoleDescription(AppConstants.USER_ROLE_DESC);
		finoUserRoles.setFinoUserDetails(finoUserDetails);
		finoUserDetails.setFinoUserRoles(Arrays.asList(finoUserRoles));
		this.finoUserDetailsRepository.save(finoUserDetails);
		userSignUpMap.put(AppConstants.status, AppConstants.success);
		userSignUpMap.put(AppConstants.statusCode, AppConstants.ok);
		userSignUpMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
	}
	catch (Exception e) {throw new BadRequest(e.getMessage());}
		return userSignUpMap;
	}

	@Override
	public Map<Object, Object> deleteFinoUserDetails(Long recieptId) {
		
		return null;
	}

	@Override
	public Map<Object, Object> updateFinoUserDetails(Long recieptId, FinoUserDetailsDto finoUserDetailsDto) {
		
		return null;
	}

	@Override
	public Map<Object, Object> getAllFinoUsersDetails() {
	
		return null;
	}

	@Override
	public FinoUserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		
		
		return this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);
	}

	@Override
	public Map<Object, Object> onUserlogin(String mobileNumber,String password) {
		Map<Object, Object> userLoginMap=new HashMap<>();
		try {
		FinoUserDetails finoUser=this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);
		if(finoUser !=null) {
			var jwtToken=this.jwtHelpers.generateToken(finoUser);
			userLoginMap.put(AppConstants.USER_NAME,finoUser.getMobileNumber());
			userLoginMap.put(AppConstants.JWT_TOKEN,jwtToken);
			userLoginMap.put(AppConstants.TOKEN_EXPIRATION_IN_MILIS,this.jwtHelpers.getExpirationDateFromToken(jwtToken).toInstant().toEpochMilli());
			userLoginMap.put(AppConstants.USER_ROLES,finoUser.getFinoUserRoles());
			userLoginMap.put(AppConstants.status, AppConstants.success);
			userLoginMap.put(AppConstants.statusCode, AppConstants.ok);
			userLoginMap.put(AppConstants.statusMessage, AppConstants.userLoggedInSuccesfully);
			return userLoginMap;
		}
		else {throw new NotFoundException("Sorry No user found with the given mobile number"+mobileNumber);}
		}catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
		
		
	}
	
	
	
}
