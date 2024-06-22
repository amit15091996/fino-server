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
@Table(name = "FINO_DIESEL_TANK_TWO")
@Entity
public class DieselTankTwo extends FuelTesting {

    @TableGenerator(allocationSize = 1, initialValue = 1, name = "fino_diesel_tank_two_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_diesel_tank_two_sequence")
    private Long dieselTankTwoId;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(nullable = false)
    private LocalDate hsdTankTwoDate;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingStockOfHsdTankTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double inwardOfHsdTankTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalStockHsdTankTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalSalesHsdTankTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingStockHsdTankTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipStockOfHsdTankTwo;
    @Column(columnDefinition = "Decimal(10,2)")
    private Double variationOfHsdTankTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipOfHsdTankTwoInCentimeter;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double dipOfHsdTankTwoInLtrs;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfHsdTankTwoNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfHsdTankTwoNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double openingMeterOfHsdTankTwoNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double closingMeterOfHsdTankTwoNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForHsdTankTwoNozzleOne;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double salesForHsdTankTwoNozzleTwo;
    @Column(columnDefinition = "Decimal(20,2)")
    private Double totalSalesForTheDayHsdTankTwo;
    @JsonFormat(shape = Shape.STRING)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(nullable = false)
    private LocalDateTime hsdTankTwoInsertDateTime;
    @Column(columnDefinition = "BOOLEAN", nullable = false)
	private boolean isHsdTankTwoAddedForDay;

}
