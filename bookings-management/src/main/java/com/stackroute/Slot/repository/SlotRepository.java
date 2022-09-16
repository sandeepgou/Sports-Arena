package com.stackroute.Slot.repository;

import com.stackroute.Slot.entity.Slot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository

public interface SlotRepository extends MongoRepository<Slot, String> {

    List<Slot> findBySlotDate(LocalDate date);
    List<Slot> findByGroundID(String groundID);
}
