package com.fino.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fino.entity.TestTransaction;

public interface FinoRepo extends JpaRepository<TestTransaction, String> {

}
