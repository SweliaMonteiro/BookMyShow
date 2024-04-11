package com.test.bookmyshow.dtos;

import com.test.bookmyshow.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {

    private ResponseStatus responseStatus;
    private int userId;
    private String failureMessage;

}