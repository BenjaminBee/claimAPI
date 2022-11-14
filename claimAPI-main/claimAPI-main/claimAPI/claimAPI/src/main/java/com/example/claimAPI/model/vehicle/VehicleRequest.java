package com.example.claimAPI.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
    private long vehicleID;
    private long userID;
    private String carReg;
    private String make;
    private String model;
    private int age;
    private double value;
    private String yearOfRegistration;
}
