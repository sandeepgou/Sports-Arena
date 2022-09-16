package com.stackroute.repository;

import com.stackroute.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    Chat findByPlayerAEmailIdAndPlayerBEmailId(String playerAEmailId,String playerBEmailId);
    List<Chat> findByPlayerAEmailId(String playerAEmailId);
}
