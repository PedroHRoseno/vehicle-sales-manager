package com.pedrohroseno.vehiclessalesmanager.service;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerByCpf(String cpf) {
        return customerRepository.findById(cpf).orElse(null);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomerByCpf(String cpf) {
        customerRepository.deleteById(cpf);
    }
}

