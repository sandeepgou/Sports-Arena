/*
package com.stackroute.ServiceTest;

import com.stackroute.player.Repository.PlayerRepository;
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
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(classes = {PlayerServiceTest.class})
public class PlayerServiceTest {
    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerServiceImpl playerService;

    ResponseEntity responseEntity;

    @Test
    public  void test_GetAllPlayers(){
        List<Player> allPlayers= new ArrayList<>();
        allPlayers.add(new Player("anil@gmail.com","Anil kumar","96655656760", "anil@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        allPlayers.add(new Player("amit@gmail.com","Amit kumar","96655656788", "amit@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerRepository.findAll()).thenReturn(allPlayers);
        assertEquals(2, playerService.getAllPlayers().size());
    }

    @Test
    public  void test_GetByUserEmail(){
        Player player=new Player("amit@gmail.com","Amit kumar","96655656788", "amit@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE);
        Mockito.when(playerRepository.findByUserEmail("amit@gmail.com")).thenReturn(player);
        assertEquals(player, playerService.getByUserEmail("amit@gmail.com"));
    }
    @Test
    public void test_GetByCityWise(){
        List<Player> players=new ArrayList<>();
        players.add(new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        players.add(new Player("amit@gmail.com","Amit kumar","96655656761", "amit@123","Gkp", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        players.add(new Player("sunil@gmail.com","Sunil kumar","9665599889", "sunil@123","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerRepository.findByCity("Chhapra")).thenReturn(players);
        assertEquals(3, playerService.getListOfPlayerBasedOnCity("Chhapra").size());
    }

    @Test
    public void test_getListOfPlayerBasedOnFavSportAndCity(){
        List<Player> playerList=new ArrayList<>();
        playerList.add(new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("rakesh@gmail.com","Rakesh kumar","96655656087", "rakesh@123","Chhapra", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("dilip@gmail.com","Dilip kumar","96655656003", "dilip@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerRepository.findByCityAndFavSport("Patna","cricket")).thenReturn(playerList);
        assertEquals(3, playerService.getListOfPlayerBasedOnFavSportAndCity("Patna","cricket").size());
    }

    @Test
    public void test_getListOfPlayerBasedOnFavSport(){
        List<Player> playerList=new ArrayList<>();
        playerList.add(new Player("anil@gmail.com","Anil kumar","96655656009", "anil@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("rakesh@gmail.com","Rakesh kumar","96655656087", "rakesh@123","Chhapra", "Bihar", "vgsgv.jpg","Footbal",  UserRole.PLAYER, UserGender.MALE));
        playerList.add(new Player("dilip@gmail.com","Dilip kumar","96655656003", "dilip@123","Patna", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER, UserGender.MALE));
        Mockito.when(playerRepository.findByFavSport(anyString())).thenReturn(playerList);
        assertEquals(3,playerService.getListOfPlayerBasedOnFavSport("Cricket").size());
    }

    @Test
    public void  test_updatePlayer(){
        Player player =new Player("anil@gmail.com","Anil","96655656760", "fsfg3","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER,UserGender.MALE);
        Mockito.when(playerRepository.findByUserEmail(player.getUserEmail())).thenReturn(player);
        Mockito.when(playerRepository.save(player)).thenReturn(player);
        assertEquals(player,playerService.updatePlayer(player));
    }


    @Test
    public  void  test_DeletePlayer(){
        playerService.deletePlayer("anil@gmail.com");
        Mockito.verify(playerRepository,Mockito.times(1)).deleteById("anil@gmail.com");

    }

    @Test
    public void  test_saveDetails() throws UserAlreadyExistException {
        Player player =new Player("anil@gmail.com","Anil","96655656760", "fsfg3","Chhapra", "Bihar", "vgsgv.jpg","cricket",  UserRole.PLAYER,UserGender.MALE);
        responseEntity= new ResponseEntity<>(player, HttpStatus.CREATED);
        Mockito.when(playerRepository.save(player)).thenReturn(player);
        try {
            assertEquals(responseEntity, playerService.saveDetails(player));
        }
       catch (Exception e ){

       }
    }
}
*/
