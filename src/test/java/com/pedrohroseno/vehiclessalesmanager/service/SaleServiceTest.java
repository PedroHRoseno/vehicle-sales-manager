package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.repository.SaleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleService saleService;

    @Test
    public void testGetAllSales(){
        List<Sale> sales = new ArrayList<>();
        sales.add(buildSale());

        Mockito.when(saleRepository.findAll()).thenReturn(sales);

        List<Sale> result = saleService.getAllSales();

        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testGetOneSales(){
        Sale sale = buildSale();


        Mockito.when(saleRepository.findById(sale.getId())).thenReturn(Optional.of(sale));

        Sale result = saleService.getSaleById(sale.getId());

        Assert.assertEquals(sale.getId(), result.getId());
        Assert.assertEquals(sale.getSalePrice(), result.getSalePrice(), 123);
    }

    private Sale buildSale(){
        Sale sale = new Sale();
        sale.setId(1L);
        sale.setSalePrice(123);
        return sale;
    }
}
