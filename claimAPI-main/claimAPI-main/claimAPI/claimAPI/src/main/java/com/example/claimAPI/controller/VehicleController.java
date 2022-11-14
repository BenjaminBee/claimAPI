package com.example.claimAPI.controller;

import com.example.claimAPI.model.vehicle.Vehicle;
import com.example.claimAPI.model.vehicle.VehicleRepository;
import com.example.claimAPI.model.vehicle.VehicleRequest;
import com.example.claimAPI.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;

    Logger logger = LoggerFactory.getLogger(VehicleController.class);

    //Register quote into the system
    @PostMapping(path="/registerVehicle")
    public ResponseEntity<?> registerVehicle(@RequestBody VehicleRequest vehicleRequest) {
        if(vehicleService.registerVehicle(vehicleRequest)) {
            logger.trace("Vehicle successfully registered");
            return ResponseEntity.ok("Registered Successfully");
        }
        logger.trace("Vehicle unsuccessfully registered");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not accepted or vehicle " +
                "exists already");
    }

    //Returns a specific vehicle searched for using vehicleID
    @PostMapping(path="/getVehicle")
    public ResponseEntity<?> getVehicle(@RequestBody VehicleRequest vehicleRequest) {
        if (vehicleService.getVehicle(vehicleRequest) != null) {
            logger.trace("Vehicle found");
            return ResponseEntity.ok(vehicleService.getVehicle(vehicleRequest));
        }
        logger.trace("Vehicle could not be found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    //Returns a list of all vehicles
    @GetMapping(path="/getVehicles")
    public ResponseEntity<?> getVehicles() {
        Iterable<Vehicle> iterable = vehicleRepository.findAll();
        if(iterable == null) {
            logger.trace("Could not find vehicles");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        logger.trace("Returned list of vehicles successfully");
        return ResponseEntity.ok(iterable);
    }
}
