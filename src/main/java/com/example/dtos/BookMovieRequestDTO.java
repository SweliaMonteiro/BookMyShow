package com.example.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {

    private List<Integer> showSeatIds;
    private int userId;
    private  int showId;

}