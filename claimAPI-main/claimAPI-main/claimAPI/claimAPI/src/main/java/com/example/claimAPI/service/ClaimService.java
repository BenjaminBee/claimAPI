package com.example.claimAPI.service;

import com.example.claimAPI.controller.AccountController;
import com.example.claimAPI.model.claim.Claim;
import com.example.claimAPI.model.claim.ClaimRequest;
import com.example.claimAPI.model.claim.ClaimRepository;
import com.example.claimAPI.model.quote.Quote;
import com.example.claimAPI.model.quote.QuoteRepository;
import com.example.claimAPI.model.vehicle.Vehicle;
import com.example.claimAPI.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimService {
    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private QuoteRepository quoteRepository;

    Logger logger = LoggerFactory.getLogger(ClaimService.class);

    //Creates a new claim instance
    public boolean registerClaim(ClaimRequest claimRequest) {
        Claim claim = new Claim(claimRequest.getQuoteID(), claimRequest.getUserID(), claimRequest.getInsuranceNo(), claimRequest.getVin(), claimRequest.getClaimAmount());
        Quote quote = quoteRepository.findById(claimRequest.getQuoteID()).get();
        claim.setClaimAmount(quote.getQuoteTotal());
        claimRepository.save(claim);
        logger.trace("Claim details saved within claim repository");
        return true;
    }

    //Return a specific claim
    public Claim getClaim(ClaimRequest claimRequest) {
        Claim claim = claimRepository.findByVinAndInsuranceNo(claimRequest.getVin(), claimRequest.getInsuranceNo()).get();
        try {
            logger.trace("Claim found");
            return claim;
        }
        catch(Exception ex) {
            logger.error("Claim could not be found: " + ex);
        }
        return null;
    }

    /*
    //Delete a specific claim
    //ARCHIVE RATHER THAN DELETE
    public boolean deleteClaim(ClaimRequest claimRequest) {
        Claim claim = claimRepository.findById(claimRequest.getClaimID()).get();
        claimRepository.delete(claim);
        return true;
    }*/

    //Accepts or denies a claim
    public boolean updateClaimStatus(ClaimRequest claimRequest) {
        Claim claim = claimRepository.findByClaimID(claimRequest.getClaimID()).get();
        try{
            if(claimRequest.isDecision()) {
                claim.setClaimStatus(Status.ACCEPTED);
            } else {
                claim.setClaimStatus(Status.DENIED);
            }
            claimRepository.save(claim);
            logger.trace("Claim status updated within claim repository");
            return true;
        }
        catch(Exception ex) {
            logger.error("Claim status could not be updated: " + ex);
        }
        return false;
    }
}
