package com.test.bookmyshow.dto;

import com.test.bookmyshow.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogInResponseDTO {

    private ResponseStatus responseStatus;
    private int userId;
    private String failureMessage;

}
