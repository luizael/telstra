package com.telstra.codechallenge.repositories;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.JsonNode;

@Configuration
public class GitSimpleFactory {

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
