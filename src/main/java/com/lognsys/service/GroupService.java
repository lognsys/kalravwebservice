package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Drama;
import com.lognsys.model.DramasTable;
import com.lognsys.model.Groups;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("groupService")
public class GroupService {

	Logger LOG = Logger.getLogger(this.getClass());

	
	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;
	
	// Injecting resource sql.properties.

	@Autowired
	@Qualifier("applicationProperties")
	private Properties applicationProperties;

	/**
	 * Add drama to database.. Check if user already exists in db
	 * 
	 * @return
	 */
	@Transactional
	public void addGroups(Groups groups) {
		String groupname = groups.getGroup_name();
		System.out.println("GroupService  addGroups groupname "+groupname);
		
		GroupsDTO groupsDTO = ObjectMapper.mapToGroupsDTO(groups);
		System.out.println("GroupService addGroups  groupsDTO "+groupsDTO);
		
		try {
			boolean isExists = jdbcGroupRepository.isExists(groupname);
			System.out.println("GroupService addGroups  isExists "+isExists);
			
			if (isExists) {
				LOG.info("Found Groups in database with groupname - " + groupname);

			} else {
				int groupID =jdbcGroupRepository.addGroup(groupsDTO);
				System.out.println("#addUser - " + "Adding Groups in database with groupID - " + groupID);
				
				System.out.println("#addUser - " + "Adding Groups to corresponding SubGROUP - " + groups.getSub_groups_name());
				jdbcGroupRepository.addGroupAndSubGroup(groupID, groups.getSub_groups_name());

				
			}
		} catch (DataAccessException dae) {
			System.out.println("GroupService addDrama  Exception "+dae);
			
		
		}
	}
	
	/**
	 * Synchronize drama from mysql to json files.
	 *
	 * @return
	 * @throws IOException
	 */
	public void refreshGroupList() throws IOException {

	}

	/**
	 * 
	 * @return
	 */
	public List<GroupsDTO> getAllGroups() {

		try {
			return jdbcGroupRepository.getAllGroups();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Error: All groups cannot be retrieved");
		}

	}

}
