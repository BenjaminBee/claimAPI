package com.example.claimAPI.service;

import com.example.claimAPI.model.user.User;
import com.example.claimAPI.model.vehicle.Vehicle;
import com.example.claimAPI.model.vehicle.VehicleRepository;
import com.example.claimAPI.model.vehicle.VehicleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.net.http.HttpRequest;
import java.text.NumberFormat;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    Logger logger = LoggerFactory.getLogger(VehicleService.class);

    //Registers new vehicle
    public boolean registerVehicle(VehicleRequest vehicleReq) {
        Optional<Vehicle> testVehicle = vehicleRepository.findByCarReg(vehicleReq.getCarReg());
        try {
            if(testVehicle.isEmpty() && vehicleReq.getValue() > 0 && vehicleReq.getAge() < 118) {
                Vehicle vehicle = new Vehicle(vehicleReq.getUserID(),
                        vehicleReq.getCarReg(), vehicleReq.getMake(),
                        vehicleReq.getModel(), vehicleReq.getAge(), vehicleReq.getValue(),
                        vehicleReq.getYearOfRegistration());
                vehicleRepository.save(vehicle);
                logger.trace("Vehicle details saved within vehicle repository");
                return true;
            }
        } catch(NumberFormatException ex) {
            logger.error("Data format error within RequestBody" + ex);
        }
        return false;
    }

    //Return a specific vehicle
    public Vehicle getVehicle(VehicleRequest vehicleReq) {

        Vehicle vehicle = vehicleRepository.findById(vehicleReq.getVehicleID()).get();
        try {
            logger.trace("Found vehicle through vehicle ID");
            return vehicle;
        }
        catch(NullPointerException ex) {
            logger.error("Vehicle could not be found within repository: " + ex);
        }
        return null;
    }
}
