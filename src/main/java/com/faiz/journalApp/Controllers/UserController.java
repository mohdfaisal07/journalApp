package com.faiz.journalApp.Controllers;


import com.faiz.journalApp.Entity.User;
import com.faiz.journalApp.Service.UserService;
import com.faiz.journalApp.Service.WeatherService;
import com.faiz.journalApp.api.response.WeatherResponse;
import com.faiz.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
   private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
      if(userInDb != null){
          userInDb.setUsername(user.getUsername());
          userInDb.setPassword(user.getPassword());
          userService.saveNewUser(userInDb);
      }
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
  userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping
    public ResponseEntity<?> Greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           WeatherResponse weatherResponse= weatherService.getWeather("Mumbai");
           String greeting= "";
           if(weatherResponse != null){
               greeting="Weather feels like "+ weatherResponse.getCurrent()+weatherResponse.getLocation();
           }
        return new ResponseEntity<>("Hii " + authentication.getName() + greeting, HttpStatus.OK);

        }
    }

