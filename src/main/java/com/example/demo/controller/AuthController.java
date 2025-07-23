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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.LoginApiData;
import com.example.demo.pojo.SignUpApiData;
import com.example.demo.service.AuthService;
import com.example.demo.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;


@RestController
public class AuthController {
	
	@Autowired
    public AuthService authService;
	
	@Autowired
	public EmailService emailService;
	
	
	
	@PostMapping("/create-account")
	public ResponseEntity<Map<String, Object>> creatingAccount(@Valid @RequestBody SignUpApiData signUpApiData )throws Exception {
		
		
		Object userData = authService.handleCreateAccount(signUpApiData);
		
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("status", "success");
		mapData.put("data", userData);
		
    	return ResponseEntity.status(HttpStatus.OK).body(mapData);
		
	}
	/*
	 get email and password from pojo
	 create login postmapping url ->receive data of email and password
	 database query
	 create a service where it will check password email is present email and password is present
	 */
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> postMethodName(@Valid @RequestBody LoginApiData loginApiData) throws Exception {
		//TODO: process POST request
		
		Object bdData = authService.loginService(loginApiData);

		Map<String, Object> userData = new HashMap<String, Object>();
		userData.put("status", "success");
		userData.put("Data", bdData);
		return ResponseEntity.status(HttpStatus.OK).body(userData);
	}
	
	@GetMapping("/sendplainmail")
	public ResponseEntity<?> sendPlainMail(){
		String fromMail = "";
		String toMailString = "";
		String subjectString = "";
		String mailBodyString = "";
		emailService.sendPlainEmail(fromMail, toMailString, subjectString, mailBodyString);
		Map<String,String> responseDataMap = new HashMap<String, String>();
		responseDataMap.put("status", "success");
		responseDataMap.put("message","Email send");
		return ResponseEntity.status(HttpStatus.OK).body(responseDataMap);
	}
	
	@GetMapping("/sendhtmlmail")
	public ResponseEntity<?> sendHtmlMail() throws Exception{
		String fromMail = "";
		String toMailString = "";
		String subjectString = "";
		String mailBodyString = "";
		mailBodyString = "hi this is Krishna, </br>"+
		                 "this is html page mailer</br>"+
				         "<a href = ''></br>";
		emailService.sendHtmlEmail(fromMail, toMailString, subjectString,mailBodyString);
		Map<String,String> responseDataMap = new HashMap<String, String>();
		responseDataMap.put("status", "success");
		responseDataMap.put("message","Email send");
		return ResponseEntity.status(HttpStatus.OK).body(responseDataMap);
	}
	
	@GetMapping("/sendTempmail")
	public ResponseEntity<?> sendTempMail() throws Exception{
		String fromMail = ""; //give from mail
		String toMailString = ""; //give to mail
		String subjectString = "Test mail from krishna";
		String mailBodyString = "";
		
		emailService.sendTempleteEmail(fromMail, toMailString, subjectString, "test-mail"); //test-mail in resources template
		Map<String,String> responseDataMap = new HashMap<String, String>();
		responseDataMap.put("status", "success");
		responseDataMap.put("message","Email send");
		return ResponseEntity.status(HttpStatus.OK).body(responseDataMap);
	}
	

}
