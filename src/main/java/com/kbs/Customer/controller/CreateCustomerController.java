package com.kbs.Customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.Customer.model.Customer;
import com.kbs.Customer.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1/customer")
public class CreateCustomerController {
	private CustomerRepository customerRepository;

	public CreateCustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@PostMapping
	public ResponseEntity<Customer> handle(@RequestBody Customer customerData) {
		var customerCreated = this.customerRepository.save(customerData);

		return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
	}
}
