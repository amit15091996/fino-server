package com.fino.records;

import java.time.LocalDate;
import java.util.List;

import com.fino.entity.FinoUserRoles;

public record UserRecords(String firstName,String lastName,LocalDate dateOfBirth,String mobileNumber,
		String emailId,List<FinoUserRoles> userRoles,boolean isActive
		) {

}
