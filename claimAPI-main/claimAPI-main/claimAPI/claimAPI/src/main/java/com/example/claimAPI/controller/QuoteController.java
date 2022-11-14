package com.example.claimAPI.controller;
import com.example.claimAPI.model.quote.Quote;
import com.example.claimAPI.model.quote.QuoteRepository;
import com.example.claimAPI.model.quote.QuoteRequest;
import com.example.claimAPI.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/quote")
public class QuoteController {
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteRepository quoteRepository;

    Logger logger = LoggerFactory.getLogger(QuoteController.class);

    //Register quote into the system
    @PostMapping(path="/requestQuote")
    public ResponseEntity<?> requestQuote(@RequestBody QuoteRequest quoteRequest) {
        if(quoteService.requestQuote(quoteRequest)) {
            logger.trace("Quote successfully registered");
            return ResponseEntity.ok("Successful Application");
        }
        logger.trace("Quote unsuccessfully registered");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not accepted");
    }

    //Returns a specific quote searched for using quoteID
    @PostMapping(path="/getQuote")
    public ResponseEntity<?> getQuote(@RequestBody QuoteRequest quoteRequest) {
        if (quoteService.getQuote(quoteRequest) != null) {
            logger.trace("Quote found");
            return ResponseEntity.ok(quoteService.getQuote(quoteRequest));
        }
        logger.trace("Quote could not be found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
    }

    //Returns a list of all quotes returned
    @GetMapping(path="/getQuotes")
    public ResponseEntity<?> getQuotes() {
        Iterable<Quote> iterable = quoteRepository.findAll();
        if(iterable == null) {
            logger.trace("Could not find quotes");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        logger.trace("Returned list of quotes successfully");
        return ResponseEntity.ok(iterable);
    }
}