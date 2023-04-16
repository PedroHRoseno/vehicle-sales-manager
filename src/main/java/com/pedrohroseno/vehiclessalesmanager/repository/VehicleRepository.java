package com.pedrohroseno.vehiclessalesmanager.repository;

import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}