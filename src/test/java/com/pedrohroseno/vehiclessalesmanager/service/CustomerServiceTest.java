package com.pedrohroseno.vehiclessalesmanager.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.pedrohroseno.vehiclessalesmanager.model.Address;
import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080); // No-args constructor defaults to port 8080


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

    @Test
    public void testCreateOneCustomer(){
        Customer customer = buildCustomer();

        wireMockRule.stubFor(get("/ws/"+ customer.getAddress().getZipCode() +"/json")
                .withHeader("Content-Type", containing("application/json"))
                .willReturn(ok()
                        .withHeader("Content-Type","application/json")
                        .withBody("{\"logradouro\":\"Rua Exemplo\"}")));

        customerService.addCustomer(customer);

        wireMockRule.verify(getRequestedFor(urlEqualTo("/ws/"+ customer.getAddress().getZipCode() +"/json"))
                .withHeader("Content-Type", equalTo("application/json")));

    }

    private Customer buildCustomer() {
        Customer customer = new Customer();
        customer.setCpf("10999538470");
        customer.setName("Pedro Roseno");
        customer.setAddress(new Address());
        customer.getAddress().setId(1L);
        customer.getAddress().setZipCode("55012010");
        return customer;
    }

}
