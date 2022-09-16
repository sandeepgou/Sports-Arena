package com.stackroute.Slot.controller;

import com.stackroute.Exception.SlotNotCreatedException;
import com.stackroute.ID.SequenceGenerator;
import com.stackroute.Slot.entity.Slot;
import com.stackroute.Slot.service.SlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.stackroute.Slot.entity.Slot.SEQUENCE;

@RestController
@RequestMapping("api/v1")
//@CrossOrigin("*")
public class SlotController {
    @Autowired
    private SlotServiceImpl slotService;
    @Autowired
    private SequenceGenerator sequenceGenerator;

    @PostMapping("/newslot")
    public ResponseEntity<Slot> createSlot(@RequestBody Slot slot) throws SlotNotCreatedException {
        try{

            slot.setSlotId("SlotID-"+sequenceGenerator.sequenceNumber(SEQUENCE));
            Slot newSlot = slotService.createSlot(slot);
            System.out.println(newSlot);
            if(newSlot==null){
                System.out.println("in if");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(newSlot, HttpStatus.CREATED);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/slot")
    public ResponseEntity<Slot> updateSlot(@RequestBody Slot slot){
        System.out.println(slot.getStatus());
        try{
            Slot newSlot = slotService.updateSlot(slot);

            return new ResponseEntity(newSlot, HttpStatus.CREATED);
        }catch(Exception ex){

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/slotslist")
    public ResponseEntity<List<Slot>> getAllSlots(){
        try{
            List<Slot> slots = slotService.getSlots();
            if(slots.isEmpty()){

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                return  new ResponseEntity<>(slots,HttpStatus.FOUND);
            }

        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/slotslist/groundID/{groundID}")
    public ResponseEntity<List<Slot>> getAllSlots(@PathVariable("groundID")String groundID){
        System.out.println("Inside get all slots");
        try{
            List<Slot> slots = slotService.getSlotByGroundID(groundID);
            return  new ResponseEntity<>(slots,HttpStatus.OK);

        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("slot/slotID/{slotID}")
    public ResponseEntity<Slot> getSlot(@PathVariable String slotID){
        try{
            Slot slot = slotService.getSlot(slotID);
            if(slot==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(slot,HttpStatus.FOUND);

        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("slot/date/{date}")
    public ResponseEntity<List<Slot>> getSlotByDate(@PathVariable (name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate slotDate){
        try{

            List<Slot> slots = slotService.getSlotByDate(slotDate);
            if(slots==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(slots,HttpStatus.FOUND);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/slot/slotID/{slotID}")
    public ResponseEntity deleteSlot(@PathVariable String slotID){
        try{
            if(slotService.deleteSlot(slotID)){
                return new ResponseEntity("Deleted Successfully",HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}
