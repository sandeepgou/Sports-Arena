package com.stackroute.controller;

import com.stackroute.Entity.Teamup;
import com.stackroute.constantEnums.RequestStatus;
import com.stackroute.service.TeamupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/teamup")
public class TeamupController {
    private ResponseEntity responseEntity;
    @Autowired(required = false)
    private TeamupService teamupService;

    @PostMapping("/sendInvite")
    public ResponseEntity<String> sendInvites(@RequestBody Teamup teamup) {
        try {
            teamupService.sendInvites(teamup);
            responseEntity = new ResponseEntity<>("Invitation Sent!", HttpStatus.OK);
        }
        catch(Exception e) {
            responseEntity = new ResponseEntity<>("Can't Send Invite to "+teamup.getRequestedPlayerEmail(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/action/requestId/{requestId}/requestStatus/{requestStatus}")
    public ResponseEntity<String> updateInvite(@PathVariable("requestId") String requestId, @PathVariable("requestStatus") RequestStatus requestStatus) {
        try {
            teamupService.updateInvite(requestId, requestStatus);
            responseEntity = new ResponseEntity<>(requestStatus, HttpStatus.OK);
        }
        catch(Exception e) {
            responseEntity =  new ResponseEntity<>("Something Went Wrong Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/requestedPlayerEmail/{requestedPlayerEmail}")
    public ResponseEntity<List<Teamup>> getRecievedInvitations(@PathVariable String requestedPlayerEmail) {
        try {
            List<Teamup> invitesList = teamupService.getRecievedInvitations(requestedPlayerEmail);
            responseEntity = new ResponseEntity<>(invitesList, HttpStatus.OK);
        }
        catch(Exception e) {
            responseEntity = new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("/senderEmail/{senderEmail}")
    public ResponseEntity<List<Teamup>> getSentInvitations(@PathVariable String senderEmail) {
        try {
            List<Teamup> invitesList = teamupService.getSentInvitations(senderEmail);
            responseEntity = new ResponseEntity(invitesList, HttpStatus.OK);
        }
        catch(Exception e) {
            responseEntity =  new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/removeinvite/requestId/{requestId}")
    public ResponseEntity<String> deleteRequest(@PathVariable String requestId) {
        try {
            teamupService.deleteRequest(requestId);
            responseEntity = new ResponseEntity<>("Invite with Id: "+requestId+" deleted successfully", HttpStatus.OK);
        }
        catch(Exception e) {
            responseEntity = new ResponseEntity<>("Something Went Wrong", HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
