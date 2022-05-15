package com.telstra.codechallenge.repositories;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telstra.codechallenge.repositories.GitOperable;
import com.telstra.codechallenge.repositories.GitRepository;
import com.telstra.codechallenge.repositories.GitRepositoryService;
import com.telstra.codechallenge.repositories.GitRespositoryController;
import com.telstra.codechallenge.repositories.GitUser;

@RunWith(SpringRunner.class)
@WebMvcTest(GitRespositoryController.class)
public class RepositoriesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private GitRespositoryController controller;	
	
	@MockBean
	private GitRepositoryService repositoriesService;

	private HttpEntity<String> resultMok;
	private StringBuilder stb;
	private ObjectMapper objMapper;
	
	@BeforeAll
	public void setup() {
		objMapper = new ObjectMapper();
		stb = new StringBuilder("{\"total_count\": 24508807,\"incomplete_results\": true,\n"
				+ "\"items\": [{\"id\": 368568795,\"name\":\"test\",\"description\":\"descrption teste\",\"html_url\":\"http://teste\",\"watchers_count\":\"123\",\"language\":\"java\",\"login\":\"login test\"}]}");
		MultiValueMap<String, String> headers = new HttpHeaders();
		resultMok = new HttpEntity( headers );
	}
	
	@Test
	public void parameterShouldBeBadRequestWhenUserIsalled() throws Exception {
		Mockito.when(	repositoriesService.usersSetParameter(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/user")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void parameterShouldBeOkRequestWhenUserIsalled() throws Exception {
		Mockito.when(	repositoriesService.usersSetParameter(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/repositories")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void parameterShouldBeBadRequestWhenRepository() throws Exception {
		Mockito.when(	repositoriesService.repositoriesSetParameter(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/repositories")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void parameterShouldBeOkWhenRepository() throws Exception {
		Mockito.when(	repositoriesService.repositoriesSetParameter(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/repositories")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testBuildObjectsUser() throws JsonProcessingException {
		List<GitOperable> result = controller.buildResponseJsonToObject( stb.toString(), new GitUser() );
		assertEquals( ((GitUser)result.get(0)).getId(), 368568795);
	}
	
	@Test
	public void testBuildObjectsRepository()throws JsonProcessingException {
		List<GitOperable> result = controller.buildResponseJsonToObject( stb.toString(), new GitRepository() );
		assertEquals( ((GitRepository)result.get(0)).getName(), "test");
	}

}
