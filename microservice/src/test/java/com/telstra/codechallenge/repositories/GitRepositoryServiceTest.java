package com.telstra.codechallenge.repositories;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;


@RunWith(SpringRunner.class)
public class GitRepositoryServiceTest {
	
	@MockBean
	private GitRepositoryService repositoriesServiceMock;

	private MultiValueMap<String, String> headers;
	private HttpEntity<String> responseMok;
	private Map<String,String> params;
	
	@BeforeAll
	public void setup() {
		headers = new HttpHeaders();
		responseMok = new HttpEntity(headers);
		params = new HashMap<>();
		params.put("one", "" );
		params.put("two", "two");
		params.put("thre", "three");
	}
	
	@Test
	public void testRepositorieTypeClass() {
		Mockito.when(repositoriesServiceMock.callGitHubApi("", responseMok, params )).thenReturn(responseMok);
		assertEquals(repositoriesServiceMock.clientSetHeader("","", params).getClass(), HttpEntity.class);
	}
	
	@Test
	public void testUserTypeClass() {
		Mockito.when(repositoriesServiceMock.clientSetHeader("", "", params )).thenReturn(responseMok);
		assertEquals(repositoriesServiceMock.usersSetParameter(1).getClass(), HttpEntity.class);
	}
	
	@Test
	public void testRepositoryTypeClass() {
		Mockito.when(repositoriesServiceMock.clientSetHeader("", "", params )).thenReturn(responseMok);
		assertEquals(repositoriesServiceMock.usersSetParameter(1).getClass(), HttpEntity.class);
	}
}
