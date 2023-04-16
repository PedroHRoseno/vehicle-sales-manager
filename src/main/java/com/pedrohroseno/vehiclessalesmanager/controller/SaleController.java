package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    @PostMapping("/")
    public void addSale(@RequestBody Sale sale) {
        saleService.addSale(sale);
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
    public void deleteSaleById(@PathVariable Long id) {
        saleService.deleteSaleById(id);
    }

}
