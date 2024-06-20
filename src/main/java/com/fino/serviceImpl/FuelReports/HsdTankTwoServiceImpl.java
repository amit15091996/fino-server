package com.fino.serviceImpl.FuelReports;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fino.configuration.FuelReportConfig.HsdTankTwoInitialData;
import com.fino.dto.FuelReports.HsdTankTwoDto;
import com.fino.entity.FuelReports.DieselTankTwo;
import com.fino.repository.FuelReportsRepository.DieselTankTwoRepository;
import com.fino.service.FuelReports.HsdTankTwoService;
import com.fino.utils.FuelReportUtils;

@Service
public class HsdTankTwoServiceImpl implements HsdTankTwoService {
    @Autowired
    private DieselTankTwoRepository dieselTankTwoRepository;

    @Autowired
    private FuelReportUtils fuelReportUtils;

    @Autowired
    private HsdTankTwoInitialData hsdTankTwoInitialData;

    @Override
    public Map<Object, Object> insertHsdTankTwoDetails(HsdTankTwoDto hsdTankTwoDto) {
        Map<Object, Object> hsdTankTwoResponseMap = new HashMap<>();

        return hsdTankTwoResponseMap;
    }

    @Override
    public Map<Object, Object> deleteHsdTankTwoDetails(Long hsdTankTwoId) {
        Map<Object, Object> hsdTankTwoResponseMap = new HashMap<>();

        return hsdTankTwoResponseMap;
    }

    @Override
    public Map<Object, Object> updateHsdTankTwoDetails(Long hsdTankTwoId, HsdTankTwoDto hsdTankTwoDto) {
        Map<Object, Object> hsdTankTwoResponseMap = new HashMap<>();

        return hsdTankTwoResponseMap;
    }

    @Override
    public Map<Object, Object> getAllHsdTankTwoDetails() {
        Map<Object, Object> hsdTankTwoResponseMap = new HashMap<>();

        return hsdTankTwoResponseMap;
    }

    Predicate<DieselTankTwo> hsdTankTwoPreviousDay = (tankTwo) -> {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, -1);
        return tankTwo.getHsdTankTwoDate().isEqual(
                LocalDateTime.ofInstant(calender.toInstant(), calender.getTimeZone().toZoneId()).toLocalDate());
    };

}
