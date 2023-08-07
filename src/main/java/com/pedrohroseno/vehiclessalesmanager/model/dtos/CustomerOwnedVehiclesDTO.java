package com.pedrohroseno.vehiclessalesmanager.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerOwnedVehiclesDTO {

    private String licensePlate;

    private String modelName;
}
