package com.lognsys.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.rest.RestRatingController;
import com.lognsys.service.RatingService;

@ContextConfiguration(locations = {  "classpath:application-context.xml", "classpath:datasource-context.xml",
"file:src/main/webapp/WEB-INF/kalrav-servlet.xml"})
@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRatingController {

	private static final String BASE_URL = "http://localhost:8080/";


	private MockMvc mockMvc;


	@Mock //Mokito Mock Object
    private RatingService ratingService;
    
    @InjectMocks
    private RestRatingController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }
    
    @Test
    public void test_create_success() throws Exception {
    	RatingsDTO ratingdto=  new RatingsDTO();
    	ratingdto.setDramas_id(1);
    	ratingdto.setRating(4.5);
    	ratingdto.setRating_date("2017-09-19");
    	ratingdto.setUsers_id(77);
		
    	
    	ratingService.addRating(ratingdto);
		when(ratingService.exists(ratingdto)).thenReturn(false);
		
		when(ratingService.addRating(ratingdto)).thenReturn(1);
		
		RatingsDTO ratingdto11=  new RatingsDTO();
		
    	assertThat(ratingService.addRating(ratingdto11), is(notNullValue()));
	 
    	
    	
    	 String url = BASE_URL + "/ratedrama";
    	    //... more
    	    ObjectMapper mapper = new ObjectMapper();
    	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    	    String requestJson=ow.writeValueAsString(ratingdto );

    	    mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
    	        .content(requestJson))
    	        .andExpect(status().isCreated());
    	
    }

    @Test
    public void test_CR() throws Exception {
    	RatingsDTO ratingdto=  new RatingsDTO();
    	ratingdto.setId(1);
    	ratingdto.setDramas_id(1);
    	ratingdto.setRating(4.5);
    	ratingdto.setRating_date("2017-09-19");
    	ratingdto.setUsers_id(77);
		
    	RatingsDTO ratingdto1=  new RatingsDTO();
    	ratingdto1.setId(2);
    	ratingdto1.setDramas_id(1);
    	ratingdto1.setRating(4.5);
    	ratingdto1.setRating_date("2017-01-19");
    	ratingdto1.setUsers_id(81);
		
    	RatingsDTO ratingdto2=  new RatingsDTO();
    	ratingdto2.setId(3);
    	ratingdto2.setDramas_id(1);
    	ratingdto2.setRating(4.5);
    	ratingdto2.setRating_date("2018-02-12");
    	ratingdto2.setUsers_id(76);
    	
    	RatingsDTO ratingdto3=  new RatingsDTO();
        
    	
//		READ ALL RATINGS=====
		when(ratingService.getAllRatings()).thenReturn(Arrays.asList(ratingdto, ratingdto1,ratingdto2));
		System.out.println(Arrays.asList(ratingdto, ratingdto1,ratingdto2));
		

//		FIND a RATINGS BY TITLE=====
		when(ratingService.FindRatingByUserIdAndDramaId(ratingdto)).thenReturn(ratingdto);
		System.out.println("FIND a RATINGS BY ratingdto "+ratingdto);
		
		
		
//		RATING EXCEPTION
		when(ratingService.FindRatingByUserIdAndDramaId(ratingdto3)).thenThrow(NullPointerException.class);
		

//		Create a RATINGS BY=====
		when(ratingService.addRating(ratingdto2)).thenReturn(ratingdto2.getId());
		System.out.println("Create a ratingdto2 by ratingdto2  "+ratingdto2.getId());
		when(ratingService.addRating(ratingdto1)).thenReturn(1);
		

//		exists a DRAMA =====
		when(ratingService.exists(ratingdto1)).thenReturn(true);
		System.out.println("exists a DRAMA ratingService.exists(ratingdto1)  "+ ratingService.exists(ratingdto1));
    }

}
