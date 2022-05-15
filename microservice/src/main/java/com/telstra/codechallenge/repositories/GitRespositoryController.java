package com.telstra.codechallenge.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.decorators.ResponseDecorator;
import com.telstra.codechallenge.decorators.ResponseImpl;
import com.telstra.codechallenge.decorators.ResponseWithLocalSizeDecorator;
import com.telstra.codechallenge.decorators.Responseable;

@RestController
public class GitRespositoryController {
	
	@Autowired
	private GitRepositoryService repositoriesService; 
	
	@Autowired
	private GitSimpleFactory factory;
	
	public GitRespositoryController(){}
	
	@RequestMapping(path = "/repositories", method = RequestMethod.GET)
	public List<GitOperable> getHotRepositories(@RequestParam Integer repositoriesNumber)throws JsonProcessingException{
		List<GitOperable> response = buildResponseJsonToObject(repositoriesService.repositoriesSetParameter( repositoriesNumber ).getBody(), new GitRepository());
		response = new ResponseWithLocalSizeDecorator( new ResponseImpl( response ), repositoriesNumber).buildResponse();
		return response;
	}
	
	@RequestMapping(path = "/olduser", method = RequestMethod.GET)	
	public List<GitOperable>getOldestUser(@RequestParam Integer usersNumber) throws JsonProcessingException{
		List<GitOperable> response = buildResponseJsonToObject( repositoriesService.usersSetParameter( usersNumber ).getBody(), new GitUser() );
		response = new ResponseWithLocalSizeDecorator( new ResponseImpl( response ), usersNumber).buildResponse();
		return response;
	}
	
	protected List<GitOperable> buildResponseJsonToObject(String stringJson, GitOperable repos)throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode rootItems = objMapper.readTree( stringJson );
		
		Iterator it = rootItems.path("items").elements();
		
		List<GitOperable> repositories = new ArrayList<>();
		while( it.hasNext() ) {
			JsonNode node = (JsonNode) it.next();
			repositories.add( factory.createSimpleInstance( repos , node) );
		}
		return repositories;
	}
		
	
}
