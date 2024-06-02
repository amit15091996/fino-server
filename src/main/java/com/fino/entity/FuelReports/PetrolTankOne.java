package com.fino.entity.FuelReports;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
    private Long petrolTankOneId;
    private String inwardOfPetrolTankOne;
    private String dipOfPetrolTankOne;
    private String actualSaleOfPetrolTankOne;
    private String closingReadingOfNozzleOne;
    private String closingReadingOfNozzleTwo;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(nullable = false)
    private LocalDate petrolTankOneDate;


    
}
