package com.tmtbackend.repo;

import com.tmtbackend.model.RegisterTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterTripRepo extends JpaRepository<RegisterTrip, Integer> {
}
