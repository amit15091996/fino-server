package com.fino.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fino.dto.FinoUserDetailsDto;
import com.fino.entity.FinoUserDetails;
import com.fino.repository.FinoUserDetailsRepository;
import com.fino.service.UserService;

@Service
public class UserServiceImplementation implements UserService, UserDetailsService {

	@Autowired
	private FinoUserDetailsRepository finoUserDetailsRepository;

	@Override
	public Map<Object, Object> insertFinoUserDetails(FinoUserDetailsDto finoUserDetailsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> deleteFinoUserDetails(Long recieptId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> updateFinoUserDetails(Long recieptId, FinoUserDetailsDto finoUserDetailsDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> getAllFinoUsersDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FinoUserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
		
		return this.finoUserDetailsRepository.findByMobileNumber(mobileNumber);
	}

}
