package com.test.bookmyshow;

import com.test.bookmyshow.controllers.UserController;
import com.test.bookmyshow.dto.LogInRequestDTO;
import com.test.bookmyshow.dto.SignUpRequestDTO;
import com.test.bookmyshow.dto.SignUpResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyShowApplication implements CommandLineRunner {

    @Autowired
    private UserController userController;

    @Override
    public void run(String... args) throws Exception {
        // To test Sign Up
        SignUpRequestDTO signUpRequestDTO = new SignUpRequestDTO();
        signUpRequestDTO.setEmail("swelia@gmail.com");
        signUpRequestDTO.setPassword("swe123");
        System.out.println(userController.signUp(signUpRequestDTO).getResponseStatus());

        // To test Log In
        LogInRequestDTO logInRequestDTO = new LogInRequestDTO();
        logInRequestDTO.setEmail("swelia@gmail.com");
        logInRequestDTO.setPassword("swe123");
        System.out.println(userController.logIn(logInRequestDTO).getResponseStatus());
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowApplication.class, args);
    }

}
