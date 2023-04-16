package com.pedrohroseno.vehiclessalesmanager.model;

import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private String licensePlate;
    private VehicleBrand brand;
    private String modelName;
    private int manufactureYear;
    private int modelYear;
    private String color;
    private int kilometersDriven;
    private Boolean inStock;

}
