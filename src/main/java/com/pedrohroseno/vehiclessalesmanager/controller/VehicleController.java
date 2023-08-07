package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/search")
    public ResponseEntity<Vehicle> getVehicle(@RequestParam("licensePlate") final String licensePlate) {
        Vehicle vehicleByLicensePlate = vehicleService.getVehicleByLicensePlate(licensePlate);
        if (vehicleByLicensePlate != null){
            return ResponseEntity.ok(vehicleByLicensePlate);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Void> addVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateVehicle(@RequestBody final Vehicle vehicle) {
        Vehicle existingVehicle = vehicleService.getVehicleByLicensePlate(vehicle.getLicensePlate());
        if (existingVehicle != null) {
            vehicle.setLicensePlate(vehicle.getLicensePlate());
            vehicleService.updateVehicle(vehicle);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{licensePlate}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable final String licensePlate) {
        vehicleService.deleteVehicle(licensePlate);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

