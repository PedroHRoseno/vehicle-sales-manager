package com.pedrohroseno.vehiclessalesmanager.repository;

import com.pedrohroseno.vehiclessalesmanager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
