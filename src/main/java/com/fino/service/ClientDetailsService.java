package com.fino.service;
import java.util.Map;
import com.fino.dto.ClientDetailsDto;

public interface ClientDetailsService {
    Map<Object, Object> insertClientDetails(ClientDetailsDto clientDetailsDto);
    Map<Object, Object> deleteClientDetails(Long clientId);
    Map<Object, Object> getAllClientDetails();

}
