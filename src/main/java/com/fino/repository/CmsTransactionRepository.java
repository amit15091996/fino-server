package com.fino.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fino.entity.CmsTransactionDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CmsTransactionRepository extends JpaRepository<CmsTransactionDetails, Long> {

	List<CmsTransactionDetails> findByCollectedBy(String mobileNumber);
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE CmsTransactionDetails ctd  SET ctd.recievedFrom=:recievedFrom,"
			+ "ctd.collectionAmount=:collectionAmount,"
			+ "ctd.cmsTransactionDate=:cmsTransactionDate,"
			+ "ctd.onlineAmount=:onlineAmount,"
			+ "ctd.balanceAmount=:balanceAmount,"
			+ "ctd.remarks=:remarks,"
			+ "ctd.cashAmount=:cashAmount WHERE ctd.cmsTransactionId=:cmsTransactionId")
	public void updateCmsTransactionDetals(@Param("recievedFrom") String recievedFrom,
			@Param("collectionAmount") double collectionAmount,
			@Param("cmsTransactionDate") LocalDate cmsTransactionDate,
			@Param("onlineAmount") double onlineAmount,
			@Param("balanceAmount") double balanceAmount,
			@Param("cashAmount") double cashAmount, 
			@Param("remarks") String remarks,
			@Param("cmsTransactionId") Long cmsTransactionId);
	
}
