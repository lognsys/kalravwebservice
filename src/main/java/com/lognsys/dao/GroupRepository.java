package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;

public interface GroupRepository {

	public List<GroupsDTO> getAllGroups();

	public String findGroupBy(int user_id);

	public List<UsersGroupsDTO> getUsersByGroup(String group_name);

	public boolean addGroup(String group_id);

	public int findIDBy(String groupname);

	public List<UsersGroupsDTO> getAllUsersAndGroup();

}
