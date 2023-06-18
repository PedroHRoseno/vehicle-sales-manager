package com.pedrohroseno.vehiclessalesmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Customer customer;
    private double purchasePrice;
    private Date purchaseDate;

}
