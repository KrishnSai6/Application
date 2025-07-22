
package com.example.demo.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.pojo.SignUpApiData;
import com.example.demo.repository.UserInterface;

@Service
public class AuthService {
	
	@Autowired
	public UserInterface userInterface;

    

   
	
	public User handleCreateAccount(SignUpApiData signUpApiData) throws Exception {
		
		  Optional<User> finduserOptional =  userInterface.findByEmail(signUpApiData.getEmail());
		  
		  if(finduserOptional.isEmpty()) {
			  
			  User user = new User();
			  
			  user.setEmail(signUpApiData.getEmail());
			  user.setName(signUpApiData.getName());
			  user.setPassword(signUpApiData.getPassword());
			  user.setPhoneNumber(signUpApiData.getPhoneNumber());
			  User newuser = userInterface.save(user);
			  return user;
			  
		  }else {
			  
			  throw new Exception("User Already Exits");
			  
			  
			  
		  }
	
		
		
	}
	
	

}
