package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.exceptions.InvalidVehicleBrandException;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import com.pedrohroseno.vehiclessalesmanager.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findById(licensePlate).orElse(null);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        // Check if the brand is valid
        if (!EnumSet.allOf(VehicleBrand.class).contains(vehicle.getBrand())) {
            throw new InvalidVehicleBrandException("Invalid vehicle brand: " + vehicle.getBrand());
        }

        return vehicleRepository.save(vehicle);
    }


    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public void deleteVehicleByLicensePlate(String licensePlate) {
        vehicleRepository.deleteById(licensePlate);
    }
}
