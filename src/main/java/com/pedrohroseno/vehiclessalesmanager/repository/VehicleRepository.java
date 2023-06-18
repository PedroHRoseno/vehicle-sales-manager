package com.pedrohroseno.vehiclessalesmanager.repository;

import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    public void deleteVehicleByLicensePlate(String licensePlate);
}
