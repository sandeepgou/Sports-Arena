//package com.stackroute.controller;
//
//import com.stackroute.Exception.SlotNotCreatedException;
//import com.stackroute.ID.SequenceGenerator;
//import com.stackroute.Slot.controller.SlotController;
//import com.stackroute.Slot.entity.Slot;
//import com.stackroute.Slot.entity.Status;
//import com.stackroute.Slot.service.SlotServiceImpl;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.stackroute.Slot.entity.Slot.SEQUENCE;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//public class SlotControllerTest {
//    @InjectMocks
//    SlotController slotController;
//    @Mock
//    SlotServiceImpl slotService;
//    @Mock
//    SequenceGenerator sequenceGenerator;
//    Slot   slot = new Slot("SlotID-1","groundId-1", LocalTime.MIDNIGHT,LocalTime.NOON, Status.AVAILABLE, LocalDate.now());
//    List<Slot> slots = new ArrayList<>();
//
//
//
//    ResponseEntity responseEntity1;
//    ResponseEntity responseEntity;
//    @Test
//    public void testWhenSlotisCreated() throws SlotNotCreatedException {
//        Slot checkSlot = new Slot("SlotID-1","groundId-1",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
//        responseEntity = new ResponseEntity<>(slot, HttpStatus.CREATED);
//        checkSlot.setSlotId("SlotID-"+ Mockito.when(sequenceGenerator.sequenceNumber(SEQUENCE)).thenReturn(1));
//        Mockito.when(slotService.createSlot(checkSlot)).thenReturn(slot);
//        assertEquals(responseEntity.toString(),slotController.createSlot(checkSlot).toString());
//
//    }
//    @Test
//    public void testWhenSlotisNotCreated() throws SlotNotCreatedException{
//        Slot checkSlot = null;
//        responseEntity = new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
//        Mockito.when(slotService.createSlot(checkSlot)).thenReturn(slot);
//        assertEquals(responseEntity,slotController.createSlot(checkSlot));
//
//    }
//    @Test
//    public void testWhenSlotisUpdated()  {
//        Slot checkSlot = new Slot("SlotID-1","groundId-1",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
//        responseEntity = new ResponseEntity<>(slot, HttpStatus.CREATED);
//        Mockito.when(slotService.updateSlot(checkSlot)).thenReturn(slot);
//        assertEquals(responseEntity.toString(),slotController.updateSlot(checkSlot).toString());
//    }
//    @Test
//    public void testWhenSlotisNotUpdated(){
//        Slot checkSlot = null;
//        responseEntity = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        Mockito.when(slotService.updateSlot(checkSlot)).thenReturn(null);
//        assertEquals(responseEntity,slotController.updateSlot(checkSlot));
//    }
//
//    @Test
//    public void testWhenGetList(){
//        List<Slot> checkslots =new ArrayList<>();
//        Slot checkSlot1 = new Slot("SlotID-1","groundId-1",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
//        Slot checkSlot2 = new Slot("SlotID-2","groundId-2",LocalTime.now(),LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
//        checkslots.add(checkSlot1);
//        checkslots.add(checkSlot2);
//        responseEntity = new ResponseEntity<>(checkslots,HttpStatus.FOUND);
//        Mockito.when(slotService.getSlots()).thenReturn(checkslots);
//
//        assertEquals(responseEntity,slotController.getAllSlots());
//
//
//    }
//    @Test
//    public void testWhenListEmpty(){
//        List<Slot> checkslots =new ArrayList<>();
//        responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        Mockito.when(slotService.getSlots()).thenReturn(checkslots);
//        assertEquals(responseEntity,slotController.getAllSlots());
//    }
//    @Test
//    public void testWhenGetSlot(){
//        slots.add(slot);
//        Slot checkSlot = new Slot("SlotID-1","groundId-1",LocalTime.MIDNIGHT,LocalTime.NOON,Status.AVAILABLE,LocalDate.now());
//        responseEntity= new ResponseEntity(checkSlot,HttpStatus.FOUND);
//        responseEntity1 = new ResponseEntity(slots,HttpStatus.FOUND);
//        Mockito.when(slotService.getSlot("SlotID-1")).thenReturn(slot);
//        Mockito.when(slotService.getSlotByDate(LocalDate.now())).thenReturn(slots);
//        assertEquals(responseEntity.toString(),slotController.getSlot("SlotID-1").toString());
//        assertEquals(responseEntity1,slotController.getSlotByDate(LocalDate.now()));
//    }
//
//    @Test
//    public void testWhenSlotisNull(){
//        responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
//        Mockito.when(slotService.getSlot(" ")).thenReturn(null);
//        Mockito.when(slotService.getSlotByDate(LocalDate.now())).thenReturn(null);
//        assertEquals(responseEntity,slotController.getSlot(" "));
//        assertEquals(responseEntity,slotController.getSlotByDate(LocalDate.now()));
//
//    }
//    @Test
//    public void testWhenDeleteSuccess(){
//        responseEntity = new ResponseEntity("Deleted Successfully",HttpStatus.OK);
//        Mockito.when(slotService.deleteSlot("SlotID-1")).thenReturn(true);
//        assertEquals(responseEntity,slotController.deleteSlot("SlotID-1"));
//    }
//    @Test
//    public void testWhenDeleteFail(){
//        responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
//        Mockito.when(slotService.deleteSlot(" ")).thenReturn(false);
//        assertEquals(responseEntity,slotController.deleteSlot(" "));
//    }
//
//}
