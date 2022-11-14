package com.example.claimAPI.model.vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table (name = "vehicles")
public class Vehicle {
//    @SequenceGenerator(
//            name = "vehicle_sequence",
//            sequenceName = "vehicle_sequence",
//            allocationSize = 1
//    )
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "vehicle_sequence"
//    )
    private long vehicleID;
    private long userID;
    private String carReg;
    private String make;
    private String model;
    private int age;

    @Column (name = "vehicleValue")
    private double value;
    private String yearOfRegistration;

    public Vehicle(Long userID, String carReg, String make,
                   String model, int age, double value, String yearOfRegistration) {
        this.userID = userID;
        this.carReg = carReg;
        this.make = make;
        this.model = model;
        this.age = age;
        this.value = value;
        this.yearOfRegistration = yearOfRegistration;
    }
}
