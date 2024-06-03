package com.fino.entity.FuelReports;

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
    public String testing;
    public String density;
    public String waterDip;
    public String remarks;
}
