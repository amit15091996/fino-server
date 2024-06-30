package com.fino.repository.FuelReportsRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.FuelReports.DieselTankOne;


import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface DieselTankOneRepository extends JpaRepository<DieselTankOne,Long> {

	
	public Optional<DieselTankOne> findByHsdTankOneDate(LocalDate hsdTankOneDate);
	
}
