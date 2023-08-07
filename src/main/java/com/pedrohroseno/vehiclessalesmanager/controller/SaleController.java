package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.dtos.CustomerOwnedVehiclesDTO;
import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.model.dtos.SaleBrandDTO;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import com.pedrohroseno.vehiclessalesmanager.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/search")
    public Sale getSaleById(@RequestParam Long id) {
        return saleService.getSaleById(id);
    }

    @GetMapping("/vehicles")
    public List<CustomerOwnedVehiclesDTO> getCustomerVehicles(@RequestParam("cpf")final String cpf) {
        return saleService.getCustomerVehicles(cpf);
    }

    @GetMapping("/salesPerBrand")
    public List<SaleBrandDTO> getCustomerVehicles() {
        return saleService.getSalesPerBrand();
    }

    @GetMapping("/profit")
    public double getTotalProfit(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                 @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return saleService.getTotalProfit(startDate, endDate);
    }

    @PostMapping
    public ResponseEntity<Void> addSale(@RequestBody Sale sale) {
        saleService.createSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public void updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        Sale existingSale = saleService.getSaleById(id);
        if (existingSale != null) {
            sale.setId(id);
            saleService.updateSale(sale);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaleById(@PathVariable Long id) {
        saleService.deleteSaleById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
