package com.fino.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {
	
	public String InvoiceNumber(LocalDateTime localDatetime) {

		return localDatetime.format(DateTimeFormatter.ofPattern("HHmmssSsssddMMyy"));
	}

}
