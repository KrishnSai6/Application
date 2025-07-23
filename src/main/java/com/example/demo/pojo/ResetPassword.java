package com.example.demo.pojo;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
public class ResetPassword {
	
	@NotNull
	private String linkeId;
	
	@NotNull
	@Size(min = 8, message="password is not Strong")
	private String password;
	
	
	@NotNull
	@Size(min = 8, message="password is not Strong")
	private String conformPassword;


	public String getLinkeId() {
		return linkeId;
	}


	public void setLinkeId(String linkeId) {
		this.linkeId = linkeId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConformPassword() {
		return conformPassword;
	}


	public void setConformPassword(String conformPassword) {
		this.conformPassword = conformPassword;
	}


	

}
