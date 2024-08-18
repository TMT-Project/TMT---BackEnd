package com.tmtbackend.service;

import com.tmtbackend.model.Countries;
import com.tmtbackend.repo.CountriesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TmtService {

    private final CountriesRepo countriesRepo;

    public List<Countries> getCountries() {
        System.out.println(countriesRepo.count());
        return countriesRepo.findAll();
    }
}
