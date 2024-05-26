package com.fino.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUEL_REPORTS_DETAILS")
public class FuelReportsDetails {

@TableGenerator(allocationSize = 1, initialValue =10, name = "fuel_reports_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "fuel_reports_sequence")
	private Long fuelReportsId;
	@Column(length = 50, nullable = false)
	private String recievedFrom;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(nullable = false)
	private LocalDate reportDate;
	@Column(columnDefinition = "Decimal(20,2)")
	private double onlineAmount;
	@Column(length =5000, nullable = false)
	private String remarks;

}
