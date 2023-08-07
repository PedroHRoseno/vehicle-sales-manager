package com.pedrohroseno.vehiclessalesmanager.model.dtos;

import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleBrandDTO {

    private VehicleBrand vehicleBrand;
    private Long salesNumber;
}
