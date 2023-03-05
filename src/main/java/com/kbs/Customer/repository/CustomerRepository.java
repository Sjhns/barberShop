package com.kbs.Customer.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbs.Customer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

}
