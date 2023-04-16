package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.repository.VehicleRepository;
import com.pedrohroseno.vehiclessalesmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{licensePlate}")
    public Vehicle getVehicleByLicensePlate(@PathVariable String licensePlate) {
        return vehicleService.getVehicleByLicensePlate(licensePlate);
    }

    @PostMapping("/")
    public void addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
    }

    @PutMapping("/{licensePlate}")
    public void updateVehicle(@PathVariable String licensePlate, @RequestBody Vehicle vehicle) {
        Vehicle existingVehicle = vehicleService.getVehicleByLicensePlate(licensePlate);
        if (existingVehicle != null) {
            vehicle.setLicensePlate(licensePlate);
            vehicleService.updateVehicle(vehicle);
        }
    }

    @DeleteMapping("/{licensePlate}")
    public void deleteVehicleByLicensePlate(@PathVariable String licensePlate) {
        vehicleService.deleteVehicleByLicensePlate(licensePlate);
    }

}

