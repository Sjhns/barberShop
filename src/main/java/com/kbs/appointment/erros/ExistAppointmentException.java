package com.kbs.appointment.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistAppointmentException extends RuntimeException {
	public ExistAppointmentException(String message) {
		super(message);
	}
}
