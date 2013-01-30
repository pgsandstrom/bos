package se.persandstrom.bos.internal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411591125212167176L;
	
	public InvalidDataException() {
		super();
	}
	
	public InvalidDataException(String message) {
		super(message);
	}

}
