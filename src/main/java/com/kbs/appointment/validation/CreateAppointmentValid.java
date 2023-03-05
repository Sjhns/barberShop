package com.kbs.appointment.validation;

import java.time.LocalDate;
import java.time.LocalTime;

import com.kbs.Customer.model.Customer;
import com.kbs.barber.models.Barber;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAppointmentValid {

	@NotBlank(message = "Campo obrigatório")
	private String service;

	@NotNull
	private Customer customer;

	@NotNull
	private Barber barber;

	@FutureOrPresent(message = "Data inválida")
	private LocalDate startDate;

	@NotNull
	private LocalTime startTime;

	@FutureOrPresent(message = "Data inválida")
	private LocalDate endDate;

	@NotNull
	private LocalTime endTime;
}