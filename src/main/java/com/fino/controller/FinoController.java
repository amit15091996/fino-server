package com.fino.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fino.entity.TestTransaction;
import com.fino.service.FinoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // Enable CORS for all origins and headers
public class FinoController {

    @Autowired
    private FinoService finoService;

    @GetMapping("/get")
    public String test() {
        return "RUNNING...";
    }

    @PostMapping("/add")
    public TestTransaction addTransaction(@RequestBody TestTransaction testTransaction) {
        return finoService.addTrans(testTransaction);
    }

    @GetMapping("/viewAll")
    public List<TestTransaction> getAll() {
        return finoService.viewAll();
    }
}
