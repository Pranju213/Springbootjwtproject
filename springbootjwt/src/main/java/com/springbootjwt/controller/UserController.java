package com.springbootjwt.controller;
//UserController defines two endpoints for 
//registering users and logins that can be 
//accessed by anyone without authentication
//UserController is calling the UserService method for 
//registration & login purposes so let's create a 
//service package with the UserService.java interface.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjwt.dto.LoginDTO;
import com.springbootjwt.dto.ResponseDTO;
import com.springbootjwt.dto.UserDTO;
import com.springbootjwt.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> 
    	addUser(@RequestBody UserDTO userDTO){
        
        userService.registerUser(userDTO);
        return new ResponseEntity<>
        	(ResponseDTO.builder()
            	.responseMsg("User register successfully.")
				.build(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> 
    	login(@RequestBody LoginDTO loginDTO){
        
        String token = userService.login(loginDTO);
        return new ResponseEntity<>
        	(ResponseDTO.builder()
            	.json(token)
                .responseMsg("Login successful.")
				.build(), HttpStatus.OK);
    }
}
