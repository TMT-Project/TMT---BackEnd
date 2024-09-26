package com.tmtbackend.repo;

import com.tmtbackend.model.RegisterTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RegisterTripRepo extends JpaRepository<RegisterTrip, Integer> {
    @Query("SELECT t FROM RegisterTrip t WHERE t.date > :time")
    List<RegisterTrip> findTripsWithDepartureAfter(LocalDateTime time);
}
