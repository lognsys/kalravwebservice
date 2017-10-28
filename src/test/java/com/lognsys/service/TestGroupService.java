package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context.xml", "classpath:datasource-context.xml",
		"classpath:mongo-context.xml" })
public class TestGroupService {
	
	@Autowired
	private GroupService groupService;
	
	
	public void setUp() {

	}

	@Test
	public void testRefreshList() throws IOException {
	
		Map<String, List<String>> m = groupService.getAllGroupAndSubGroup();
		
		Assert.notNull(m, "Map of Group & Subgroup");
		Assert.notEmpty(m, "Map of Group & Subgroup not empty");
		
		for(Map.Entry<String, List<String>> map :  m.entrySet()) {
			System.out.println("GROUP - "+map.getKey());
			
			List<String> listOfSubgroup = map.getValue();
			for(String str : listOfSubgroup) {
				System.out.println("SUBGROUP - "+str);
			}
		}
	}

	

}
