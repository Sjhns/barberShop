package com.kbs.barber.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbs.barber.models.Barber;
import com.kbs.barber.repository.BarberRepository;

@RestController
@RequestMapping("/api/v1/barber")
public class CreateBarberController {
	private BarberRepository barberRepository;

	public CreateBarberController(BarberRepository barberRepository) {
		this.barberRepository = barberRepository;
	}

	@PostMapping
	public ResponseEntity<Barber> handle(@RequestBody Barber barberData) {
		var barberCreated = this.barberRepository.save(barberData);

		return ResponseEntity.status(HttpStatus.CREATED).body(barberCreated);
	}

}
