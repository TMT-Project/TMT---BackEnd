package com.tmtbackend.repo;

import com.tmtbackend.model.Countries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountriesRepo extends JpaRepository<Countries,Integer> {
}
