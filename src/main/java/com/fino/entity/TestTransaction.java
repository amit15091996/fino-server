package com.fino.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestTransaction {

	@Id
	private String transId;
	private String transType;
	private double transAmt;
	private String mode;
}
