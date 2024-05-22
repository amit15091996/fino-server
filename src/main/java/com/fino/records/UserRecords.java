package com.fino.records;

import java.time.LocalDate;
import java.util.List;

import com.fino.entity.FinoUserRoles;

public record UserRecords(String userName,LocalDate dateOfBirth,String mobileNumber,
		String emailId,List<String> userRoles,String isActive
		) {

}
