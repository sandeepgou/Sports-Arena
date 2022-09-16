package com.stackroute.player.Controller;

import com.stackroute.player.Service.PlayerService;
import com.stackroute.player.custom.exception.UserAlreadyExistException;
import com.stackroute.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("*")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

     //sign up
     @PostMapping("/newplayer")
     public ResponseEntity<Player> saveDetails(@RequestBody Player player) throws UserAlreadyExistException {
         Player savePlayer= playerService.saveDetails(player);
         return new ResponseEntity<>(savePlayer, HttpStatus.CREATED);
    }

     // find the player by userEmail
    @GetMapping("/player/userEmail/{userEmail}")
    public Player getPlayerBasedOnEmail(@PathVariable("userEmail") String userEmail) {
         return playerService.getByUserEmail(userEmail);
    }

    //update the particular player
    @PutMapping("/player")
    public Player updatingPlayerDetails(@RequestBody Player player) {
         return playerService.updatePlayer( player);
    }

    //find the list of all player
    @GetMapping("/playerlist")
    public List<Player> getListOfPlayer() {
         List<Player> players = playerService.getAllPlayers();
         return players;
    }

    //delete the player by email
    @DeleteMapping("/removeplayer/userEmail/{userEmail}")
    public String deletePlayer(@PathVariable("userEmail") String userEmail) {
         playerService.deletePlayer(userEmail);
         return "delete Successfully";
    }

    // find the player by favSport
    @GetMapping("/playerslist/favSport/{favSport}")
    public List<Player> findListBasedOnFavSport(@PathVariable("favSport") String favSport) {
        List<Player> players = playerService.getListOfPlayerBasedOnFavSport(favSport);
        return players;
    }

    //based on city
    @GetMapping("/playerslist/city/{city}")
    public List<Player> findListBasedOnCity(@PathVariable("city") String city) {
        List<Player> players = playerService.getListOfPlayerBasedOnCity(city);
        return players;
    }

    // based on favSport and city
    @GetMapping("/playerlist/city/{city}/favSport/{favSport}")
    public List<Player> findListOfPlayerBasedOnFavSportAndCity(@PathVariable("city") String city, @PathVariable("favSport") String favSport){
        List<Player> playerList= playerService.getListOfPlayerBasedOnFavSportAndCity(city, favSport);
        return playerList;
    }

    @ExceptionHandler(value= UserAlreadyExistException.class)
    public  ResponseEntity<String> userAlreadyExistException(UserAlreadyExistException userAlreadyExistException){
        return  new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
    }
    }








