package com.test.bookmyshow.repositories;

import com.test.bookmyshow.models.SeatTypeShow;
import com.test.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatTypeShowRepository extends JpaRepository<SeatTypeShow, Integer> {

    List<SeatTypeShow> findAllByShow(Show show);

    SeatTypeShow save(SeatTypeShow seatTypeShow);

}