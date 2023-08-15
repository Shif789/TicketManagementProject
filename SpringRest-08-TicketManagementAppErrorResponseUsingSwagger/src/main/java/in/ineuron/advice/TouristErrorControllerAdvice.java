package in.ineuron.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.ineuron.error.ErrorDetails;
import in.ineuron.exception.TouristNotFoundException;

@RestControllerAdvice
public class TouristErrorControllerAdvice {

	@ExceptionHandler(TouristNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTouristNotFound(TouristNotFoundException tException) {
		System.out.println("TouristErrorControllerAdvice.handleTouristNotFound()");

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), tException.getMessage(), "404-Not Found");
		ResponseEntity<ErrorDetails> responseEntity = new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		return responseEntity;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllProblems(Exception exception) {
		System.out.println("TouristErrorControllerAdvice.handleAllProblems()");
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
				"problem in execution");
		ResponseEntity<ErrorDetails> responseEntity = new ResponseEntity<>(errorDetails,
				HttpStatus.INTERNAL_SERVER_ERROR);
		
		return responseEntity;
	}
}
