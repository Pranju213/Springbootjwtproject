package com.springbootjwt.dto;

import lombok.Data;
@Data
public class Principal {

    private String emailAddress;

    private String token;

    private String role;

}
