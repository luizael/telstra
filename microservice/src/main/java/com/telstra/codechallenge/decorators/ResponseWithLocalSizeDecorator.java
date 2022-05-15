package com.telstra.codechallenge.decorators;

import java.util.List;
import java.util.stream.Collectors;

import com.telstra.codechallenge.repositories.GitOperable;

public class ResponseWithLocalSizeDecorator extends ResponseDecorator {
	
	private Integer size;
	
	public ResponseWithLocalSizeDecorator(Responseable response, Integer size){
		super( response );
		this.size = size;
	}
	
	@Override
	public List<GitOperable> buildResponse() {
		List<GitOperable> resp = super.buildResponse();
		return resp.stream().limit(size).collect(Collectors.toList());
	}

}
