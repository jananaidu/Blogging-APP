package com.bikash.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikash.constant.AppConst;
import com.bikash.dto.LoginFormData;
import com.bikash.dto.RegisterFormData;
import com.bikash.entity.Account;
import com.bikash.repository.IAcconutRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserManagementServiceImpl implements IUserManagementService {

	@Autowired
	private IAcconutRepo accountRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean registerUser(RegisterFormData register) {
		Account user=accountRepo.findByEmail(register.getEmail());
		if(user==null)
		{
			Account acc=new Account();
			BeanUtils.copyProperties(register,acc);
			accountRepo.save(acc);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean loginUser(LoginFormData login) {
		Account user=accountRepo.findByEmail(login.getEmail());
		if(user!=null)
		{
			if(user.getPassword().equals(login.getPassword()))
			{
				//Keep user id in session for session management
				int id=user.getUserId();
				session.setAttribute(AppConst.STR_USER_ID,id);
				return true;
			}
		}
		return false;
	}

}
