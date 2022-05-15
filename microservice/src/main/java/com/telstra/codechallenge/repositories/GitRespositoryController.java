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

import com.telstra.codechallenge.decorators.ResponseImpl;
import com.telstra.codechallenge.decorators.ResponseWithLocalSizeDecorator;

@RestController
public class GitRespositoryController {
	
	@Autowired
	private GitRepositoryService repositoriesService; 
	
	@Autowired
	private GitConfigureMethod config;
	
	public GitRespositoryController(){}
	
	@RequestMapping(path = "/repositories", method = RequestMethod.GET)
	public List<GitOperable> getHotRepositories(@RequestParam Integer repositoriesNumber)throws JsonProcessingException{
		List<GitOperable> response = config.buildResponseJsonToObject(repositoriesService.repositoriesSetParameter( repositoriesNumber ).getBody(), new GitRepository());
		response = new ResponseWithLocalSizeDecorator( new ResponseImpl( response ), repositoriesNumber).buildResponse();
		return response;
	}
	
	@RequestMapping(path = "/olduser", method = RequestMethod.GET)	
	public List<GitOperable>getOldestUser(@RequestParam Integer usersNumber) throws JsonProcessingException{
		List<GitOperable> response = config.buildResponseJsonToObject( repositoriesService.usersSetParameter( usersNumber ).getBody(), new GitUser() );
		response = new ResponseWithLocalSizeDecorator( new ResponseImpl( response ), usersNumber).buildResponse();
		return response;
	}
		
	
}
