package com.pedrohroseno.vehiclessalesmanager.repository;

import com.pedrohroseno.vehiclessalesmanager.Utils;
import com.pedrohroseno.vehiclessalesmanager.model.Address;
import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import com.pedrohroseno.vehiclessalesmanager.model.Vehicle;
import com.pedrohroseno.vehiclessalesmanager.model.dtos.SaleBrandDTO;
import com.pedrohroseno.vehiclessalesmanager.model.enums.VehicleBrand;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SaleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SaleRepository saleRepository;

    @Test
    public void shouldReturnCustomersVehicleByCpf(){

        Sale sale = Utils.buildSale();
        entityManager.persist(sale.getCustomer().getAddress());
        entityManager.persist(sale.getCustomer());
        entityManager.persist(sale.getVehicle());
        entityManager.persist(sale);
        entityManager.flush();

        List<SaleBrandDTO> saleBrandDTOS =  saleRepository.salesPerBrand();

        Assertions.assertEquals(1, saleBrandDTOS.size());
        Assertions.assertEquals(VehicleBrand.FERRARI, saleBrandDTOS.get(0).getVehicleBrand());
        Assertions.assertEquals(1, saleBrandDTOS.get(0).getSalesNumber());

    }


}
