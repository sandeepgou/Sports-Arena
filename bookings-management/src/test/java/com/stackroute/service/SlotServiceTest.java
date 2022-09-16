/*
package com.stackroute.service;

import com.stackroute.Slot.entity.Slot;
import com.stackroute.Slot.entity.Status;
import com.stackroute.Slot.repository.SlotRepository;
import com.stackroute.Slot.service.SlotServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SlotServiceTest {
    @InjectMocks
    SlotServiceImpl slotService;
    @Mock
    SlotRepository slotRepository;
    Slot slot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
    @Test
    public void testWhenSlotisCreated(){
        Slot checkSlot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
        Mockito.when(slotRepository.save(checkSlot)).thenReturn(slot);
        assertEquals(slot,slotService.createSlot(checkSlot));
    }
    @Test
    public void testWhenSlotisNotCreated(){
        Mockito.when(slotRepository.save(null)).thenReturn(null);
        assertEquals(null,slotService.createSlot(null));
    }
    @Test
    public void testWhenslotisUpdated(){
        Slot checkSlot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
        Mockito.when(slotRepository.existsById("SlotID-1")).thenReturn(true);
        Mockito.when(slotRepository.save(checkSlot)).thenReturn(slot);
        assertEquals(slot.toString(),slotService.updateSlot(checkSlot).toString());
    }
@Test
    public void testWhenSlotisNotUpdated(){
        Slot slot1 = null;
    Slot checkSlot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
        Mockito.when(slotRepository.existsById("SlotID-1")).thenReturn(false);
        assertEquals(slot1,slotService.updateSlot(checkSlot));
    }
    @Test
    public void testWhenGetSlotsisSuccess(){
        List<Slot> checkslots =new ArrayList<>();
        Slot checkSlot1 = new Slot("SlotID-1","groundId-1",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
        Slot checkSlot2 = new Slot("SlotID-2","groundId-2",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
        checkslots.add(checkSlot1);
        checkslots.add(checkSlot2);
        Mockito.when(slotRepository.findAll()).thenReturn(checkslots);
        assertEquals(checkslots,slotService.getSlots());
    }
    @Test
    public void testWhenGetSlotsisFailure(){
        Mockito.when(slotRepository.findAll()).thenReturn(null);
        assertEquals(null,slotService.getSlots());
    }
    @Test
    public void testWhenGetSlotisSuccess(){
    Slot checkSlot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
    Mockito.when(slotRepository.findById("SlotID-1")).thenReturn(Optional.ofNullable(slot));
    assertEquals(slot.toString(),slotService.getSlot("SlotID-1").toString());

    }
    @Test
    public void testWhenGetSlotisFailure(){
       Mockito.when(slotRepository.findById(" ")).thenReturn(null);
       assertEquals(null,slotService.getSlot(" "));
    }


    @Test
    public void testWhenGetSlotByDateisSuccess(){
        List<Slot> checkslots =new ArrayList<>();
        Slot checkSlot1 = new Slot("SlotID-1","groundId-1",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
        Slot checkSlot2 = new Slot("SlotID-2","groundId-2",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
        checkslots.add(checkSlot1);
        checkslots.add(checkSlot2);
        Mockito.when(slotRepository.findBySlotDate(LocalDate.now())).thenReturn(checkslots);
        assertEquals(checkslots,slotService.getSlotByDate(LocalDate.now()));

    }

    @Test
    public void testWhenGetSlotByDateisFailure(){
        Mockito.when(slotRepository.findBySlotDate(LocalDate.now())).thenReturn(null);
        assertEquals(null,slotService.getSlotByDate(LocalDate.now()));
    }

    @Test
    public void testWhenDeleteSlotisSuccess(){
        Slot checkSlot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
        Mockito.when(slotRepository.existsById("SlotID-1")).thenReturn(true);
     assertEquals(true,slotService.deleteSlot("SlotID-1"));

    }


    @Test
    public void testWhenDeleteSlotisFailure(){
        Slot checkSlot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
        Mockito.when(slotRepository.existsById("SlotID-1")).thenReturn(false);
        assertEquals(false,slotService.deleteSlot("SlotID-1"));

    }



}
*/
