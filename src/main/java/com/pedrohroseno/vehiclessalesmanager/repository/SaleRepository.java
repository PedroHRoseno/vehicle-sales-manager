package com.pedrohroseno.vehiclessalesmanager.repository;

import com.pedrohroseno.vehiclessalesmanager.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
