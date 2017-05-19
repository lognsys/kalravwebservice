package com.lognsys.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.model.Drama;
import com.lognsys.service.DramaService;


@RestController
public class RestDramaController {


	@Autowired
	private DramaService dramaService;
	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;
	
	@GetMapping("/listalldrama")
	public List getDramas() {

		System.out.println("RestDramaController getDramas ");
		return dramaService.getDramas();
	
	}
	
	@GetMapping("/getalldramaandgroup")
	public List getAllDramasAndGroup() {
		{
			System.out.println("RestDramaController getAllDramasAndGroup ");
			return jdbcGroupRepository.getAllDramasAndGroup();
				
		}
	
	}

	@GetMapping("/finddramabyid/{id}")
	public ResponseEntity getDramaById(@PathVariable("id") int id) {
		System.out.println("RestDramaController getDramaById ");
		System.out.println("RestDramaController getDramaById id "+id);
		
		DramasDTO dramasDTO = dramaService.findByDrama(id);
		if (dramasDTO == null) {
			return new ResponseEntity("No Dramas found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(dramasDTO, HttpStatus.OK);
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

}