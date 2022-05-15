package com.telstra.codechallenge.repositores;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

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

@RunWith(SpringRunner.class)
@WebMvcTest(RespositoresController.class)
public class RepositoriesControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	 
	@MockBean
	private RepositoriesService repositoriesService;

	HttpEntity<String> resultMok;
	
	@BeforeAll
	public void setup() {
		MultiValueMap<String, String> headers = new HttpHeaders();
		resultMok = new HttpEntity( headers );
	}
	
	@Test
	public void parameterShouldBeBadRequestWhenUserIsalled() throws Exception {
		Mockito.when(	repositoriesService.getOldestUser(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/user")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void parameterShouldBeOkRequestWhenUserIsalled() throws Exception {
		Mockito.when(	repositoriesService.getOldestUser(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/repositories")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void parameterShouldBeBadRequestWhenRepository() throws Exception {
		Mockito.when(	repositoriesService.getRepositories(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/repositories")).andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void parameterShouldBeOkWhenRepository() throws Exception {
		Mockito.when(	repositoriesService.getRepositories(1) ).thenReturn(resultMok);
		this.mockMvc.perform(get("/repositories")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	

}
