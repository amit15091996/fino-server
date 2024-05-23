package com.fino.service;

import java.util.Map;

import com.fino.dto.TransactionDetailsDto;

public interface CmsTransactionService {
	Map<Object, Object> insertCmsTransactionDetails(TransactionDetailsDto transactionDetailsDto);
	Map<Object, Object> deleteCmsTransactionDetails(Long cmsTransactionId);
	Map<Object, Object> updateCmsTransactionDetails(Long cmsTransactionId, TransactionDetailsDto transactionDetailsDto);
	Map<Object, Object> getAllCmsTransactionDetails(String mobileNumber);
	Map<Object, Object> getAllCMSTransactionDetailsViaSerachParams(String year,String month,String fromDate,String toDate);
}
