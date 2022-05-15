package com.telstra.codechallenge.repositories;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitRepositoryService {
	
	@Value("${git.hub.uri.repositories}")
	private String gitHubEndpoint;
	
	private RestTemplate restTemplate;
	private StringBuilder stbQParameter;
	private Map<String,String> params;
	private HttpEntity<String> response;
	
	public GitRepositoryService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public HttpEntity<String> repositoriesSetParameter(Integer repositoriesNumber) {
		String urlParams = "q={qParameters}&sort={sortValue}&order={orderValue}";
		stbQParameter = new StringBuilder();
		stbQParameter.append("created:>2022-05-10");//last weekend
		//ps: I didn't find that github parameter in github documentation to append with created key 
		params = new HashMap<>();
		params.put("qParameters", stbQParameter.toString() );
		params.put("sortValue", "starts");
		params.put("orderValue", "desc");
		response = clientSetHeader("/repositories?", urlParams, params);
		return response;	
	}
	
	public HttpEntity<String> usersSetParameter(Integer usersNumber){
		String urlParams = "q={qParameters}&sort={sortValue}&order={orderValue}";
		stbQParameter = new StringBuilder();
		stbQParameter.append("followers:0");//number of followers
		params = new HashMap<>();
		params.put("qParameters", stbQParameter.toString() );
		params.put("sortValue", "joined");
		params.put("orderValue", "asc");
		response = clientSetHeader("/users?", urlParams, params);
		return response;
	}
	
	protected HttpEntity<String> clientSetHeader(String url, String urlParams, Map<String,String> params){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/vnd.github.preview");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		response = callGitHubApi( gitHubEndpoint + url + urlParams, entity, params );
		return response;	
	}
	
	protected HttpEntity<String> callGitHubApi(String url,HttpEntity<String> entity, Map<String,String> params){
		response = restTemplate.exchange( url, HttpMethod.GET, entity, String.class, params);
		return response;	
	} 
}
