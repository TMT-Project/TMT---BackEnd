package com.tmtbackend.config;

import com.tmtbackend.model.Countries;
import com.tmtbackend.repo.CountriesRepo;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TmtConfiguration {

    private final CountriesRepo countriesRepo;

    @Bean
    public List<Countries> getCountries() throws IOException, InterruptedException {
        List<Countries> result = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                        "https://rest-countries10.p.rapidapi.com/countries"
                )).
                header("x-rapidapi-key", "95c6521fbfmsh9cf5be1c44333c7p1c4896jsn34d81033e9ed").
                header("x-rapidapi-host", "rest-countries10.p.rapidapi.com").
                method("GET",HttpRequest.BodyPublishers.noBody()).
                build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());
        JSONArray jsonArray = new JSONArray(response.body());
        for (int i = 0; i < jsonArray.length(); i++) {
            Countries countries = new Countries();

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONObject code = jsonObject.getJSONObject("code");
            JSONObject name = jsonObject.getJSONObject("name");

            countries.setCode(code.getString("alpha3code"));
            countries.setName(name.getString("shortname"));

            result.add(countries);
        }
        System.out.println(result);
        for (Countries country:result){
            Countries county = new Countries();
            county.setCode(country.getCode());
            county.setName(country.getName());
            countriesRepo.save(county);
        }
        return result;
    }
}
