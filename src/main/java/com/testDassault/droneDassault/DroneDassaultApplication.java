package com.testDassault.droneDassault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DroneDassaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneDassaultApplication.class, args);
    }
}
