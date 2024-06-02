package com.fino.repository.FuelReportsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.FuelReports.DieselTankOne;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface DieselTankOneRepository extends JpaRepository<DieselTankOne,Long> {

}
