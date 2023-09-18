package com.pedrohroseno.vehiclessalesmanager.repository;

import com.pedrohroseno.vehiclessalesmanager.model.dtos.CustomerOwnedVehiclesDTO;
import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.model.dtos.SaleBrandDTO;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import jakarta.persistence.MapKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.pedrohroseno.vehiclessalesmanager.model.dtos.CustomerOwnedVehiclesDTO(s.vehicle.licensePlate, s.vehicle.modelName) " +
            "FROM Sale s " +
            "INNER JOIN Customer ON s.customer.cpf = :cpf " +
            "INNER JOIN Vehicle v ON s.vehicle = v.licensePlate")
    List<CustomerOwnedVehiclesDTO> getCustomerVehiclesByCpf(@Param("cpf") String cpf);

    @Query("SELECT new com.pedrohroseno.vehiclessalesmanager.model.dtos.SaleBrandDTO(v.brand, COUNT(s)) FROM Sale s INNER JOIN s.vehicle v GROUP BY v.brand")
    List<SaleBrandDTO> salesPerBrand();

    @Query("SELECT SUM(s.salePrice) FROM Sale s WHERE s.saleDate > :startDate AND s.saleDate < :endDate")
    double profitPerMonth(Date startDate, Date endDate);
}
