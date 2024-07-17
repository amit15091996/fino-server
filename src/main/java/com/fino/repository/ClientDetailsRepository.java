package com.fino.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fino.entity.ClientDetails;
import com.fino.entity.FinoUserDetails;

import jakarta.transaction.Transactional;


@Repository
@Transactional
public interface ClientDetailsRepository extends JpaRepository<ClientDetails,Long>{

	@Query("SELECT clientDetails from  ClientDetails clientDetails  WHERE clientDetails.isClientActive=true")
	List<ClientDetails> getAllActiveClients();
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE ClientDetails cd  SET cd.isClientActive=:isClientActive WHERE cd.clientId=:clientId")
	public void updateClientStatus(
			@Param("isClientActive") boolean isClientActive,
			@Param("clientId") Long clientId);

	public ClientDetails findByMobileNumber(String mobileNumber);
}
