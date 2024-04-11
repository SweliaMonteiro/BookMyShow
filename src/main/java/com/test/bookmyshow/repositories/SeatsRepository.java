package com.test.bookmyshow.repositories;

import com.test.bookmyshow.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByScreenId(int screenId);

}