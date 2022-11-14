package com.example.claimAPI.controller;

import com.example.claimAPI.model.claim.Claim;
import com.example.claimAPI.model.claim.ClaimRequest;
import com.example.claimAPI.service.ClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.claimAPI.model.claim.ClaimRepository;

@RestController
@RequestMapping(path = "/claims")
public class ClaimController {
    @Autowired
    private ClaimService claimService;
    @Autowired
    private ClaimRepository claimRepository;

    Logger logger = LoggerFactory.getLogger(ClaimController.class);

    //Register claim into the system
    @PostMapping(path="/regClaim")
    public ResponseEntity<?> registerClaim(@RequestBody ClaimRequest claimRequest) {
        if(claimService.registerClaim(claimRequest)) {
            logger.trace("Claim registered");
            return ResponseEntity.ok("Claim accepted");
        }
        logger.trace("Claim registration data not accepted");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not accepted");
    }

    //Returns a specific claim searched for using insuranceNo and VIN number
    @PostMapping(path="/getClaim")
    public ResponseEntity<?> getClaim(@RequestBody ClaimRequest claimRequest) {
        if (claimService.getClaim(claimRequest) != null) {
            logger.trace("Claim found");
            return ResponseEntity.ok(claimService.getClaim(claimRequest));
        }
        logger.trace("Claim could not be found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    //Returns a list of all claims made
    @GetMapping(path="/getClaims")
    public ResponseEntity<?> getClaims() {
        Iterable<Claim> iterable = claimRepository.findAll();
        if(iterable == null) {
            logger.trace("List of claims returned");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        logger.trace("Could not find claims");
        return ResponseEntity.ok(iterable);
    }

    //Update a claim status to either accepted or denied
    @PatchMapping(path="/updateClaim")
    public ResponseEntity<?> updateClaimStatus(@RequestBody ClaimRequest claimRequest) {
        if(claimService.updateClaimStatus(claimRequest)) {
            logger.trace("Claim details updated");
            return ResponseEntity.ok("Claim Updated");
        }
        logger.trace("Claim could not be found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Claim not found");
    }
}