package com.example.repositories;

import com.example.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {

    @Override
    Optional<Show> findById(Integer integer);

    @Override
    Show save(Show show);

}