package com.fino.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fino.dto.TransactionDetailsDto;
import com.fino.helpers.AuthorizationHelpers;
import com.fino.service.BankTransactionService;
import com.fino.service.CmsTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/fino/system/operation")
public class FinoOperationController {
	
    @Autowired
	private BankTransactionService bankTransactionService;
    
    @Autowired
   	private CmsTransactionService cmsTransactionService;
    
    @GetMapping("/get-all-bank-transaction")
    @PreAuthorize(AuthorizationHelpers.USER_AUTH)
    public ResponseEntity<Map<Object, Object>> getAllBankTransactions(){
    	
    	return ResponseEntity.ok(this.bankTransactionService.getAllBankTransactionDetails());
    }

    @PostMapping("/insert-bank-transaction")
    @PreAuthorize(AuthorizationHelpers.USER_AUTH)
    public ResponseEntity<Map<Object, Object>> insertBankTransactions(@RequestBody @Valid TransactionDetailsDto transactionDetailsDto){
    	
    	return ResponseEntity.ok(this.bankTransactionService.insertBankTransactionDetails(transactionDetailsDto));
    }
    
    @DeleteMapping("/delete-bank-transaction/{bankTransactionId}")
    @PreAuthorize(AuthorizationHelpers.ADMIN_AUTH)
    public ResponseEntity<Map<Object, Object>> deleteBankTransactions(@PathVariable(name = "bankTransactionId") Long bankTransactionId){
    	return ResponseEntity.ok(this.bankTransactionService.deleteBankTransactionDetails(bankTransactionId));
    }
    
    
    @PutMapping("/update-bank-transaction/{bankTransactionId}")
    @PreAuthorize(AuthorizationHelpers.ADMIN_AUTH)
    public ResponseEntity<Map<Object, Object>> updateBankTransactions(@PathVariable(name = "bankTransactionId") Long bankTransactionId,@RequestBody @Valid TransactionDetailsDto transactionDetailsDto){
    	return ResponseEntity.ok(this.bankTransactionService.updateBankTransactionDetails(bankTransactionId,transactionDetailsDto));
    }
    
    
    @GetMapping("/get-all-cms-transaction")
    @PreAuthorize(AuthorizationHelpers.USER_AUTH)
    public ResponseEntity<Map<Object, Object>> getAllCmsTransactions(){
    	
    	return ResponseEntity.ok(this.cmsTransactionService.getAllCmsTransactionDetails());
    }
    
    @PostMapping("/insert-cms-transaction")
    @PreAuthorize(AuthorizationHelpers.USER_AUTH)
    public ResponseEntity<Map<Object, Object>> insertCmsTransactions(@RequestBody @Valid TransactionDetailsDto transactionDetailsDto){
    	
    	return ResponseEntity.ok(this.cmsTransactionService.insertCmsTransactionDetails(transactionDetailsDto));
    }
    
    @DeleteMapping("/delete-cms-transaction/{cmsTransactionId}")
    @PreAuthorize(AuthorizationHelpers.ADMIN_AUTH)
    public ResponseEntity<Map<Object, Object>> deleteCmsTransactions(@PathVariable(name = "cmsTransactionId") Long cmsTransactionId){
    	return ResponseEntity.ok(this.cmsTransactionService.deleteCmsTransactionDetails(cmsTransactionId));
    }
    

    @PutMapping("/update-cms-transaction/{cmsTransactionId}")
    @PreAuthorize(AuthorizationHelpers.ADMIN_AUTH)
    public ResponseEntity<Map<Object, Object>> updateCmsTransactions(@PathVariable(name = "cmsTransactionId") Long cmsTransactionId,@RequestBody @Valid TransactionDetailsDto transactionDetailsDto){
    	return ResponseEntity.ok(this.cmsTransactionService.updateCmsTransactionDetails(cmsTransactionId,transactionDetailsDto));
    }
    
    
}
