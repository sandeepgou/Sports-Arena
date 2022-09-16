package com.stackroute.Slot.service;

import com.stackroute.Slot.entity.Slot;
import com.stackroute.Slot.repository.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SlotServiceImpl implements SlotService{
    @Autowired
    private SlotRepository slotRepository;
    @Override
    public Slot createSlot(Slot slot) {
        try{
            return slotRepository.save(slot);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

@Override
    public Slot updateSlot(Slot slot) {
        try{


                Slot updatedSlot = slotRepository.findById(slot.getSlotId()).get();
                updatedSlot.setSlotDate(LocalDate.parse(slot.getSlotDate().format(DateTimeFormatter.ISO_DATE)));
                updatedSlot.setEndTime(slot.getEndTime());
                updatedSlot.setGroundID(slot.getGroundID());
                updatedSlot.setStatus(slot.getStatus());
                updatedSlot.setStartTime(slot.getStartTime());
                updatedSlot.setSlotId(slot.getSlotId());
                slotRepository.save(updatedSlot);
                return updatedSlot;

        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Slot> getSlots() {
        try{
            return slotRepository.findAll();
        }catch(Exception ex){
            return null;
        }

    }

    @Override
    public Slot getSlot(String slotID) {
        try{
         Slot slot =  slotRepository.findById(slotID).orElse(null);

            return slot;
        }catch (Exception ex){
            return null;
        }

    }

    @Override
    public List<Slot> getSlotByGroundID(String groundID) {
        try{
            System.out.println(slotRepository.findByGroundID(groundID));
            return slotRepository.findByGroundID(groundID);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean deleteSlot(String slotID) {
        if(slotRepository.existsById(slotID)){
            slotRepository.deleteById(slotID);
            return true;
        }
        return false;
    }

    @Override
    public List<Slot> getSlotByDate(LocalDate date) {
        List<Slot> slots = slotRepository.findBySlotDate(date);
        if(slots==null){
            return null;
        }
        return slots;
    }


}
