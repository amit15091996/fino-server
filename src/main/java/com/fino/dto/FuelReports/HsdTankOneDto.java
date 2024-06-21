package com.fino.dto.FuelReports;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HsdTankOneDto {
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate HsdTankOneDate;
	private Double inwardOfHsdTankOne;
	private Double dipStockOfHsdTankOneInLtrs;
	private Double dipStockOfHsdTankOneInCentimeters;
	private Double testing;
	private Double density;
	private Double waterDip;
	private String remarks;
	private Double closingMeterOfHsdTankOneNozzleOne;
	private Double closingMeterOfHsdTankOneNozzleTwo;
	private Double closingMeterOfHsdTankOneNozzleThree;
	private Double closingMeterOfHsdTankOneNozzleFour;
}
