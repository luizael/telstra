package com.telstra.codechallenge.quotes;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpringBootQuotesService {

  @Value("${new.quotes.base.url}")
  private String quotesBaseUrl;

  private RestTemplate restTemplate;

  public SpringBootQuotesService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  /**
   * Returns an array of spring boot quotes. Taken from https://spring.io/guides/gs/consuming-rest/.
   *
   * @return - a quote array
   */
  public Quote[] getQuotes() {
	 return restTemplate.getForObject(quotesBaseUrl + "/api/quote", Quote[].class );
  }

  /**
   * Returns a random spring boot quote. Taken from https://spring.io/guides/gs/consuming-rest/.
   *
   * @return - a quote
   */
  public Quote getRandomQuote() {
	List<Quote> quotes = Arrays.asList(getQuotes());
	Collections.shuffle(quotes, new Random());
    return quotes.get(0);
  }
}
