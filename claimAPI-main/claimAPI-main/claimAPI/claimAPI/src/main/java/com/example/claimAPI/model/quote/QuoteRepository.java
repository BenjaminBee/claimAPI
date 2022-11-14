package com.example.claimAPI.model.quote;

import com.example.claimAPI.model.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

}