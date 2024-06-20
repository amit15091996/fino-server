package com.fino.service.FuelReports;

import java.util.Map;
import com.fino.dto.FuelReports.MsSaleDto;

public interface MsSaleService {
	Map<Object, Object> insertMsSaleDetails(MsSaleDto msSaleDto);

	Map<Object, Object> deleteMsSaleDetails(Long msSaleId);

	Map<Object, Object> updateMsSaleDetails(Long msSaleId, MsSaleDto msSaleDto);

	Map<Object, Object> getAllMsSaleDetails();
}
