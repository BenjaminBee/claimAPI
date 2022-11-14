package com.example.claimAPI.model.claim;

import com.example.claimAPI.model.claim.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    Optional<Claim> findByVinAndInsuranceNo(String vin, String insuranceNo);
    Optional<Claim> findByClaimID(Long claimID);
}