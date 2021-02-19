package com.itgarden.ERP;

// start date 05/06/2018 1.58 pm
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
/// @Configuration,@EnableAutoConfiguration, @ComponentScan this three annotation is combine of @SpringBootApplication annotation
@EnableJpaRepositories
@EnableTransactionManagement
public class ErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErpApplication.class, args);
    }
}
