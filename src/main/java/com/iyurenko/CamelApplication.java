package com.iyurenko;

import com.iyurenko.dao.domain.Thing;
import com.iyurenko.dao.repository.ThingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelApplication {

	private static final Logger log = LoggerFactory.getLogger(CamelApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CamelApplication.class, args);
	}

}
