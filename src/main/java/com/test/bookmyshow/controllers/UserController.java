package com.test.bookmyshow.controllers;

import com.test.bookmyshow.dtos.*;
import com.test.bookmyshow.enums.ResponseStatus;
import com.test.bookmyshow.models.User;
import com.test.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public SignUpResponseDTO signUp(SignUpRequestDTO request) {
        SignUpResponseDTO response = new SignUpResponseDTO();

        try {
            User user = userService.signUp(request.getEmail(), request.getPassword());
            response.setUserId(user.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }

        return response;
    }


    public LogInResponseDTO logIn(LogInRequestDTO request) {
        LogInResponseDTO response = new LogInResponseDTO();

        try {
            User user = userService.logIn(request.getEmail(), request.getPassword());
            response.setUserId(user.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch(Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }

        return response;
    }

}
