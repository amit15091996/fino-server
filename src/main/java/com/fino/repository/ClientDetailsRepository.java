package com.fino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fino.entity.ClientDetails;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface ClientDetailsRepository extends JpaRepository<ClientDetails,Long>{

}
