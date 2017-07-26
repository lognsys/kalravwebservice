package com.lognsys.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.json.simple.JSONArray;
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

import net.minidev.json.JSONObject;

@RestController
public class RestAuditoriumController {
	@Autowired
	private AuditoriumService auditoriumService;
	@Autowired
	private JdbcAuditoriumRepository jdbcAuditoriumRepository;
	
	
	
	// list all  auditoriumlist 
	@GetMapping("/auditoriumlist/{dramas_id}/{strDate}")
	public ResponseEntity<?>  getAuditoriumList(@PathVariable("dramas_id") int dramas_id ,@PathVariable("strDate")  String strDate) {
		
				try {
					System.out.println("getAuditoriumList String date "+strDate);  
						JSONArray jsonArray= auditoriumService.getAuditoriumList(dramas_id,strDate);		
						if(jsonArray!= null && jsonArray.size()>0)
						{
							return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.OK);
						}
						else{
							/*JSONObject object =new JSONObject();
							object.put("msg", "No audotorium available : ");
							JSONArray jarray=new JSONArray();
							jarray.add(object);*/
							return new ResponseEntity<String>("No auditorium available" , HttpStatus.NOT_FOUND);
						}
				} catch (Exception e) {
					System.out.println("#RestAuditoriumController Exception e"+e);
					return new ResponseEntity<String>("No auditorium found with drama  " , HttpStatus.NOT_FOUND);
					
				}
				
			
	}
}
