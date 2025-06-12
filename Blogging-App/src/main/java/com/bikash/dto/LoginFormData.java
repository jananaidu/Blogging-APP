package com.bikash.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LoginFormData {
	
	@Column(length = 50)
	private String email;
	
	@Column(length = 50)
	private String password;
}
