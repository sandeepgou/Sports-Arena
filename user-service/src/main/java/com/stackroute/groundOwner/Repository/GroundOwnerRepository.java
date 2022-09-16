package com.stackroute.groundOwner.Repository;

import com.stackroute.groundOwner.model.GroundOwner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroundOwnerRepository extends MongoRepository<GroundOwner, String> {
    List<GroundOwner> findByCityAndGroundType(String city, String groundType);
    List<GroundOwner> findByCity(String City);
    List<GroundOwner> findByGroundType(String groundType);
    List<GroundOwner> findListByOwnerEmail(String ownerEmail);
    GroundOwner findByOwnerEmail(String ownerEmail);
    GroundOwner findByGroundID(String groundID);
}
