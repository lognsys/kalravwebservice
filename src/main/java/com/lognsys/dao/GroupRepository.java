package com.lognsys.dao;

import java.util.List;

import com.lognsys.dao.dto.GroupsDTO;

public interface GroupRepository {
	
	public List<GroupsDTO> getAllGroups();
	
	public GroupsDTO findById(int id);

}
