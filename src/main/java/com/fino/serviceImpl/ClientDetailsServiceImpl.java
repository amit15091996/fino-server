package com.fino.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fino.dto.ClientDetailsDto;
import com.fino.dto.ClientSearchDto;
import com.fino.entity.ClientDetails;
import com.fino.exception.BadRequest;
import com.fino.exception.NotFoundException;
import com.fino.helpers.AppConstants;
import com.fino.records.ClientRecord;
import com.fino.repository.ClientDetailsRepository;
import com.fino.service.ClientDetailsService;
import com.fino.utils.FilterTransactionByDate;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private FilterTransactionByDate filterTransactionByDate;

	@Override
	public Map<Object, Object> insertClientDetails(ClientDetailsDto clientDetailsDto) {
		Map<Object, Object> clientResponseMap = new HashMap<>();
		var clientDetails = new ClientDetails();
		clientDetails.setBankName(clientDetailsDto.getBankName());
		clientDetails.setClientName(clientDetailsDto.getClientName());
		clientDetails.setClientActive(Boolean.TRUE);
		try {
			var clientDetailsResponse = this.clientDetailsRepository.save(clientDetails);
			if (clientDetailsResponse != null) {
				clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
				clientResponseMap.put(AppConstants.status, AppConstants.success);
				clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
			}
		} catch (Exception e) {
			throw new BadRequest(e.getMessage());
		}
		return clientResponseMap;
	}

	@Override
	public Map<Object, Object> deleteClientDetails(Long clientId) {
		Map<Object, Object> clientResponseMap = new HashMap<>();
		if (this.clientDetailsRepository.findById(clientId).isPresent()) {
			this.clientDetailsRepository.deleteById(clientId);
			clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			clientResponseMap.put(AppConstants.status, AppConstants.success);
			clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + clientId);
		}
		return clientResponseMap;
	}

	@Override
	public Map<Object, Object> getAllClientDetails() {
		Map<Object, Object> clientResponseMap = new HashMap<>();
		clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
		clientResponseMap.put(AppConstants.status, AppConstants.success);
		clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
		clientResponseMap.put(AppConstants.response,
				this.clientDetailsRepository.getAllActiveClients().stream().map((clientList) -> {
					return new ClientRecord(clientList.getClientId(), clientList.getClientName(),
							clientList.getBankName(), clientList.getCmsTransactionDetails(),
							clientList.getFinoUserDetails() != null ? clientList.getFinoUserDetails().getMobileNumber()
									: null);
				}).collect(Collectors.toList()));
		return clientResponseMap;
	}

	@Override
	public Map<Object, Object> getClientTransactionByUserName(String mobileNumber, ClientSearchDto clientSearchDto) {
		Map<Object, Object> clientResponseMap = new HashMap<>();
		var allCmsTxnOfUser = this.clientDetailsRepository.getAllActiveClients().stream()
				.filter(user -> user.getFinoUserDetails() != null)
				.filter(userDetails -> userDetails.getFinoUserDetails().getMobileNumber()
						.equalsIgnoreCase(mobileNumber))
				.map(clientTxn -> clientTxn.getCmsTransactionDetails()).flatMap(cms -> cms.parallelStream())
				.collect(Collectors.toList());

		if (clientSearchDto.getYear() != null && !clientSearchDto.getYear().isEmpty()) {
			clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			clientResponseMap.put(AppConstants.status, AppConstants.success);
			clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			clientResponseMap.put(AppConstants.response,
					allCmsTxnOfUser.stream().filter(cms -> cms.getCmsTransactionDate().toString().split("-")[0]
							.equalsIgnoreCase(clientSearchDto.getYear())).collect(Collectors.toList()));

		} else if (clientSearchDto.getMonth() != null && !clientSearchDto.getMonth().isEmpty()) {
			clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			clientResponseMap.put(AppConstants.status, AppConstants.success);
			clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			clientResponseMap.put(AppConstants.response,
					allCmsTxnOfUser.stream().filter(cms -> cms.getCmsTransactionDate().toString().split("-")[1]
							.equalsIgnoreCase(clientSearchDto.getMonth())).collect(Collectors.toList()));
		}

		else if ((clientSearchDto.getFromDate() != null && !clientSearchDto.getFromDate().isEmpty())
				&& (clientSearchDto.getToDate() != null && !clientSearchDto.getToDate().isEmpty())) {

			clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			clientResponseMap.put(AppConstants.status, AppConstants.success);
			clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			clientResponseMap.put(AppConstants.response, this.filterTransactionByDate.getAllCMSTransactionBetweenDates(
					clientSearchDto.getFromDate(), clientSearchDto.getToDate(), allCmsTxnOfUser));

		} else {
			clientResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			clientResponseMap.put(AppConstants.status, AppConstants.success);
			clientResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
			clientResponseMap.put(AppConstants.response, allCmsTxnOfUser);

		}

		return clientResponseMap;
	}

}
