package com.telstra.codechallenge.repositores;

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

@RestController
public class RespositoresController {
	
	@Autowired
	private RepositoriesService repositoriesService; 
	
	public RespositoresController(){}
	
	@RequestMapping(path = "/repositories", method = RequestMethod.GET)
	public List<GitHubRepository> getHotRepositories(@RequestParam Integer repositoriesNumber){
		return buildObjects(repositoriesService.getRepositories( repositoriesNumber ).getBody(), new Repository());
	}
	
	@RequestMapping(path = "/olduser", method = RequestMethod.GET)	
	public List<GitHubRepository>getOldestUser(){
		return buildObjects( repositoriesService.getOldestUser().getBody(), new User() );
	}
	
	private List<GitHubRepository> buildObjects(String result, GitHubRepository repos){
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode rootItems = null;
		List<GitHubRepository> repositories = new ArrayList<>();
		try {
			rootItems = objMapper.readTree( result );
			Iterator it = rootItems.path("items").elements();
			while( it.hasNext() ) {
				JsonNode node = (JsonNode) it.next();
				repositories.add( getInstance(repos, node));
			}
		} catch (JsonProcessingException e) {
			// I miss 
		}
		return repositories;
	}
	
	private GitHubRepository getInstance(GitHubRepository repos, JsonNode node) {
		if(repos instanceof Repository) {
			((Repository)repos).setName( node.path("name").asText());
			((Repository)repos).setDescription( node.path("description").asText());
			((Repository)repos).setHtml_url( node.path("html_url").asText());
			((Repository)repos).setWatchers_count( node.path("watchers_count").asText());
			((Repository)repos).setLanguage( node.path("language").asText());
		}else if (repos instanceof User) {
			((User)repos).setId(node.path("id").asText());
			((User)repos).setLogin(node.path("login").asText());
			((User)repos).setHtml_url(node.path("htlm_url").asText());
		}
		return repos;
	}
	
}
