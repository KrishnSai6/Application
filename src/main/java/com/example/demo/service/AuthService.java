
package com.example.demo.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.pojo.LoginApiData;
import com.example.demo.pojo.SignUpApiData;
import com.example.demo.repository.UserInterface;

@Service
public class AuthService {
	
	@Autowired
	public UserInterface userInterface;
	
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
}
