package com.siemens.daacathon.lifePrediction.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PravahRollerBearingLifePredictionApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(PravahRollerBearingLifePredictionApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PravahRollerBearingLifePredictionApplication.class, args);
	}
}