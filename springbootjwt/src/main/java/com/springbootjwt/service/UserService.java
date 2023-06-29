package com.springbootjwt.service;

import com.springbootjwt.dto.LoginDTO;
import com.springbootjwt.dto.UserDTO;

public interface UserService
{
	  void registerUser(UserDTO userDTO);
	  String login(LoginDTO loginDTO);
}
