package com.kbs.middlewares;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kbs.appointment.erros.DeleteAppointmentException;
import com.kbs.appointment.erros.ExistAppointmentException;
import com.kbs.appointment.erros.NotFoundAppointmentException;

import lombok.Data;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseBody
	@ExceptionHandler(NotFoundAppointmentException.class)
	public ResponseEntity<MessageDefaultError> handleInvalidDateException(Exception error) {

		var messageError = new MessageDefaultError(error.getMessage(), HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<MessageDefaultError>(messageError, HttpStatus.NOT_FOUND);

	}

	@ResponseBody
	@ExceptionHandler(ExistAppointmentException.class)
	public ResponseEntity<MessageDefaultError> handleExistAppointmentException(Exception error) {

		var messageError = new MessageDefaultError(error.getMessage(), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<MessageDefaultError>(messageError, HttpStatus.BAD_REQUEST);

	}
	
	@ResponseBody
	@ExceptionHandler(DeleteAppointmentException.class)
	public ResponseEntity<MessageDefaultError> handleDeleteAppointmentException(Exception error) {

		var messageError = new MessageDefaultError(error.getMessage(), HttpStatus.BAD_GATEWAY.value());

		return new ResponseEntity<MessageDefaultError>(messageError, HttpStatus.BAD_GATEWAY);

	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MessageDefaultError> argumentsNotValid(MethodArgumentNotValidException error) {

		BindingResult result = error.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		StringBuilder messageErrorModel = new StringBuilder("Os campos seguites são inválidos/nulos: ");

		String fields = fieldErrors.stream().map(field -> " -> " + field.getField() + " <- ")
				.collect(Collectors.joining(","));
		messageErrorModel.append(fields);

		var messageError = new MessageDefaultError(messageErrorModel.toString(), HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<MessageDefaultError>(messageError, HttpStatus.BAD_REQUEST);

	}
}

@Data
class MessageDefaultError {
	private String message;
	private LocalDate data;
	private LocalTime time;
	private Integer status;

	public MessageDefaultError(String message, Integer status) {
		this.message = message;
		this.data = LocalDate.now();
		this.time = LocalTime.now();
		this.status = status;
	}

}