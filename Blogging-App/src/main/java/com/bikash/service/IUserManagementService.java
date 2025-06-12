package com.bikash.service;


import com.bikash.dto.LoginFormData;
import com.bikash.dto.RegisterFormData;

public interface IUserManagementService {
	public boolean registerUser(RegisterFormData register);
	public boolean loginUser(LoginFormData login);
}
