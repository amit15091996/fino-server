package com.fino.serviceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fino.dto.TransactionDetailsDto;
import com.fino.entity.BankTransactionDetails;
import com.fino.helpers.AppConstants;
import com.fino.repository.BankTransactionRepository;
import com.fino.service.BankTransactionService;
import com.fino.utils.FilterTransactionByDate;
import com.fino.exception.*;

@Service
public class BankTransactionServiceImpl implements BankTransactionService {

	@Autowired
	private BankTransactionRepository bankTransactionRepository;

	@Autowired
	private FilterTransactionByDate filterTransactionByDate;

	@Override
	public Map<Object, Object> insertBankTransactionDetails(TransactionDetailsDto transactionDetailsDto) {
		Map<Object, Object> bankResponseMap = new HashMap<>();

		var bankTransactionDetails = new BankTransactionDetails();
		bankTransactionDetails.setBankTransactionDate(transactionDetailsDto.getTransactionDate());
		bankTransactionDetails.setCashAmount(transactionDetailsDto.getCashAmount());
		bankTransactionDetails.setDepositedBy(transactionDetailsDto.getCollectedBy());
		bankTransactionDetails.setCollectionAmount(transactionDetailsDto.getCollectionAmount());
		bankTransactionDetails.setDepositedInBank(transactionDetailsDto.getRecievedFrom());
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
		try {
			if (this.bankTransactionRepository.findById(bankTransactionId).isPresent()) {
				this.bankTransactionRepository.deleteById(bankTransactionId);
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
			} else {
				throw new NotFoundException(AppConstants.noRecordFound + bankTransactionId);
			}
			return bankResponseMap;
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

	}

	@Override
	public Map<Object, Object> updateBankTransactionDetails(Long bankTransactionId,
			TransactionDetailsDto transactionDetailsDto) {
		Map<Object, Object> bankResponseMap = new HashMap<>();

		try {
			if (this.bankTransactionRepository.findById(bankTransactionId).isPresent()) {
				try {
					this.bankTransactionRepository.updateBankTransactionDetals(transactionDetailsDto.getRecievedFrom(),
							transactionDetailsDto.getCollectionAmount(), transactionDetailsDto.getTransactionDate(),
							transactionDetailsDto.getCashAmount(), transactionDetailsDto.getRemarks(),
							bankTransactionId);

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
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

		return bankResponseMap;
	}

	@Override
	public Map<Object, Object> getAllBankTransactionDetails(String mobileNumber, String allTransaction) {
		Map<Object, Object> bankResponseMap = new HashMap<>();

		try {

			if (allTransaction != null && allTransaction.equalsIgnoreCase("ALL")) {
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				bankResponseMap.put(AppConstants.response, this.bankTransactionRepository.findAll());
				return bankResponseMap;
			} else {
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				bankResponseMap.put(AppConstants.response,
						this.bankTransactionRepository.findBydepositedBy(mobileNumber));
				return bankResponseMap;
			}
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

	}

	@Override
	public Map<Object, Object> getAllBankTransactionDetailsViaSerachParams(String mobileNumber, String year,
			String month, String fromDate, String toDate) {
		Map<Object, Object> bankResponseMap = new HashMap<>();
		try {

			if (year != null) {
				List<BankTransactionDetails> bankDepositByYear = this.bankTransactionRepository
						.findBydepositedBy(mobileNumber);
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				bankResponseMap.put(AppConstants.response, bankDepositByYear.stream().filter(
						deposit -> deposit.getBankTransactionDate().toString().split("-")[0].equalsIgnoreCase(year))
						.collect(Collectors.toList()));
			}

			else if (month != null) {
				List<BankTransactionDetails> bankDepositByYear = this.bankTransactionRepository
						.findBydepositedBy(mobileNumber);
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				bankResponseMap.put(AppConstants.response, bankDepositByYear.stream().filter(
						deposit -> deposit.getBankTransactionDate().toString().split("-")[1].equalsIgnoreCase(month))
						.collect(Collectors.toList()));
			}

			else if (fromDate != null && toDate != null) {
				List<BankTransactionDetails> bankDepositByDate = this.bankTransactionRepository
						.findBydepositedBy(mobileNumber);
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				bankResponseMap.put(AppConstants.response, this.filterTransactionByDate
						.getAllBankTransactionBetweenDates(fromDate, toDate, bankDepositByDate));
			}

			else {
				bankResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				bankResponseMap.put(AppConstants.status, AppConstants.success);
				bankResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				bankResponseMap.put(AppConstants.response, Collections.EMPTY_LIST);
			}

			return bankResponseMap;

		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}

	}

}
