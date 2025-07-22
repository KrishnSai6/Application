/*
 Controller is to receive the request mapping like GET, POST, UPDTAE, DELETE
 stages to create API
 API Path
 receive Data -> name, email, password, mobile
 validate the data and throw exception
 check if the email already exits -> if exits then throw error
 if not exits then create a account
 
 */



package com.example.demo.controller;




import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.pojo.SignUpApiData;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;


@RestController
public class AuthController {
	
	@Autowired
    public AuthService authService;
	
	
	@PostMapping("/create-account")
	public ResponseEntity<Map<String, Object>> creatingAccount(@Valid @RequestBody SignUpApiData signUpApiData )throws Exception {
		
		
		Object userData = authService.handleCreateAccount(signUpApiData);
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("status", "success");
		mapData.put("data", userData);
		
    	return ResponseEntity.status(HttpStatus.OK).body(mapData);
		
	}

}
