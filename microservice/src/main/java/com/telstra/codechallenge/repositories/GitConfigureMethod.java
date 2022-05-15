package com.telstra.codechallenge.repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class GitConfigureMethod {

	protected List<GitOperable> buildResponseJsonToObject(String stringJson, GitOperable repos)throws JsonProcessingException {
		ObjectMapper objMapper = new ObjectMapper();
		JsonNode rootItems = objMapper.readTree( stringJson );
		
		Iterator it = rootItems.path("items").elements();
		
		List<GitOperable> repositories = new ArrayList<>();
		while( it.hasNext() ) {
			JsonNode node = (JsonNode) it.next();
			repositories.add( createSimpleInstance( repos , node) );
		}
		return repositories;
	}
	
	protected GitOperable createSimpleInstance(GitOperable repos, JsonNode node) {
		if(repos instanceof GitRepository) {
			((GitRepository)repos).setName( node.path("name").asText());
			((GitRepository)repos).setDescription( node.path("description").asText());
			((GitRepository)repos).setHtml_url( node.path("html_url").asText());
			((GitRepository)repos).setWatchers_count( node.path("watchers_count").asText());
			((GitRepository)repos).setLanguage( node.path("language").asText());
		}else if (repos instanceof GitUser) {
			((GitUser)repos).setId(node.path("id").asText());
			((GitUser)repos).setLogin(node.path("login").asText());
			((GitUser)repos).setHtml_url(node.path("html_url").asText());
		}
		return repos;
	}
}
