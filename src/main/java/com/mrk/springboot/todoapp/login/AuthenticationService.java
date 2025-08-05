package com.mrk.springboot.todoapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
	
	public boolean authenticate(String username , String password) {
		boolean validUser = username.equalsIgnoreCase("madhu");
		boolean validPass = password.equalsIgnoreCase("1234");
		
		return validUser && validPass ;
	}
}
