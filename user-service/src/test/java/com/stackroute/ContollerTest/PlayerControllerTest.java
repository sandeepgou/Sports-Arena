/*
package com.stackroute.ContollerTest;

import com.stackroute.player.Controller.PlayerController;
import com.stackroute.player.Service.PlayerServiceImpl;
import com.stackroute.player.custom.exception.UserAlreadyExistException;
import com.stackroute.player.model.Player;
import com.stackroute.player.model.UserGender;
import com.stackroute.player.model.UserRole;
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

@SpringBootTest(classes = {PlayerControllerTest.class})
public class PlayerControllerTest {
    @Mock
    PlayerServiceImpl playerService;

    @InjectMocks
    PlayerController playerController;

    ResponseEntity responseEntity;
    @Test
    public  void test_getListOfPlayer(){
        List<Player> playerList= new ArrayList<>();
        playerList.add(new Player("anil@gmail.com","Anil kumar","96655656760", "anil@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("bikash@gmail.com","Bikash Singh","96655656767", "bikash@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER,UserGender.MALE));
        Mockito.when(playerService.getAllPlayers()).thenReturn(playerList);
        assertEquals(2, playerController.getListOfPlayer().size());
    }

    @Test
    public  void test_GetByUserEmail(){
        Player player=new Player("bikash@gmail.com","Bikash Singh","96655656767", "bikash@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER,UserGender.MALE);
        Mockito.when(playerService.getByUserEmail("bbb@gmail.com")).thenReturn(player);
        assertEquals(player, playerController.getPlayerBasedOnEmail("bbb@gmail.com"));
    }

    @Test
    public void test_GetByCityWise() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        players.add(new Player("amit@gmail.com","Amit kumar","96655656761", "amit@123","Gkp", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        players.add(new Player("sunil@gmail.com","Sunil kumar","9665599889", "sunil@123","Gopalganj", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        players.add(new Player("ajit@gmail.com","Ajit kumar","96655656765", "ajit@123","Siwan", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerService.getListOfPlayerBasedOnCity("Jaipur")).thenReturn(players);
        assertEquals(4, playerController.findListBasedOnCity("Jaipur").size());
    }

    @Test
    public void test_getListOfPlayerBasedOnFavSportAndCity(){
        List<Player> playerList=new ArrayList<>();
        playerList.add(new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("rakesh@gmail.com","Rakesh kumar","96655656087", "rakesh@123","Chhapra", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("dilip@gmail.com","Dilip kumar","96655656003", "dilip@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerService.getListOfPlayerBasedOnFavSportAndCity("Patna","cricket")).thenReturn(playerList);
        assertEquals(3, playerController.findListOfPlayerBasedOnFavSportAndCity("Patna","cricket").size());
    }

    @Test
    public void test_getListOfPlayerBasedOnFavSport(){
        List<Player> playerList=new ArrayList<>();
        playerList.add(new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("rakesh@gmail.com","Rakesh kumar","96655656087", "rakesh@123","Chhapra", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("dilip@gmail.com","Dilip kumar","96655656003", "dilip@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerService.getListOfPlayerBasedOnFavSport("Cricket")).thenReturn(playerList);
        assertEquals(3,playerController.findListBasedOnFavSport("Cricket").size());
    }
    @Test
    public void  test_updatePlayer(){
        Player player =new Player("sunil@gmail.com","Sunil","86655656760", "suili@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER,UserGender.FEMALE);
        Mockito.when(playerController.getPlayerBasedOnEmail("sunil@gmail.com")).thenReturn(player);
        Mockito.when(playerController.updatingPlayerDetails(player)).thenReturn(player);
        assertEquals(player,playerService.updatePlayer(player));
    }

    @Test
    public  void  test_DeletePlayer(){
        playerController.deletePlayer("sunil@gmail.com");
        Mockito.verify(playerService, Mockito.times(1)).deletePlayer("sunil@gmail.com");
    }

    @Test
    public  void  test_saveDetails() throws UserAlreadyExistException {
        Player player =new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE);
        responseEntity= new ResponseEntity<>(player, HttpStatus.CREATED);
        Mockito.when(playerService.saveDetails(player)).thenReturn(player);
        assertEquals(responseEntity, playerController.saveDetails(player));

    }

}
*/
