/*
package com.stackroute.controller;

import com.stackroute.entity.User;
import com.stackroute.entity.UserRoles;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.security.SecurityTokenGeneratorImpl;
import com.stackroute.service.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class AuthenticationControllerTest {
    @InjectMocks
    AuthenticationController controller;
    @Mock
    AuthenticationServiceImpl service;
    @Mock
    SecurityTokenGeneratorImpl token;
    User   user =  new User("tarhaneanuja@gmail.com","Anuja@17",UserRoles.GROUND_OWNER);
    ResponseEntity entity;
    @Test
    public void testWhenCredintialsValid() throws UserNotFoundException {
        Map<String, String> map = new HashMap<>();
        map.put("Autheticated User", "gduosxdhaoqwdqwdjqwu");
        entity = new ResponseEntity(map, HttpStatus.OK);
        Mockito.when(service.getUserByUserEmailAndPassword(anyString(),anyString())).thenReturn(user);
        Mockito.when(token.generateToken(user)).thenReturn(map);
        assertEquals(entity, controller.login(user));
    }

    @Test
    public void testWhenCredintialsInValid() throws UserNotFoundException {
        entity = new ResponseEntity("Error", HttpStatus.BAD_REQUEST);
        Mockito.when(service.getUserByUserEmailAndPassword(anyString(), anyString())).thenReturn(user);
        assertEquals(entity, controller.login(user));
    }
}
*/
