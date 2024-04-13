package com.example.models;

import com.example.enums.SeatType;
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
