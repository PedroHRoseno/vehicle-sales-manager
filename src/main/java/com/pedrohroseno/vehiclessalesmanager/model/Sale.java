package com.pedrohroseno.vehiclessalesmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Customer customer;
    private double salePrice;
    private Date saleDate;
}
