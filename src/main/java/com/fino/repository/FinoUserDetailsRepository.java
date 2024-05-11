package com.fino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.FinoUserDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FinoUserDetailsRepository extends JpaRepository<FinoUserDetails, Long> {
	
	FinoUserDetails findByMobileNumber(String mobileNumber);
//	 @Query("SELECT u FROM FinoUserDetails u WHERE u.mobileNumber = :mobileNumber")
//	 public FinoUserDetails findByUsernameMoileNumber(@Param("username") String username);

}
