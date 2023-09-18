package com.pedrohroseno.vehiclessalesmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    private Long id;
    private String streetName;
    private String number;
    private String city;
    private String state;
    private String reference;
    private String zipCode;

}
