
package com.example.demo.service;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.pojo.ForGotPasswordApi;
import com.example.demo.pojo.LoginApiData;
import com.example.demo.pojo.ResetPassword;
import com.example.demo.pojo.SignUpApiData;
import com.example.demo.repository.UserInterface;

import jakarta.validation.Valid;

@Service
public class AuthService {

   
	
	@Autowired
	public UserInterface userInterface;
	
	@Autowired
	public EmailService emailService;
	
	
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

   
	
	public User handleCreateAccount(SignUpApiData signUpApiData) throws Exception {
		
		  Optional<User> finduserOptional =  userInterface.findByEmail(signUpApiData.getEmail());
		  
		  if(finduserOptional.isEmpty()) {
			  
			  User user = new User();
			  
			  user.setEmail(signUpApiData.getEmail());
			  user.setName(signUpApiData.getName());
			  user.setPassword(passwordEncoder.encode(signUpApiData.getPassword()));
			  user.setPhoneNumber(signUpApiData.getPhoneNumber());
			  User newuser = userInterface.save(user);
			  return user;
		  }else {
			  throw new Exception("User Already Exits");  
		  }	
	}
	
	
	public User loginService(LoginApiData loginApiData) throws Exception {
		
		Optional<User> dbemail =  userInterface.findByEmail(loginApiData.getEmail());
		
		if(dbemail.isEmpty()) {
			throw new Exception("Email is not register");
		}else {
			
			User userdata = dbemail.get();
			Boolean isMatchingBoolean = passwordEncoder.matches(loginApiData.getPassword(), userdata.getPassword());
			if(isMatchingBoolean == true) {
				return userdata;
				
			}else {
				throw new Exception("password is not matching");
			}
		}
	}
	
	//for got password
	public void forgotPasswordSeervice(ForGotPasswordApi forGotPasswordApi) throws Exception {
		
		Optional<User> dbemail =  userInterface.findByEmail(forGotPasswordApi.getEmail());
		
		if(dbemail.isEmpty()) {
			throw new Exception("Email does not exits please Do SignUp");
			
			
		}else {
			User userdata = dbemail.get();
			userdata.setPasswordResetKet(UUID.randomUUID().toString());
			userInterface.save(userdata);
			String mailBodyString = "Hi"+userdata.getName()+"<br/>"+
			                        "please Reset you password <br/>"+
					                 "please <a href='http://localhost:8080/reset-password-ui?link"+userdata.getPasswordResetKet()+"'>Click Here</a>"+
			                          "<b>Regards<br/>Spring boot app</b>";
			emailService.sendHtmlEmail("krishnasaimec@gmail.com", userdata.getEmail(), "password Reset link", mailBodyString);
		}		
	}
	
	public void resetPassword(ResetPassword resetPassword) throws Exception {
		
		if(resetPassword.getPassword().equals(resetPassword.getConformPassword()) == false) {
			throw new Exception("password and Confirm password is not matching");
			
		}else {
			 Optional<User> userLinkkey = userInterface.findByPasswordResetKet(resetPassword.getLinkeId());
			 
			 if(userLinkkey.isEmpty()) {
				 throw new Exception("invalid url or link got expired");
				 
			 }else {
				 User user = userLinkkey.get();
				 user.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
				 user.setPasswordResetKet("");
				 
				 userInterface.save(user);
				 
			 }
			 
			
		} 
		
	}


	
}
