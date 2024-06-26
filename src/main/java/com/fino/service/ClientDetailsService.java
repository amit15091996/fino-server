package com.fino.service;
import java.util.Map;
import com.fino.dto.ClientDetailsDto;
import com.fino.dto.ClientSearchDto;

public interface ClientDetailsService {
    Map<Object, Object> insertClientDetails(ClientDetailsDto clientDetailsDto);
    Map<Object, Object> deleteClientDetails(Long clientId);
    Map<Object, Object> getAllClientDetails();
    Map<Object, Object> getClientTransactionByUserName(String mobileNumber,ClientSearchDto clientSearchDto);
}
