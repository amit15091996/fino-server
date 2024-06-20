package com.fino.entity.FuelReports;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FINO_DIESEL_TANK_ONE")
@Entity
public class DieselTankOne extends FuelTesting {

    @TableGenerator(allocationSize = 1, initialValue = 1, name = "fino_diesel_tank_one_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_diesel_tank_one_sequence")
    private Long dieselTankOneId;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(nullable = false)
    private LocalDate HsdTankOneDate;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingStockOfHsdTankOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double inwardOfHsdTankOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalStockHsdTankOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalSalesHsdTankOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingStockHsdTankOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipStockOfHsdTankOne;
    @Column(columnDefinition = "Decimal(10,2)")
    private Double variationOfHsdTankOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipOfHsdTankOneInCentimeter;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipOfHsdTankOneInLtrs;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfHsdTankOneNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfHsdTankOneNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfHsdTankOneNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfHsdTankOneNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfHsdTankOneNozzleThree;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfHsdTankOneNozzleThree;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfHsdTankOneNozzleFour;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfHsdTankOneNozzleFour;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForHsdTankOneNozzleOne;

    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForHsdTankOneNozzleTwo;

    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForHsdTankOneNozzleThree;

    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForHsdTankOneNozzleFour;

    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalSalesForTheDayHsdTankOne;
    @JsonFormat(shape = Shape.STRING)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(nullable = false)
    private LocalDateTime HsdTankOneInsertDateTime;
    @Column(columnDefinition = "BOOLEAN", nullable = false)
	private boolean isHsdTankOneAddedForDay;

}
