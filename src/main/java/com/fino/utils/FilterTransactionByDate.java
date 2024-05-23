package com.fino.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fino.entity.BankTransactionDetails;
import com.fino.entity.CmsTransactionDetails;

@Component
public class FilterTransactionByDate {

	public List<BankTransactionDetails> getAllBankTransactionBetweenDates(String fromDate, String toDate,
			List<BankTransactionDetails> bankTransaction) {

		return (bankTransaction.stream().filter(f ->

		f.getBankTransactionDate().isEqual(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))) ||

				(f.getBankTransactionDate()
						.isAfter(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))) &&

						f.getBankTransactionDate()
								.isBefore(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				|| f.getBankTransactionDate()
						.isEqual(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))))

		).collect(Collectors.toList());

	}
	
	
	public List<CmsTransactionDetails> getAllCMSTransactionBetweenDates(String fromDate, String toDate,
			List<CmsTransactionDetails> cmsTransaction) {

		return (cmsTransaction.stream().filter(f ->

		f.getCmsTransactionDate().isEqual(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))) ||

				(f.getCmsTransactionDate()
						.isAfter(LocalDate.parse(fromDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))) &&

						f.getCmsTransactionDate()
								.isBefore(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
				|| f.getCmsTransactionDate()
						.isEqual(LocalDate.parse(toDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))))

		).collect(Collectors.toList());

	}


}
