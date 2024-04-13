package com.example.services;

import com.example.exceptions.ShowNotFoundException;
import com.example.exceptions.UserNotFoundException;
import com.example.repositories.BookingRepository;
import com.example.repositories.ShowRepository;
import com.example.repositories.ShowSeatRepository;
import com.example.repositories.UserRepository;
import com.example.strategies.PricingStrategy;
import com.example.enums.BookingStatus;
import com.example.enums.SeatStatus;
import com.example.exceptions.SeatsNotAvailableException;
import com.example.models.Booking;
import com.example.models.Show;
import com.example.models.ShowSeat;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PricingStrategy pricingStrategy;


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(int userId, int showId, List<Integer> showSeatIds) throws UserNotFoundException, ShowNotFoundException, SeatsNotAvailableException {
        // Get User using userId from DB
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("Invalid User");
        }
        User bookedBy = optionalUser.get();

        // Get Show using showId from DB
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()) {
            throw new ShowNotFoundException("Invalid Show");
        }
        Show show = optionalShow.get();

        // Get all the records from ShowSeat table for the given Ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // Before Blocking the Seats, first check if all Seats mentioned can be booked i.e. available
        // If the Seat is booked, unavailable or if Seat is blocked check the blocked duration
        // If less than 15 minutes then throw error
        for(ShowSeat showSeat:showSeats) {
            if(!(showSeat.getSeatStatus().equals(SeatStatus.AVAILABLE) ||
                    (showSeat.getSeatStatus().equals(SeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15))) {
                throw new SeatsNotAvailableException("Selected seats are not available");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();
        // If all the Seats are available for booking, then mark them as blocked and updated lockedAt column
        for(ShowSeat showSeat:showSeats) {
            showSeat.setSeatStatus(SeatStatus.BLOCKED);
            showSeat.setLockedAt(new Date());
            // Update ShowSeat in DB
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        // Create the Booking object, set its values
        Booking booking = new Booking();
        booking.setBookingDate(new Date());
        booking.setBookedBy(bookedBy);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setShow(show);
        booking.setAmount(pricingStrategy.calculateAmount(savedShowSeats, show));

        // Store Booking object in DB
        booking = bookingRepository.save(booking);

        return booking;
    }

}