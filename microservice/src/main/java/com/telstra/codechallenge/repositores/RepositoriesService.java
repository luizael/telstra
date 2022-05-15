package com.telstra.codechallenge.repositores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RepositoriesService {
	
	@Value("${git.hub.uri.repositories}")
	private String gitHubEndpoint;
	private String url = "/repositories?";
	
	private RestTemplate restTemplate;
	
	public RepositoriesService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		
	}
	
	public HttpEntity<String> getRepositories(Integer repositoriesNumber) {
		String urlParams = "q={qParameters}&sort={sortValue}&order={orderValue}";
		StringBuilder stbQParameter = new StringBuilder();
		
		stbQParameter.append("created:>2022-05-10");//last weekend
		//obs: I didn't find that github parameter in github documentation to append with created key 
		
		Map<String,String> params = new HashMap<>();
		params.put("qParameters", stbQParameter.toString() );
		params.put("sortValue", "starts");
		params.put("orderValue", "desc");
		
		return clientRequest(urlParams,params);	
	}
	
	public HttpEntity<String> getOldestUser(){
		String urlParams = "q={qParameters}&sort={sortValue}&order={orderValue}";
		StringBuilder stbQParameter = new StringBuilder();
		
		stbQParameter.append("followers:0");//last weekend
		
		Map<String,String> params = new HashMap<>();
		params.put("qParameters", stbQParameter.toString() );
		params.put("sortValue", "joined");
		params.put("orderValue", "asc");
		
		return clientRequest(urlParams, params);
	}
	
	private HttpEntity<String> clientRequest(String urlParams, Map<String,String> params){
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/vnd.github.preview");
		
		HttpEntity<String> entity = new HttpEntity<>(headers);
		HttpEntity<String> result = restTemplate.exchange(gitHubEndpoint + url + urlParams, HttpMethod.GET, entity, String.class, params);
		
		return result;	
	}
}
