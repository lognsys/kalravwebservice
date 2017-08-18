package com.lognsys.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.service.AuditoriumService;
import com.lognsys.service.DramaService;
import com.lognsys.util.Constants;


@RestController
public class RestAuditoriumController {
	@Autowired
	private AuditoriumService auditoriumService;
	@Autowired
	private JdbcAuditoriumRepository jdbcAuditoriumRepository;
	
	
	// Injecting resource application.properties.
		@Autowired
		@Qualifier("applicationProperties")
		private Properties applicationProperties;

	
	
	// list all  auditoriumlist 
	@GetMapping("/auditoriumlist/{dramas_id}/{strDate}")
	public ResponseEntity<?>  getAuditoriumList(@PathVariable("dramas_id") int dramas_id ,@PathVariable("strDate")  String strDate) {
		
				try {
						JSONArray jsonArray= auditoriumService.getAuditoriumList(dramas_id,strDate);	

						System.out.println("getAuditoriumList jsonArray.length "+jsonArray.size());  
						if(jsonArray!= null && jsonArray.size()>0)
						{
							return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.OK);
						}
						else{
							String str = applicationProperties.getProperty(Constants.REST_MSGS.response_auditoriumempty.name());
							return new ResponseEntity<String>(str, HttpStatus.NOT_FOUND);	
						
						}
				} catch (Exception e) {
					System.out.println("#RestAuditoriumController Exception e"+e);
					String str = applicationProperties.getProperty(Constants.REST_MSGS.response_auditoriumempty.name());
					return new ResponseEntity<String>(str, HttpStatus.NOT_FOUND);	
				
					
				}
				
			
	}
}
