package com.fino.repository;

import java.time.LocalDate;
import java.util.List;

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

	@Query("SELECT users from  FinoUserDetails users  WHERE users.isActive=true")
	List<FinoUserDetails> getAllActiveUser();

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE FinoUserDetails fud  SET fud.Password=:Password WHERE fud.mobileNumber=:mobileNumber")
	public void updateFinoUserPassword(
			@Param("Password") String Password,
			@Param("mobileNumber") String mobileNumber);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE FinoUserDetails fud  SET fud.isActive=:isActive WHERE fud.mobileNumber=:mobileNumber")
	public void updateFinoUserStatus(
			@Param("isActive") boolean isActive,
			@Param("mobileNumber") String mobileNumber);

	@Modifying(flushAutomatically = true, clearAutomatically = true)
	@Query("UPDATE FinoUserDetails fud  SET fud.firstName=:firstName, fud.lastName=:lastName,fud.dateOfBirth=:dateOfBirth,fud.emailId=:emailId WHERE fud.mobileNumber=:mobileNumber")
	public void updateFinoUserDetails(
			@Param("firstName") String firstName, @Param("lastName") String lastName,
			@Param("dateOfBirth") LocalDate dateOfBirth, @Param("emailId") String emailId,
			@Param("mobileNumber") String mobileNumber);

}
