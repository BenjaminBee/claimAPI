package com.example.claimAPI.model.quote;

import com.example.claimAPI.util.Status;
import com.example.claimAPI.util.quoteBrackets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "quotes")
public class Quote {

//    @SequenceGenerator(
//            name = "quotes",
//            sequenceName = "quote_sequence",
//            allocationSize = 1
//    )
    @Id
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "quote_sequence"
//    )
    private long quoteID;
    private long userID;
    private long vehicleID;
    private double quoteTotal;

    private quoteBrackets quoteBracket = quoteBrackets.UNDEFINED;

    public Quote(long userID, long vehicleID, double quoteTotal) {
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.quoteTotal = quoteTotal;
    }
}