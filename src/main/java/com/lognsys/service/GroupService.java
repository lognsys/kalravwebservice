package com.lognsys.service;
/**
 * @author - pdoshi
 * 
 * Description:
 * 
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.util.Constants;

@Service("groupService")
public class GroupService {

	//Logging 
	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private  JdbcGroupRepository jdbcGroupRepository;

	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;
	
	//Global variable
	private static Map<String, List<String>> groupMap = new HashMap<>();

	/**
	 * This method returns Map <String, List<String> of Groups and Subgroups
	 * 
	 * FIXED - made static group
	 * 
	 * @return
	 */
	public  Map<String, List<String>> getAllGroupAndSubGroup() {
	
		// Sort Groups and Subgroups by having Groups at the beginning
		int groupCount = jdbcGroupRepository.getGroupCount();
		
		if(groupMap.size() == groupCount)
			return groupMap;
		
		//This method will return all groups and subgroup arraylist
		List<GroupsDTO> listOfGroup = jdbcGroupRepository.getAllGroups();

		// Sorting of Groups in the beginning
		Collections.sort(listOfGroup, new Comparator<GroupsDTO>() {
			public int compare(GroupsDTO g1, GroupsDTO g2) {
				return g1.getParent_id() - g2.getParent_id();
			}
		});

		// Interim List to sort groups and associate groups to corresponding
		// subgroup
		List<String> groupNameAndIds = new ArrayList<>();

		for (GroupsDTO g : listOfGroup) {

			// Groups list with ID:Name
			if (!g.isIs_subgroup()) {
				groupNameAndIds.add(g.getId() + ":" + g.getGroup_name());
				groupMap.put(g.getGroup_name(), new ArrayList<String>());
			}

			// ASSUMPTION THAT AUTOINCREMENT OF MYSQL STARTS WITH 1
			int groupID = 0;
			String groupName = "";
			String[] groupArr = null;

			// check if its a subgroup
			if (g.isIs_subgroup()) {

				// check if subgroup ID matches group ID
				for (String str : groupNameAndIds) {
					groupArr = str.split(":");
					groupID = Integer.parseInt(groupArr[0]);
					groupName = groupArr[1];

					if (g.getParent_id() == groupID)
						break;
				}

				
				// check subgroup parentID is equal to GroupID
				if (g.getParent_id() == groupID) {
						
						List<String> subgroupList = groupMap.get(groupName);
						subgroupList.add(g.getGroup_name());
						groupMap.put(groupName, subgroupList);
					
				} else {
					
					//do nothing
				}
			}
		}
		return groupMap;
	}

	
	
	/**
	 * This method return list of groups and corresponding subgroup
	 * 
	 * @return List<GroupsDTO>
	 */
	public List<GroupsDTO> getAllGroups() {
		List<GroupsDTO> listOfGroups = null;
		try {
			listOfGroups = jdbcGroupRepository.getAllGroups();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
			throw new IllegalStateException(
					applicationProperties.getProperty(Constants.EXCEPTIONS_MSG.exception_allgroups.name()));
		}
		return listOfGroups;
	}
	
	
	/**
	 * 
	 * Add Group or Subgroup
	 */
	public void addGroup(GroupsDTO groupsDTO){
		jdbcGroupRepository.addGroup(groupsDTO);
	}

	/**
	 * 
	 * @param groupsDTO
	 */
	public void removeSubgroup(GroupsDTO groupsDTO){
		
	}
	
	
}
