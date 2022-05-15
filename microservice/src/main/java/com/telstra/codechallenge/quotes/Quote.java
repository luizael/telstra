package com.telstra.codechallenge.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = false)
//@Data
public class Quote {
	private String type;
	private Value value;
	  
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	

  @JsonIgnoreProperties(ignoreUnknown = true)
  //@Data
  public class Value {
	private String id;
	private String quote;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
    
    
  }
}
