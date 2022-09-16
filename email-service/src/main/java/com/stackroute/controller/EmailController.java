package com.stackroute.controller;

import com.stackroute.entity.Email;
import com.stackroute.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private ResponseEntity responseEntity;
    @Autowired
    private EmailService emailService;

    @PostMapping("/api/v1/email")
    public ResponseEntity<String> sendEmailConfiramtion(@RequestBody Email emailBody) {
        try {
            emailService.sendEmail(emailBody);
            responseEntity = new ResponseEntity<>("Acknowledgement Sent Successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            responseEntity = new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

}
