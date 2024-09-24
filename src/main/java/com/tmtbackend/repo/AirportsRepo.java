package com.tmtbackend.repo;

import com.tmtbackend.model.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AirportsRepo extends JpaRepository<Airports,Integer> {

    List<Airports> findAllByTypeIn(List<String> type);

}
