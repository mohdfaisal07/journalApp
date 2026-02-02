package com.faiz.journalApp.Service;

import com.faiz.journalApp.Cache.AppCache;
import com.faiz.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

@Autowired
private RestTemplate restTemplate;

@Autowired
private AppCache appCache;

public WeatherResponse getWeather(String city) {
    String finalAPI = appCache.get(AppCache.keys.WEATHER_API.toString()).replace("<CITY>", city).replace("<API_KEY>", apiKey);
    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
    WeatherResponse body = response.getBody();
    return body;
}}
