package com.fino.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

import com.fino.dto.FuelReports.HsdTankOneDto;
import com.fino.dto.FuelReports.HsdTankTwoDto;
import com.fino.dto.FuelReports.MsSaleDto;
import com.fino.entity.FuelReports.DieselTankOne;
import com.fino.entity.FuelReports.DieselTankTwo;
import com.fino.entity.FuelReports.PetrolTankOne;
import com.fino.helpers.AppConstants;

@Component
public class FuelReportUtils {

	public <T> T getPreviousDayReport(List<T> reports, Predicate<T> predicate) {
		return reports.stream().filter(predicate).findFirst().get();
	}

	public PetrolTankOne msSaleDetailsIfNoDataAvailable(MsSaleDto msSaleDto, Map<String, String> mssaleInitialValue) {
		var petrolTankOne = new PetrolTankOne();
		petrolTankOne.setMsSaleDate(msSaleDto.getMsSaleDate());
		petrolTankOne.setOpeningStockOfMSSale(Double.parseDouble(mssaleInitialValue.get(AppConstants.OPENING_STOCK)));
		petrolTankOne.setInwardOfMSSale(msSaleDto.getInwardOfMSSale());
		petrolTankOne.setTotalStockMSSale(
				Double.parseDouble(mssaleInitialValue.get(AppConstants.OPENING_STOCK)) + msSaleDto.getInwardOfMSSale());
		petrolTankOne.setTotalSalesMSSale((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
				- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
				+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
						- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_TWO_READING)))

		);
		petrolTankOne.setClosingStockMSSale(((Double.parseDouble(mssaleInitialValue.get(AppConstants.OPENING_STOCK))
				+ msSaleDto.getInwardOfMSSale())
				- ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
						- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
						+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
								- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_TWO_READING)))))
				+ msSaleDto.getTesting());
		petrolTankOne.setDipStockOfMSSale(msSaleDto.getDipStockOfMSSaleInLtrs());
		petrolTankOne.setDipStockOfMSSaleInLtrs(msSaleDto.getDipStockOfMSSaleInLtrs());
		petrolTankOne.setDipStockOfMSSaleInCentimeter(msSaleDto.getDipStockOfMSSaleInCentimeters());
		petrolTankOne.setVariationOfMSSale((msSaleDto.getDipStockOfMSSaleInLtrs())
				- (((Double.parseDouble(mssaleInitialValue.get(AppConstants.OPENING_STOCK))
						+ msSaleDto.getInwardOfMSSale())
						- ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
								- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
								+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
										- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_TWO_READING)))))
						+ msSaleDto.getTesting())

		);
		petrolTankOne.setOpeningMeterOfMSSaleNozzleOne(
				Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_ONE_READING)));
		petrolTankOne.setClosingMeterOfMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne());
		petrolTankOne.setOpeningMeterOfMSSaleNozzleTwo(
				Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_TWO_READING)));
		petrolTankOne.setClosingMeterOfMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo());
		petrolTankOne.setSalesForMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne()
				- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_ONE_READING)));
		petrolTankOne.setSalesForMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
				- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_TWO_READING)));
		petrolTankOne.setTotalMeterSalesForTheDayForMSSaleInLtrs((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
				- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
				+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
						- Double.parseDouble(mssaleInitialValue.get(AppConstants.NOZZLE_TWO_READING))));
		petrolTankOne.setTesting(msSaleDto.getTesting());
		petrolTankOne.setDensity(msSaleDto.getDensity());
		petrolTankOne.setWaterDip(msSaleDto.getWaterDip());
		petrolTankOne.setRemarks(msSaleDto.getRemarks());
		petrolTankOne.setMSSaleAddedForDay(Boolean.TRUE);
		petrolTankOne.setMsSaleInsertDateTime(LocalDateTime.now());

		return petrolTankOne;
	}

	public PetrolTankOne msSaleDetailsIfPreviousDayDataAvailable(MsSaleDto msSaleDto, PetrolTankOne previousDayMssale) {

		var petrolTankOne = new PetrolTankOne();
		petrolTankOne.setMsSaleDate(msSaleDto.getMsSaleDate());
		petrolTankOne.setOpeningStockOfMSSale(previousDayMssale.getDipStockOfMSSale());
		petrolTankOne.setInwardOfMSSale(msSaleDto.getInwardOfMSSale());
		petrolTankOne.setTotalStockMSSale(previousDayMssale.getDipStockOfMSSale() + msSaleDto.getInwardOfMSSale());
		petrolTankOne.setTotalSalesMSSale((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
				- previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
				+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo() - previousDayMssale.getClosingMeterOfMSSaleNozzleTwo())

		);
		petrolTankOne.setClosingStockMSSale(((previousDayMssale.getDipStockOfMSSale() + msSaleDto.getInwardOfMSSale())
				- ((msSaleDto.getClosingMeterOfMSSaleNozzleOne() - previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
						+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
								- previousDayMssale.getClosingMeterOfMSSaleNozzleTwo())))
				+ msSaleDto.getTesting());
		petrolTankOne.setDipStockOfMSSale(msSaleDto.getDipStockOfMSSaleInLtrs());
		petrolTankOne.setDipStockOfMSSaleInLtrs(msSaleDto.getDipStockOfMSSaleInLtrs());
		petrolTankOne.setDipStockOfMSSaleInCentimeter(msSaleDto.getDipStockOfMSSaleInCentimeters());
		petrolTankOne.setVariationOfMSSale(

				msSaleDto.getDipStockOfMSSaleInLtrs()
						- (((previousDayMssale.getDipStockOfMSSale() + msSaleDto.getInwardOfMSSale())
								- ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
										- previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
										+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
												- previousDayMssale.getClosingMeterOfMSSaleNozzleTwo())))
								+ msSaleDto.getTesting())

		);
		petrolTankOne.setOpeningMeterOfMSSaleNozzleOne(previousDayMssale.getClosingMeterOfMSSaleNozzleOne());
		petrolTankOne.setClosingMeterOfMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne());
		petrolTankOne.setOpeningMeterOfMSSaleNozzleTwo(previousDayMssale.getClosingMeterOfMSSaleNozzleTwo());
		petrolTankOne.setClosingMeterOfMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo());
		petrolTankOne.setSalesForMSSaleNozzleOne(
				msSaleDto.getClosingMeterOfMSSaleNozzleOne() - previousDayMssale.getClosingMeterOfMSSaleNozzleOne());
		petrolTankOne.setSalesForMSSaleNozzleTwo(
				msSaleDto.getClosingMeterOfMSSaleNozzleTwo() - previousDayMssale.getClosingMeterOfMSSaleNozzleTwo());
		petrolTankOne.setTotalMeterSalesForTheDayForMSSaleInLtrs(
				(msSaleDto.getClosingMeterOfMSSaleNozzleOne() - previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
						+ (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
								- previousDayMssale.getClosingMeterOfMSSaleNozzleTwo()));
		petrolTankOne.setTesting(msSaleDto.getTesting());
		petrolTankOne.setDensity(msSaleDto.getDensity());
		petrolTankOne.setWaterDip(msSaleDto.getWaterDip());
		petrolTankOne.setRemarks(msSaleDto.getRemarks());
		petrolTankOne.setMSSaleAddedForDay(Boolean.TRUE);
		petrolTankOne.setMsSaleInsertDateTime(LocalDateTime.now());

		return petrolTankOne;
	}

	public DieselTankTwo hsdTankTwoDetailsIfNoDataAvailable(HsdTankTwoDto hsdTankTwoDto,
			Map<String, String> hsdTankTwoInitialValue) {
		var dieselTankTwo = new DieselTankTwo();
		dieselTankTwo.setHsdTankTwoDate(hsdTankTwoDto.getHsdTankTwoDate());
		dieselTankTwo.setOpeningStockOfHsdTankTwo(
				Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.OPENING_STOCK)));
		dieselTankTwo.setInwardOfHsdTankTwo(hsdTankTwoDto.getInwardOfHsdTankTwo());
		dieselTankTwo.setTotalStockHsdTankTwo(Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.OPENING_STOCK))
				+ hsdTankTwoDto.getInwardOfHsdTankTwo());
		dieselTankTwo.setTotalSalesHsdTankTwo((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
				- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
				+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
						- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_TWO_READING)))

		);
		dieselTankTwo.setClosingStockHsdTankTwo(((Double.parseDouble(
				hsdTankTwoInitialValue.get(AppConstants.OPENING_STOCK)) + hsdTankTwoDto.getInwardOfHsdTankTwo())
				- ((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
						- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
						+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
								- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_TWO_READING)))))
				+ hsdTankTwoDto.getTesting());

		dieselTankTwo.setDipStockOfHsdTankTwo(hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs());
		dieselTankTwo.setDipOfHsdTankTwoInLtrs(hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs());
		dieselTankTwo.setDipOfHsdTankTwoInCentimeter(hsdTankTwoDto.getDipStockOfHsdTankTwoInCentimeters());
		dieselTankTwo.setVariationOfHsdTankTwo((hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs()) - (((Double.parseDouble(
				hsdTankTwoInitialValue.get(AppConstants.OPENING_STOCK)) + hsdTankTwoDto.getInwardOfHsdTankTwo())
				- ((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
						- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
						+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
								- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_TWO_READING)))))
				+ hsdTankTwoDto.getTesting())

		);
		dieselTankTwo.setOpeningMeterOfHsdTankTwoNozzleOne(
				Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_ONE_READING)));
		dieselTankTwo.setClosingMeterOfHsdTankTwoNozzleOne(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne());
		dieselTankTwo.setOpeningMeterOfHsdTankTwoNozzleTwo(
				Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_TWO_READING)));
		dieselTankTwo.setClosingMeterOfHsdTankTwoNozzleTwo(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo());
		dieselTankTwo.setSalesForHsdTankTwoNozzleOne(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
				- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_ONE_READING)));
		dieselTankTwo.setSalesForHsdTankTwoNozzleTwo(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
				- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_TWO_READING)));
		dieselTankTwo.setTotalSalesForTheDayHsdTankTwo((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
				- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
				+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
						- Double.parseDouble(hsdTankTwoInitialValue.get(AppConstants.NOZZLE_TWO_READING))));
		dieselTankTwo.setTesting(hsdTankTwoDto.getTesting());
		dieselTankTwo.setDensity(hsdTankTwoDto.getDensity());
		dieselTankTwo.setWaterDip(hsdTankTwoDto.getWaterDip());
		dieselTankTwo.setRemarks(hsdTankTwoDto.getRemarks());
		dieselTankTwo.setHsdTankTwoAddedForDay(Boolean.TRUE);
		dieselTankTwo.setHsdTankTwoInsertDateTime(LocalDateTime.now());

		return dieselTankTwo;
	}

	public DieselTankTwo hsdTankTwoDetailsIfPreviousDayDataAvailable(HsdTankTwoDto hsdTankTwoDto,
			DieselTankTwo previousDayOfHsdTankTwo) {

		var dieselTankTwo = new DieselTankTwo();

		dieselTankTwo.setHsdTankTwoDate(hsdTankTwoDto.getHsdTankTwoDate());
		dieselTankTwo.setOpeningStockOfHsdTankTwo(previousDayOfHsdTankTwo.getDipStockOfHsdTankTwo());
		dieselTankTwo.setInwardOfHsdTankTwo(hsdTankTwoDto.getInwardOfHsdTankTwo());
		dieselTankTwo.setTotalStockHsdTankTwo(
				previousDayOfHsdTankTwo.getDipStockOfHsdTankTwo() + hsdTankTwoDto.getInwardOfHsdTankTwo());
		dieselTankTwo.setTotalSalesHsdTankTwo((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
				- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleOne())
				+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
						- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleTwo())

		);
		dieselTankTwo.setClosingStockHsdTankTwo(
				((previousDayOfHsdTankTwo.getDipStockOfHsdTankTwo() + hsdTankTwoDto.getInwardOfHsdTankTwo())
						- ((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
								- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleOne())
								+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
										- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleTwo())))
						+ hsdTankTwoDto.getTesting());
		dieselTankTwo.setDipStockOfHsdTankTwo(hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs());
		dieselTankTwo.setDipOfHsdTankTwoInLtrs(hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs());
		dieselTankTwo.setDipOfHsdTankTwoInCentimeter(hsdTankTwoDto.getDipStockOfHsdTankTwoInCentimeters());
		dieselTankTwo.setVariationOfHsdTankTwo(

				hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs()
						- (((previousDayOfHsdTankTwo.getDipStockOfHsdTankTwo() + hsdTankTwoDto.getInwardOfHsdTankTwo())
								- ((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
										- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleOne())
										+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
												- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleTwo())))
								+ hsdTankTwoDto.getTesting())

		);
		dieselTankTwo
				.setOpeningMeterOfHsdTankTwoNozzleOne(previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleOne());
		dieselTankTwo.setClosingMeterOfHsdTankTwoNozzleOne(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne());
		dieselTankTwo
				.setOpeningMeterOfHsdTankTwoNozzleTwo(previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleTwo());
		dieselTankTwo.setClosingMeterOfHsdTankTwoNozzleTwo(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo());
		dieselTankTwo.setSalesForHsdTankTwoNozzleOne(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
				- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleOne());
		dieselTankTwo.setSalesForHsdTankTwoNozzleTwo(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
				- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleTwo());

		dieselTankTwo.setTotalSalesForTheDayHsdTankTwo((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
				- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleOne())
				+ (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
						- previousDayOfHsdTankTwo.getClosingMeterOfHsdTankTwoNozzleTwo()));
		dieselTankTwo.setTesting(hsdTankTwoDto.getTesting());
		dieselTankTwo.setDensity(hsdTankTwoDto.getDensity());
		dieselTankTwo.setWaterDip(hsdTankTwoDto.getWaterDip());
		dieselTankTwo.setRemarks(hsdTankTwoDto.getRemarks());
		dieselTankTwo.setHsdTankTwoAddedForDay(Boolean.TRUE);
		dieselTankTwo.setHsdTankTwoInsertDateTime(LocalDateTime.now());

		return dieselTankTwo;
	}

	public DieselTankOne hsdTankOneDetailsIfNoDataAvailable(HsdTankOneDto hsdTankOneDto,
			Map<String, String> hsdTankOneInitialValue) {
		var dieselTankOne = new DieselTankOne();

		dieselTankOne.setHsdTankOneDate(hsdTankOneDto.getHsdTankOneDate());
		dieselTankOne.setOpeningStockOfHsdTankOne(
				Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.OPENING_STOCK)));

		dieselTankOne.setInwardOfHsdTankOne(hsdTankOneDto.getInwardOfHsdTankOne());

		dieselTankOne.setTotalStockHsdTankOne(Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.OPENING_STOCK))
				+ hsdTankOneDto.getInwardOfHsdTankOne());

		dieselTankOne.setTotalSalesHsdTankOne((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
				- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_TWO_READING)))
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_THREE_READING)))

				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_FOUR_READING))));

		dieselTankOne.setClosingStockHsdTankOne(((Double.parseDouble(
				hsdTankOneInitialValue.get(AppConstants.OPENING_STOCK)) + hsdTankOneDto.getInwardOfHsdTankOne())

				- ((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
						+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
								- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_TWO_READING)))
						+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
								- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_THREE_READING)))

						+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
								- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_FOUR_READING))))

		) + hsdTankOneDto.getTesting());

		dieselTankOne.setDipStockOfHsdTankOne(hsdTankOneDto.getDipStockOfHsdTankOneInLtrs());
		dieselTankOne.setDipOfHsdTankOneInLtrs(hsdTankOneDto.getDipStockOfHsdTankOneInLtrs());
		dieselTankOne.setDipOfHsdTankOneInCentimeter(hsdTankOneDto.getDipStockOfHsdTankOneInCentimeters());

		dieselTankOne.setVariationOfHsdTankOne((hsdTankOneDto.getDipStockOfHsdTankOneInLtrs()) - (((Double.parseDouble(
				hsdTankOneInitialValue.get(AppConstants.OPENING_STOCK)) + hsdTankOneDto.getInwardOfHsdTankOne())
				- ((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
						+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
								- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_TWO_READING)))
						+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
								- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_THREE_READING)))

						+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
								- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_FOUR_READING))))

		) + hsdTankOneDto.getTesting())

		);
		dieselTankOne.setOpeningMeterOfHsdTankOneNozzleOne(
				Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_ONE_READING)));
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleOne(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne());
		dieselTankOne.setOpeningMeterOfHsdTankOneNozzleTwo(
				Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_TWO_READING)));
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleTwo(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo());

		dieselTankOne.setOpeningMeterOfHsdTankOneNozzleThree(
				Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_THREE_READING)));
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleThree(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree());

		dieselTankOne.setOpeningMeterOfHsdTankOneNozzleFour(
				Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_FOUR_READING)));
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleFour(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour());

		dieselTankOne.setSalesForHsdTankOneNozzleOne(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
				- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_ONE_READING)));

		dieselTankOne.setSalesForHsdTankOneNozzleTwo(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
				- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_TWO_READING)));

		dieselTankOne.setSalesForHsdTankOneNozzleThree(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
				- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_THREE_READING)));

		dieselTankOne.setSalesForHsdTankOneNozzleFour(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
				- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_FOUR_READING)));

		dieselTankOne.setTotalSalesForTheDayHsdTankOne((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
				- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_ONE_READING)))
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_TWO_READING)))
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_THREE_READING)))

				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
						- Double.parseDouble(hsdTankOneInitialValue.get(AppConstants.NOZZLE_FOUR_READING))));

		dieselTankOne.setTesting(hsdTankOneDto.getTesting());
		dieselTankOne.setDensity(hsdTankOneDto.getDensity());
		dieselTankOne.setWaterDip(hsdTankOneDto.getWaterDip());
		dieselTankOne.setRemarks(hsdTankOneDto.getRemarks());
		dieselTankOne.setHsdTankOneAddedForDay(Boolean.TRUE);
		dieselTankOne.setHsdTankOneInsertDateTime(LocalDateTime.now());

		return dieselTankOne;
	}

	public DieselTankOne hsdTankOneDetailsIfPreviousDayDataAvailable(HsdTankOneDto hsdTankOneDto,
			DieselTankOne previousDayOfHsdTankOne) {

		var dieselTankOne = new DieselTankOne();

		dieselTankOne.setHsdTankOneDate(hsdTankOneDto.getHsdTankOneDate());

		dieselTankOne.setOpeningStockOfHsdTankOne(previousDayOfHsdTankOne.getDipStockOfHsdTankOne());

		dieselTankOne.setInwardOfHsdTankOne(hsdTankOneDto.getInwardOfHsdTankOne());
		;
		dieselTankOne.setTotalStockHsdTankOne(
				previousDayOfHsdTankOne.getDipStockOfHsdTankOne() + hsdTankOneDto.getInwardOfHsdTankOne());

		dieselTankOne.setTotalSalesHsdTankOne((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
				- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleOne())
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
						- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleTwo())
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
						- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleThree())

				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
						- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleFour())

		);
		dieselTankOne.setClosingStockHsdTankOne(
				((previousDayOfHsdTankOne.getDipStockOfHsdTankOne() + hsdTankOneDto.getInwardOfHsdTankOne())
						- ((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
								- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleOne())
								+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
										- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleTwo())
								+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
										- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleThree())

								+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
										- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleFour()))

				) + hsdTankOneDto.getTesting());

		dieselTankOne.setDipStockOfHsdTankOne(hsdTankOneDto.getDipStockOfHsdTankOneInLtrs());
		dieselTankOne.setDipOfHsdTankOneInLtrs(hsdTankOneDto.getDipStockOfHsdTankOneInLtrs());
		dieselTankOne.setDipOfHsdTankOneInCentimeter(hsdTankOneDto.getDipStockOfHsdTankOneInCentimeters());

		dieselTankOne.setVariationOfHsdTankOne(

				hsdTankOneDto.getDipStockOfHsdTankOneInLtrs()
						- (((previousDayOfHsdTankOne.getDipStockOfHsdTankOne() + hsdTankOneDto.getInwardOfHsdTankOne())
								- ((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
										- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleOne())
										+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
												- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleTwo())
										+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
												- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleThree())

										+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
												- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleFour())))
								+ hsdTankOneDto.getTesting())

		);

		dieselTankOne
				.setOpeningMeterOfHsdTankOneNozzleOne(previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleOne());
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleOne(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne());

		dieselTankOne
				.setOpeningMeterOfHsdTankOneNozzleTwo(previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleTwo());
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleTwo(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo());

		dieselTankOne.setOpeningMeterOfHsdTankOneNozzleThree(
				previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleThree());
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleThree(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree());

		dieselTankOne
				.setOpeningMeterOfHsdTankOneNozzleFour(previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleFour());
		dieselTankOne.setClosingMeterOfHsdTankOneNozzleFour(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour());

		dieselTankOne.setSalesForHsdTankOneNozzleOne(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
				- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleOne());

		dieselTankOne.setSalesForHsdTankOneNozzleTwo(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
				- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleTwo());

		dieselTankOne.setSalesForHsdTankOneNozzleThree(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
				- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleThree());

		dieselTankOne.setSalesForHsdTankOneNozzleFour(hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
				- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleFour());

		dieselTankOne.setTotalSalesForTheDayHsdTankOne((hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleOne()
				- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleOne())
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleTwo()
						- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleTwo())
				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleThree()
						- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleThree())

				+ (hsdTankOneDto.getClosingMeterOfHsdTankOneNozzleFour()
						- previousDayOfHsdTankOne.getClosingMeterOfHsdTankOneNozzleFour()));

		dieselTankOne.setTesting(hsdTankOneDto.getTesting());
		dieselTankOne.setDensity(hsdTankOneDto.getDensity());
		dieselTankOne.setWaterDip(hsdTankOneDto.getWaterDip());
		dieselTankOne.setRemarks(hsdTankOneDto.getRemarks());
		dieselTankOne.setHsdTankOneAddedForDay(Boolean.TRUE);
		dieselTankOne.setHsdTankOneInsertDateTime(LocalDateTime.now());

		return dieselTankOne;
	}

}
