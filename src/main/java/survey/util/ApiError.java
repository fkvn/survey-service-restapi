package survey.util;

import java.util.Date;

import org.springframework.http.HttpStatus;


public class ApiError {

	private Date timestamp;

	private HttpStatus status;

	private String error;

	private String message;

	private String path;

	public ApiError(HttpStatus status, String error, String message, String path) {

		super();
		this.timestamp = new Date();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public ApiError() {

		super();
		this.timestamp = new Date();
	}


	public Date getTimestamp() {

		return timestamp;
	}


	public void setTimestamp(Date timestamp) {

		this.timestamp = timestamp;
	}


	public HttpStatus getStatus() {

		return status;
	}


	public void setStatus(HttpStatus status) {

		this.status = status;
	}


	public String getError() {

		return error;
	}


	public void setError(String error) {

		this.error = error;
	}


	public String getMessage() {

		return message;
	}


	public void setMessage(String message) {

		this.message = message;
	}


	public String getPath() {

		return path;
	}


	public void setPath(String path) {

		this.path = path;
	}
}
