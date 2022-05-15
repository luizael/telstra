package com.telstra.codechallenge.decorators;

import java.util.List;

import com.telstra.codechallenge.repositories.GitOperable;

public abstract class ResponseDecorator implements Responseable{

	
	private Responseable pageSizeable;
	
	public ResponseDecorator(Responseable responseable){
		this.pageSizeable = responseable;
	}
	
	public List<GitOperable> buildResponse(){
		return  pageSizeable.buildResponse();
	}
	
}
