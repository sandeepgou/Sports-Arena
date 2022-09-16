/*
package com.stackroute;

import com.stackroute.Entity.Teamup;
import com.stackroute.constantEnums.RequestStatus;
import com.stackroute.controller.TeamupController;
import com.stackroute.service.TeamupServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class TeamupControllerTest {
    @Mock
    private TeamupServiceImpl teamupService;
    private MockMvc mockMvc;

    @InjectMocks
    private TeamupController teamupController;

    List<Teamup> teamupList = new ArrayList<>();
    List<Teamup> teamupListFailure = new ArrayList<>();
    Teamup teamup = new Teamup();
    private ResponseEntity responseEntity;

    @Before
    public void setTeamup() {
        teamup.setRequestId("121");
        teamup.setSenderEmail("kumar@gmail.com");
        teamup.setRequestedPlayerEmail("swastik@gmail.com");
        teamup.setRequestId(String.valueOf(RequestStatus.REJECTED));
        teamup.setRequestDate(LocalDate.now());
        teamupList.add(teamup);
    }

    @Test
    public void sendInvitesTest() throws Exception {
        Mockito.when(teamupService.sendInvites(teamup)).thenReturn(teamup);
        responseEntity = new ResponseEntity<>("Invitation Sent!", HttpStatus.OK);
        assertEquals(responseEntity, teamupController.sendInvites(teamup));
    }

    @Test
    public void sendInvitesFailureTest() throws Exception {
        Mockito.when(teamupService.sendInvites(teamup)).thenThrow(new Exception());
        responseEntity = new ResponseEntity<>("Can't Send Invite to "+teamup.getRequestedPlayerEmail(), HttpStatus.BAD_REQUEST);
        assertEquals(responseEntity, teamupController.sendInvites(teamup));
    }

    @Test
    public void updateInviteTest() throws Exception {
        Mockito.when(teamupService.updateInvite("121", RequestStatus.ACCEPTED)).thenReturn(String.valueOf(RequestStatus.ACCEPTED));
        responseEntity = new ResponseEntity<>(RequestStatus.ACCEPTED, HttpStatus.OK);
        assertEquals(responseEntity, teamupController.updateInvite("121", RequestStatus.ACCEPTED));
    }

    @Test
    public void updateInviteFailureTest() throws Exception {
        Mockito.when(teamupService.updateInvite("121", RequestStatus.ACCEPTED)).thenThrow(new Exception());
        responseEntity =  new ResponseEntity<>("Something Went Wrong Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(responseEntity, teamupController.updateInvite("121", RequestStatus.ACCEPTED));
    }

    @Test
    public void getRecievedInviationsControllerTest() throws Exception {
        Mockito.when(teamupService.getRecievedInvitations("swastik@gmail.com")).thenReturn(teamupList);
        responseEntity = new ResponseEntity<>(teamupList, HttpStatus.OK);
        assertEquals(responseEntity, teamupController.getRecievedInvitations("swastik@gmail.com"));
    }

    @Test
    public void getRecievedInviationsControllerFailureTest() throws Exception {
        Mockito.when(teamupService.getRecievedInvitations("swastik@gmail.com")).thenThrow(new RuntimeException());
        responseEntity = new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        assertEquals(responseEntity, teamupController.getRecievedInvitations("swastik@gmail.com"));
    }

    @Test
    public void getSentInvitationsTest() throws Exception {
        Mockito.when(teamupService.getSentInvitations("swastik@gmail.com")).thenReturn(teamupList);
        responseEntity = new ResponseEntity<>(teamupList, HttpStatus.OK);
        assertEquals(responseEntity, teamupController.getSentInvitations("swastik@gmail.com"));
    }

    @Test
    public void getSentInvitationsFailureTest() throws Exception {
        Mockito.when(teamupService.getSentInvitations("swastik@gmail.com")).thenThrow(new RuntimeException());
        responseEntity = new ResponseEntity<>("No Record Found", HttpStatus.NOT_FOUND);
        assertEquals(responseEntity, teamupController.getSentInvitations("swastik@gmail.com"));
    }

    @Test
    public void deleteRequestTest() throws Exception {
        teamupController.deleteRequest(teamup.getRequestId());
        Mockito.verify(teamupService, Mockito.times(1)).deleteRequest(teamup.getRequestId());
    }
}
*/
