package com.fino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.FinoUserRoles;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface FinoUserRolesRepository extends JpaRepository<FinoUserRoles, Long>{

}
