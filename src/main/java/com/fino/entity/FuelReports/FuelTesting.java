package com.fino.entity.FuelReports;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class FuelTesting {
    @Column(columnDefinition = "Decimal(20,2)")
    public String testing;
    @Column(columnDefinition = "Decimal(20,2)")
    public String density;
    @Column(columnDefinition = "Decimal(20,2)")
    public String waterDip;
    @Column(length = 5000)
    public String remarks;
}
