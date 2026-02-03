package com.faiz.journalApp.repo;

import com.faiz.journalApp.repository.UserRepositoryImpl;
import org.bson.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepository ;

@Test
    public void getUser(){
    Assertions.assertNotNull(userRepository.getUserForSA());
}
}
