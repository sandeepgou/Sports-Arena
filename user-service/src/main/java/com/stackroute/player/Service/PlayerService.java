package com.stackroute.player.Service;

import com.stackroute.player.custom.exception.UserAlreadyExistException;
import com.stackroute.player.model.Player;
import java.util.List;

public interface PlayerService {

    //save the player details
    Player saveDetails(Player player) throws UserAlreadyExistException;

    //found the list of all players
    List<Player> getAllPlayers();

    //find the player based on the Email
    Player getByUserEmail(String userEmail);

    //delete the player based on email
    void deletePlayer(String userEmail);

    //find the list of player based on the favSport
    List<Player> getListOfPlayerBasedOnFavSport(String favSport);

    //find the player based on the city
    List<Player> getListOfPlayerBasedOnCity(String city);

    //find the list of player based on sport and city
    List<Player> getListOfPlayerBasedOnFavSportAndCity(String favSport, String city);

    // update the player profile
    Player updatePlayer(Player player);

}