package com.stackroute.groundOwner.Service;
import com.stackroute.groundOwner.customException.GroundOwnerAlreadyExistException;
import com.stackroute.groundOwner.model.GroundOwner;
import java.util.List;

public interface GroundOwnerService {

    //Save groundOwner Details
    GroundOwner saveDetails(GroundOwner groundOwner) throws GroundOwnerAlreadyExistException;
    GroundOwner getDetailsByGroundOwner(String ownerEmail);

    GroundOwner updateGroundOwner(GroundOwner groundOwner);

    List<GroundOwner> getAllGroundOwner();

    List<GroundOwner> getListOnCityAndGroundType(String city,  String groundType);

    List<GroundOwner> getListBasedOnCity(String city);

    List<GroundOwner> getListBasedOnGroundType(String groundType);

    List<GroundOwner> getListBasedOnEmail(String ownerEmail);

    void deleteGroundOwner(String groundID);

     GroundOwner fetchByGroundID(String groundID);
}
