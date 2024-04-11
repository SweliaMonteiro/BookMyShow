package com.test.bookmyshow.services;

import com.test.bookmyshow.exceptions.*;
import com.test.bookmyshow.models.User;
import com.test.bookmyshow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User signUp(String email, String password) throws UserExistsException {
        // If user already exists then throw error as need not create new user account
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()) {
            throw new UserExistsException("User already exists");
        }

        // Else create new user and store in DB
        User user = new User();
        user.setEmail(email);
        // Encrypt the password using BCrypt encryption technique to store in DB
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setBookings(new ArrayList<>());
        user = userRepository.save(user);

        return user;
    }


    public User logIn(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        // Authenticate the User by verifying the email and password
        // If user does not exist then throw error
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("No user found with the given email Id");
        }

        User user = optionalUser.get();
        // Check if the password entered is correct
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return user;
        }

        throw new InvalidPasswordException("Invalid password");
    }

}