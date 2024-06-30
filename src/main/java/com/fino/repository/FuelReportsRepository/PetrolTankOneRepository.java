package com.fino.repository.FuelReportsRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.FuelReports.PetrolTankOne;

import jakarta.transaction.Transactional;
import java.util.Optional;
import java.time.LocalDate;


@Repository
@Transactional
public interface PetrolTankOneRepository extends JpaRepository<PetrolTankOne,Long> {
	
	public Optional<PetrolTankOne> findByMsSaleDate(LocalDate msSaleDate);

}
