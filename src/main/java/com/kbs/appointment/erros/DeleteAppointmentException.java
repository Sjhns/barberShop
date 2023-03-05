package com.kbs.appointment.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class DeleteAppointmentException extends RuntimeException {
	public DeleteAppointmentException(String message) {
		super(message);
	}

}
