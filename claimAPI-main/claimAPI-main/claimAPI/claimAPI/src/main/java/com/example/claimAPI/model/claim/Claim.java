package com.example.claimAPI.model.claim;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.example.claimAPI.util.Status;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table (name = "claims")
public class Claim {

//    @SequenceGenerator(
//            name = "claim_sequence",
//            sequenceName = "claim_sequence",
//            allocationSize = 1
//    )
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "claim_sequence"
//    )
    private long claimID;
    private long quoteID;
    private long userID;
    private String vin;
    private String insuranceNo;
    private double claimAmount;
    private Status claimStatus = Status.PENDING;


    public Claim(long quoteID, long userID, String insuranceNo, String vin, double claimAmount) {
        this.quoteID = quoteID;
        this.userID = userID;
        this.insuranceNo = insuranceNo;
        this.vin = vin;
        this.claimAmount = claimAmount;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimID=" + claimID +
                ", vin='" + vin + '\'' +
                ", insuranceNo='" + insuranceNo + '\'' +
//                ", claimAmount=" + claimAmount +
                '}';
    }
}
