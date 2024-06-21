package com.fino.service.FuelReports;

import java.util.Map;
import com.fino.dto.FuelReports.HsdTankOneDto;

public interface HsdTankOneService {
	Map<Object, Object> insertHsdTankOneDetails(HsdTankOneDto hsdTankOneDto);

	Map<Object, Object> deleteHsdTankOneDetails(Long hsdTankOneId);

	Map<Object, Object> updateHsdTankOneDetails(Long hsdTankOneId, HsdTankOneDto hsdTankOneDto);

	Map<Object, Object> getAllHsdTankOneDetails();
}
