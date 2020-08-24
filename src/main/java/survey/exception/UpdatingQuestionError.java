package survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdatingQuestionError extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public UpdatingQuestionError(String msg) {

		super(msg != null && !msg.equals("") ? msg : "Unsuccesful updating question!");
	}
}
