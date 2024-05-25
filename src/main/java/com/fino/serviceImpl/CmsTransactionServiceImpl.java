package com.fino.serviceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fino.dto.TransactionDetailsDto;
import com.fino.entity.CmsTransactionDetails;
import com.fino.helpers.AppConstants;
import com.fino.repository.CmsTransactionRepository;
import com.fino.service.CmsTransactionService;
import com.fino.utils.FilterTransactionByDate;
import com.fino.exception.*;

@Service
public class CmsTransactionServiceImpl implements CmsTransactionService {

	@Autowired
	private CmsTransactionRepository cmsTransactionRepository;


	@Autowired
	private FilterTransactionByDate filterTransactionByDate;
	
	
	@Override
	public Map<Object, Object> insertCmsTransactionDetails(TransactionDetailsDto transactionDetailsDto) {
		Map<Object, Object> cmsResponseMap = new HashMap<>();

		var cmsTransactionDetails = new CmsTransactionDetails();
		cmsTransactionDetails.setBalanceAmount(transactionDetailsDto.getBalanceAmount());
		cmsTransactionDetails.setCmsTransactionDate(transactionDetailsDto.getTransactionDate());
		cmsTransactionDetails.setCashAmount(transactionDetailsDto.getCashAmount());
		cmsTransactionDetails.setCollectedBy(transactionDetailsDto.getCollectedBy());
		cmsTransactionDetails.setCollectionAmount(transactionDetailsDto.getCollectionAmount());
		cmsTransactionDetails.setOnlineAmount(transactionDetailsDto.getOnlineAmount());
		cmsTransactionDetails.setRecievedFrom(transactionDetailsDto.getRecievedFrom());
		cmsTransactionDetails.setRemarks(transactionDetailsDto.getRemarks());
		try {
			var bankTransactionDetailsResponse = this.cmsTransactionRepository.save(cmsTransactionDetails);
			if (bankTransactionDetailsResponse != null) {
				cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				cmsResponseMap.put(AppConstants.status, AppConstants.success);
				cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
			}
		} catch (Exception e) {
			throw new BadRequest(e.getMessage());
		}
		return cmsResponseMap;
	}

	@Override
	public Map<Object, Object> deleteCmsTransactionDetails(Long cmsTransactionId) {
		Map<Object, Object> cmsResponseMap = new HashMap<>();
		if (this.cmsTransactionRepository.findById(cmsTransactionId).isPresent()) {
			this.cmsTransactionRepository.deleteById(cmsTransactionId);
			cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			cmsResponseMap.put(AppConstants.status, AppConstants.success);
			cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + cmsTransactionId);
		}
		return cmsResponseMap;
	}

	@Override
	public Map<Object, Object> updateCmsTransactionDetails(Long cmsTransactionId,
			TransactionDetailsDto transactionDetailsDto) {
		Map<Object, Object> cmsResponseMap = new HashMap<>();

		if (this.cmsTransactionRepository.findById(cmsTransactionId).isPresent()) {
			try {
				this.cmsTransactionRepository.updateCmsTransactionDetals(transactionDetailsDto.getRecievedFrom(),
						transactionDetailsDto.getCollectionAmount(), transactionDetailsDto.getTransactionDate(),
						transactionDetailsDto.getOnlineAmount(), transactionDetailsDto.getBalanceAmount(),
						transactionDetailsDto.getCashAmount(), transactionDetailsDto.getRemarks(), cmsTransactionId);

				cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				cmsResponseMap.put(AppConstants.status, AppConstants.success);
				cmsResponseMap.put(AppConstants.statusMessage,
						AppConstants.recordUpdatedSuccessFully + cmsTransactionId);

			} catch (Exception e) {
				throw new BadRequest(e.getMessage());
			}

		} else {
			throw new NotFoundException(AppConstants.noRecordFound + cmsTransactionId);
		}

		return cmsResponseMap;
	}

	@Override
	public Map<Object, Object> getAllCmsTransactionDetails(String mobileNumber,String allTransaction) {
		Map<Object, Object> cmsResponseMap = new HashMap<>();

		 if( allTransaction !=null && allTransaction.equalsIgnoreCase("ALL")) {
			 cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				cmsResponseMap.put(AppConstants.status, AppConstants.success);
				cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				cmsResponseMap.put(AppConstants.response, this.cmsTransactionRepository.findAll());
				return cmsResponseMap;
			 
		 }else {
			 cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				cmsResponseMap.put(AppConstants.status, AppConstants.success);
				cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
				cmsResponseMap.put(AppConstants.response, this.cmsTransactionRepository.findByCollectedBy(mobileNumber));
				return cmsResponseMap;
		 }
		
		
	}

	@Override
	public Map<Object, Object> getAllCMSTransactionDetailsViaSerachParams(String mobileNumber,String year, String month, String fromDate,
			String toDate) {
		Map<Object, Object> cmsResponseMap = new HashMap<>();

		if (year != null) {
			List<CmsTransactionDetails> cmsDepositByYear = this.cmsTransactionRepository.findByCollectedBy(mobileNumber);
			cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			cmsResponseMap.put(AppConstants.status, AppConstants.success);
			cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			cmsResponseMap.put(AppConstants.response,cmsDepositByYear.stream().filter(deposit->
				deposit.getCmsTransactionDate().toString().split("-")[0].equalsIgnoreCase(year)
			).collect(Collectors.toList()));
		}

		else if (month != null) {
			List<CmsTransactionDetails> cmsDepositByMonth = this.cmsTransactionRepository.findByCollectedBy(mobileNumber);
			cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			cmsResponseMap.put(AppConstants.status, AppConstants.success);
			cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			cmsResponseMap.put(AppConstants.response,cmsDepositByMonth.stream().filter(deposit->
				deposit.getCmsTransactionDate().toString().split("-")[1].equalsIgnoreCase(month)
			).collect(Collectors.toList()));
		}

		else if (fromDate != null && toDate != null) {
			List<CmsTransactionDetails> cmsDepositByDates = this.cmsTransactionRepository.findByCollectedBy(mobileNumber);
			cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			cmsResponseMap.put(AppConstants.status, AppConstants.success);
			cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			cmsResponseMap.put(AppConstants.response,this.filterTransactionByDate.getAllCMSTransactionBetweenDates(fromDate, toDate, cmsDepositByDates));
		}

		else {
			cmsResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			cmsResponseMap.put(AppConstants.status, AppConstants.success);
			cmsResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			cmsResponseMap.put(AppConstants.response, Collections.EMPTY_LIST);
		}

	return cmsResponseMap;	
	}

}
