package com.lognsys.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.model.Groups;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;

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
		System.out.println("GroupService addGroups groupname " + groupname);

		GroupsDTO groupsDTO = ObjectMapper.mapToGroupsDTO(groups);
		System.out.println("GroupService addGroups groupsDTO " + groupsDTO);

		try {
			boolean isExists = jdbcGroupRepository.isExists(groupname);
			System.out.println("GroupService addGroups isExists " + isExists);

			if (isExists) {
				LOG.info("Found Groups in database with groupname - " + groupname);

			} else {
				int groupID = jdbcGroupRepository.addGroup(groupsDTO);

			}
		} catch (DataAccessException dae) {
			System.out.println("GroupService addDrama Exception " + dae);

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
	 * Returns List<GroupsDTO> groups and subgroups
	 *
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

}
