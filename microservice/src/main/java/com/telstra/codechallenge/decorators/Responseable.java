package com.telstra.codechallenge.decorators;

import java.util.List;

import com.telstra.codechallenge.repositories.GitOperable;

public interface Responseable {

	public List<GitOperable> buildResponse();
}
