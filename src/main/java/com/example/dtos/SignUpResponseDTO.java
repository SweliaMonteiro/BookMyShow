package com.example.dtos;

import com.example.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {

    private ResponseStatus responseStatus;
    private int userId;
    private String failureMessage;

}