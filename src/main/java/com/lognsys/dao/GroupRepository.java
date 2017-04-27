package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;

public interface GroupRepository {

	public List<GroupsDTO> getAllGroups();

	public String findGroupBy(int user_id);

	public List<UsersGroupsDTO> getUsersByGroup(String group_name);

	public boolean addGroup(String group_id);

	public int findIDBy(String groupname);

	public List<UsersGroupsDTO> getAllUsersAndGroup();



	public String findGroupByDramaId(int drama_id);

	public List<DramasGroupsDTO> getDramasByGroup(String group_name);

	public List<DramasGroupsDTO> getAllDramasAndGroup();

	
	


}
