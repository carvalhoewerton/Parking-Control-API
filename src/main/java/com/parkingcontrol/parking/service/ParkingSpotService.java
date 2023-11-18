package com.parkingcontrol.parking.service;

import com.parkingcontrol.parking.model.ParkingSpot;
import com.parkingcontrol.parking.repository.ParkingSpotRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ParkingSpotService {
    @Autowired
    public ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpot save (ParkingSpot parkingSpot){
        return parkingSpotRepository.save(parkingSpot);
    }


    public List<ParkingSpot> findALl() {
        return parkingSpotRepository.findAll();
    }

    public Object findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    public void delete(UUID id) {
        parkingSpotRepository.deleteById(id);
    }
}
