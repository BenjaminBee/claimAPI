package com.example.claimAPI.model.claim;


import com.example.claimAPI.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimRequest {

    private long claimID;
    private long quoteID;
    private long userID;
    private String vin;
    private String insuranceNo;
    private double claimAmount;
    private Status claimStatus;
    private boolean decision;
}