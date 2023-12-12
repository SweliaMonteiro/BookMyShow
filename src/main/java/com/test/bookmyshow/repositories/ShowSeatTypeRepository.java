package com.test.bookmyshow.repositories;

import com.test.bookmyshow.models.Show;
import com.test.bookmyshow.models.ShowSeatType;
import com.test.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {

    List<ShowSeatType> findAllByShow(Show show);

}
