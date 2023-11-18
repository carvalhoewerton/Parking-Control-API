package com.parkingcontrol.parking.controller;

import com.parkingcontrol.parking.model.ParkingSpot;
import com.parkingcontrol.parking.model.ParkingSpotDto;
import com.parkingcontrol.parking.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    @Autowired
    public ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }


    @PostMapping
    public ResponseEntity<Object> saveParkingLot (@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        var parkingSpot = new ParkingSpot();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpot);
        parkingSpot.setRegistrationDate(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpot));

    }

    @GetMapping
    public ResponseEntity<List<ParkingSpot>> getAllParkingSpots(){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findALl());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findById(id));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        parkingSpotService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Parking spot deleted successfully");
    }

}
