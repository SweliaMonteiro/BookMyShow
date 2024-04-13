package com.example.models;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;

@Getter
@Setter
@Entity
public class Movie extends BaseModel {

    private String name;

    private String description;

}
