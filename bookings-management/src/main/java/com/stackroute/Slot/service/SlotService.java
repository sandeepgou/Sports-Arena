package com.stackroute.Slot.service;

import com.stackroute.Slot.entity.Slot;

import java.time.LocalDate;
import java.util.List;

public interface SlotService {
    Slot createSlot(Slot slot);

    Slot updateSlot(Slot slot);

    List<Slot> getSlots();

    Slot getSlot(String slotID);

    List<Slot> getSlotByGroundID(String groundID);
    boolean deleteSlot(String slotID);

    List<Slot> getSlotByDate(LocalDate date);
}
