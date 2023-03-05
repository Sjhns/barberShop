package com.kbs.appointment.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.services.CreateAppointment;
import com.kbs.appointment.validation.CreateAppointmentValid;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/appointment")
public class CreateAppointmentController {
	private CreateAppointment createAppointmentService;

	public CreateAppointmentController(CreateAppointment createAppointmentService) {
		this.createAppointmentService = createAppointmentService;
	}

	@PostMapping
	public ResponseEntity<Appointment> handle(@RequestBody @Valid CreateAppointmentValid appointmentValid) {

		Appointment newAppointment = new Appointment();

		BeanUtils.copyProperties(appointmentValid, newAppointment);

		var appointmentCreated = this.createAppointmentService.execute(newAppointment);

		return ResponseEntity.status(HttpStatus.CREATED).body(appointmentCreated);
	}
}
