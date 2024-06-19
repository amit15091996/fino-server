package com.fino.serviceImpl.FuelReports;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fino.dto.FuelReports.MsSaleDto;
import com.fino.entity.FuelReports.PetrolTankOne;
import com.fino.exception.BadRequest;
import com.fino.helpers.AppConstants;
import com.fino.repository.FuelReportsRepository.PetrolTankOneRepository;
import com.fino.service.FuelReports.MsSaleService;
import com.fino.utils.FuelReportUtils;

@Service
public class MsSaleServiceImpl implements MsSaleService {

    @Autowired
    private PetrolTankOneRepository petrolTankOneRepository;

    @Autowired
    private FuelReportUtils fuelReportUtils;

    @Override
    public Map<Object, Object> insertMsSaleDetails(MsSaleDto msSaleDto) {
        Map<Object, Object> msSaleResponseMap = new HashMap<>();
        var msSaleDetails = new PetrolTankOne();

        try {
            var clientDetailsResponse = this.petrolTankOneRepository.save(msSaleDetails);
            if (clientDetailsResponse != null) {
                msSaleResponseMap.put(AppConstants.statusCode, AppConstants.ok);
                msSaleResponseMap.put(AppConstants.status, AppConstants.success);
                msSaleResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
            }
        } catch (Exception e) {
            throw new BadRequest(e.getMessage());
        }
        return msSaleResponseMap;
    }

    @Override
    public Map<Object, Object> deleteMsSaleDetails(Long msSaleId) {
        Map<Object, Object> msSaleResponseMap = new HashMap<>();

        return msSaleResponseMap;
    }

    @Override
    public Map<Object, Object> updateMsSaleDetails(Long msSaleId, MsSaleDto msSaleDto) {
        Map<Object, Object> msSaleResponseMap = new HashMap<>();

        return msSaleResponseMap;
    }

    @Override
    public Map<Object, Object> getAllMsSaleDetails() {
        Map<Object, Object> msSaleResponseMap = new HashMap<>();

        return msSaleResponseMap;
    }

    Predicate<PetrolTankOne> msSaleOfPreviousDay=(petrol)->{
        Calendar calender  = Calendar.getInstance(); calender.add(Calendar.DATE, -1);
       return petrol.getMSSaleDate().isEqual( LocalDateTime.ofInstant(calender.toInstant(), calender.getTimeZone().toZoneId()).toLocalDate());
    };

}
