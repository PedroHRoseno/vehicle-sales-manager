package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.clients.ViaCepClient;
import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.ViaCepResponse;
import com.pedrohroseno.vehiclessalesmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ViaCepClient viaCepClient;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByCpf(String cpf) {
        return customerRepository.findById(cpf).orElse(null);
    }

    public ViaCepResponse getDataFromCustomerAddress(String cep){
        return viaCepClient.getDataFromViaCep(cep);
    }

    public void addCustomer(Customer customer) {
        if (customer.getAddress().getStreetName() == null && customer.getAddress().getZipCode() != null){
            ViaCepResponse viaCepResponse = getDataFromCustomerAddress(customer.getAddress().getZipCode());
            customer.getAddress().setStreetName(viaCepResponse.getLogradouro());
            customer.setAddress(
                    customer.getAddress()
            );
        }
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomerByCpf(String cpf) {
        customerRepository.deleteById(cpf);
    }
}

