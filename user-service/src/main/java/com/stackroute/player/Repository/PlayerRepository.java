package com.stackroute.player.Repository;

import com.stackroute.player.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {

    Player findByUserEmail(String userEmail);

    List<Player> findByCity(String city);

    List<Player> findByFavSport(String favSport);

    List<Player> findByCityAndFavSport(String city, String favSport);

}