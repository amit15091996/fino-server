package com.fino.repository.FuelReportsRepository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fino.entity.FuelReports.DieselTankTwo;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface DieselTankTwoRepository extends JpaRepository<DieselTankTwo,Long>{
	
	public Optional<DieselTankTwo> findByHsdTankTwoDate(LocalDate hsdTankTwoDate);

}
