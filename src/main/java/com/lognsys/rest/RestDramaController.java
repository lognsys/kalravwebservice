package com.lognsys.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcRatingsRepository;
import com.lognsys.model.Drama;
import com.lognsys.model.Ratings;
import com.lognsys.service.DramaService;
import com.lognsys.service.RatingService;
import com.lognsys.util.PushNotificationHelper;


@RestController
public class RestDramaController {



	@Autowired
	private DramaService dramaService;
	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;
	
	
//	list all  drama with group name  even if customer has not assign groupname
	@GetMapping("/getalldramaandgroup")
	public List getAllDramasAndGroup() {
		{
			return jdbcGroupRepository.getAllDramasAndGroup();
				
		}
	
	}
// detail screen of drama with  respect to drama id
	@GetMapping("/getdramadetailbyid/{id}")
	public ResponseEntity getDramaById(@PathVariable("id") int id) {
		DramasDTO dramasDTO=null;
		try {
			
			 dramasDTO = dramaService.findByDrama(id);
			

			return new ResponseEntity(dramasDTO, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("manageDrama getDramaById dramasDTO "+dramasDTO);
			
			if (dramasDTO == null) {
				return new ResponseEntity("No Dramas found for id " + id, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity(dramasDTO, HttpStatus.OK);
		
	}

	
	// list all  drama with group name  
		@GetMapping("/getalldramaandgroup/{group_name}")
		public List getAllDramasAndGroup(@PathVariable("group_name") String group_name) {
			
			return  jdbcGroupRepository.getDramasByGroup(group_name);
		}

	
	
	@PostMapping(value = "/createdrama")
	public ResponseEntity createDrama(@RequestBody Drama dramas) {
		System.out.println("RestDramaController createDrama ");
		
		dramaService.addDrama(dramas);
		
		return new ResponseEntity(dramas, HttpStatus.OK);
	}
	@DeleteMapping("/deletedrama/{dramaIds}")
	public ResponseEntity deleteDrama(@PathVariable("dramaIds") String dramaIds) {
		{
			System.out.println("RestDramaController deleteDrama "+dramaIds);
		
			/*JSONParser parser = new JSONParser();
			try {
				
				Object obj = parser.parse(dramaIds);
				
				JSONArray arr = (JSONArray) obj;
				
				Integer[] dramaIDs = new Integer[arr.size()];
				if(arr.size()>0){
				for (int i = 0; i < arr.size(); i++) {
					JSONObject jsonObject = (JSONObject) arr.get(i);
					
					int id = Integer.parseInt(jsonObject.get("id").toString());
					System.out.println("manageDrama arr id "+id);
					
					dramaIDs[i] = id;
					System.out.println("manageDrama arr dramaIDs[i] "+dramaIDs[i]);
					
				}
				System.out.println("manageDrama arr dramaIDs length "+dramaIDs.length);
				
				dramaService.deleteDramas(dramaIDs);
				}
				
			} catch (ParseException e) {
				System.out.println("RestDramaController ParseException "+e);
				
			}	*/
		}
		return new ResponseEntity(dramaIds, HttpStatus.OK);

	}


	@PutMapping("/updatedrama/{id}")
	public ResponseEntity updateDrama(@PathVariable int id, @RequestBody DramasDTO dramasDTO) {
		System.out.println("RestDramaController updateDrama ");
		
		int Updatecount =dramaService.updateDrama(id,dramasDTO);

		if (0 == Updatecount) {
			return new ResponseEntity("No Drama found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(Updatecount, HttpStatus.OK);
	}
	
	// list all  drama with group name  
	@GetMapping("/getaudidatetimebydramaid/{drama_id}/{datetime}")
	public List getAudiDatetimeByDramaId(@PathVariable("group_name") String group_name) {
		
		return  jdbcGroupRepository.getDramasByGroup(group_name);
	}
	




	@PostMapping(value = "/notify")
	public ResponseEntity sendNotification() {
		System.out.println(" sendNotification");
		String result=null;
		try {
			String deviceToken="eY2UboGuVsc:APA91bHPlohGb1QYBwByk1JKbquUoJ8aCxaWOCQZ19J-ZNiWSH3T0zZGJBpHLm-crjlJ1wuT46MQ54To9rB_XkjDnTP50NyfX6N9phsZKUTEZwDpqXm_oTLwHmb7ktGFWrnhy8X1VbMr";
			 result=PushNotificationHelper.sendPushNotification(deviceToken);
			 System.out.println(" sendNotification result   "+result);
				
		} catch (IOException e) {
			System.out.println(" sendNotification IOException "+e);
			
			e.printStackTrace();
		}
		
		return new ResponseEntity(result,HttpStatus.OK);
	}
}