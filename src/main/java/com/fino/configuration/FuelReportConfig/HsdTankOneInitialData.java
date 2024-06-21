package com.fino.configuration.FuelReportConfig;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("hsdtanktwo")
@Getter
@Setter
@Component
public class HsdTankOneInitialData {
	private Map<String, String> hsdtankoneinitialvalue;
}
