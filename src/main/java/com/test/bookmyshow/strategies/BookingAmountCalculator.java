package com.test.bookmyshow.strategies;

import com.test.bookmyshow.models.SeatTypeShow;
import com.test.bookmyshow.models.Show;
import com.test.bookmyshow.models.ShowSeat;
import com.test.bookmyshow.repositories.SeatTypeShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class BookingAmountCalculator implements PricingStrategy {

    @Autowired
    private SeatTypeShowRepository showSeatTypeRepository;


    @Override
    public int calculateAmount(List<ShowSeat> showSeats, Show show) {
        // Get all the SeatTypes for the given show
        List<SeatTypeShow> seatTypeShows = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;
        // Iterate over showSeats, and get the amount for the matching SeatType from seatTypeShows
        for(ShowSeat showSeat:showSeats) {
            for(SeatTypeShow seatTypeShow:seatTypeShows) {
                if(showSeat.getSeat().getSeatType().equals(seatTypeShow.getSeatType())) {
                    amount += seatTypeShow.getPrice();
                }
            }
        }

        return  amount;
    }

}
