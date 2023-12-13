package com.test.bookmyshow.services;

import com.test.bookmyshow.models.Show;
import com.test.bookmyshow.models.ShowSeat;
import com.test.bookmyshow.models.ShowSeatType;
import com.test.bookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmountCalculatorService {

    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public AmountCalculatorService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    
    public int calculateAmount(List<ShowSeat> showSeats, Show show) {

        // Get all the SeatTypes for the given show
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);

        int amount = 0;
        // Iterate over showSeats, and get the amount for the matching SeatType from showSeatTypes
        for(ShowSeat showSeat:showSeats) {
            for(ShowSeatType showSeatType:showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(showSeatType.getSeatType())) {
                    amount += showSeatType.getAmount();
                }
            }
        }

        return  amount;
    }

}
