package com.fino.configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fino.helpers.AppConstants;

import exception.CustomException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		final ObjectMapper mapper = new ObjectMapper();
		CustomException customException=new CustomException(AppConstants.Forbidden,"You don't have authority to access this endpoint",LocalDateTime.now(),request.getServletPath(),AppConstants.Forbidden_desc);
			
			Map<Object, Object> map1=new HashMap<>();
			map1.put(AppConstants.statusCode, customException.getStatusCode());
			map1.put(AppConstants.status, customException.getStatus());
			map1.put(AppConstants.timeStamp, customException.getTimestamp());
			map1.put(AppConstants.statusMessage, customException.getMessage());
			map1.put(AppConstants.description, customException.getDescription());
		
			 JavaTimeModule javaTimeModule = new JavaTimeModule();
			 mapper.registerModule(javaTimeModule);
			 mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);   
			mapper.writeValue(response.getOutputStream(), map1);
	}

}
