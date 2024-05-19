package com.fino.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fino.configuration.CustomAuthentication;
import com.fino.dto.FinoUserDetailsDto;
import com.fino.service.UserService;
import com.fino.utils.JavaMailUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fino/system/auth")
public class FinoAuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private CustomAuthentication customAuthentication;

	@Autowired
	private JavaMailUtil javaMailUtil;

	@PostMapping("/sign-up")
	public ResponseEntity<Map<Object, Object>> userSignUp(@RequestBody @Valid FinoUserDetailsDto finoUserDetailsDto) {

		return ResponseEntity.ok(this.userService.insertFinoUserDetails(finoUserDetailsDto));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<Map<Object, Object>> userSignIn(
			@RequestParam(name = "mobileNumber", required = true) String mobileNumber,
			@RequestParam(name = "password", required = true) String password) {
		this.finoAuthentication(mobileNumber, password);
		return ResponseEntity.ok(this.userService.onUserlogin(mobileNumber, password));
	}



	// @PostMapping("/send-mail")
	// public ResponseEntity<Map<Object, Object>> sendMail() {
	// 	return ResponseEntity.ok(this.javaMailUtil.sendTextMail());
	// }


	private void finoAuthentication(String mobileNumber, String password) {
		UsernamePasswordAuthenticationToken userNamePasswordauth = new UsernamePasswordAuthenticationToken(mobileNumber,
				password);
		this.customAuthentication.authenticate(userNamePasswordauth);

	}

}
