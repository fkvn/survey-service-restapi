package survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidResponse extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public InvalidResponse(String msg) {

		super("Invalid Response. " + msg);
	}
}
