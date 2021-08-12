package survey.web.controllerAdvice;

import java.text.ParseException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import survey.exception.UserNotFoundException;
import survey.model.core.User;
import survey.util.ApiError;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ModelAttribute("sub")
	protected String extractUserFromJWT(HttpServletRequest request) throws ParseException {

//		System.out.println("checking");
//		String sub = "";
		String sub = "testing user";

//		if (request.getHeader("Authorization") == null
//				|| request.getHeader("Authorization").length() == 0
//				|| request.getHeader("Authorization").equals("null")) {
//			return "";
//		}
//		
//		else {
////			System.out.println("authorization");
////			System.out.println(request.getHeader("Authorization"));
//			
//			String token = request.getHeader("Authorization").split(" ")[1]; // get jwt from header
//			String encodedPayload = token.split("\\.")[1]; // get second encoded part in jwt
//			Base64 base64Url = new Base64(true);
//			String payload = new String(base64Url.decode(encodedPayload));
//
//			JSONParser parser = new JSONParser();
//			JSONObject claimsObj = null;
//			
//			
//			try {
//				claimsObj = (JSONObject) parser.parse(payload);
//			} catch (org.json.simple.parser.ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			sub = claimsObj.get("sub").toString();
//
//			if (sub == null || sub.length() == 0)
//				throw new AccessDeniedException("405 returned");
//		}

		return sub;
	}

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

	@ExceptionHandler({ UserNotFoundException.class })
	protected ResponseEntity<Object> hanlderCustomExceptions(Exception ex, WebRequest request) {

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


	// @ExceptionHandler
	// protected ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
	// System.out.println("aaa " + ex.getClass().getSimpleName());
	// ex.printStackTrace();
	//
	// ApiError apiError = new ApiError();
	// apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
	// apiError.setError(ex.getCause().getLocalizedMessage());
	//
	// apiError.setPath(request.getDescription(true).split(";")[0].split("=")[1]);
	// apiError.setMessage("aa: " + ex.getLocalizedMessage());
	//
	// return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	// }

}
