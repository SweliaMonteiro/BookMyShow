package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Theatre extends BaseModel {

    private String address;

    @ManyToOne
    private Region region;

    @OneToMany
    private List<Screen> screens;

}