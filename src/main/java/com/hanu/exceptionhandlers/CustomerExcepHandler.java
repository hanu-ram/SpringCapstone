package com.hanu.exceptionhandlers;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerExcepHandler {
	
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<?> custNotFoundHandler(CustomerNotFoundException c)
	
	{
		String msg=c.getMsg();	
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(value = AccountIsNotFoundException.class)
	public ResponseEntity<?> custNotFoundHandler(AccountIsNotFoundException c)
	{
		String msg=c.getMsg();	
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(value =HttpMessageNotReadableException.class)
	public ResponseEntity<?> handler(HttpMessageNotReadableException e)
	{
		return new ResponseEntity<>("!!No employee object is given!!",HttpStatus.NOT_ACCEPTABLE);
	}
	@ExceptionHandler(value =NoDataException.class)
	public ResponseEntity<?> noDataHandler(NoDataException c)
	{
		String msg = c.getMsg();
		return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> methodNotSupportedHandler(HttpRequestMethodNotSupportedException c)
	{
		
		return new ResponseEntity<>("Method is incorrect, select appropriate method",HttpStatus.METHOD_NOT_ALLOWED);
	}
	@ExceptionHandler(value=TransferFailedExceptoin.class)
	public ResponseEntity<?>transferFailedHandler(TransferFailedExceptoin c)
	{
		String msg = c.getMsg();
		return new ResponseEntity<>(msg,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<?> internalServerError(DataIntegrityViolationException e)
	{
		return new ResponseEntity<>("cannot delte the account",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
