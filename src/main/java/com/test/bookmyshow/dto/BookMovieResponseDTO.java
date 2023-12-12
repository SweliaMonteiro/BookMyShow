package com.test.bookmyshow.dto;

import com.test.bookmyshow.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO {

    private ResponseStatus responseStatus;
    private int bookingId;
    private int amount;
    private String FailureMessage;

}
