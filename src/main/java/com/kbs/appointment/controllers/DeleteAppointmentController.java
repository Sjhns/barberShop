package com.kbs.appointment.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.appointment.services.DeleteAppointment;

@RestController
@RequestMapping("/api/v1/appointment")
public class DeleteAppointmentController {

	private DeleteAppointment deleteAppointmentService;

	public DeleteAppointmentController(DeleteAppointment deleteAppointmentService) {
		this.deleteAppointmentService = deleteAppointmentService;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> handle(@PathVariable UUID id) {
		String result = this.deleteAppointmentService.execute(id);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
