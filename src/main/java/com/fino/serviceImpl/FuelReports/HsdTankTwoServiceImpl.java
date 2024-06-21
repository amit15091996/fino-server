package com.fino.serviceImpl.FuelReports;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fino.configuration.FuelReportConfig.HsdTankTwoInitialData;
import com.fino.dto.FuelReports.HsdTankTwoDto;
import com.fino.entity.FuelReports.DieselTankTwo;
import com.fino.exception.BadRequest;
import com.fino.exception.NotFoundException;
import com.fino.helpers.AppConstants;
import com.fino.repository.FuelReportsRepository.DieselTankTwoRepository;
import com.fino.service.FuelReports.HsdTankTwoService;
import com.fino.utils.FuelReportUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
		List<DieselTankTwo> hsdTankTwoList = this.dieselTankTwoRepository.findAll();
		if (hsdTankTwoList.isEmpty()) {
			try {
				var hsdTankTwoResponse = this.dieselTankTwoRepository
						.save(this.fuelReportUtils.hsdTankTwoDetailsIfNoDataAvailable(hsdTankTwoDto,
								this.hsdTankTwoInitialData.getHsdtanktwoinitialvalue()));
				if (hsdTankTwoResponse != null) {
					hsdTankTwoResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					hsdTankTwoResponseMap.put(AppConstants.status, AppConstants.success);
					hsdTankTwoResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
				}
			} catch (Exception e) {
				throw new BadRequest(e.getLocalizedMessage());
			}
		} else {

			try {
				var previouseDayhsdTankTwo = this.fuelReportUtils.getPreviousDayReport(hsdTankTwoList,
						this.hsdTankTwoPreviousDay);
				log.info("previous day data:: " + previouseDayhsdTankTwo.getHsdTankTwoDate());

				var hsdTankTwoIfDataAvailableResponse = this.dieselTankTwoRepository.save(this.fuelReportUtils
						.hsdTankTwoDetailsIfPreviousDayDataAvailable(hsdTankTwoDto, previouseDayhsdTankTwo));
				if (hsdTankTwoIfDataAvailableResponse != null) {
					hsdTankTwoResponseMap.put(AppConstants.statusCode, AppConstants.ok);
					hsdTankTwoResponseMap.put(AppConstants.status, AppConstants.success);
					hsdTankTwoResponseMap.put(AppConstants.statusMessage, AppConstants.dataSubmitedsuccessfully);
				}
			} catch (Exception e) {
				throw new BadRequest(e.getLocalizedMessage());
			}
		}
		return hsdTankTwoResponseMap;
	}

	@Override
	public Map<Object, Object> deleteHsdTankTwoDetails(Long hsdTankTwoId) {
		Map<Object, Object> hsdTankTwoResponseMap = new HashMap<>();
		if (this.dieselTankTwoRepository.findById(hsdTankTwoId).isPresent()) {
			this.dieselTankTwoRepository.deleteById(hsdTankTwoId);
			hsdTankTwoResponseMap.put(AppConstants.statusCode, AppConstants.ok);
			hsdTankTwoResponseMap.put(AppConstants.status, AppConstants.success);
			hsdTankTwoResponseMap.put(AppConstants.statusMessage, AppConstants.dataDeletedSuccesFully);
		} else {
			throw new NotFoundException(AppConstants.noRecordFound + hsdTankTwoId);
		}
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
		hsdTankTwoResponseMap.put(AppConstants.statusCode, AppConstants.ok);
		hsdTankTwoResponseMap.put(AppConstants.status, AppConstants.success);
		hsdTankTwoResponseMap.put(AppConstants.statusMessage, AppConstants.dataFetchedSuccesfully);
		hsdTankTwoResponseMap.put(AppConstants.response, this.dieselTankTwoRepository.findAll());
		return hsdTankTwoResponseMap;
	}

	Predicate<DieselTankTwo> hsdTankTwoPreviousDay = (tankTwo) -> {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, -1);
		return tankTwo.getHsdTankTwoDate().isEqual(
				LocalDateTime.ofInstant(calender.toInstant(), calender.getTimeZone().toZoneId()).toLocalDate());
	};

}
