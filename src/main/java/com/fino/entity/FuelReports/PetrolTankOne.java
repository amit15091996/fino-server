package com.fino.entity.FuelReports;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
@Table(name = "FINO_PETROL_TANK_ONE")
@Entity
public class PetrolTankOne extends FuelTesting {

    @TableGenerator(allocationSize = 1, initialValue = 1, name = "fino_petrol_tank_one_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_petrol_tank_one_sequence")
    private Long MSSaleId;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(nullable = false)
    private LocalDate msSaleDate;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingStockOfMSSale;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double inwardOfMSSale;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalStockMSSale;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalSalesMSSale;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingStockMSSale;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipStockOfMSSale;
    @Column(columnDefinition = "Decimal(10,2)")
    private Double variationOfMSSale;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipStockOfMSSaleInCentimeter;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipStockOfMSSaleInLtrs;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfMSSaleNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfMSSaleNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfMSSaleNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfMSSaleNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForMSSaleNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForMSSaleNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalMeterSalesForTheDayForMSSaleInLtrs;
    @JsonFormat(shape = Shape.STRING)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(nullable = false)
    private LocalDateTime msSaleInsertDateTime;
    @Column(columnDefinition = "BOOLEAN", nullable = false)
	private boolean isMSSaleAddedForDay;
}
