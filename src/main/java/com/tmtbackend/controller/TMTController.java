package com.tmtbackend.controller;

import com.tmtbackend.model.Countries;
import com.tmtbackend.service.TmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tmt/")
public class TMTController {


    private final TmtService tmtService;

    @GetMapping("/countries")
    public List<Countries> getCountries() {
        return tmtService.getCountries();
    }
}
