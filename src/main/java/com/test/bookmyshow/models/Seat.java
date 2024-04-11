package com.test.bookmyshow.models;

import com.test.bookmyshow.enums.SeatType;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

    @ManyToOne
    private Screen screen;

}
