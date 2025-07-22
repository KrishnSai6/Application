package com.example.demo.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	/*
	 this exception will handle the validate error which is in the pojo class
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleApiInputDataValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errors.put(error.getField(), error.getDefaultMessage());
		});
		
		Map<String, Object> responceMap = new HashMap<String, Object>();
		responceMap.put("result", "failed");
		responceMap.put("error", errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responceMap);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex){

			Map<String,String> resMap = new HashMap<String, String>();
			resMap.put("message", ex.getMessage());
			resMap.put("status", "failed");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resMap);
	}

}
