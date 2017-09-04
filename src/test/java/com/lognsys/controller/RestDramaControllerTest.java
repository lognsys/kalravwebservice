package com.lognsys.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.model.Drama;
import com.lognsys.rest.RestDramaController;
import com.lognsys.service.DramaService;
import com.lognsys.util.*;

import static org.hamcrest.Matchers.*;
/*
 * Annotate the class with the @RunWith annotation and ensure that test is executed by using the MockitoJUnitRunner.
 * 
*/
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"file:src/main/webapp/WEB-INF/kalrav-servlet.xml"})
@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
public class RestDramaControllerTest {
	
	private MockMvc mockMvc;

    @Mock
    private DramaService dramaServiceMock;
   
    @InjectMocks
    private RestDramaController dramaController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dramaController)
                .addFilters(new CORSFilter())
                .build();
    }
    // =========================================== Get All DramasDTO By ID =========================================

    @Test
    public void test_get_allDramasAndGroup_success() throws Exception {
//    	
        List<Drama> dramas = Arrays.asList(
        	    new Drama(1, "Romeo and Juliet", "Tragedy", "star_cast", "director","writer",
						"description","2017-05-17 09:25:07","http://www.pictures.zimbio.com/gi/Twyla+Tharp+2011+Juilliard+School+Commencement+bTePZyFwnJFl.jpg",
						 "1:00:00 - 2:00:00","music","3.5","GUEST","Kalrav", "language"),
                new Drama(2, "The Kite Runner", "Tragedy", "star_cast", "director","writer",
						"description","2017-05-17 09:25:07","http://www.pictures.zimbio.com/gi/Twyla+Tharp+2011+Juilliard+School+Commencement+bTePZyFwnJFl.jpg",
						 "1:00:00 - 2:00:00","music","3.5","GUEST","Kalrav", "language"));
        
        List<DramasGroupsDTO> dgDTO=new ArrayList<>();
        
        dgDTO.add(new DramasGroupsDTO(1,new GroupsDTO(1, "NONE"), com.lognsys.util.ObjectMapper.mapToDramasDTO(dramas.get(0))));
        dgDTO.add(new DramasGroupsDTO(2,new GroupsDTO(2, "Kalrav"), com.lognsys.util.ObjectMapper.mapToDramasDTO(dramas.get(1))));
        
        
        when(dramaServiceMock.getAllDramasAndGroup()).thenReturn(dgDTO);

        mockMvc.perform(get("/getalldramaandgroup"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)));
        verify(dramaServiceMock, times(1)).getAllDramasAndGroup();
        verifyNoMoreInteractions(dramaServiceMock);
    }
    
    // =========================================== Get DramasDTO By ID =========================================

    @Test
    public void test_get_by_id_success() throws Exception {
    	DramasDTO drama = new DramasDTO(1, "Romeo and Juliet","http://www.pictures.zimbio.com/gi/Twyla+Tharp+2011+Juilliard+School+Commencement+bTePZyFwnJFl.jpg",
				 "1:00:00 - 2:00:00","2017-05-17 09:25:07",
				 "Tragedy", "star_cast","description", "director","writer",
				"music","3.5", "language");

        when(dramaServiceMock.findByDrama(1)).thenReturn(drama);

        mockMvc.perform(get("/getdramadetailbyid/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Romeo and Juliet")));

        verify(dramaServiceMock, times(1)).findByDrama(1);
        verifyNoMoreInteractions(dramaServiceMock);
    }
    

    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {
        when(dramaServiceMock.findByDrama(111)).thenReturn(null);

        mockMvc.perform(get("/getdramadetailbyid/{id}", 111))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

        verify(dramaServiceMock, times(1)).findByDrama(1);
        verifyNoMoreInteractions(dramaServiceMock);
    }


    // =========================================== Create New DramasDTO ========================================

    @Test
    public void test_create_user_success() throws Exception {
    	Drama drama=    new Drama("The Kite Runner", "Tragedy", "star_cast", "director","writer",
				"description","2017-05-17 09:25:07","http://www.pictures.zimbio.com/gi/Twyla+Tharp+2011+Juilliard+School+Commencement+bTePZyFwnJFl.jpg",
				 "1:00:00 - 2:00:00","music","3.5","GUEST","Kalrav", "language");
        when(dramaServiceMock.exists(drama)).thenReturn(false);
        doNothing().when(dramaServiceMock).addDrama(drama);

        mockMvc.perform(
                post("/createdrama")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(drama)))
                .andExpect(status().isCreated());
//                .andExpect(header().string("location", containsString("/getdramadetailbyid/0")));

        verify(dramaServiceMock, times(1)).exists(drama);
        verify(dramaServiceMock, times(1)).addDrama(drama);
        verifyNoMoreInteractions(dramaServiceMock);
    }
    
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
