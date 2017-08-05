package com.lognsys.rest;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcBookingRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.model.Drama;
import com.lognsys.service.AuditoriumService;
import com.lognsys.service.BookingService;
import com.lognsys.service.DramaService;
import com.lognsys.util.Constants;


@RestController
public class RestBookingController {
	@Autowired
	private BookingService bookingService;
	@Autowired
	private JdbcBookingRepository jdbcBookingRepository;
	
	
	// Injecting resource application.properties.
		@Autowired
		@Qualifier("applicationProperties")
		private Properties applicationProperties;

		
		
		@RequestMapping(value = "/bookingconfirm", method = { RequestMethod.POST })
		public ResponseEntity<?> bookingconfirm(@RequestBody String response) {
			String unique = null;
			try {
				  System.out.println("bookingconfirm response "+response);
				    
				JSONObject jsonObject=bookingService.addBooking(response);
				
				 return new ResponseEntity<JSONObject>(jsonObject,HttpStatus.FOUND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return new ResponseEntity<String>("Fail  to  add booking ",HttpStatus.NOT_FOUND);
		}
		

		@RequestMapping(value = "/bookedseats", method = { RequestMethod.POST })
		public ResponseEntity<?> bookedseats(@RequestBody String response) {
			String unique = null;
			try {
				  System.out.println("bookedseats response "+response);
				    
				JSONObject jsonObject=bookingService.getBookedSeats(response);
				  System.out.println("bookedseats jsonObject "+jsonObject);
					
				 return new ResponseEntity<JSONObject>(jsonObject,HttpStatus.FOUND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  return new ResponseEntity<String>("Fail  to  add booking ",HttpStatus.NOT_FOUND);
		}
}
