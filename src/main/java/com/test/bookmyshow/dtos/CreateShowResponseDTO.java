package com.test.bookmyshow.dtos;

import com.test.bookmyshow.enums.ResponseStatus;
import com.test.bookmyshow.models.Show;
import lombok.Data;

@Data
public class CreateShowResponseDTO {

    private ResponseStatus responseStatus;
    private Show show;

}
