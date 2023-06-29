package com.springbootjwt.dto;

import lombok.Data;

@Data
public class LoginDTO 
{
	private String emailAddress;

    private String password;
}
