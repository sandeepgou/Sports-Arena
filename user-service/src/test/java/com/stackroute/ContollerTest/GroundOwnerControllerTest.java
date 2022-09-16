package com.stackroute.ContollerTest;
import com.stackroute.groundOwner.Controller.GroundOwnerController;
import com.stackroute.groundOwner.Service.GroundOwnerServiceImpl;
import com.stackroute.groundOwner.customException.GroundOwnerAlreadyExistException;
import com.stackroute.groundOwner.model.GroundOwner;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {GroundOwnerControllerTest.class})
public class GroundOwnerControllerTest {
    @Mock
    GroundOwnerServiceImpl groundOwnerService;

    @InjectMocks
    GroundOwnerController groundOwnerController;

    private ResponseEntity responseEntity;

    @Test
    public void test_getAllGroundOwner(){
        List<GroundOwner> groundOwners= new ArrayList<>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
//        Mockito.when(groundOwnerService.getAllGroundOwner()).thenReturn(groundOwners);
//        assertEquals(2,groundOwnerController.getListOfGroundOwner().size());

        Mockito.when(groundOwnerService.getAllGroundOwner()).thenReturn(groundOwners);
        assertEquals(2, groundOwnerController.getListOfGroundOwner().size());
    }


    @Test
    public void test_getListOnCityAndGroundType(){
        List<GroundOwner> groundOwners= new ArrayList<GroundOwner>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerService.getListOnCityAndGroundType("Delhi","Footbal")).thenReturn(groundOwners);
        assertEquals(2,groundOwnerController.getListOnCityAndGroundType("Delhi","Footbal").size());
    }

    @Test
    public void test_getListBasedOnCity(){
        List<GroundOwner> groundOwners= new ArrayList<GroundOwner>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerService.getListBasedOnCity("Delhi")).thenReturn(groundOwners);
        assertEquals(2,groundOwnerController.getListBasedOnCity("Delhi").size());
    }

    @Test
    public void test_getListBasedOnGroundType(){
        List<GroundOwner> groundOwners= new ArrayList<GroundOwner>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerService.getListBasedOnGroundType("Cricket")).thenReturn(groundOwners);
        assertEquals(2,groundOwnerController.getListBasedOnGroundType("Cricket").size());
    }
    @Test
    public void test_getGroundListBasedOnEmail(){
        List<GroundOwner> groundOwners= new ArrayList<GroundOwner>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerService.getListBasedOnEmail("mukesh@gmail.com")).thenReturn(groundOwners);
        assertEquals(2, groundOwnerController.getGroundlistBasedOnEmail("mukesh@gmail.com").size());
    }

    @Test
    public void test_deleteGroundOwner(){
        GroundOwner groundOwner=new GroundOwner();
        groundOwnerController.deleteGroundOwner("Suresh@gmail.com");
        Mockito.verify(groundOwnerService, Mockito.times(1)).deleteGroundOwner("Suresh@gmail.com");
    }

    @Test
    public void test_saveGroundOwnerDetails() throws GroundOwnerAlreadyExistException {
        GroundOwner groundOwner=new GroundOwner();
        Mockito.when(groundOwnerService.saveDetails(groundOwner)).thenReturn(groundOwner);
        responseEntity=new ResponseEntity<>(groundOwner, HttpStatus.CREATED);
        assertEquals(responseEntity,groundOwnerController.saveGroundOwnerDetails(groundOwner));
    }

    @Test
    public void test_getGroundBasedOnGroundId() {
        String[] listOfAmenities={"Bat","Ball","Water"};
        GroundOwner groundOwner=new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000);
        Mockito.when(groundOwnerService.fetchByGroundID("id101")).thenReturn(groundOwner);
        assertEquals(groundOwner,groundOwnerController.getGroundBasedOnGroundId("id101"));
    }

    @Test
    public void test_updateGroundOwner(){
        GroundOwner groundOwner=new GroundOwner();
        Mockito.when(groundOwnerController.getGroundBasedOnGroundId("2")).thenReturn(groundOwner);
        Mockito.when(groundOwnerController.updateGroundOwner(groundOwner)).thenReturn(groundOwner);
        assertEquals(groundOwner,groundOwnerService.updateGroundOwner(groundOwner));
    }
}


