package com.fino.serviceImpl.FuelReports;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fino.configuration.FuelReportConfig.MsSaleInitialData;
import com.fino.dto.FuelReports.MsSaleDto;
import com.fino.entity.FuelReports.PetrolTankOne;
import com.fino.exception.BadRequest;
import com.fino.exception.NotFoundException;
import com.fino.helpers.AppConstants;
import com.fino.repository.FuelReportsRepository.PetrolTankOneRepository;
import com.fino.service.FuelReports.MsSaleService;
import com.fino.utils.FuelReportUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MsSaleServiceImpl implements MsSaleService {

    @Autowired
    private PetrolTankOneRepository petrolTankOneRepository;

    @Autowired
    private FuelReportUtils fuelReportUtils;

    @Autowired
    private MsSaleInitialData msSaleInitialData;

    @Override
    public Map<Object, Object> insertMsSaleDetails(MsSaleDto msSaleDto) {
        Map<Object, Object> msSaleResponseMap = new HashMap<>();
        List<PetrolTankOne> msSaleList = this.petrolTankOneRepository.findAll();
        if (msSaleList.isEmpty()) {
            try {
                var msSaleResponse = this.petrolTankOneRepository
                        .save(this.fuelReportUtils.msSaleDetailsIfNoDataAvailable(msSaleDto,
                                msSaleInitialData.getMssaleinitialvalue()));
                if (msSaleResponse != null) {
                    msSaleResponseMap.put(AppConstants.statusCode, AppConstants.ok);
                    msSaleResponseMap.put(AppConstants.status, AppConstants.success);
                    msSaleResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
                }
            } catch (Exception e) {
                throw new BadRequest(e.getLocalizedMessage());
            }
        } else {

            try {
                var previouseDayMsSale = this.fuelReportUtils.getPreviousDayReport(msSaleList,
                        this.msSaleOfPreviousDay);
                log.info("previous day data:: " + previouseDayMsSale.getMSSaleDate());

                var msSaleResponseIfDataAvailable = this.petrolTankOneRepository.save(
                        this.fuelReportUtils.msSaleDetailsIfPreviousDayDataAvailable(msSaleDto, previouseDayMsSale));
                if (msSaleResponseIfDataAvailable != null) {
                    msSaleResponseMap.put(AppConstants.statusCode, AppConstants.ok);
                    msSaleResponseMap.put(AppConstants.status, AppConstants.success);
                    msSaleResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
                }
            } catch (Exception e) {
                throw new BadRequest(e.getLocalizedMessage());
            }
        }
        return msSaleResponseMap;
    }

    @Override
    public Map<Object, Object> deleteMsSaleDetails(Long msSaleId) {
        Map<Object, Object> msSaleResponseMap = new HashMap<>();
		if (this.petrolTankOneRepository.findById(msSaleId).isPresent()) {
			this.petrolTankOneRepository.deleteById(msSaleId);
			msSaleResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			msSaleResponseMap.put(AppConstants.status, AppConstants.success);
			msSaleResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + msSaleId);
		}
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
        msSaleResponseMap.put(AppConstants.statusCode, AppConstants.ok);
        msSaleResponseMap.put(AppConstants.status, AppConstants.success);
        msSaleResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
        msSaleResponseMap.put(AppConstants.response, this.petrolTankOneRepository.findAll());
        return msSaleResponseMap;
    }

    Predicate<PetrolTankOne> msSaleOfPreviousDay = (petrol) -> {
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, -1);
        return petrol.getMSSaleDate().isEqual(
                LocalDateTime.ofInstant(calender.toInstant(), calender.getTimeZone().toZoneId()).toLocalDate());
    };

}
