package com.stackroute.groundOwner.Controller;

import com.stackroute.groundOwner.Service.GroundOwnerService;
import com.stackroute.groundOwner.customException.GroundOwnerAlreadyExistException;
import com.stackroute.groundOwner.model.GroundOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1")
//@CrossOrigin("*")
public class GroundOwnerController {
    @Autowired
   private  GroundOwnerService groundOwnerService;

    //Save groundOwner Details
    @PostMapping("/newground")
    public ResponseEntity<GroundOwner> saveGroundOwnerDetails(@RequestBody GroundOwner groundOwner) throws GroundOwnerAlreadyExistException {
        GroundOwner groundOwnerSave= groundOwnerService.saveDetails(groundOwner);
        return new ResponseEntity<>(groundOwnerSave, HttpStatus.CREATED);
    }

    //update the particular ground Owner
    @PutMapping("/ground")
    public GroundOwner updateGroundOwner(@RequestBody GroundOwner groundOwner) {
        return groundOwnerService.updateGroundOwner(groundOwner);
    }

    //find the list of ground owner
    @GetMapping("/groundslist")
    public List<GroundOwner> getListOfGroundOwner(){
        List<GroundOwner> groundOwners=groundOwnerService.getAllGroundOwner();
        return  groundOwners;
    }
    //find the list ground based on city and ground type
    @GetMapping("/groundslist/city/{city}/groundType/{groundType}")
    public  List<GroundOwner> getListOnCityAndGroundType(@PathVariable ("city") String city, @PathVariable("groundType") String groundType){
        List<GroundOwner> groundOwners=groundOwnerService.getListOnCityAndGroundType(city,groundType);
        return groundOwners;
    }

    //find the list ground based on city
    @GetMapping("/groundslist/city/{city}")
    public  List<GroundOwner> getListBasedOnCity(@PathVariable ("city") String city){
        List<GroundOwner> listGroundOwners=groundOwnerService.getListBasedOnCity(city);
        return listGroundOwners;
    }

    //find the list ground based on ground type
    @GetMapping("/groundslist/groundType/{groundType}")
    public  List<GroundOwner> getListBasedOnGroundType(@PathVariable ("groundType") String groundType){
      List<GroundOwner> listBasedOnGroundType  =groundOwnerService.getListBasedOnGroundType(groundType);
      return  listBasedOnGroundType;
    }

    //find the list of ground based on email
    @GetMapping("/groundslist/ownerEmail/{ownerEmail}")
    public List<GroundOwner> getGroundlistBasedOnEmail(@PathVariable("ownerEmail") String ownerEmail){
       List<GroundOwner> groundOwnerList=groundOwnerService.getListBasedOnEmail(ownerEmail);
       return  groundOwnerList;
    }
    //delete record based on the email id
    @DeleteMapping("/removeground/groundId/{groundID}")
    public  String deleteGroundOwner(@PathVariable("groundID") String groundID){
        groundOwnerService.deleteGroundOwner(groundID);
        return "Delete one Record";

    }

   // find the ground based on groundId
    @GetMapping("/ground/groundID/{groundID}")
    public GroundOwner getGroundBasedOnGroundId(@PathVariable("groundID") String groundID){
        return groundOwnerService.fetchByGroundID(groundID);
    }

    @GetMapping("/ground/ownerEmail/{ownerEmail}")
    public GroundOwner getGroundBasedOnOwnerEmail(@PathVariable("ownerEmail") String ownerEmail){
        return groundOwnerService.getDetailsByGroundOwner(ownerEmail);
    }


    @ExceptionHandler(value= GroundOwnerAlreadyExistException.class)
    public  ResponseEntity<String>  groundOwnerAlreadyExistException(GroundOwnerAlreadyExistException groundOwnerAlreadyExistException){
        return  new ResponseEntity<>("Ground ID Already Exist!!", HttpStatus.CONFLICT);
    }


}
