package com.fino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fino.entity.FinoUserDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FinoUserDetailsRepository extends JpaRepository<FinoUserDetails, Long> {
	
	FinoUserDetails findByMobileNumber(String mobileNumber);
	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE FinoUserDetails fud  SET fud.Password=:Password WHERE fud.mobileNumber=:mobileNumber")
	public void updateFinoUserPassword(
			@Param("Password") String Password,
			@Param("mobileNumber") String mobileNumber);
}
