package com.test.bookmyshow.controllers;

import com.test.bookmyshow.dtos.BookMovieRequestDTO;
import com.test.bookmyshow.dtos.BookMovieResponseDTO;
import com.test.bookmyshow.enums.ResponseStatus;
import com.test.bookmyshow.models.Booking;
import com.test.bookmyshow.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    public BookMovieResponseDTO bookMovie(BookMovieRequestDTO request) {
        BookMovieResponseDTO response = new BookMovieResponseDTO();

        try {
            Booking booking = bookingService.bookMovie(request.getUserId(), request.getShowId(), request.getShowSeatIds());
            response.setBookingId(booking.getId());
            response.setAmount(booking.getAmount());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception e) {
            response.setResponseStatus(ResponseStatus.FAILURE);
            response.setFailureMessage(e.getMessage());
        }

        return response;
    }

}
