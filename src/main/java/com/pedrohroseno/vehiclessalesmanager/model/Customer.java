package com.pedrohroseno.vehiclessalesmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    private String cpf;
    private String name;
    private String phoneNumber1;
    private String phoneNumber2;
    private String streetName;
    private String number;
    private String city;
    private String state;
    private String reference;
}
