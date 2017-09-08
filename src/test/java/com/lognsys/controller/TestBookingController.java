package com.lognsys.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.model.Booking;
import com.lognsys.model.Drama;
import com.lognsys.rest.RestBookingController;
import com.lognsys.service.BookingService;
import com.lognsys.service.DramaService;
import com.lognsys.web.controller.DramaController;

import net.minidev.json.JSONArray;

@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
"file:src/main/webapp/WEB-INF/kalrav-servlet.xml"})
@WebAppConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBookingController {
	private MockMvc mockMvc;


	@Mock //Mokito Mock Object
    private BookingService bookingService;
    
    @InjectMocks
    private RestBookingController controller;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }
    
    @Test
    public void test_create_success() throws Exception {
    	String jsonToString=BookingData();
    	bookingService.addBooking(jsonToString);
    	/*{
    		"dramas_id":1,
    		"auditoriums_id":1
    	}*/
		when(bookingService.getBookedSeats(jsonFindBookedSeatsinJsonForm())).thenReturn(BookedSeatsJSON());
		
		when(bookingService.addBooking(jsonToString)).thenReturn(confirmationCodeJSON());
		
	 
    }
    
    public String BookingData(){
    /*	{
    		"dramas_id":1,
    		"booking_date" :"2017-08-04 12:07:00",
    		"users_id":40,
    		"auditoriums_id":1,
    		"price":700.00,
    		"seatnumber":["Z5","Z6"],
    		"no_of_seats":2
    	}*/
    	JSONArray seatnumber =new JSONArray();
    	seatnumber.add("Z1");
    	seatnumber.add("Z2");
    	
    	JSONObject jsonObject=new JSONObject();
    	jsonObject.put("drama_id", 1);
    	jsonObject.put("booking_date", "2017-08-04 12:07:00");
    	jsonObject.put("users_id", 1);
    	jsonObject.put("auditoriums_id", 1);
    	jsonObject.put("price", 200.00);
    	jsonObject.put("seatnumber", seatnumber);
    	jsonObject.put("no_of_seats", 2);
    
    	return jsonObject.toString();
    	}    
    public String jsonFindBookedSeatsinJsonForm(){
    /*	{
    		"dramas_id":1,
    		"auditoriums_id":1
    	}*/
    	
    	JSONObject jsonObject=new JSONObject();
    	jsonObject.put("drama_id", 1);
    	jsonObject.put("auditoriums_id", 1);
    
    	return jsonObject.toString();
    	}
    
    public JSONObject BookedSeatsJSON(){
    /*	{
    		"dramas_id":1,
    		"auditoriums_id":1
    	}*/
    	JSONArray seatnumber =new JSONArray();
    	seatnumber.add("Z1");
    	seatnumber.add("Z2");
    	JSONObject jsonObject=new JSONObject();
    	jsonObject.put("seatsdetails",seatnumber);
    	jsonObject.put("auditoriums_id", 1);
    
    	return jsonObject;
    	} 
    
    public JSONObject confirmationCodeJSON(){
    /*	{
    		"dramas_id":1,
    		"auditoriums_id":1
    	}*/
    	
    	JSONObject jsonObject=new JSONObject();
    	jsonObject.put("confirmation_code","xmxmxmekjelnflwnvrwrvn");
    	jsonObject.put("booking_id", 1);
    
    	return jsonObject;
    	}
}
