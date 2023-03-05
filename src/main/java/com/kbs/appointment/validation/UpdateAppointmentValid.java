package com.kbs.appointment.validation;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAppointmentValid {
	@NotBlank(message = "Campo obrigatório")
	private String service;

	@FutureOrPresent(message = "Data inválida")
	private LocalDate startDate;

	@NotNull
	private LocalTime startTime;

	@FutureOrPresent(message = "Data inválida")
	private LocalDate endDate;

	@NotNull
	private LocalTime endTime;
}
