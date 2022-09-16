
package com.stackroute.Booking.Controller;

import com.stackroute.Booking.Entity.BookSlot;
import com.stackroute.Booking.Service.BookSlotServiceImpl;
import com.stackroute.ID.SequenceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.stackroute.Slot.entity.Slot.SEQUENCE;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin("*")
public class BookSlotController {
    @Autowired
    private BookSlotServiceImpl bookSlotService;

    @Autowired
    private SequenceGenerator sequenceGenerator;
    @PostMapping("/newbooking")
    public ResponseEntity<BookSlot> bookSlot(@RequestBody BookSlot bookSlot) {
        try{
            bookSlot.setBookingID("BookingID-"+sequenceGenerator.sequenceNumber(SEQUENCE));
            BookSlot newBooking =  bookSlotService.bookSlot(bookSlot);
//            if(newBooking==null){
//                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//            }

            return new ResponseEntity<>(newBooking, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/booking")
    public  ResponseEntity<BookSlot> updateBooking(@RequestBody BookSlot bookSlot){
        try{
            BookSlot updateBooking = bookSlotService.updateBooking(bookSlot);

            return new ResponseEntity(updateBooking, HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/booking/ownerEmail/{ownerEmail}/status/{status}/date/{date}")
    public ResponseEntity<List<BookSlot>> getBookingsByOwnerEmailAndStatusAndDate(@PathVariable String ownerEmail,@PathVariable String status,@PathVariable (name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate slotDate){
        try{
            List<BookSlot> bookSlots = bookSlotService.getBookingsByOwnerEmailAndStatusAndDate(ownerEmail,status,slotDate);
            if(bookSlots==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookSlots,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/booking/ownerEmail/{ownerEmail}/status/{status}")
    public ResponseEntity<List<BookSlot>> getBookingsByOwnerEmailAndStatus(@PathVariable String ownerEmail, @PathVariable String status){
        try{
            List<BookSlot> bookSlots = bookSlotService.getBookingsByOwnerEmailAndStatus(ownerEmail,status);
            if(bookSlots==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookSlots,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @DeleteMapping("/booking/id/{bookingID}/status/{status}")
    public ResponseEntity<BookSlot> cancelBookingsByBookingIDAndStatus(@PathVariable String bookingID,@PathVariable String status){
        try{
            BookSlot bookSlot = bookSlotService.cancelBookingsByBookingIDAndStatus(bookingID,status);
            if(bookSlot==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookSlot,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookingslist/playerEmail/{playerEmail}")
    public ResponseEntity<List<BookSlot>> getAllBookings(@PathVariable("playerEmail")String playerEmail){
        try{
            List<BookSlot> allBookings = bookSlotService.getBookings(playerEmail);

                return new ResponseEntity<>(allBookings,HttpStatus.OK);


        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookingslist/playerEmail/{playerEmail}/status/{status}")
    public ResponseEntity<List<BookSlot>> getAllBookingsBasedOnStatus(@PathVariable("playerEmail")String playerEmail,@PathVariable("status")String status){
        try{
            List<BookSlot> allBookings = bookSlotService.getBookingsByStatus(playerEmail,status);

            return new ResponseEntity<>(allBookings,HttpStatus.OK);


        }catch(Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/booking/bookingID/{bookingID}")
    public ResponseEntity<BookSlot> getBooking(@PathVariable String bookingID){
        try{
            BookSlot booking = bookSlotService.getBooking(bookingID);
            if(booking==null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(booking,HttpStatus.FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/booking/date/{date}/playerEmail/{playerEmail}")
    public ResponseEntity<List<BookSlot>> getBookingByDateAndPlayerEmail(@PathVariable (name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate slotDate,@PathVariable("playerEmail")String playerEmail){
        try{

            List<BookSlot> bookings = bookSlotService.getBookingsByDate(slotDate,playerEmail);

            return new ResponseEntity<>(bookings,HttpStatus.OK);

        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/booking/playerEmail/{playerEmail}/status/{status}/date/{date}")
    public ResponseEntity<List<BookSlot>> getBookingsByPlayerEmailAndStatusAndDate(@PathVariable String playerEmail,@PathVariable String status,@PathVariable (name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate slotDate){
        try{
            List<BookSlot> bookSlots = bookSlotService.getBookingsByPlayerEmailAndStatusAndDate(playerEmail,status,slotDate);
            if(bookSlots==null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookSlots,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/booking/bookingID/{bookingID}")
    public ResponseEntity deleteSlot(@PathVariable String bookingID){
        try{
            if(bookSlotService.deleteBooking(bookingID)){
                return new ResponseEntity("Deleted Successfully",HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }catch(Exception ex){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
}

