package com.springbootjwt.exception;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestException extends RuntimeException
{
	//for error message
    private String msg;

}
