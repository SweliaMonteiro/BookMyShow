package com.test.bookmyshow.controllers;

import com.test.bookmyshow.dtos.CreateShowRequestDTO;
import com.test.bookmyshow.dtos.CreateShowResponseDTO;
import com.test.bookmyshow.enums.ResponseStatus;
import com.test.bookmyshow.models.Show;
import com.test.bookmyshow.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ShowController {

    @Autowired
    private ShowService showService;

    public CreateShowResponseDTO createShow(CreateShowRequestDTO requestDTO) {
        CreateShowResponseDTO response = new CreateShowResponseDTO();

        try {
            Show show = showService.createShow(requestDTO.getUserId(), requestDTO.getMovieId(), requestDTO.getScreenId(), requestDTO.getStartTime(), requestDTO.getEndTime(), requestDTO.getPricingConfig(), requestDTO.getFeatures());
            response.setShow(show);
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}

