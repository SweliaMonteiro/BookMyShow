package com.example.repositories;

import com.example.models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seat, Integer> {

    List<Seat> findAllByScreenId(int screenId);

}