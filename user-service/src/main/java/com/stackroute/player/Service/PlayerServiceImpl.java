package com.stackroute.player.Service;
import com.stackroute.player.Repository.PlayerRepository;
import com.stackroute.player.custom.exception.UserAlreadyExistException;
import com.stackroute.player.model.Player;

import com.stackroute.rabbitMQ.Producer;
import com.stackroute.rabbitMQ.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    Producer producer;

    //save the player details
    @Override
    public Player saveDetails(Player player) throws UserAlreadyExistException {
        UserDTO userdto=new UserDTO();
        userdto.setUserEmail(player.getUserEmail());
        userdto.setPassword(player.getPassword());
        userdto.setRole("newplayer");
        if(playerRepository.existsById(player.getUserEmail()))
        {
            System.out.println(playerRepository.findById(player.getUserEmail()));
            throw new UserAlreadyExistException();
        }
        else{
            playerRepository.save(player);
            producer.sendMessageToRabbitMq(userdto);
       }
        return player;

    }

    //find by userEmail
    @Override
    public Player getByUserEmail(String userEmail){
        Player player = playerRepository.findByUserEmail(userEmail);
        return player;
    }

    //find the list of player based on city
    @Override
    public List<Player> getListOfPlayerBasedOnCity(String city) {
        List<Player> players = playerRepository.findByCity(city);
        return players;
    }

    // update the player profile
    @Override
    public Player updatePlayer(Player player) {
        Player player1 = playerRepository.findByUserEmail(player.getUserEmail());
        player1.setName(player.getName());
        player1.setPhoneNumber(player.getPhoneNumber());
        player1.setPassword(player.getPassword());
        player1.setCity(player.getCity());
        player1.setState(player.getState());
        player1.setProfilePhoto(player.getProfilePhoto());
        player1.setFavSport(player.getFavSport());
        player1.setGender(player.getGender());
        return playerRepository.save(player1);
    }

    //found the list of all players
    @Override
    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players;
    }

    //delete a player based on the email id
    @Override
    public void deletePlayer(String userEmail) {
        playerRepository.deleteById(userEmail);
    }

    //find the list of player based on sport
    @Override
    public List<Player> getListOfPlayerBasedOnFavSport(String favSport) {
        return playerRepository.findByFavSport(favSport);
    }

    //find the list of player based on sport and city
    @Override
    public List<Player> getListOfPlayerBasedOnFavSportAndCity(String favSport, String city) {
        return playerRepository.findByCityAndFavSport(favSport, city);

    }

}