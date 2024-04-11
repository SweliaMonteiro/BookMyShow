package com.test.bookmyshow.services;

import java.util.*;
import com.test.bookmyshow.enums.*;
import com.test.bookmyshow.models.*;
import com.test.bookmyshow.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import com.test.bookmyshow.exceptions.*;
import org.springframework.stereotype.Service;


@Service
public class ShowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ScreenRepository screenRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private SeatTypeShowRepository seatTypeShowRepository;

    @Autowired
    private SeatsRepository seatsRepository;

    @Autowired
    private ShowSeatRepository showSeatRepository;


    public Show createShow(int userId, int movieId, int screenId, Date startTime, Date endTime, List<Pair<SeatType, Integer>> pricingConfig, List<Feature> features) throws MovieNotFoundException, ScreenNotFoundException, FeatureNotSupportedByScreen, InvalidDateException, UserNotFoundException, UnAuthorizedAccessException {
        // Get User using userId from DB
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        // Check if the User is an Admin
        User user = optionalUser.get();
        if(!user.getUserType().equals(UserType.ADMIN)) {
            throw new UnAuthorizedAccessException("Un-authorized access");
        }

        // Get Movie using movieId from DB
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if(optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie not found");
        }
        Movie movie = optionalMovie.get();

        // Get Screen using screenId from DB
        Optional<Screen> optionalScreen = screenRepository.findById(screenId);
        if(optionalScreen.isEmpty()) {
            throw new ScreenNotFoundException("Screen not found");
        }
        Screen screen = optionalScreen.get();

        // Check if the Features are supported by the Screen
        Set<Feature> featuresSupportedByScreen = new HashSet<>(screen.getFeatures());
        for(Feature feature:features) {
            if(!featuresSupportedByScreen.contains(feature)) {
                throw new FeatureNotSupportedByScreen("Feature not supported by screen");
            }
        }

        // Check if the startTime and endTime are valid i.e. startTime should be in future and endTime should be after startTime
        if(startTime.before(new Date()) ) {
            throw new InvalidDateException("Invalid date");
        }
        if(endTime.before(startTime)) {
            throw new InvalidDateException("Invalid date");
        }

        // Create a new Show and store in DB
        Show show = new Show();
        show.setMovie(movie);
        show.setStartTime(startTime);
        show.setEndTime(endTime);
        show.setFeatures(features);
        show.setScreen(screen);
        show = showRepository.save(show);

        // Create SeatTypeShow using SeatType and pricing related details for the given Show and store in DB
        for(Pair<SeatType, Integer> pair:pricingConfig) {
            SeatTypeShow seatTypeShow = new SeatTypeShow();
            seatTypeShow.setSeatType(pair.getFirst());
            seatTypeShow.setPrice(pair.getSecond());
            seatTypeShow.setShow(show);
            seatTypeShowRepository.save(seatTypeShow);
        }

        // Create ShowSeat for all the seats making all seats as available in the Screen for the given Show and store in DB
        List<Seat> seats = seatsRepository.findAllByScreenId(screenId);
        for(Seat seat:seats) {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setSeat(seat);
            showSeat.setSeatStatus(SeatStatus.AVAILABLE);
            showSeat.setShow(show);
            showSeatRepository.save(showSeat);
        }

        return show;
    }

}
