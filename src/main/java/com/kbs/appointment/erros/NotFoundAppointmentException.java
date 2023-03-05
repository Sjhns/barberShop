package com.kbs.appointment.erros;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundAppointmentException extends RuntimeException {

	public NotFoundAppointmentException(String message) {
		super(message);

	}
}
