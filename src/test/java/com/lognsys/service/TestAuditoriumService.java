package com.lognsys.service;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
		"classpath:mongo-context.xml" })
public class TestAuditoriumService {
	@Autowired
	private AuditoriumService auditoriumService;
	
	public void setUp() {

	}

	@Test
	public void testRefreshList() throws IOException {
		
		try {
			int dramas_id=1;
			JSONArray jsonArray= auditoriumService.getAuditoriumList(dramas_id);		
			if(jsonArray!= null && jsonArray.size()>0)
			{
				System.out.println("#RestAuditoriumController jsonArray"+jsonArray);

				//				return new ResponseEntity(jsonArray, HttpStatus.OK);
			}
			else{
				System.out.println("#RestAuditoriumController No audotorium available  ");

				//				return new ResponseEntity("No audotorium available : " , HttpStatus.NOT_FOUND);
			}
	} catch (Exception e) {
		System.out.println("#RestAuditoriumController Exception e"+e);
//		return new ResponseEntity("No auditorium found with drama : " , HttpStatus.NOT_FOUND);
		
	}
	}
	

}
