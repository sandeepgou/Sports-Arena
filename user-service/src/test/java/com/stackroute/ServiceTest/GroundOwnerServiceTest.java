package com.stackroute.ServiceTest;

import com.stackroute.groundOwner.Repository.GroundOwnerRepository;
import com.stackroute.groundOwner.Service.GroundOwnerServiceImpl;
import com.stackroute.groundOwner.customException.GroundOwnerAlreadyExistException;
import com.stackroute.groundOwner.model.GroundOwner;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {GroundOwnerServiceTest.class})
public class GroundOwnerServiceTest {
    @Mock
    GroundOwnerRepository groundOwnerRepository;
    @InjectMocks
    GroundOwnerServiceImpl groundOwnerService;

    @Test
    public void test_getAllGroundOwner(){
        List<GroundOwner> groundOwners= new ArrayList<>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","9875456880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay123", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerRepository.findAll()).thenReturn(groundOwners);
        assertEquals(2,groundOwnerService.getAllGroundOwner().size());
    }

    @Test
    public void test_GetListByEmail(){
        List<GroundOwner> groundOwners= new ArrayList<>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","9875456880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay123", "name2", "footbal","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerRepository.findListByOwnerEmail("ankit@gmail.com")).thenReturn(groundOwners);
        assertEquals(2, groundOwnerService.getListBasedOnEmail("ankit@gmail.com").size());
    }

    @Test
    public  void test_getListBasedOnCity(){
        List<GroundOwner> groundOwners= new ArrayList<>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "footbal","main road","Delhi",listOfAmenities, "ghbb.jpg","9875458880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "Cricket","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerRepository.findByCity("Delhi")).thenReturn(groundOwners);
        assertEquals(2, groundOwnerService.getListBasedOnCity("Delhi").size());
    }

    @Test
    public void test_getListBasedOnGroundType(){
        List<GroundOwner> groundOwners= new ArrayList<>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "Cricket","main road","Delhi",listOfAmenities, "ghbb.jpg","9875458887",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay kumar", "name2", "Cricket","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","987545775",1000));
        Mockito.when(groundOwnerRepository.findByGroundType("Cricket")).thenReturn(groundOwners);
        assertEquals(2,groundOwnerService.getListBasedOnGroundType("Cricket").size());
    }

    @Test
    public void test_getListOnCityAndGroundType(){
        List<GroundOwner> groundOwners= new ArrayList<>();
        String[] listOfAmenities={"Bat","Ball","Water"};
        groundOwners.add(new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "Cricket","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id102", "vijay@gmail.com", "Vijay234", "name2", "footbal","Hospital road","Jaipur",listOfAmenities, "ghbb.jpg","98754577598",1000));
        groundOwners.add(new GroundOwner("id103", "rakesh@gmail.com", "password@12","groundName2", "Cricket","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000));
        groundOwners.add(new GroundOwner("id104", "sonu@gmail.com", "kumar@23", "name4", "Cricket","Hospital road","Delhi",listOfAmenities, "ghbb.jpg","98754577598",1000));
        Mockito.when(groundOwnerRepository.findByCityAndGroundType("Chhapra", "Cricket")).thenReturn(groundOwners);
        assertEquals(4,groundOwnerService.getListOnCityAndGroundType("Chhapra", "Cricket").size());
    }

    @Test
    public void test_saveDetails() throws GroundOwnerAlreadyExistException {
        String[] listOfAmenities={"Bat","Ball","Water"};
        GroundOwner groundOwner=new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "Cricket","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000);
        Mockito.when(groundOwnerRepository.save(groundOwner)).thenReturn(groundOwner);
        try{
            assertEquals(groundOwner,groundOwnerService.saveDetails(groundOwner));
        }catch (Exception e){

        }
    }

    @Test
    public void test_fetchByGroundID() {
        String[] listOfAmenities={"Bat","Ball","Water"};
        GroundOwner groundOwner=new GroundOwner("id101", "mukesh@gmail.com", "password","groundName", "Cricket","main road","Delhi",listOfAmenities, "ghbb.jpg","98754588880",1000);
        Mockito.when(groundOwnerRepository.findByGroundID("id101")).thenReturn(groundOwner);
        assertEquals(groundOwner,groundOwnerService.fetchByGroundID("id101"));
    }

    @Test
    public void test_deleteGroundOwner(){
        groundOwnerService.deleteGroundOwner("ankit@gmail.com");
        Mockito.verify(groundOwnerRepository, Mockito.times(1)).deleteById("ankit@gmail.com");
    }

/*
    @Test
    public void  test_updateGroundOwner(){
        GroundOwner groundOwner=new GroundOwner();
        Mockito.when(groundOwnerRepository.findByOwnerEmail(groundOwner.getOwnerEmail())).thenReturn(groundOwner);
        Mockito.when(groundOwnerRepository.save(groundOwner)).thenReturn(groundOwner);
        assertEquals(groundOwner,groundOwnerService.updateGroundOwner(groundOwner));
    }
*/


}
