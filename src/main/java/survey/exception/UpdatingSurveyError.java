package survey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UpdatingSurveyError extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public UpdatingSurveyError(String msg) {

		super(msg != null && !msg.equals("") ? msg : "Unsuccesful updating survey!");
	}
}
