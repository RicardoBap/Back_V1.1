package com.ricbap.salvavidas.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ricbap.salvavidas.api.config.property.SalvavidasApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(SalvavidasApiProperty.class)
public class SalvavidasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvavidasApiApplication.class, args);
	}

}
