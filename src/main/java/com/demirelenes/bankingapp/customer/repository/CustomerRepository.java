package com.demirelenes.bankingapp.customer.repository;

import com.demirelenes.bankingapp.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
