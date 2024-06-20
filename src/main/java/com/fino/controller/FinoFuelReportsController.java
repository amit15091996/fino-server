package com.fino.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fino.dto.FuelReports.MsSaleDto;
import com.fino.helpers.AuthorizationHelpers;
import com.fino.service.FuelReports.MsSaleService;

@RestController
@RequestMapping("/fino/system/fuel-report")
public class FinoFuelReportsController {

    @Autowired
    private MsSaleService msSaleService;

    @PostMapping("/insert-mssale-reports")
    @PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
    public ResponseEntity<Map<Object, Object>> insertMsSaleRecord( @RequestBody MsSaleDto msSaleDto){
    	
    	return ResponseEntity.ok(this.msSaleService.insertMsSaleDetails(msSaleDto));
    }
    @GetMapping("/get-all-mssale-reports")
    @PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
    public ResponseEntity<Map<Object, Object>> getALlMssaleReports(){
    	
    	return ResponseEntity.ok(this.msSaleService.getAllMsSaleDetails());
    }
}
