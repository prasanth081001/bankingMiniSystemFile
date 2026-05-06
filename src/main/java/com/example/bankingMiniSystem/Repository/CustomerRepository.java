package com.example.bankingMiniSystem.Repository;

import com.example.bankingMiniSystem.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
