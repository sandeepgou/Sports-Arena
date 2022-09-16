package com.stackroute.repository;

import com.stackroute.Entity.Teamup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamupRepository extends MongoRepository<Teamup, String> {
    
    List<Teamup> findByRequestedPlayerEmail(String requestedPlayerEmail);
    List<Teamup> findBySenderEmail(String senderEmail);
    Teamup findByRequestId(String requestId);
}
