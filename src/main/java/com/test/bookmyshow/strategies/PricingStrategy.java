package com.test.bookmyshow.strategies;

import com.test.bookmyshow.models.Show;
import com.test.bookmyshow.models.ShowSeat;
import java.util.List;

public interface PricingStrategy {

    int calculateAmount(List<ShowSeat> showSeats, Show show);

}
