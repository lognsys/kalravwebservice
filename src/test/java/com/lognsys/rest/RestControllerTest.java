package com.lognsys.rest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.service.DramaService;

import java.util.Arrays;
 
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"classpath:mongo-context.xml" })
@WebAppConfiguration
public class RestControllerTest {
	 private MockMvc mockMvc;
	 
//	    @Autowired
//	    private TodoService todoServiceMock;
	  
	 	@Autowired
	    private DramaService todoServiceMock;
	 
	    //Add WebApplicationContext field here.
	 
	    //The setUp() method is omitted.
	 
//	    @Test
//	    public void findAll_TodosFound_ShouldReturnFoundTodoEntries() throws Exception {
//	    	 DramasDTO first = new DramasDTO();
//	    	 
//	    	 first.setId(9);
//	    	 first.setTitle("Title9");
//	    	 first.setImageurl("img111111111222");
//	    	 first.setDrama_length("1 hour");
//	    	 first.setDate("2017-06-16");
//	    	 first.setGenre("Genre");
//	    	 first.setStar_cast("abc");
//	    	 first.setDescription("desc");
//	    	 first.setDirector("Director");
//	    	 first.setWriter("writer");
//	    	 first.setMusic("music");
//	    	 first.setAvg_rating("2");
//	    	 first.setDrama_language("Eng");
//		        
//		        DramasDTO second = new DramasDTO();
//		   	 
//		        second.setId(10);
//		        second.setTitle("Title10");
//		        second.setImageurl("img1111111111111111");
//		        second.setDrama_length("1 hour");
//		        second.setDate("2017-06-16");
//		        second.setGenre("Genre");
//		        second.setStar_cast("abc");
//		        second.setDescription("desc");
//		        second.setDirector("Director");
//		        second.setWriter("writer");
//		        second.setMusic("music");
//		        second.setAvg_rating("2");
//		        second.setDrama_language("Eng");
//		        
//	        when(todoServiceMock.getTestDramas()).thenReturn(Arrays.asList(first, second));
//	 
//	        mockMvc.perform(get("/api/todo"))
//	                .andExpect(status().isOk())
//	                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//	                .andExpect(jsonPath("$", hasSize(2)))
//	                .andExpect(jsonPath("$[10].id", is(9)))
//	                .andExpect(jsonPath("$[10].description", is("Lorem ipsum")))
//	                .andExpect(jsonPath("$[10].title", is("Foo")))
//	                
//	                .andExpect(jsonPath("$[12].id", is(10)))
//	                .andExpect(jsonPath("$[12].description", is("Lorem ipsum")))
//	                .andExpect(jsonPath("$[12].title", is("Bar")));
//	 
//	        verify(todoServiceMock, times(9)).getTestDramas();
//	        verifyNoMoreInteractions(todoServiceMock);
//	    }
}
