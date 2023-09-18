package com.pedrohroseno.vehiclessalesmanager.controller;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import com.pedrohroseno.vehiclessalesmanager.model.ViaCepResponse;
import com.pedrohroseno.vehiclessalesmanager.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/search")
    public ResponseEntity<Customer> getCustomer(@RequestParam("cpf")final String cpf) {
        Customer customer = customerService.getCustomerByCpf(cpf);
        if(customer != null){
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{cpf}")
    public void updateCustomer(@PathVariable String cpf, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerByCpf(cpf);
        if (existingCustomer != null) {
            customer.setCpf(cpf);
            customerService.updateCustomer(customer);
        }
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deleteCustomerByCpf(@PathVariable String cpf) {
        customerService.deleteCustomerByCpf(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
