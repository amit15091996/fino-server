package com.fino.service;

import java.util.List;

import com.fino.entity.TestTransaction;

public interface FinoService {

	public TestTransaction addTrans(TestTransaction testTransaction);
	
	public TestTransaction viewTransByTransId(String transId);
	
	public List<TestTransaction> viewAll();
}
