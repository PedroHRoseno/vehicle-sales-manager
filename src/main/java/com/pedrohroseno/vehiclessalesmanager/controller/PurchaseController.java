package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.exceptions.ResourceNotFoundException;
import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.Purchase;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.repository.CustomerRepository;
import com.pedrohroseno.vehiclessalesmanager.repository.PurchaseRepository;
import com.pedrohroseno.vehiclessalesmanager.repository.VehicleRepository;
import com.pedrohroseno.vehiclessalesmanager.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // GET all purchases
    @GetMapping
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }

    // GET purchase by ID
    @GetMapping("/search")
    public ResponseEntity<Purchase> getPurchaseById(@RequestParam Long id) {
        Purchase purchase = purchaseService.getPurchaseById(id);
        if(purchase != null){
            return ResponseEntity.ok(purchase);
        }
        return ResponseEntity.notFound().build();
    }

    // POST a new purchase
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase addPurchase(@RequestBody Purchase purchase) {
        return purchaseService.createPurchase(purchase);
    }

    // PUT an updated purchase
    @PutMapping("/{id}")
    public Purchase updatePurchase(@PathVariable Long id, @RequestBody Purchase purchase) {
        return purchaseService.updatePurchase(id, purchase);
    }

    // DELETE a purchase
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
    }
}
