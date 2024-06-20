package com.fino.utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;

import com.fino.dto.FuelReports.HsdTankTwoDto;
import com.fino.dto.FuelReports.MsSaleDto;
import com.fino.entity.FuelReports.DieselTankTwo;
import com.fino.entity.FuelReports.PetrolTankOne;

@Component
public class FuelReportUtils {

        public <T> T getPreviousDayReport(List<T> reports, Predicate<T> predicate) {
                return reports.stream().filter(predicate).findFirst().get();
        }

        public PetrolTankOne msSaleDetailsIfNoDataAvailable(MsSaleDto msSaleDto,
                        Map<String, String> mssaleInitialValue) {
                var petrolTankOne = new PetrolTankOne();
                petrolTankOne.setMSSaleDate(msSaleDto.getMsSaleDate());
                petrolTankOne.setOpeningStockOfMSSale(Double.parseDouble(mssaleInitialValue.get("openingStock")));
                petrolTankOne.setInwardOfMSSale(msSaleDto.getInwardOfMSSale());
                petrolTankOne.setTotalStockMSSale(
                                Double.parseDouble(mssaleInitialValue.get("openingStock"))
                                                + msSaleDto.getInwardOfMSSale());
                petrolTankOne.setTotalSalesMSSale(
                                (msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                - Double.parseDouble(mssaleInitialValue.get("nozzleOneReading")))
                                                +
                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                - Double.parseDouble(mssaleInitialValue
                                                                                .get("nozzleTwoReading")))

                );
                petrolTankOne.setClosingStockMSSale(
                                ((Double.parseDouble(mssaleInitialValue.get("openingStock"))
                                                + msSaleDto.getInwardOfMSSale())
                                                - ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                                - Double.parseDouble(mssaleInitialValue
                                                                                .get("nozzleOneReading")))
                                                                +
                                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                                - Double.parseDouble(mssaleInitialValue
                                                                                                .get("nozzleTwoReading")))))
                                                + msSaleDto.getTesting());
                petrolTankOne.setDipStockOfMSSale(msSaleDto.getDipStockOfMSSaleInLtrs());
                petrolTankOne.setDipStockOfMSSaleInLtrs(msSaleDto.getDipStockOfMSSaleInLtrs());
                petrolTankOne.setDipStockOfMSSaleInCentimeter(msSaleDto.getDipStockOfMSSaleInCentimeters());
                petrolTankOne.setVariationOfMSSale(
                                (msSaleDto.getDipStockOfMSSaleInLtrs()) - (((Double
                                                .parseDouble(mssaleInitialValue.get("openingStock"))
                                                + msSaleDto.getInwardOfMSSale())
                                                - ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                                - Double.parseDouble(mssaleInitialValue
                                                                                .get("nozzleOneReading")))
                                                                +
                                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                                - Double.parseDouble(mssaleInitialValue
                                                                                                .get("nozzleTwoReading")))))
                                                + msSaleDto.getTesting())

                );
                petrolTankOne.setOpeningMeterOfMSSaleNozzleOne(
                                Double.parseDouble(mssaleInitialValue.get("nozzleOneReading")));
                petrolTankOne.setClosingMeterOfMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne());
                petrolTankOne.setOpeningMeterOfMSSaleNozzleTwo(
                                Double.parseDouble(mssaleInitialValue.get("nozzleTwoReading")));
                petrolTankOne.setClosingMeterOfMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo());
                petrolTankOne.setSalesForMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                - Double.parseDouble(mssaleInitialValue.get("nozzleOneReading")));
                petrolTankOne.setSalesForMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                - Double.parseDouble(mssaleInitialValue.get("nozzleTwoReading")));
                petrolTankOne.setTotalMeterSalesForTheDayForMSSaleInLtrs(
                                (msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                - Double.parseDouble(mssaleInitialValue.get("nozzleOneReading")))
                                                +
                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                - Double.parseDouble(mssaleInitialValue
                                                                                .get("nozzleTwoReading"))));
                petrolTankOne.setTesting(msSaleDto.getTesting());
                petrolTankOne.setDensity(msSaleDto.getDensity());
                petrolTankOne.setWaterDip(msSaleDto.getWaterDip());
                petrolTankOne.setRemarks(msSaleDto.getRemarks());
                petrolTankOne.setMSSaleAddedForDay(Boolean.TRUE);
                petrolTankOne.setMSSaleInsertDateTime(LocalDateTime.now());

                return petrolTankOne;
        }

        public PetrolTankOne msSaleDetailsIfPreviousDayDataAvailable(MsSaleDto msSaleDto,
                        PetrolTankOne previousDayMssale) {

                var petrolTankOne = new PetrolTankOne();
                petrolTankOne.setMSSaleDate(msSaleDto.getMsSaleDate());
                petrolTankOne.setOpeningStockOfMSSale(previousDayMssale.getDipStockOfMSSale());
                petrolTankOne.setInwardOfMSSale(msSaleDto.getInwardOfMSSale());
                petrolTankOne.setTotalStockMSSale(
                                previousDayMssale.getDipStockOfMSSale() + msSaleDto.getInwardOfMSSale());
                petrolTankOne.setTotalSalesMSSale(
                                (msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                - previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
                                                +
                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                - previousDayMssale.getClosingMeterOfMSSaleNozzleTwo())

                );
                petrolTankOne.setClosingStockMSSale(
                                ((previousDayMssale.getDipStockOfMSSale() + msSaleDto.getInwardOfMSSale())
                                                - ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                                - previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
                                                                +
                                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                                - previousDayMssale
                                                                                                .getClosingMeterOfMSSaleNozzleTwo())))
                                                + msSaleDto.getTesting());
                petrolTankOne.setDipStockOfMSSale(msSaleDto.getDipStockOfMSSaleInLtrs());
                petrolTankOne.setDipStockOfMSSaleInLtrs(msSaleDto.getDipStockOfMSSaleInLtrs());
                petrolTankOne.setDipStockOfMSSaleInCentimeter(msSaleDto.getDipStockOfMSSaleInCentimeters());
                petrolTankOne.setVariationOfMSSale(

                                msSaleDto.getDipStockOfMSSaleInLtrs() - (((previousDayMssale.getDipStockOfMSSale()
                                                + msSaleDto.getInwardOfMSSale())
                                                - ((msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                                - previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
                                                                +
                                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                                - previousDayMssale
                                                                                                .getClosingMeterOfMSSaleNozzleTwo())))
                                                + msSaleDto.getTesting())

                );
                petrolTankOne.setOpeningMeterOfMSSaleNozzleOne(previousDayMssale.getClosingMeterOfMSSaleNozzleOne());
                petrolTankOne.setClosingMeterOfMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne());
                petrolTankOne.setOpeningMeterOfMSSaleNozzleTwo(previousDayMssale.getClosingMeterOfMSSaleNozzleTwo());
                petrolTankOne.setClosingMeterOfMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo());
                petrolTankOne.setSalesForMSSaleNozzleOne(msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                - previousDayMssale.getClosingMeterOfMSSaleNozzleOne());
                petrolTankOne.setSalesForMSSaleNozzleTwo(msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                - previousDayMssale.getClosingMeterOfMSSaleNozzleTwo());
                petrolTankOne.setTotalMeterSalesForTheDayForMSSaleInLtrs(
                                (msSaleDto.getClosingMeterOfMSSaleNozzleOne()
                                                - previousDayMssale.getClosingMeterOfMSSaleNozzleOne())
                                                +
                                                (msSaleDto.getClosingMeterOfMSSaleNozzleTwo()
                                                                - previousDayMssale
                                                                                .getClosingMeterOfMSSaleNozzleTwo()));
                petrolTankOne.setTesting(msSaleDto.getTesting());
                petrolTankOne.setDensity(msSaleDto.getDensity());
                petrolTankOne.setWaterDip(msSaleDto.getWaterDip());
                petrolTankOne.setRemarks(msSaleDto.getRemarks());
                petrolTankOne.setMSSaleAddedForDay(Boolean.TRUE);
                petrolTankOne.setMSSaleInsertDateTime(LocalDateTime.now());

                return petrolTankOne;
        }

        public DieselTankTwo hsdTankTwoDetailsIfNoDataAvailable(HsdTankTwoDto hsdTankTwoDto,
                        Map<String, String> hsdTankTwoInitialValue) {
                var dieselTankTwo = new DieselTankTwo();
                dieselTankTwo.setHsdTankTwoDate(hsdTankTwoDto.getHsdTankTwoDate());
                dieselTankTwo.setOpeningStockOfHsdTankTwo(
                                Double.parseDouble(hsdTankTwoInitialValue.get("openingStock")));
                dieselTankTwo.setInwardOfHsdTankTwo(hsdTankTwoDto.getInwardOfHsdTankTwo());
                dieselTankTwo.setTotalStockHsdTankTwo(
                                Double.parseDouble(hsdTankTwoInitialValue.get("openingStock"))
                                                + hsdTankTwoDto.getInwardOfHsdTankTwo());
                dieselTankTwo.setTotalSalesHsdTankTwo(
                                (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
                                                - Double.parseDouble(hsdTankTwoInitialValue.get("nozzleOneReading")))
                                                +
                                                (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
                                                                - Double.parseDouble(hsdTankTwoInitialValue
                                                                                .get("nozzleTwoReading")))

                );
                dieselTankTwo.setClosingStockHsdTankTwo(
                                ((Double.parseDouble(hsdTankTwoInitialValue.get("openingStock"))
                                                + hsdTankTwoDto.getInwardOfHsdTankTwo())
                                                - ((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
                                                                - Double.parseDouble(hsdTankTwoInitialValue
                                                                                .get("nozzleOneReading")))
                                                                +
                                                                (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
                                                                                - Double.parseDouble(
                                                                                                hsdTankTwoInitialValue
                                                                                                                .get("nozzleTwoReading")))))
                                                + hsdTankTwoDto.getTesting());

                dieselTankTwo.setDipStockOfHsdTankTwo(hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs());
                dieselTankTwo.setDipOfHsdTankTwoInLtrs(hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs());
                dieselTankTwo.setDipOfHsdTankTwoInCentimeter(hsdTankTwoDto.getDipStockOfHsdTankTwoInCentimeters());
                dieselTankTwo.setVariationOfHsdTankTwo(
                                (hsdTankTwoDto.getDipStockOfHsdTankTwoInLtrs()) - (((Double
                                                .parseDouble(hsdTankTwoInitialValue.get("openingStock"))
                                                + hsdTankTwoDto.getInwardOfHsdTankTwo())
                                                - ((hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
                                                                - Double.parseDouble(hsdTankTwoInitialValue
                                                                                .get("nozzleOneReading")))
                                                                +
                                                                (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
                                                                                - Double.parseDouble(
                                                                                                hsdTankTwoInitialValue
                                                                                                                .get("nozzleTwoReading")))))
                                                + hsdTankTwoDto.getTesting())

                );
                dieselTankTwo.setOpeningMeterOfHsdTankTwoNozzleOne(
                                Double.parseDouble(hsdTankTwoInitialValue.get("nozzleOneReading")));
                dieselTankTwo.setClosingMeterOfHsdTankTwoNozzleOne(
                                hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne());
                dieselTankTwo.setOpeningMeterOfHsdTankTwoNozzleTwo(
                                Double.parseDouble(hsdTankTwoInitialValue.get("nozzleTwoReading")));
                dieselTankTwo.setClosingMeterOfHsdTankTwoNozzleTwo(
                                hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo());
                dieselTankTwo.setSalesForHsdTankTwoNozzleOne(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
                                - Double.parseDouble(hsdTankTwoInitialValue.get("nozzleOneReading")));
                dieselTankTwo.setSalesForHsdTankTwoNozzleTwo(hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
                                - Double.parseDouble(hsdTankTwoInitialValue.get("nozzleTwoReading")));
                dieselTankTwo.setTotalSalesForTheDayHsdTankTwo(
                                (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleOne()
                                                - Double.parseDouble(hsdTankTwoInitialValue.get("nozzleOneReading")))
                                                +
                                                (hsdTankTwoDto.getClosingMeterOfHsdTankTwoNozzleTwo()
                                                                - Double.parseDouble(hsdTankTwoInitialValue
                                                                                .get("nozzleTwoReading"))));
                dieselTankTwo.setTesting(hsdTankTwoDto.getTesting());
                dieselTankTwo.setDensity(hsdTankTwoDto.getDensity());
                dieselTankTwo.setWaterDip(hsdTankTwoDto.getWaterDip());
                dieselTankTwo.setRemarks(hsdTankTwoDto.getRemarks());
                dieselTankTwo.setHsdTankTwoAddedForDay(Boolean.TRUE);
                dieselTankTwo.setHsdTankTwoInsertDateTime(LocalDateTime.now());

                return dieselTankTwo;
        }

}
