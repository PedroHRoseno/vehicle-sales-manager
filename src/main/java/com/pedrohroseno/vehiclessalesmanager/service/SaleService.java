package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public void addSale(Sale sale) {
        Optional<Vehicle> vehicle = Optional.ofNullable(vehicleService.getVehicleByLicensePlate(sale.getVehicle().getLicensePlate()));
        Optional<Customer> customer = Optional.ofNullable(customerService.getCustomerByCpf(sale.getCustomer().getCpf()));
        if (vehicle.isPresent() && customer.isPresent()){
            saleRepository.save(sale);
        }
    }

    public void updateSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }
}
