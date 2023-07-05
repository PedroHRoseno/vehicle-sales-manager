package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers(){
        List<Customer> customers = new ArrayList<>();
        Customer customer = buildCustomer();
        customers.add(customer);

        Mockito.when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerService.getAllCustomers();

        Assert.assertEquals(1, result.size());

    }

    @Test
    public void testGetOneCustomers(){
        Customer customer = buildCustomer();

        Mockito.when(customerRepository.findById(customer.getCpf())).thenReturn(Optional.of(customer));

        Customer result = customerService.getCustomerByCpf(customer.getCpf());

        Assert.assertEquals(customer.getCpf(), result. getCpf());
        Assert.assertEquals(customer.getName(), result. getName());

    }

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setCpf("10999538470");
        customer.setName("Pedro Roseno");
        return customer;
    }

}
