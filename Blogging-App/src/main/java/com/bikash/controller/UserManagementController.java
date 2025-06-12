package com.bikash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bikash.constant.AppConst;
import com.bikash.dto.LoginFormData;
import com.bikash.dto.RegisterFormData;
import com.bikash.service.IUserManagementService;

@Controller
public class UserManagementController {
	
	@Autowired
	private IUserManagementService userService;
	
	@GetMapping("/register")
	public String loadRegisterPage(@ModelAttribute("register") RegisterFormData register)
	{
		return "register";
	}
	
	@PostMapping("/register")
	public String registerData(@ModelAttribute("register") RegisterFormData register,RedirectAttributes red)
	{
		Boolean status=userService.registerUser(register);
		if(status)
		{
			red.addFlashAttribute(AppConst.STR_SUCCESS_MSG,AppConst.STR_ACC_REGISTER);
			return "redirect:/login";
		}
		red.addFlashAttribute(AppConst.STR_ERROR_MSG,AppConst.STR_ACC_EXIST);
		return "redirect:/register";
	}
	
	
	@GetMapping("/login")
	public String loadLoginPage(@ModelAttribute("login") LoginFormData login)
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String loginData(@ModelAttribute("login") LoginFormData login,RedirectAttributes red)
	{
		Boolean status=userService.loginUser(login);
		if(status)
		{
			return "redirect:/dashboard";
		}
		red.addFlashAttribute(AppConst.STR_ERROR_MSG,AppConst.STR_INVALID_CRED);
		return "redirect:/login";
	}
}
