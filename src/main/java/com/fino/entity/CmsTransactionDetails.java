package com.fino.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "CMS_TRANSACTION_DETAILS")
@Entity
public class CmsTransactionDetails {

	@TableGenerator(allocationSize = 1, initialValue = 1000, name = "cms_transaction_sequence")
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "cms_transaction_sequence")
	private Long cmsTransactionId;
	@Column(length = 50, nullable = false)
	private String recievedFrom;
	@Column(length = 50, nullable = false)
	private String collectedBy;
	@Column(columnDefinition = "Decimal(20,2)")
	private double collectionAmount;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@Column(nullable = false)
	private LocalDate cmsTransactionDate;
	@Column(columnDefinition = "Decimal(20,2)")
	private double onlineAmount;
	@Column(columnDefinition = "Decimal(20,2)")
	private double cashAmount;
	@Column(columnDefinition = "Decimal(20,2)")
	private double balanceAmount;
	@Column(length = 5000)
	private String remarks;
	@Column(length = 200)
	private String companyName;

}
