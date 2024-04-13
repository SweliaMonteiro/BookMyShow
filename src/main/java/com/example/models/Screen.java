package com.example.models;

import com.example.enums.Feature;
import com.example.enums.ScreenStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel {

    private String name;

    @OneToMany
    private List<Seat> seats;

    private ScreenStatus status;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

}
