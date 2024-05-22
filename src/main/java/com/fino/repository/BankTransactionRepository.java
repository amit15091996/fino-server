package com.fino.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fino.entity.BankTransactionDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BankTransactionRepository extends JpaRepository<BankTransactionDetails, Long> {

	List<BankTransactionDetails> findByCollectedBy(String collectedBy);
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE BankTransactionDetails btd  SET btd.recievedFrom=:recievedFrom,"
			+ "btd.collectionAmount=:collectionAmount,"
			+ "btd.bankTransactionDate=:bankTransactionDate,"
			+ "btd.onlineAmount=:onlineAmount,"
			+ "btd.balanceAmount=:balanceAmount,"
			+ "btd.remarks=:remarks,"
			+ "btd.cashAmount=:cashAmount WHERE btd.bankTransactionId=:bankTransactionId")
	public void updateBankTransactionDetals(@Param("recievedFrom") String recievedFrom,
			@Param("collectionAmount") double collectionAmount,
			@Param("bankTransactionDate") LocalDate bankTransactionDate,
			@Param("onlineAmount") double onlineAmount,
			@Param("balanceAmount") double balanceAmount,
			@Param("cashAmount") double cashAmount, 
			@Param("remarks") String remarks,
			@Param("bankTransactionId") Long bankTransactionId);

	
}
