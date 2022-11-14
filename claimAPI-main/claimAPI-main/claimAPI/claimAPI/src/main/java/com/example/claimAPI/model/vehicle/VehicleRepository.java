package com.example.claimAPI.model.vehicle;

import com.example.claimAPI.model.user.User;
import com.example.claimAPI.model.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findByCarReg(String CarReg);
}