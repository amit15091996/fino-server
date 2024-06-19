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

	List<BankTransactionDetails> findBydepositedBy(String depositedBy);
	
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE BankTransactionDetails btd  SET btd.depositedInBank=:depositedInBank,"
			+ "btd.collectionAmount=:collectionAmount,"
			+ "btd.bankTransactionDate=:bankTransactionDate,"
			+ "btd.remarks=:remarks,"
			+ "btd.cashAmount=:cashAmount WHERE btd.bankTransactionId=:bankTransactionId")
	public void updateBankTransactionDetals(@Param("depositedInBank") String depositedInBank,
			@Param("collectionAmount") double collectionAmount,
			@Param("bankTransactionDate") LocalDate bankTransactionDate,
			@Param("cashAmount") double cashAmount, 
			@Param("remarks") String remarks,
			@Param("bankTransactionId") Long bankTransactionId);

	
}
