package com.lognsys.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.util.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.model.Drama;
import com.lognsys.model.Notifications;
import com.lognsys.rest.RestDramaController;
import com.lognsys.service.DramaService;
import com.lognsys.util.*;
import com.lognsys.web.controller.DramaController;

import static org.hamcrest.Matchers.*;
/*
 * Annotate the class with the @RunWith annotation and ensure that test is executed by using the MockitoJUnitRunner.
 * 
*/
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"file:src/main/webapp/WEB-INF/kalrav-servlet.xml"})
@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDramaController {
	
	private MockMvc mockMvc;


	@Mock //Mokito Mock Object
	@Autowired
    private DramaService dramaServiceMock;
    
    @InjectMocks
    private DramaController dramaController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dramaController)
                .build();
    }
   
    @Test
    public void test_create_success() throws Exception {
    	Drama dramas=  new Drama();
    	
		dramas.setTitle("Hello ");
		dramas.setGenre("Historic");
		dramas.setStar_cast("Priyank Doshi");
		dramas.setDirector("Priyank Doshi");
		dramas.setWriter("Priyank Doshi");
		dramas.setDescription("description");
		dramas.setDate("2017-08-08");
		dramas.setAvg_rating( "5");
		dramas.setImageurl("http://www.gmail.com");
		dramas.setDrama_length("2 hour");
		dramas.setMusic("Asian music");
		dramas.setDrama_language("Hindi, English");
		dramas.setGroup("NONE");
		dramas.setAuditorium("Kalrav");
		dramaServiceMock.addDrama(dramas);
		when(dramaServiceMock.exists(dramas)).thenReturn(false);
		
		when(dramaServiceMock.addDrama(dramas)).thenReturn(1);

	 
    }

    @Test
    public void test_CRUD() throws Exception {
	Drama dramas=  new Drama();
		dramas.setId(1);
		dramas.setTitle("Hello 1");
		dramas.setGenre("Historic");
		dramas.setStar_cast("Priyank Doshi");
		dramas.setDirector("Priyank Doshi");
		dramas.setWriter("Priyank Doshi");
		dramas.setDescription("description");
		dramas.setDate("2017-08-08");
		dramas.setAvg_rating( "5");
		dramas.setImageurl("http://www.gmail.com");
		dramas.setDrama_length("2 hour");
		dramas.setMusic("Asian music");
		dramas.setDrama_language("Hindi, English");
		dramas.setGroup("NONE");
		dramas.setAuditorium("Kalrav");
		
		
		Drama dramas2=  new Drama();

		dramas2.setId(2);
		dramas2.setTitle("Hello 2");
		dramas2.setGenre("Historic");
		dramas2.setStar_cast("Priyank Doshi");
		dramas2.setDirector("Priyank Doshi");
		dramas2.setWriter("Priyank Doshi");
		dramas2.setDescription("description");
		dramas2.setDate("2017-08-08");
		dramas2.setAvg_rating( "5");
		dramas2.setImageurl("http://www.gmail.com");
		dramas2.setDrama_length("2 hour");
		dramas2.setMusic("Asian music");
		dramas2.setDrama_language("Hindi, English");
		dramas2.setGroup("NONE");
		dramas2.setAuditorium("Kalrav");
		
		
		Drama dramas3=  new Drama();

		dramas3.setId(3);
		dramas3.setTitle("Hello 3");
		dramas3.setGenre("Historic");
		dramas3.setStar_cast("Priyank Doshi");
		dramas3.setDirector("Priyank Doshi");
		dramas3.setWriter("Priyank Doshi");
		dramas3.setDescription("description");
		dramas3.setDate("2017-08-08");
		dramas3.setAvg_rating( "5");
		dramas3.setImageurl("http://www.gmail.com");
		dramas3.setDrama_length("2 hour");
		dramas3.setMusic("Asian music");
		dramas3.setDrama_language("Hindi, English");
		dramas3.setGroup("NONE");
		dramas3.setAuditorium("Kalrav");
		
		DramasDTO dto=com.lognsys.util.ObjectMapper.mapToDramasDTO(dramas);
		DramasDTO dto2=com.lognsys.util.ObjectMapper.mapToDramasDTO(dramas2);
		DramasDTO dto3=com.lognsys.util.ObjectMapper.mapToDramasDTO(dramas3);
		
//		READ ALL DRAMAS=====
		when(dramaServiceMock.getDramas()).thenReturn(Arrays.asList(dto, dto2,dto3));
		System.out.println(Arrays.asList(dto, dto2,dto3));
		

//		FIND a DRAMA BY TITLE=====
		when(dramaServiceMock.findDramaByTitle("Hello 2")).thenReturn(dto2);
		when(dramaServiceMock.findDramaByTitle("Hello 2")).thenReturn(dto);
		when(dramaServiceMock.findDramaByTitle("Hello 2")).thenThrow(RuntimeException.class);
		System.out.println("thenReturn by title dto2 "+dto2);
		

//		Create a DRAMA BY=====
		when(dramaServiceMock.addDrama(dramas2)).thenReturn(dramas2.getId());
		System.out.println("Create a DRAMA by dramas2  "+dramas2.getId());
		when(dramaServiceMock.addDrama(dramas)).thenReturn(1);
		

//		UPDATE a DRAMA BY ID 1 and drama obje=====
		dto.setTitle("Savior");
		
		when(dramaServiceMock.updateDrama(1, dto)).thenReturn(true);
		System.out.println("UPDATE a DRAMA by  dto  "+ dto.toString());
		
		

//		exists a DRAMA =====
		when(dramaServiceMock.exists(dramas)).thenReturn(true);
		System.out.println("exists a DRAMA dramaServiceMock.exists(dramas)  "+ dramaServiceMock.exists(dramas));
		
		

//		FIND a DRAMA BY TITLE=====
		 when(dramaServiceMock.findByDrama(dto.getId())).thenReturn(dto);

			
 /*
  * DELETE
 */
		 try {
				dramaServiceMock.addDrama(dramas);
				dramaServiceMock.addDrama(dramas2);	
				dramaServiceMock.addDrama(dramas3);	

				List<Drama> lists=new ArrayList<Drama>();
				
					lists.add(dramas);
					lists.add(dramas2);
					lists.add(dramas3);
					
					Integer[] arryIds =new Integer[lists.size()];
					for(int i=0;i< lists.size();i++){
						arryIds[i]=lists.get(i).getId();
						
					}

				    when(dramaServiceMock.deleteDramas(arryIds)).thenReturn(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			 
    }
}
