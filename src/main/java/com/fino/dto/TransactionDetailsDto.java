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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDetailsDto {
	@NotBlank(message = "Please enter your Recieved From(Mandatory)")
	private String recievedFrom;
	@NotBlank(message = "Please enter your First Collected By(Mandatory)")
	private String collectedBy;
	@NotNull(message = "Please enter your  Collection Amount(Mandatory)")
	private double collectionAmount;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@NotNull(message = "Please enter your  Date Of Birth(Mandatory)")
	private LocalDate TransactionDate;
	private double onlineAmount;
	private double cashAmount;
	private double balanceAmount;
	private String remarks;

}
