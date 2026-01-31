package com.faiz.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class  WeatherResponse {
    public Location location;
    public Current current;

    @Getter
    @Setter
    public static class Current{
        @JsonProperty("temp_c")
        private double tempC;

        @JsonProperty("temp_f")
        private double tempF;

        @JsonProperty("feelslike_c")
       private double feelsLikeC;

        @JsonProperty("feelslike_f")
        private double feelsLikeF;

    }

    @Getter
    @Setter
    public static class Location{
        private String name;
        private String region;
        private String country;

}}





