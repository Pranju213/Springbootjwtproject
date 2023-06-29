package com.springbootjwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootjwt.dto.ResponseDTO;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Logger logger= 
    	LoggerFactory.getLogger(ProductController.class);

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<ResponseDTO> addProduct(){
     
    //NOTE This method is just to check add product 
    	//is called or not.
        
     logger.info("Add product called successfully.");
     
     return new ResponseEntity<>(
        ResponseDTO.builder().responseMsg("Product Added.")
		.build(), HttpStatus.CREATED);
    }
}
