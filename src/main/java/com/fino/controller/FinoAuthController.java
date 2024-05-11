package com.fino.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fino.configuration.CustomAuthentication;
import com.fino.dto.FinoUserDetailsDto;
import com.fino.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/fino/system/auth")
public class FinoAuthController {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private CustomAuthentication customAuthentication;

	@PostMapping("/sign-up")
	public Map<Object, Object> userSignUp(@RequestBody @Valid FinoUserDetailsDto finoUserDetailsDto) {

		return this.userService.insertFinoUserDetails(finoUserDetailsDto);
	}

	@PostMapping("/sign-in")
	public Map<Object, Object> userSignIn(@RequestParam(name = "mobileNumber", required = true) String mobileNumber,
			@RequestParam(name = "password", required = true) String password) {
		this.finoAuthentication(mobileNumber, password);
		return this.userService.onUserlogin(mobileNumber, password);
	}
	
	private void finoAuthentication(String mobileNumber,String password)  {
		UsernamePasswordAuthenticationToken userNamePasswordauth=new UsernamePasswordAuthenticationToken(mobileNumber, password);

			this.customAuthentication.authenticate(userNamePasswordauth);

	}
}
