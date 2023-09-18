package com.pedrohroseno.vehiclessalesmanager.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Customer {

    @Id
    private String cpf;
    private String name;
    private String phoneNumber1;
    private String phoneNumber2;
    @ManyToOne(cascade= CascadeType.PERSIST)
    private Address address;
}
