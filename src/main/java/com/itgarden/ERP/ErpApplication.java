package com.itgarden.ERP;

// start date 05/06/2018 1.58 pm

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication  
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@EnableJpaRepositories

public class ErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}
}
