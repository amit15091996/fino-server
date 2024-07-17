package com.fino.records;

import java.util.List;

import com.fino.entity.CmsTransactionDetails;

public record ClientRecord(
		Long clientId,String clientName,String bankName,List<CmsTransactionDetails> cmsTransactionDetails,
		String mobileNumber,String companyName
		) {

}
