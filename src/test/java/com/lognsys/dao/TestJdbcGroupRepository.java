package com.lognsys.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.util.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:datasource-context.xml" })
public class TestJdbcGroupRepository {

	@Autowired
	private JdbcGroupRepository groupRepo;

	public void setUp() {

	}

	@Test
	public void testGetUsersByGroup() {
		List<UsersGroupsDTO> ug = groupRepo.getAllUsersAndGroup();

		Assert.notNull(ug, "Check list of Users NOT NULL");
		Assert.notEmpty(ug, "Collection not empty..list of Users and Group object");
	}
	
	@Test
	public void testGetAllUsersAndGroup() {
		String group_name_1 = "ladies";
		List<UsersGroupsDTO> ug1 = groupRepo.getUsersByGroup(group_name_1);
		org.junit.Assert.assertEquals("No of Users in Ladies Group", 3, ug1.size());
		
		String group_name_2 = "couple";
		List<UsersGroupsDTO> ug2 = groupRepo.getUsersByGroup(group_name_2);
		org.junit.Assert.assertEquals("No of Users in Couple Group", 4, ug2.size());
	}

	
	@Test
	public void testfindGroupBy(){
		int user_id = 6;
		
		String expected = "ladies";
		String actual = groupRepo.findGroupBy(user_id);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateGroupOfUser() {
		
		String userName = "msharma@gmail.com";
		String group_name = "Couple";
		boolean isUpdate = groupRepo.updateGroupOfUser(userName, group_name);
		Assert.isTrue(isUpdate, "Update User - "+userName+"- with Group - "+group_name);
		
		
		
	}
}
