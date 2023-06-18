package com.pedrohroseno.vehiclessalesmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//A anotação springboot application abstrai um conjunto de anotações como @Configuration
@SpringBootApplication
public class VehiclesSalesManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclesSalesManagerApplication.class, args);
    }

}
