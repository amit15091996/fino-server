package com.fino.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fino.entity.TestTransaction;
import com.fino.repo.FinoRepo;
import com.fino.service.FinoService;

@Service
public class FinoServiceImpl implements FinoService {

    @Autowired
    private FinoRepo finoRepo;

    @Override
    public TestTransaction addTrans(TestTransaction testTransaction) {
        // Set attributes using the values from the incoming testTransaction parameter
        TestTransaction transaction = new TestTransaction();
        transaction.setTransId("Fino-" + System.currentTimeMillis());
        transaction.setTransType(testTransaction.getTransType());
        transaction.setMode(testTransaction.getMode());
        transaction.setTransAmt(testTransaction.getTransAmt());
        
        // Save the transaction to the database
        finoRepo.save(transaction);
        
        return transaction;
    }

    @Override
    public TestTransaction viewTransByTransId(String transId) {
        // Implementation for viewing transaction by ID
        return null;
    }

    @Override
    public List<TestTransaction> viewAll() {
        // Implementation for viewing all transactions
        return finoRepo.findAll();
    }
}
