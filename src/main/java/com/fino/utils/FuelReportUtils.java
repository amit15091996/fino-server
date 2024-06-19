package com.fino.utils;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class FuelReportUtils {

    public <T> T getPreviousDayReport(List<T> reports,Predicate<T> predicate){
        return reports.stream().filter(predicate).findFirst().get();
    }  

}
