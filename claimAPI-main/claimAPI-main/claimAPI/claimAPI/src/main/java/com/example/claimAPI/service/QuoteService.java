package com.example.claimAPI.service;

import com.example.claimAPI.model.quote.Quote;
import com.example.claimAPI.model.quote.QuoteRepository;
import com.example.claimAPI.model.quote.QuoteRequest;
import com.example.claimAPI.model.vehicle.Vehicle;
import com.example.claimAPI.model.vehicle.VehicleRepository;
import com.example.claimAPI.util.quoteBrackets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private VehicleRepository vehicleRepository;

    Logger logger = LoggerFactory.getLogger(QuoteService.class);

    //Creates a new quote instance
    public boolean requestQuote(QuoteRequest quoteRequest) {
        Quote quote = new Quote(quoteRequest.getUserID(), quoteRequest.getVehicleID(), quoteRequest.getQuoteTotal());
        quoteRepository.save(quote);
        logger.trace("Quote details saved within quote repository");
        return true;
    }

    public Quote setBracket(Quote quote, Vehicle vehicle) {
        if(vehicle.getValue() <= 10000) {
            quote.setQuoteBracket(quoteBrackets.BRONZE);
        } else if(vehicle.getValue() <= 20000) {
            quote.setQuoteBracket(quoteBrackets.SILVER);
        } else {
            quote.setQuoteBracket(quoteBrackets.GOLD);
        }
        quoteRepository.save(quote);
        logger.trace("Quote bracket saved within quote repository");
        return quote;
    }

    //Return a specific quote
    public Quote getQuote(QuoteRequest quoteRequest) {
        Vehicle vehicle = vehicleRepository.findById(quoteRequest.getVehicleID()).get();
        Quote quote = quoteRepository.findById(quoteRequest.getQuoteID()).get();
        try {
            setBracket(quote, vehicle);
            switch(quote.getQuoteBracket()) {
                case GOLD :
                    quote.setQuoteTotal(vehicle.getValue());
                    break;
                case SILVER:
                    quote.setQuoteTotal(1.5 * vehicle.getValue());
                    break;
                case BRONZE:
                    quote.setQuoteTotal(2 * vehicle.getValue());
                    break;
            }
            quoteRepository.save(quote);
            logger.trace("Quote amount saved within quote repository");
            return quote;
        }
        catch(Exception ex) {
            logger.error("Quote amount update failed: " + ex);
        }
        return null;
    }
}
