package com.test.bookmyshow.dtos;

import com.test.bookmyshow.enums.Feature;
import com.test.bookmyshow.enums.SeatType;
import lombok.Data;
import org.springframework.data.util.Pair;
import java.util.Date;
import java.util.List;

@Data
public class CreateShowRequestDTO {

    private int movieId;
    private int userId;
    private int screenId;
    private Date startTime;
    private Date endTime;
    private List<Feature> features;
    private List<Pair<SeatType, Integer>> pricingConfig;

}
