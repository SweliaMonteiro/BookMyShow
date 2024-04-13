package com.example.dtos;

import com.example.enums.ResponseStatus;
import com.example.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {

    private ResponseStatus responseStatus;
    private Show show;

}
