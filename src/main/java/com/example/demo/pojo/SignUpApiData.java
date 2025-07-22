package com.example.demo.pojo;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpApiData {
	
	@NotNull
	@Size(min = 2)
	private String name;
	
	
	
	@NotNull
	@Size(min = 6)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;
	
	
	@NotNull
	@Size(min=10, max = 10)
	@Pattern(regexp="^\\d{10}$")
	private String phoneNumber;
	
	
	@NotNull
	@Size(min = 8, message="password is not Strong")
	private String password;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
