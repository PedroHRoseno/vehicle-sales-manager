package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.dtos.CustomerOwnedVehiclesDTO;
import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.model.dtos.SaleBrandDTO;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import com.pedrohroseno.vehiclessalesmanager.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

    public void createSale(Sale sale) {
        Vehicle vehicle = sale.getVehicle();
        Optional<Customer> customer = Optional.ofNullable(customerService.getCustomerByCpf(sale.getCustomer().getCpf()));
        if (vehicleService.vehicleExistsByLicensePlate(vehicle.getLicensePlate()) && customer.isPresent() && vehicleService.vehicleIsAvailable(vehicle.getLicensePlate())){
            sale.setSaleDate(new Date());
            saleRepository.save(sale);
            vehicleService.setVehicleStock(vehicle, false);
        }
    }

    public void updateSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void deleteSaleById(Long id) {
        saleRepository.deleteById(id);
    }

    public List<CustomerOwnedVehiclesDTO> getCustomerVehicles(String cpf) {
        return saleRepository.getCustomerVehiclesByCpf(cpf);
    }

    public List<SaleBrandDTO> getSalesPerBrand(){
        return saleRepository.salesPerBrand();
    }

    public double getTotalProfit(Date startDate, Date endDate) {
        return saleRepository.profitPerMonth(startDate, endDate);
    }
}
