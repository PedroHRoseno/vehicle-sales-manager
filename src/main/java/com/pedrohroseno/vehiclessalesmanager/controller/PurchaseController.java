package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.exceptions.ResourceNotFoundException;
import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.Purchase;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.repository.CustomerRepository;
import com.pedrohroseno.vehiclessalesmanager.repository.PurchaseRepository;
import com.pedrohroseno.vehiclessalesmanager.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // Create a new purchase
    @PostMapping("")
    public ResponseEntity<?> createPurchase(@RequestBody Purchase purchase) {
        try {
            Vehicle vehicle = vehicleRepository.findById(purchase.getVehicle().getLicensePlate())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + purchase.getVehicle().getLicensePlate()));
            Customer customer = customerRepository.findById(purchase.getCustomer().getCpf())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with CPF " + purchase.getCustomer().getCpf()));

            purchase.setVehicle(vehicle);
            purchase.setCustomer(customer);

            Purchase newPurchase = purchaseRepository.save(purchase);
            return new ResponseEntity<>(newPurchase, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Retrieve all purchases
    @GetMapping("")
    public ResponseEntity<?> getAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    // Retrieve a purchase by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with id " + id));
        return new ResponseEntity<>(purchase, HttpStatus.OK);
    }

    // Update a purchase
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePurchase(@PathVariable Long id, @RequestBody Purchase purchaseDetails) {
        try {
            Purchase purchase = purchaseRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with id " + id));

            Vehicle vehicle = vehicleRepository.findById(purchaseDetails.getVehicle().getLicensePlate())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id " + purchaseDetails.getVehicle().getLicensePlate()));
            Customer customer = customerRepository.findById(purchaseDetails.getCustomer().getCpf())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with CPF " + purchaseDetails.getCustomer().getCpf()));

            purchase.setVehicle(vehicle);
            purchase.setCustomer(customer);
            purchase.setPurchasePrice(purchaseDetails.getPurchasePrice());
            purchase.setPurchaseDate(purchaseDetails.getPurchaseDate());

            Purchase updatedPurchase = purchaseRepository.save(purchase);
            return new ResponseEntity<>(updatedPurchase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a purchase
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePurchase(@PathVariable Long id) {
        try {
            Purchase purchase = purchaseRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Purchase not found with id " + id));

            purchaseRepository.delete(purchase);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
