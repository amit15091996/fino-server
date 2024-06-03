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
@Table(name = "FINO_DIESEL_TANK_TWO")
@Entity
public class DieselTankTwo extends FuelTesting{

    @TableGenerator(allocationSize = 1, initialValue = 1, name = "fino_diesel_tank_two_sequence")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "fino_diesel_tank_two_sequence")
    private Long dieselTankTwoId;
    private String inwardOfDieselTankTwo;
    private String dipOfDieselTankTwo;
    private String actualSaleOfDieselTankTwo;
    private String closingReadingOfNozzleOne;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(nullable = false)
    private LocalDate dieselTankTwoDate;

   
}
