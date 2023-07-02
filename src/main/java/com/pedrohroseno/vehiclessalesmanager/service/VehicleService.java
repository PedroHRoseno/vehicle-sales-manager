package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.exceptions.InvalidVehicleBrandException;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import com.pedrohroseno.vehiclessalesmanager.repository.VehicleRepository;
import jakarta.transaction.Transactional;
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

    public void addVehicle(Vehicle vehicle) {
        // Check if the brand is valid
        if (!EnumSet.allOf(VehicleBrand.class).contains(vehicle.getBrand())) {
            throw new InvalidVehicleBrandException("Invalid vehicle brand: " + vehicle.getBrand());
        }

        vehicleRepository.save(vehicle);
    }


    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Transactional
    public void deleteVehicle(String licensePlate) {
        vehicleRepository.deleteVehicleByLicensePlate(licensePlate);
    }

    public void setVehicleStock(Vehicle vehicle, Boolean status){
        vehicle.setInStock(status);
        this.updateVehicle(vehicle);
    }

    public boolean vehicleExistsByLicensePlate(String licensePlate){
        Vehicle vehicle = getVehicleByLicensePlate(licensePlate);
        return vehicle != null;
    }

    public boolean vehicleIsAvailable(String licensePlate){
        if (vehicleExistsByLicensePlate(licensePlate)){
            Vehicle vehicle = getVehicleByLicensePlate(licensePlate);
            return vehicle.getInStock();
        }
        else {
            return false;
        }
    }

}
