package com.tmtbackend.service;

import com.tmtbackend.model.Countries;
import com.tmtbackend.repo.CountriesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TmtService {

    private final CountriesRepo countriesRepo;

    public List<Countries> getCountries() {
        log.info(String.valueOf(countriesRepo.count()));
        return countriesRepo.findAll();
    }
}
