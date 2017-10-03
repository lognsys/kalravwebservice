package com.lognsys.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lognsys.model.Notifications;
import com.lognsys.model.NotificationsTable;
import com.lognsys.service.NotificationService;
import com.lognsys.util.ObjectMapper;
import com.lognsys.web.controller.NotificationController;
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"file:src/main/webapp/WEB-INF/kalrav-servlet.xml"})
@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestNotificationController {

	private static final String BASE_URL = "http://localhost:8080/";

	@Mock //Mokito Mock Object
	@Autowired
	NotificationService notificationService;
	
	@InjectMocks //setup controller and mock  object into it
	NotificationController controller;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this); //initialize controller and mocks
		mockMvc=MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	/*
	 * CREATE VALID
	 * 
	*/@Test
	public void testSave() throws Exception{
		 Notifications added = new Notifications(1,true,"HelloWorld");
		 
		when(notificationService.addNotification(added)).thenReturn(added);
		
   	 String url = BASE_URL + "/sendnotification";
   	    //... more
   	    com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
   	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
   	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
   	    String requestJson=ow.writeValueAsString(added );

   	    mockMvc.perform(post(url)
   	    		.contentType(MediaType.APPLICATION_JSON_VALUE)
   	    		.param("message", "helloWorld")
   	    		.param("realame", "Priyank")
   	    		.param("dramaTitle", "ThisIsIt"));
		   	   /* .requestAttr("notifications", new Notifications(1,true,"HelloWorld")))
        .andExpect(model().attributeHasFieldErrors("notifications", "realname"))
        .andExpect(model().attribute("notifications", hasProperty("dramaTitle", nullValue())));*/
	}
	
	/*
	 * 
	 * CREATE INVALID
	 * 
	*/ @Test
	    public void testSaveInvalidNotify() throws Exception {
	        String message =null;
	   	 Notifications added = new Notifications(2,true,"");
	    
	   	 String  exoectedResult= null;
	     
	   	 Notifications actualResult =  notificationService.getNotificationByMessage(message);
	   	 
//	   	 System.out.println(resultExpected.toString());
	   	 
	    verify(notificationService, times(1)).getNotificationByMessage(message); 
		assertEquals(exoectedResult, actualResult);
	
	 }
	/*
	 * 
	 * READ 
	*/
	@Test
	public void testList() throws Exception{
		List<NotificationsTable> lists=new ArrayList<NotificationsTable>();
		lists.add(new NotificationsTable(1,true,"HelloWorld","Priyank","Drama1"));
		lists.add(new NotificationsTable(2,true,"HelloWorld","Monika","Drama2"));
		
		//specific mokito interaction, tell stub to return list of notification
		when(notificationService.getAllNotifications()).thenReturn(lists);
		
	}
	
/*
 * DELETE
*/
@Test
public void deletingDecrementsSize() {
	try {
		notificationService.addNotification(new Notifications(1,true,"hELLO",1,1,"Priyank","Drama1"));
		notificationService.addNotification(new Notifications(2,true,"hELLO2",2,2,"Priyank2","Drama2"));	
		notificationService.addNotification(new Notifications(3,true,"hELLO",3,3,"Priyank3","Drama3"));	
	 
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
	
	Notifications added = new Notifications(2,true,"hELLO",1,1,"Priyank","Drama1");
	
	List<Notifications> lists=new ArrayList<Notifications>();
		lists.add(new Notifications(1,true,"hELLO",1,1,"Priyank","Drama1"));
		lists.add(new Notifications(2,true,"hELLO1",2,2,"Priyank1","Drama11"));
		lists.add(new Notifications(3,true,"hELLO2",2,2,"Priyank2","Drama111"));
		Integer[] arryIds =new Integer[lists.size()];
		for(int i=0;i< lists.size();i++){
			arryIds[i]=lists.get(i).getId();
			
		}
	 try {

		    when(notificationService.deleteNotification(arryIds)).thenReturn(true);
		;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
