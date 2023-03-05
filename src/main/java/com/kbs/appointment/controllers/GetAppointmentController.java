package com.kbs.appointment.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.appointment.DTO.AppointmentDTO;
import com.kbs.appointment.DTO.ConvertToDTO;
import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.services.GetAppointment;

@RestController
@RequestMapping("/api/v1/appointment")
public class GetAppointmentController {
	private GetAppointment getAppointmentService;

	public GetAppointmentController(GetAppointment getAppointmentService) {
		this.getAppointmentService = getAppointmentService;
	}

	@GetMapping
	public ResponseEntity<List<AppointmentDTO>> getAll(Appointment appointment) {
		List<Appointment> appointments = this.getAppointmentService.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(ConvertToDTO.all(appointments));

	}

	@GetMapping("/details/{id}")
	public ResponseEntity<AppointmentDTO> getOne(@PathVariable("id") UUID id) {
		var appointment = this.getAppointmentService.getOne(id);

		return ResponseEntity.status(HttpStatus.OK).body(ConvertToDTO.one(appointment));
	}
}
