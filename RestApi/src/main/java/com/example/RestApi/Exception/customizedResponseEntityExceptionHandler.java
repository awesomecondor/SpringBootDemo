package com.example.RestApi.Exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class customizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails error = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
		ErrorDetails error = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);

	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails error = new ErrorDetails(LocalDate.now()," Total error count  " +ex.getErrorCount() + "  First Error: " + ex.getFieldError().getDefaultMessage(), request.getDescription(false));

		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}

