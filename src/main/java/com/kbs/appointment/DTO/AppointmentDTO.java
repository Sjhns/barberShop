package com.kbs.appointment.DTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentDTO {
	private UUID id;

	private String service;

	private LocalDate startDate;

	private LocalTime startTime;

	private LocalDate endDate;
	private LocalTime endTime;

	private AppointmentBarberDTO barber;
	private AppointmentCustomerDTO customer;
}
