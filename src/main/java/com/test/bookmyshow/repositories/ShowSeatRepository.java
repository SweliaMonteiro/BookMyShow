package com.test.bookmyshow.repositories;

import com.test.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {

    @Override
    List<ShowSeat> findAllById(Iterable<Integer> integers);

    @Override
    ShowSeat save(ShowSeat showSeat);

}