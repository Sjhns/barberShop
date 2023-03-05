package com.kbs.appointment.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.appointment.DTO.AppointmentDTO;
import com.kbs.appointment.DTO.ConvertToDTO;
import com.kbs.appointment.model.Appointment;
import com.kbs.appointment.services.UpdateAppointment;
import com.kbs.appointment.validation.UpdateAppointmentValid;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/appointment")
public class UpdateAppointmentController {
	private UpdateAppointment updateAppointmentService;

	public UpdateAppointmentController(UpdateAppointment updateAppointmentService) {
		this.updateAppointmentService = updateAppointmentService;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AppointmentDTO> handle(@PathVariable UUID id,
			@RequestBody @Valid UpdateAppointmentValid appointmentValid) {
		Appointment result = this.updateAppointmentService.execute(id, appointmentValid);
		
		return ResponseEntity.status(HttpStatus.OK).body(ConvertToDTO.one(result));
	}
}
