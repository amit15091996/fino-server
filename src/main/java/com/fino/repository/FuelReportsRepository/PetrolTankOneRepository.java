package com.fino.repository.FuelReportsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.FuelReports.PetrolTankOne;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface PetrolTankOneRepository extends JpaRepository<PetrolTankOne,Long> {

}
