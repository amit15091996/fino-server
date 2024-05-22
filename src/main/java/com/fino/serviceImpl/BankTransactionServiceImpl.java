package com.fino.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fino.dto.TransactionDetailsDto;
import com.fino.entity.BankTransactionDetails;
import com.fino.helpers.AppConstants;
import com.fino.repository.BankTransactionRepository;
import com.fino.service.BankTransactionService;
import com.fino.exception.*;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

	@Autowired
	private BankTransactionRepository bankTransactionRepository;

	@Override
	public Map<Object, Object> insertBankTransactionDetails(TransactionDetailsDto transactionDetailsDto) {
		Map<Object, Object> bankResponseMap = new HashMap<>();

		var bankTransactionDetails = new BankTransactionDetails();
		bankTransactionDetails.setBalanceAmount(transactionDetailsDto.getBalanceAmount());
		bankTransactionDetails.setBankTransactionDate(transactionDetailsDto.getTransactionDate());
		bankTransactionDetails.setCashAmount(transactionDetailsDto.getCashAmount());
		bankTransactionDetails.setCollectedBy(transactionDetailsDto.getCollectedBy());
		bankTransactionDetails.setCollectionAmount(transactionDetailsDto.getCollectionAmount());
		bankTransactionDetails.setOnlineAmount(transactionDetailsDto.getOnlineAmount());
		bankTransactionDetails.setRecievedFrom(transactionDetailsDto.getRecievedFrom());
		bankTransactionDetails.setRemarks(transactionDetailsDto.getRemarks());
		try {
			var bankTransactionDetailsResponse = this.bankTransactionRepository.save(bankTransactionDetails);
			if (bankTransactionDetailsResponse != null) {
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
			}
		} catch (Exception e) {
			throw new BadRequest(e.getMessage());
		}
		return bankResponseMap;
	}

	@Override
	public Map<Object, Object> deleteBankTransactionDetails(Long bankTransactionId) {
		Map<Object, Object> bankResponseMap = new HashMap<>();
		if (this.bankTransactionRepository.findById(bankTransactionId).isPresent()) {
			this.bankTransactionRepository.deleteById(bankTransactionId);
			bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			bankResponseMap.put(AppConstants.status, AppConstants.success);
			bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + bankTransactionId);
		}
		return bankResponseMap;
	}

	@Override
	public Map<Object, Object> updateBankTransactionDetails(Long bankTransactionId,
			TransactionDetailsDto transactionDetailsDto) {
		Map<Object, Object> bankResponseMap = new HashMap<>();

		if (this.bankTransactionRepository.findById(bankTransactionId).isPresent()) {
			try {
				this.bankTransactionRepository.updateBankTransactionDetals(transactionDetailsDto.getRecievedFrom(),
						transactionDetailsDto.getCollectionAmount(), transactionDetailsDto.getTransactionDate(),
						transactionDetailsDto.getOnlineAmount(), transactionDetailsDto.getBalanceAmount(),
						transactionDetailsDto.getCashAmount(), transactionDetailsDto.getRemarks(), bankTransactionId);

				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage,
						AppConstants.recordUpdatedSuccessFully + bankTransactionId);

			} catch (Exception e) {
				throw new BadRequest(e.getMessage());
			}

		} else {
			throw new NotFoundException(AppConstants.noRecordFound + bankTransactionId);
		}

		return bankResponseMap;
	}

	@Override
	public Map<Object, Object> getAllBankTransactionDetails(String mobileNumber) {
		Map<Object, Object> bankResponseMap = new HashMap<>();

		bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
		bankResponseMap.put(AppConstants.status, AppConstants.success);
		bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
		bankResponseMap.put(AppConstants.response, this.bankTransactionRepository.findByCollectedBy(mobileNumber));

		return bankResponseMap;
	}

}
