package com.fino.service.FuelReports;

import java.util.Map;

import com.fino.dto.FuelReports.HsdTankTwoDto;

public interface HsdTankTwoService {
    Map<Object, Object> insertHsdTankTwoDetails(HsdTankTwoDto hsdTankTwoDto);

    Map<Object, Object> deleteHsdTankTwoDetails(Long hsdTankTwoId);

    Map<Object, Object> updateHsdTankTwoDetails(Long hsdTankTwoId, HsdTankTwoDto hsdTankTwoDto);

    Map<Object, Object> getAllHsdTankTwoDetails();
}
