package com.telstra.codechallenge.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.repositories.GitRepository;
import com.telstra.codechallenge.repositories.GitSimpleFactory;
import com.telstra.codechallenge.repositories.GitUser;

@RunWith(SpringRunner.class)
public class GitSimpleFactoryTest {

	@Autowired
	private GitSimpleFactory factory;
	
	private ObjectMapper objMapper;
	private StringBuilder stb;
	
	@BeforeAll
	public void setup() {
		objMapper = new ObjectMapper();
		stb = new StringBuilder("{\"total_count\": 24508807,\"incomplete_results\": true,\n"
				+ "\"items\": [{\"id\": 368568795,\"name\":\"test\",\"description\":\"descrption teste\",\"html_url\":\"http://teste\",\"watchers_count\":\"123\",\"language\":\"java\",\"login\":\"login test\"}]}");
	}
	
	@Test
	public void testBuildObjectsUser() throws JsonProcessingException {
		GitUser user = ( GitUser ) factory.createSimpleInstance(new GitUser(), objMapper.readTree( stb.toString() ));
		assertEquals(user.getId(), 368568795);
	}
	
	@Test
	public void testBuildObjectsRepository()throws JsonProcessingException {
		GitRepository repo = ( GitRepository ) factory.createSimpleInstance(new GitRepository(), objMapper.readTree( stb.toString() ));
		assertEquals(repo.getName(), "test");
	}
}
