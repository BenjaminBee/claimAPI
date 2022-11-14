package com.example.claimAPI.model.quote;

import com.example.claimAPI.util.Status;
import com.example.claimAPI.util.quoteBrackets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuoteRequest {
    private long quoteID;
    private long userID;
    private long vehicleID;
    private double quoteTotal;
    private quoteBrackets quoteBracket;
}
