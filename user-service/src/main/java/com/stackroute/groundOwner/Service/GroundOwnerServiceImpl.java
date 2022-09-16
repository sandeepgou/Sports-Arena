package com.stackroute.groundOwner.Service;

import com.stackroute.groundOwner.Repository.GroundOwnerRepository;
import com.stackroute.groundOwner.customException.GroundOwnerAlreadyExistException;
import com.stackroute.groundOwner.model.GroundOwner;

import com.stackroute.rabbitMQ.Producer;
import com.stackroute.rabbitMQ.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroundOwnerServiceImpl implements GroundOwnerService {
    @Autowired
    private GroundOwnerRepository groundOwnerRepository;
    //Save groundOwner Details
    @Autowired
    private Producer producer;
    @Override
    public GroundOwner saveDetails(GroundOwner groundOwner) throws GroundOwnerAlreadyExistException {
        UserDTO userdto=new UserDTO();
        userdto.setUserEmail(groundOwner.getOwnerEmail());
        userdto.setPassword(groundOwner.getOwnerPassword());
        userdto.setRole("newground");
        if(groundOwnerRepository.findById(groundOwner.getGroundID()).isPresent())
        {
            throw new GroundOwnerAlreadyExistException();
        }
        else{
            groundOwnerRepository.save(groundOwner);
            producer.sendMessageToRabbitMq(userdto);
        }
        return groundOwner;
    }

    @Override
    public GroundOwner getDetailsByGroundOwner(String ownerEmail) {
        return groundOwnerRepository.findByOwnerEmail(ownerEmail);
    }

    @Override
    public List<GroundOwner> getAllGroundOwner() {
        List<GroundOwner> groundOwners= groundOwnerRepository.findAll();
        return groundOwners;
    }

    @Override
    public List<GroundOwner> getListOnCityAndGroundType(String city, String groundType) {
        return groundOwnerRepository.findByCityAndGroundType(city,groundType);
    }

    @Override
    public List<GroundOwner> getListBasedOnCity(String city) {
        return  groundOwnerRepository.findByCity(city);
    }

    @Override
    public List<GroundOwner> getListBasedOnGroundType(String groundType) {
      return groundOwnerRepository.findByGroundType(groundType);
    }

    @Override
    public GroundOwner fetchByGroundID(String groundID) {
        return groundOwnerRepository.findByGroundID(groundID);
    }

    @Override
    public List<GroundOwner> getListBasedOnEmail(String ownerEmail){
        return groundOwnerRepository.findListByOwnerEmail(ownerEmail);
    }

    @Override
    public void deleteGroundOwner(String groundID) {
        groundOwnerRepository.deleteById(groundID);
    }


    @Override
    public GroundOwner updateGroundOwner(GroundOwner groundOwner) {
        GroundOwner groundOwner1 = groundOwnerRepository.findByGroundID(groundOwner.getGroundID());
        groundOwner1.setOwnerEmail(groundOwner.getOwnerEmail());
        groundOwner1.setOwnerPassword(groundOwner.getOwnerPassword());
        groundOwner1.setGroundName(groundOwner.getGroundName());
        groundOwner1.setGroundType(groundOwner.getGroundType());
        groundOwner1.setAddressLineOne(groundOwner.getAddressLineOne());
        groundOwner1.setCity(groundOwner.getCity());
        groundOwner1.setAmenities(groundOwner.getAmenities());
        groundOwner1.setGroundPicture(groundOwner.getGroundPicture());
        groundOwner1.setPhoneNumber(groundOwner.getPhoneNumber());
        groundOwner1.setCharge(groundOwner.getCharge());
        return groundOwnerRepository.save(groundOwner1);
    }


}
