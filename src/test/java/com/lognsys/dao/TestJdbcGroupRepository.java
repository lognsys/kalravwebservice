package com.lognsys.dao;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcGroupRepository {

	@Autowired
	private JdbcGroupRepository groupRepo;

	@Before
	public void setUp() {

	}

	@Test
	public void testGetAllUsersAndGroup() {
		List<UsersGroupsDTO> ug = groupRepo.getAllUsersAndGroup();

		Assert.notNull(ug, "TEST All users with corresponding group");
		Assert.notEmpty(ug, "Collection not empty..list of Users and Group object");
	}
	
	@Test
	public void testGetUsersByGroup() {
		String group_name_1 = "Kalrav";
		List<UsersGroupsDTO> ug1 = groupRepo.getUsersByGroup(group_name_1);
		org.junit.Assert.assertEquals("No of Users in Ladies Group", 3, ug1.size());
		
		String group_name_2 = "Couple";
		List<UsersGroupsDTO> ug2 = groupRepo.getUsersByGroup(group_name_2);
		org.junit.Assert.assertEquals("No of Users in Couple Group", 4, ug2.size());
	}


	
	@Test
	public void testfindGroupByUserId(){
		int user_id = 6;
		String expected = "Ladies";
		String actual = groupRepo.findGroupBy(user_id);
		assertEquals("TEST Expected Group - KALRAV By ID - 6",expected, actual);
	}
	
	@Test
	public void testUpdateGroupOfUser() {
		
		String userName = "msharma@gmail.com";
		String group_name = "Couple";
		boolean isUpdate = groupRepo.updateGroupOfUser(userName, group_name);
		Assert.isTrue(isUpdate, "Update User - "+userName+"- with Group - "+group_name);
	}
	
	@Test
	public void testGetAllGroups() {
		List<GroupsDTO> listOfGroup=groupRepo.getAllGroups();
		int actual = listOfGroup.size();
		int expected = 14;
		assertEquals("TEST Total Number of Groups 14 - ",expected,actual);
		
	}
	
	@Test
	public void testHasSubGroup(){
		String group_name_1 = "Kalrav";
		boolean actual;
		boolean expected;
		
		actual = groupRepo.hasSubgroups(group_name_1);
		expected = true;
		assertEquals("TEST Group : Kalarv has subgroups ", expected, actual);

		String group_name_2 = "NO_GROUP";
		actual = groupRepo.hasSubgroups(group_name_2);
	    expected = false;
		assertEquals("TEST Group : Kalarv has subgroups ", expected, actual);
	}

	
	
}
