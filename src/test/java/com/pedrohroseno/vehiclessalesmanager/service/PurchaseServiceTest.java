package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Purchase;
import com.pedrohroseno.vehiclessalesmanager.repository.PurchaseRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    @Test
    public void testGetAllPurchases(){
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(buildPurchase());

        Mockito.when(purchaseService.getAllPurchases()).thenReturn(purchases);

        List<Purchase> result = purchaseService.getAllPurchases();

        Assert.assertEquals(1L, result.size());

    }

    @Test
    public void testGetOnePurchases(){
        Purchase purchase = buildPurchase();

        Mockito.when(purchaseService.getPurchaseById(purchase.getId())).thenReturn(purchase);

        Purchase result = purchaseService.getPurchaseById(purchase.getId());

        Assert.assertEquals(purchase.getId(), result.getId());
        Assert.assertEquals(purchase.getPurchaseDate(), result.getPurchaseDate());

    }

    private Purchase buildPurchase(){
        Purchase purchase = new Purchase();
        purchase.setId(1L);
        purchase.setPurchaseDate(Date.from(Instant.now()));
        return purchase;
    }


}
