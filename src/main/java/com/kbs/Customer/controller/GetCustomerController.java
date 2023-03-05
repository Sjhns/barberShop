package com.kbs.Customer.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.Customer.model.Customer;
import com.kbs.Customer.repository.CustomerRepository;
import com.kbs.appointment.model.Appointment;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api/v1/customer")
public class GetCustomerController {
	private CustomerRepository customerRepository;

	public GetCustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@GetMapping
	public ResponseEntity<List<Customer>> handle() {
		var customers = this.customerRepository.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}

	@GetMapping("/details/{id}")
	public ResponseEntity<Object> getOne(@PathVariable("id") UUID id) {
		Optional<Customer> result = this.customerRepository.findById(id);

		if (!result.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body("Usuario não existe.");
		}

		List<Appointment> appointments = result.get().getAppointments();

		List<CustomerAppointmentDTO> appointmentDTOs = appointments.stream()
				.map(appointment -> new CustomerAppointmentDTO(
										appointment.getService(), 
										appointment.getBarber().getName(),
										appointment.getStartDate(), appointment.getStartTime(), 
										appointment.getEndDate(), appointment.getEndTime()))
				.collect(Collectors.toList());

		CustomerDTO dataCustomer = new CustomerDTO(result.get().getId(), result.get().getName(),
				result.get().getLastname(), result.get().getEmail(), result.get().getPhoneNumber(), appointmentDTOs);

		return ResponseEntity.status(HttpStatus.OK).body(dataCustomer);

	}

}

@Data
@AllArgsConstructor
class CustomerDTO {
	private UUID id;

	private String name;

	private String lastname;

	private String email;

	private String phoneNumber;

	private List<CustomerAppointmentDTO> appointments;

}

@Data
@AllArgsConstructor
class CustomerAppointmentDTO {
	private String service;

	private String barber;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime endTime;

}
