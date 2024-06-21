package com.fino.configuration.FuelReportConfig;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.*;

@Configuration
@ConfigurationProperties("hsdtankone")
@Getter
@Setter
@Component
public class HsdTankTwoInitialData {
private Map<String, String> hsdtanktwoinitialvalue;
}