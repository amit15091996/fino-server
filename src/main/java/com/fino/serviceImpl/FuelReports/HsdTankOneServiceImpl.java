package com.fino.serviceImpl.FuelReports;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fino.configuration.FuelReportConfig.HsdTankOneInitialData;
import com.fino.dto.FuelReports.HsdTankOneDto;
import com.fino.entity.FuelReports.DieselTankOne;
import com.fino.exception.BadRequest;
import com.fino.exception.InternalServerError;
import com.fino.exception.NotFoundException;
import com.fino.helpers.AppConstants;
import com.fino.repository.FuelReportsRepository.DieselTankOneRepository;
import com.fino.service.FuelReports.HsdTankOneService;
import com.fino.utils.FuelReportUpdateUtil;
import com.fino.utils.FuelReportUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HsdTankOneServiceImpl implements HsdTankOneService {

	@Autowired
	private DieselTankOneRepository dieselTankOneRepository;

	@Autowired
	private FuelReportUtils fuelReportUtils;
	@Autowired
	private HsdTankOneInitialData hsdTankOneInitialData;

	@Autowired
	private FuelReportUpdateUtil fuelReportUpdateUtil;

	@Override
	public Map<Object, Object> insertHsdTankOneDetails(HsdTankOneDto hsdTankOneDto) {
		Map<Object, Object> hsdTankOneResponseMap = new HashMap<>();
		List<DieselTankOne> hsdTankOneList = this.dieselTankOneRepository.findAll();
		if (hsdTankOneList.isEmpty()) {
			try {
				var hsdTankOneResponse = this.dieselTankOneRepository
						.save(this.fuelReportUtils.hsdTankOneDetailsIfNoDataAvailable(hsdTankOneDto,
								this.hsdTankOneInitialData.getHsdtankoneinitialvalue()));
				if (hsdTankOneResponse != null) {
					hsdTankOneResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					hsdTankOneResponseMap.put(AppConstants.status, AppConstants.success);
					hsdTankOneResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
				}
			} catch (Exception e) {
				throw new BadRequest(e.getLocalizedMessage());
			}
		} else {

			try {
				var previouseDayhsdTankOne = Collections.max(hsdTankOneList,
						Comparator.comparing(hsdOne -> hsdOne.getHsdTankOneDate()));
				log.info("previous day data:: " + previouseDayhsdTankOne.getHsdTankOneDate());

				var hsdTankOneIfDataAvailableResponse = this.dieselTankOneRepository.save(this.fuelReportUtils
						.hsdTankOneDetailsIfPreviousDayDataAvailable(hsdTankOneDto, previouseDayhsdTankOne));
				if (hsdTankOneIfDataAvailableResponse != null) {
					hsdTankOneResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					hsdTankOneResponseMap.put(AppConstants.status, AppConstants.success);
					hsdTankOneResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
				}
			} catch (Exception e) {
				throw new BadRequest(e.getLocalizedMessage());
			}
		}

		return hsdTankOneResponseMap;
	}

	@Override
	public Map<Object, Object> deleteHsdTankOneDetails(Long hsdTankOneId) {
		Map<Object, Object> hsdTankOneResponseMap = new HashMap<>();
		try {
			
		if (this.dieselTankOneRepository.findById(hsdTankOneId).isPresent()) {
			this.dieselTankOneRepository.deleteById(hsdTankOneId);
			hsdTankOneResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			hsdTankOneResponseMap.put(AppConstants.status, AppConstants.success);
			hsdTankOneResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + hsdTankOneId);
		}
		return hsdTankOneResponseMap;
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	@Override
	public Map<Object, Object> updateHsdTankOneDetails(Long hsdTankOneId, HsdTankOneDto hsdTankOneDto) {
		Map<Object, Object> hsdTankOneResponseMap = new HashMap<>();

		var hsdTankOneRecord = this.dieselTankOneRepository.findById(hsdTankOneId);
		var previousDayRecordOfhsdTankOne = this.dieselTankOneRepository
				.findByHsdTankOneDate(hsdTankOneDto.getHsdTankOneDate().minusDays(1));

		if (hsdTankOneRecord.isPresent()) {

			if (previousDayRecordOfhsdTankOne.isEmpty()) {
				var hsdTankOneIfPrevDayUnavailable = this.fuelReportUtils.hsdTankOneDetailsIfNoDataAvailable(
						hsdTankOneDto, this.hsdTankOneInitialData.getHsdtankoneinitialvalue());
				try {
					this.dieselTankOneRepository.save(this.fuelReportUpdateUtil
							.getUpdatedHsdTankOne(hsdTankOneRecord.get(), hsdTankOneIfPrevDayUnavailable));
					hsdTankOneResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					hsdTankOneResponseMap.put(AppConstants.status, AppConstants.success);
					hsdTankOneResponseMap.put(AppConstants.statusMessage,
							AppConstants.recordUpdatedSuccessFully + hsdTankOneId);

				} catch (Exception e) {
					throw new BadRequest(e.getMessage());
				}
			} else {
				try {
					var updatedHsdTankOneRecord = this.fuelReportUtils.hsdTankOneDetailsIfPreviousDayDataAvailable(
							hsdTankOneDto, previousDayRecordOfhsdTankOne.get());

					this.dieselTankOneRepository.save(this.fuelReportUpdateUtil
							.getUpdatedHsdTankOne(hsdTankOneRecord.get(), updatedHsdTankOneRecord));
					hsdTankOneResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					hsdTankOneResponseMap.put(AppConstants.status, AppConstants.success);
					hsdTankOneResponseMap.put(AppConstants.statusMessage,
							AppConstants.recordUpdatedSuccessFully + hsdTankOneId);

				} catch (Exception e) {
					throw new BadRequest(e.getMessage());
				}
			}

		} else {
			throw new NotFoundException(AppConstants.noRecordFound + hsdTankOneId);
		}

		return hsdTankOneResponseMap;
	}

	@Override
	public Map<Object, Object> getAllHsdTankOneDetails() {
		
		try {
		Map<Object, Object> hsdTankOneResponseMap = new HashMap<>();
		hsdTankOneResponseMap.put(AppConstants.statusCode, AppConstants.ok);
		hsdTankOneResponseMap.put(AppConstants.status, AppConstants.success);
		hsdTankOneResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
		hsdTankOneResponseMap.put(AppConstants.response, this.dieselTankOneRepository.findAll());
		return hsdTankOneResponseMap;
		} catch (Exception e) {
			throw new InternalServerError(e.getMessage());
		}
	}

	Predicate<DieselTankOne> hsdTankOnePreviousDay = (tankOne) -> {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, -1);
		return tankOne.getHsdTankOneDate().isEqual(
				LocalDateTime.ofInstant(calender.toInstant(), calender.getTimeZone().toZoneId()).toLocalDate());
	};

}
