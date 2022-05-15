package com.telstra.codechallenge.decorators;

import java.util.List;

import com.telstra.codechallenge.repositories.GitOperable;

public class ResponseImpl implements Responseable {
	
	private List<GitOperable> response;
	
	public ResponseImpl(List<GitOperable> response){
		this.response = response;
	}
	
	@Override
	public List<GitOperable> buildResponse() {
		return response;
	}

}
