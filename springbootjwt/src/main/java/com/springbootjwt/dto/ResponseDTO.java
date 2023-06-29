package com.springbootjwt.dto;
import lombok.*;
@Data
@Builder
public class ResponseDTO<T> {

    private String responseMsg;

    private T json;
}
