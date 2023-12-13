package com.test.bookmyshow.services;

import com.test.bookmyshow.enums.BookingStatus;
import com.test.bookmyshow.enums.ShowSeatStatus;
import com.test.bookmyshow.models.Booking;
import com.test.bookmyshow.models.Show;
import com.test.bookmyshow.models.ShowSeat;
import com.test.bookmyshow.models.User;
import com.test.bookmyshow.repositories.BookingRepository;
import com.test.bookmyshow.repositories.ShowRepository;
import com.test.bookmyshow.repositories.ShowSeatRepository;
import com.test.bookmyshow.repositories.UserRepository;
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

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private AmountCalculatorService amountCalculatorService;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository, AmountCalculatorService amountCalculatorService) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.amountCalculatorService = amountCalculatorService;
    }

    
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds) {
        // Get User using userId from DB
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid User");
        }
        User bookedBy = optionalUser.get();

        // Get Show using showId from DB
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()) {
            throw new RuntimeException("Invalid Show");
        }
        Show show = optionalShow.get();

        // Get all the records from ShowSeat table for the given Ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        // Before Blocking the Seats, first check if all Seats mentioned can be booked i.e. available
        // If the Seat is booked, unavailable or if Seat is blocked check the blocked duration
        // If less than 15 minutes then throw error
        for(ShowSeat showSeat:showSeats) {
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes() > 15))) {
                throw new RuntimeException("Selected seats are not available");
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<ShowSeat>();
        // If the all Seats are available for booking, then mark them as blocked and updated lockedAt column
        for(ShowSeat showSeat:showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
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
        booking.setAmount(amountCalculatorService.calculateAmount(savedShowSeats, show));

        // Store Booking object in DB
        booking = bookingRepository.save(booking);

        return booking;
    }

}
