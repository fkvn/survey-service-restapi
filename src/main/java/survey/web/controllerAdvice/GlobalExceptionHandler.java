package survey.web.controllerAdvice;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import survey.exception.UserNotFoundException;
import survey.util.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ TransactionSystemException.class, DataIntegrityViolationException.class })
	protected ResponseEntity<Object> handleConstraintViolationExceptions(Exception ex,
			WebRequest request) {

		ex.printStackTrace();

		ApiError apiError = new ApiError();
		apiError.setStatus(HttpStatus.BAD_REQUEST);
		apiError.setError(ex.getCause().getLocalizedMessage());
		apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);

		if (ex.getClass().equals(TransactionSystemException.class)) {

			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			Set<ConstraintViolation<?>> constraintViolations = null;

			if (cause instanceof ConstraintViolationException) {

				constraintViolations = ((ConstraintViolationException) cause).getConstraintViolations();
				String errorMessage = null;

				for (Iterator<ConstraintViolation<?>> it = constraintViolations.iterator(); it.hasNext();) {
					ConstraintViolation<?> cv = it.next();
					errorMessage = cv.getMessage();
				}

				apiError.setMessage(errorMessage);

			} else {

				apiError.setMessage(ex.getMessage());
			}
		}

		else if (ex.getClass().equals(DataIntegrityViolationException.class)) {
			apiError
					.setMessage(((DataIntegrityViolationException) ex).getRootCause().getLocalizedMessage());
		}

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({UserNotFoundException.class})
	protected ResponseEntity<Object> hanlderCustomExceptions(Exception ex, WebRequest request) {
		System.out.println("aaa " + ex.getCause().getMessage());
		
		ex.printStackTrace();

		ApiError apiError = new ApiError();

		if (ex.getClass().equals(UserNotFoundException.class))
			apiError.setStatus(HttpStatus.NOT_FOUND);
		
		else
			apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

		apiError.setError(ex.getClass().getSuperclass().getSimpleName());
		apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);
		apiError.setMessage(ex.getMessage());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}


//	@ExceptionHandler
//	protected ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
//		System.out.println("aaa " + ex.getClass().getSimpleName());
//		ex.printStackTrace();
//		
//		ApiError apiError = new ApiError();
//		apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//		apiError.setError(ex.getCause().getLocalizedMessage());
//		
//		apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);
//		apiError.setMessage("aa: " + ex.getLocalizedMessage());
//		
//		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
//	}

}
