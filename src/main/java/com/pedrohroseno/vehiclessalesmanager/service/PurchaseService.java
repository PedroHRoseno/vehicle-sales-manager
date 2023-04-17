package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.exceptions.PurchaseNotFoundException;
import com.pedrohroseno.vehiclessalesmanager.model.Purchase;
import com.pedrohroseno.vehiclessalesmanager.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseById(Long id) {
        return purchaseRepository.findById(id)
                .orElseThrow(() -> new PurchaseNotFoundException("Purchase not found with id " + id));
    }

    public Purchase createPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(Long id, Purchase purchase) {
        Purchase existingPurchase = getPurchaseById(id);
        existingPurchase.setVehicle(purchase.getVehicle());
        existingPurchase.setCustomer(purchase.getCustomer());
        existingPurchase.setPurchasePrice(purchase.getPurchasePrice());
        existingPurchase.setPurchaseDate(purchase.getPurchaseDate());
        return purchaseRepository.save(existingPurchase);
    }

    public void deletePurchase(Long id) {
        Purchase purchase = getPurchaseById(id);
        purchaseRepository.delete(purchase);
    }

}