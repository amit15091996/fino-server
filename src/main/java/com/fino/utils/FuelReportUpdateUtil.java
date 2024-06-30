package com.fino.utils;

import org.springframework.stereotype.Component;

import com.fino.entity.FuelReports.DieselTankOne;
import com.fino.entity.FuelReports.DieselTankTwo;
import com.fino.entity.FuelReports.PetrolTankOne;

@Component
public class FuelReportUpdateUtil {

	public PetrolTankOne getUpdatedMssale(PetrolTankOne oldRecord, PetrolTankOne newRecord) {

		oldRecord.setTesting(newRecord.getTesting());
		oldRecord.setDensity(newRecord.getDensity());
		oldRecord.setWaterDip(newRecord.getWaterDip());
		oldRecord.setRemarks(newRecord.getRemarks());

		oldRecord.setClosingMeterOfMSSaleNozzleOne(newRecord.getClosingMeterOfMSSaleNozzleOne());
		oldRecord.setClosingMeterOfMSSaleNozzleTwo(newRecord.getClosingMeterOfMSSaleNozzleTwo());
		oldRecord.setClosingStockMSSale(newRecord.getClosingStockMSSale());
		oldRecord.setDipStockOfMSSale(newRecord.getDipStockOfMSSale());
		oldRecord.setDipStockOfMSSaleInCentimeter(newRecord.getDipStockOfMSSaleInCentimeter());
		oldRecord.setDipStockOfMSSaleInLtrs(newRecord.getDipStockOfMSSaleInLtrs());
		oldRecord.setInwardOfMSSale(newRecord.getInwardOfMSSale());
		oldRecord.setOpeningMeterOfMSSaleNozzleOne(newRecord.getOpeningMeterOfMSSaleNozzleOne());
		oldRecord.setOpeningMeterOfMSSaleNozzleTwo(newRecord.getOpeningMeterOfMSSaleNozzleTwo());
		oldRecord.setOpeningStockOfMSSale(newRecord.getOpeningStockOfMSSale());
		oldRecord.setSalesForMSSaleNozzleOne(newRecord.getSalesForMSSaleNozzleOne());
		oldRecord.setSalesForMSSaleNozzleTwo(newRecord.getSalesForMSSaleNozzleTwo());
		oldRecord.setTotalMeterSalesForTheDayForMSSaleInLtrs(newRecord.getTotalMeterSalesForTheDayForMSSaleInLtrs());
		oldRecord.setTotalSalesMSSale(newRecord.getTotalSalesMSSale());
		oldRecord.setTotalStockMSSale(newRecord.getTotalStockMSSale());
		oldRecord.setVariationOfMSSale(newRecord.getVariationOfMSSale());

		return oldRecord;
	}

	public DieselTankTwo getUpdatedHsdTankTwo(DieselTankTwo oldRecord, DieselTankTwo newRecord) {

		oldRecord.setTesting(newRecord.getTesting());
		oldRecord.setDensity(newRecord.getDensity());
		oldRecord.setWaterDip(newRecord.getWaterDip());
		oldRecord.setRemarks(newRecord.getRemarks());

		oldRecord.setClosingMeterOfHsdTankTwoNozzleOne(newRecord.getClosingMeterOfHsdTankTwoNozzleOne());
		oldRecord.setClosingMeterOfHsdTankTwoNozzleTwo(newRecord.getClosingMeterOfHsdTankTwoNozzleTwo());

		oldRecord.setClosingStockHsdTankTwo(newRecord.getClosingStockHsdTankTwo());
		oldRecord.setDipStockOfHsdTankTwo(newRecord.getDipStockOfHsdTankTwo());

		oldRecord.setDipOfHsdTankTwoInCentimeter(newRecord.getDipOfHsdTankTwoInCentimeter());
		oldRecord.setDipOfHsdTankTwoInLtrs(newRecord.getDipOfHsdTankTwoInLtrs());
		oldRecord.setInwardOfHsdTankTwo(newRecord.getInwardOfHsdTankTwo());
		oldRecord.setOpeningMeterOfHsdTankTwoNozzleOne(newRecord.getOpeningMeterOfHsdTankTwoNozzleOne());
		oldRecord.setOpeningMeterOfHsdTankTwoNozzleTwo(newRecord.getOpeningMeterOfHsdTankTwoNozzleTwo());

		oldRecord.setOpeningStockOfHsdTankTwo(newRecord.getOpeningStockOfHsdTankTwo());
		oldRecord.setSalesForHsdTankTwoNozzleOne(newRecord.getSalesForHsdTankTwoNozzleOne());
		oldRecord.setSalesForHsdTankTwoNozzleTwo(newRecord.getSalesForHsdTankTwoNozzleTwo());
		oldRecord.setTotalSalesForTheDayHsdTankTwo(newRecord.getTotalSalesForTheDayHsdTankTwo());
		oldRecord.setTotalSalesHsdTankTwo(newRecord.getTotalSalesHsdTankTwo());
		oldRecord.setTotalStockHsdTankTwo(newRecord.getTotalStockHsdTankTwo());
		oldRecord.setVariationOfHsdTankTwo(newRecord.getVariationOfHsdTankTwo());

		return oldRecord;
	}

	public DieselTankOne getUpdatedHsdTankOne(DieselTankOne oldRecord, DieselTankOne newRecord) {

		oldRecord.setTesting(newRecord.getTesting());
		oldRecord.setDensity(newRecord.getDensity());
		oldRecord.setWaterDip(newRecord.getWaterDip());
		oldRecord.setRemarks(newRecord.getRemarks());

		oldRecord.setClosingMeterOfHsdTankOneNozzleOne(newRecord.getClosingMeterOfHsdTankOneNozzleOne());
		oldRecord.setClosingMeterOfHsdTankOneNozzleTwo(newRecord.getClosingMeterOfHsdTankOneNozzleTwo());
		oldRecord.setClosingMeterOfHsdTankOneNozzleThree(newRecord.getClosingMeterOfHsdTankOneNozzleThree());
		oldRecord.setClosingMeterOfHsdTankOneNozzleFour(newRecord.getClosingMeterOfHsdTankOneNozzleFour());

		oldRecord.setClosingStockHsdTankOne(newRecord.getClosingStockHsdTankOne());
		oldRecord.setDipStockOfHsdTankOne(newRecord.getDipStockOfHsdTankOne());

		oldRecord.setDipOfHsdTankOneInCentimeter(newRecord.getDipOfHsdTankOneInCentimeter());
		oldRecord.setDipOfHsdTankOneInLtrs(newRecord.getDipOfHsdTankOneInLtrs());
		oldRecord.setInwardOfHsdTankOne(newRecord.getInwardOfHsdTankOne());

		oldRecord.setOpeningMeterOfHsdTankOneNozzleOne(newRecord.getOpeningMeterOfHsdTankOneNozzleOne());
		oldRecord.setOpeningMeterOfHsdTankOneNozzleTwo(newRecord.getOpeningMeterOfHsdTankOneNozzleTwo());
		oldRecord.setOpeningMeterOfHsdTankOneNozzleThree(newRecord.getOpeningMeterOfHsdTankOneNozzleThree());
		oldRecord.setOpeningMeterOfHsdTankOneNozzleFour(newRecord.getOpeningMeterOfHsdTankOneNozzleFour());

		oldRecord.setOpeningStockOfHsdTankOne(newRecord.getOpeningStockOfHsdTankOne());

		oldRecord.setSalesForHsdTankOneNozzleOne(newRecord.getSalesForHsdTankOneNozzleOne());
		oldRecord.setSalesForHsdTankOneNozzleTwo(newRecord.getSalesForHsdTankOneNozzleTwo());
		oldRecord.setSalesForHsdTankOneNozzleThree(newRecord.getSalesForHsdTankOneNozzleThree());
		oldRecord.setSalesForHsdTankOneNozzleFour(newRecord.getSalesForHsdTankOneNozzleFour());

		oldRecord.setTotalSalesForTheDayHsdTankOne(newRecord.getTotalSalesForTheDayHsdTankOne());
		oldRecord.setTotalSalesHsdTankOne(newRecord.getTotalSalesHsdTankOne());
		oldRecord.setTotalStockHsdTankOne(newRecord.getTotalStockHsdTankOne());
		oldRecord.setVariationOfHsdTankOne(newRecord.getVariationOfHsdTankOne());

		return oldRecord;
	}
}
