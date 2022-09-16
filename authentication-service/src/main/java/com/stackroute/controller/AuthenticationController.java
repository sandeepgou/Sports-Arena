package com.stackroute.controller;

import com.stackroute.entity.User;
import com.stackroute.exception.UserNotFoundException;
import com.stackroute.security.SecurityTokenGeneratorImpl;
import com.stackroute.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    public AuthenticationServiceImpl service;
    @Autowired
    public SecurityTokenGeneratorImpl securityTokenGenerator;
    ResponseEntity responseEntity ;

    //dummy registration handler
 /*   @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        User newUser = service.saveUser(user);
        return new ResponseEntity(newUser,HttpStatus.CREATED);
    }*/
    /*@RabbitListener(queues = MQConfig.QUEUE)
    public void listener(User user){
        System.out.println(user);
        service.saveUser(user);

    }*/
    @PostMapping(value = "/userauth")
    public ResponseEntity login(@RequestBody User user) throws UserNotFoundException {
        try{
            Map<String,String> map = null;

            User user1 =  service.getUserByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
            if(user1.getUserEmail().equalsIgnoreCase(user.getUserEmail()) && user1.getPassword().equalsIgnoreCase(user.getPassword())) {
                map = securityTokenGenerator.generateToken(user);
                if(!map.isEmpty()){
                    responseEntity=new ResponseEntity(map,HttpStatus.OK);
                }else{
                    responseEntity = new ResponseEntity("Error",HttpStatus.BAD_REQUEST);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
            responseEntity = new ResponseEntity("Error",HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
  /*  @GetMapping("/hello")
    public  String hello(){
        return "hi";
    }*/


}



