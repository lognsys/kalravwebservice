package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;

public interface GroupRepository {

	// NOTE: This applies to both groups and subgroups
	public List<GroupsDTO> getAllGroups();

	// NOTE: This applies to both group and subgroup
	public String findGroupBy(int user_id);

	public List<UsersGroupsDTO> getUsersByGroup(String group_name);

	// NOTE: this applies to both group and subgroup
	public int addGroup(GroupsDTO groupsDTO);

	// NOTE: this applies to both group and subgroup
	public int findIDBy(String name);

	// NOTE: This applies to both and subgroup
	public List<UsersGroupsDTO> getAllUsersAndGroup();

	// NOTE: This applies to both group and subgroup
	public boolean isExists(String group_name);

	// NOTE: This applies to both group and
	public String findGroupByDramaId(int drama_id);

	// NOTE: This workd for group and subgroup
	public List<DramasGroupsDTO> getDramasByGroup(String group_name);

	public List<DramasGroupsDTO> getAllDramasAndGroup();

	// NOTE: This applies to only group and subgroup
	public boolean updateGroupOfUser(String userName, String group_name);
	
	//Get total count of Groups & its SubGroups
	public int getGroupCount();
  
	//This is group or subgroup
 	public boolean deleteGroup(String group_name);
	
 	//Make sure group has sub groups
	public boolean hasSubgroups(String group_name);

}
