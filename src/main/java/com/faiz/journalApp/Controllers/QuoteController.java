package com.faiz.journalApp.Controllers;

import com.faiz.journalApp.Dto.Quote;
import com.faiz.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/quote")
public class QuoteController {

    private String url = "https://zenquotes.io/api/random";

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote() {
        Quote[] response = restTemplate.getForObject(url, Quote[].class);
      if (response != null && response.length>0){
        Quote quote= response[0];
        return new ResponseEntity<>(quote.getQ(), HttpStatus.OK);
      }
        return new ResponseEntity<>("Nahi mila", HttpStatus.BAD_REQUEST);

    }

}
