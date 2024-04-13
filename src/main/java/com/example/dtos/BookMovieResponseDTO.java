package com.example.dtos;

import com.example.enums.ResponseStatus;
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