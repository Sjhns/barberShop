package com.kbs.barber.controller;

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

import com.kbs.appointment.model.Appointment;
import com.kbs.barber.models.Barber;
import com.kbs.barber.repository.BarberRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api/v1/barber")
public class GetBarberController {

	private BarberRepository barberRepository;

	public GetBarberController(BarberRepository barberRepository) {
		this.barberRepository = barberRepository;
	}

	@GetMapping
	public ResponseEntity<List<Barber>> handle() {
		var barbers = this.barberRepository.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(barbers);
	}
	
	@GetMapping("/details/{id}")
	public ResponseEntity<Object> getOne(@PathVariable("id") UUID id) {
		Optional<Barber> result = this.barberRepository.findById(id);

		if (!result.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body("Bartbeiro não existe.");
		}
		
		Barber barber = result.get();

		List<Appointment> appointments = barber.getAppointments();

		List<BarberAppointmentDTO> appointmentDTOs = appointments.stream().map(
										appointment -> new BarberAppointmentDTO(
										appointment.getService(), 
										appointment.getCustomer().getName(),
										appointment.getStartDate(), appointment.getStartTime(), 
										appointment.getEndDate(), appointment.getEndTime())).collect(Collectors.toList());

		BarberDTO dataCustomer = new BarberDTO(barber.getId(), barber.getName(),
												barber.getLastname(), barber.getEmail(), 
												barber.getPhoneNumber(), appointmentDTOs);

		return ResponseEntity.status(HttpStatus.OK).body(dataCustomer);

	}
}

@Data
@AllArgsConstructor
class BarberAppointmentDTO {
	private String service;

	private String customer;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime startTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime endTime;

}

@Data
@AllArgsConstructor
class BarberDTO {
	private UUID id;

	private String name;
	private String lastname;

	private String phoneNumber;

	private String email;

	private List<BarberAppointmentDTO> appointments;
}
