package com.example.strategies;

import com.example.models.Show;
import com.example.models.ShowSeat;
import java.util.List;

public interface PricingStrategy {

    int calculateAmount(List<ShowSeat> showSeats, Show show);

}
