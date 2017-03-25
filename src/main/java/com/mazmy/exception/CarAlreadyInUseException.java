package com.mazmy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "The selected car already in use.")
public class CarAlreadyInUseException extends Exception {

	private static final long serialVersionUID = -1111853904L;

	public CarAlreadyInUseException(String message) {
		super(message);
	}

}
