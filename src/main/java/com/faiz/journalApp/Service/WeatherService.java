package com.faiz.journalApp.Service;

import com.faiz.journalApp.Cache.AppCache;
import com.faiz.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {


@Autowired
private RestTemplate restTemplate;

@Autowired
private AppCache appCache;

public WeatherResponse getWeather(String city) {
    String baseUrl = appCache.getValue("Weather_api");
    String apiKey = appCache.getValue("API_key");

    String finalAPI = baseUrl
            .replace("CITY", city)
            .replace("API_Key", apiKey);

    ResponseEntity<WeatherResponse> response =
            restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

    return response.getBody();
}}
