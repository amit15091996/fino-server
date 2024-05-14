package com.fino.service;

import java.util.Map;
import com.fino.dto.TransactionDetailsDto;

public interface BankTransactionService {
	Map<Object, Object> insertBankTransactionDetails(TransactionDetailsDto transactionDetailsDto);
	Map<Object, Object> deleteBankTransactionDetails(Long bankTransactionId);
	Map<Object, Object> updateBankTransactionDetails(Long bankTransactionId, TransactionDetailsDto transactionDetailsDto);
	Map<Object, Object> getAllBankTransactionDetails();
}
