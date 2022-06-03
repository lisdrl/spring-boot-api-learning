package br.com.lisdr.ecommerce.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import br.com.lisdr.ecommerce.exception.custom.BadRequestException;
import br.com.lisdr.ecommerce.exception.custom.InternalServerErrorException;
import br.com.lisdr.ecommerce.exception.custom.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFoundException (ResourceNotFoundException exception, WebRequest webRequest){
//		ErrorDetails errorDetails = new ErrorDetails(
//				HttpStatus.NOT_FOUND.value(), 
//				new Date(), 
//				exception.getMessage(), 
//				webRequest.getDescription(false));
//		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
//	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<?> handleResourceNotFoundException (ResourceNotFoundException exception, WebRequest webRequest){
//		ErrorDetails.builder()
		return ResponseEntity.status(exception.getStatus()).body(new ErrorDetails(exception.getStatus().value(), new Date(), exception.getMessage(), webRequest.getDescription(false)));
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorDetails> handleBadRequestException (BadRequestException exception, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(
				HttpStatus.BAD_REQUEST.value(), 
				new Date(), 
				exception.getMessage(), 
				webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<List<FormErrorDTO>> handle(MethodArgumentNotValidException exception) {
		List<FormErrorDTO> errorDtoList = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		for (FieldError error : fieldErrors) {
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			errorDtoList.add(new FormErrorDTO(new Date(), HttpStatus.BAD_REQUEST.value() ,error.getField(), message));
		}
		return ResponseEntity.badRequest().body(errorDtoList);
	}
	
////	Substitui a exceção InternalServerErrorException
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails> (errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	Substitui o ExceptionHandler genérico acima
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<ErrorDetails> handleInternalServerErrorException(InternalServerErrorException exception, WebRequest webRequest) {

		ErrorDetails errorDetails = new ErrorDetails(
				HttpStatus.INTERNAL_SERVER_ERROR.value(),
				new Date(), 
				exception.getMessage(),
				webRequest.getDescription(false)
		);
		
//		System.out.println(exception.printStackTrace());
		
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

