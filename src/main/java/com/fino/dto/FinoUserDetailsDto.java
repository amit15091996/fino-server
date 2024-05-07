package com.fino.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FinoUserDetailsDto {
	@NotBlank(message = "Please enter your First Name(Mandatory)")
	private String firstName;
	@NotBlank(message = "Please enter your Last Name(Mandatory)")
	private String lastName;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@NotNull(message = "Please enter your  Date Of Birth(Mandatory)")
	private LocalDate dateOfBirth;
	@NotBlank(message = "Please enter your User Name(Mandatory)")
	private String userName;
	@NotBlank(message = "Please enter your Password (Mandatory)")
	private String Password;
	@Size(max = 10, min = 10, message = "Mobile number should be 10 digit")
	private String mobileNumber;
	@NotBlank(message = "Please enter your Email Id (Mandatory)")
	private String emailId;

}
