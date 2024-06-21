package com.fino.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fino.dto.FuelReports.HsdTankOneDto;
import com.fino.dto.FuelReports.HsdTankTwoDto;
import com.fino.dto.FuelReports.MsSaleDto;
import com.fino.helpers.AuthorizationHelpers;
import com.fino.service.FuelReports.HsdTankOneService;
import com.fino.service.FuelReports.HsdTankTwoService;
import com.fino.service.FuelReports.MsSaleService;

@RestController
@RequestMapping("/fino/system/fuel-report")
public class FinoFuelReportsController {

	@Autowired
	private MsSaleService msSaleService;
	@Autowired
	private HsdTankOneService hsdTankOneService;
	@Autowired
	private HsdTankTwoService hsdTankTwoService;

	@PostMapping("/insert-mssale-reports")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> insertMsSaleRecord(@RequestBody MsSaleDto msSaleDto) {

		return ResponseEntity.ok(this.msSaleService.insertMsSaleDetails(msSaleDto));
	}

	@GetMapping("/get-all-mssale-reports")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> getALlMssaleReports() {

		return ResponseEntity.ok(this.msSaleService.getAllMsSaleDetails());
	}

	@DeleteMapping("/delete-a-mssale-reports/{msSaleId}")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> deleteAMssaleReports(@PathVariable("msSaleId") Long msSaleId) {

		return ResponseEntity.ok(this.msSaleService.deleteMsSaleDetails(msSaleId));
	}

	@PostMapping("/insert-hsd-tank-one-reports")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> insertHsdTankOneRecord(@RequestBody HsdTankOneDto hsdTankOneDto) {
		return ResponseEntity.ok(this.hsdTankOneService.insertHsdTankOneDetails(hsdTankOneDto));
	}

	@GetMapping("/get-all-hsd-tank-one-reports")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> getALlHsdTankOneReports() {

		return ResponseEntity.ok(this.hsdTankOneService.getAllHsdTankOneDetails());
	}

	@DeleteMapping("/delete-a-hsd-tank-one-reports/{hsdTankOneId}")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> deleteAHsdTankOneReports(
			@PathVariable("hsdTankOneId") Long hsdTankOneId) {

		return ResponseEntity.ok(this.hsdTankOneService.deleteHsdTankOneDetails(hsdTankOneId));
	}

	@PostMapping("/insert-hsd-tank-two-reports")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> insertHsdTankTwoRecord(@RequestBody HsdTankTwoDto hsdTankTwoDto) {
		return ResponseEntity.ok(this.hsdTankTwoService.insertHsdTankTwoDetails(hsdTankTwoDto));
	}

	@GetMapping("/get-all-hsd-tank-two-reports")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> getALlHsdTankTwoReports() {

		return ResponseEntity.ok(this.hsdTankTwoService.getAllHsdTankTwoDetails());
	}

	@DeleteMapping("/delete-a-hsd-tank-two-reports/{hsdTankTwoId}")
	@PreAuthorize(AuthorizationHelpers.MANAGER_AUTH)
	public ResponseEntity<Map<Object, Object>> deleteAHsdTankTwoReports(
			@PathVariable("hsdTankTwoId") Long hsdTankTwoId) {

		return ResponseEntity.ok(this.hsdTankTwoService.deleteHsdTankTwoDetails(hsdTankTwoId));
	}
	
	
}
