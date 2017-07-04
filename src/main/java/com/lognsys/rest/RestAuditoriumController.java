package com.lognsys.rest;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class RestAuditoriumController {
	@Autowired
	private AuditoriumService auditoriumService;
	@Autowired
	private JdbcAuditoriumRepository jdbcAuditoriumRepository;
	
	
	
	// list all  auditoriumlist 
	@GetMapping("/auditoriumlist/{dramas_id}")
	public ResponseEntity<Hashtable<String, String[]>>  getAudi(@PathVariable("dramas_id") int dramas_id) {
		
//		List<AuditoriumsDTO> auditoriumsDTOs=auditoriumService.getAuditoriumList(dramas_id);
		Hashtable<String, String[]> hashtable=auditoriumService.getAuditoriumList(dramas_id);		
		System.out.println("#RestAuditoriumController hashtable "+hashtable);
		System.out.println("#RestAuditoriumController (hashtable.size() "+hashtable.size());
		
				try {
					/*if(auditoriumsDTOs.size()>0){
						return new ResponseEntity(auditoriumsDTOs, HttpStatus.OK);
					}
					else{
						return new ResponseEntity("No auditorium found with drama: " , HttpStatus.NOT_FOUND);
						
					}*/
					if(hashtable.size()>0){
						return new ResponseEntity(hashtable, HttpStatus.OK);
					}
					else{
						return new ResponseEntity("No auditorium found with drama: " , HttpStatus.NOT_FOUND);
						
					}
				} catch (Exception e) {
					System.out.println("#RestAuditoriumController Exception e"+e);
					
					if ( hashtable== null) {
						return new ResponseEntity("No auditorium found  ", HttpStatus.NOT_FOUND);
					}
				}
				  	return new ResponseEntity(hashtable, HttpStatus.OK);
				
			
	}
}
