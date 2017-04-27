package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.GroupRepository;
import com.lognsys.dao.dto.DramasGroupsDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.UsersGroupsDTO;
import com.lognsys.dao.jdbc.resultset.DramaGroupsResultSetExtractor;
import com.lognsys.dao.jdbc.resultset.UserGroupsResultSetExtractor;
import com.lognsys.util.Constants;

@Repository
public class JdbcGroupRepository implements GroupRepository {

	// Injecting namedParamterTemplate
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	/**
	 * returns all the groups
	 */
	@Override
	public List<GroupsDTO> getAllGroups() {

		List<GroupsDTO> groupslist = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groups_all.name()),
				new BeanPropertyRowMapper<GroupsDTO>(GroupsDTO.class));

		return groupslist;
	}

	/**
	 * Returns the group_name that the user is associated to the database
	 * 
	 * @param user_id
	 *            - this is the user_id passed to retrieve the group_name
	 */
	@Override
	public String findGroupBy(int user_id) {
		SqlParameterSource param = new MapSqlParameterSource("user_id", user_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groupname_byuserid.name()), param,
				String.class);
	}

	/**
	 * 
	 */
	@Override
	public List<UsersGroupsDTO> getUsersByGroup(String group_name) {

		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_usersbygroups.name()), param,
				new UserGroupsResultSetExtractor());

	}

	/**
	 * 
	 */
	@Override
	public List<UsersGroupsDTO> getAllUsersAndGroup() {

		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_usersgroups_all.name()),
				new UserGroupsResultSetExtractor());

	}

	/**
	 * 
	 */
	@Override
	public boolean addGroup(String group_name) {
		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate
				.update(sqlProperties.getProperty(Constants.GROUP_QUERIES.insert_user_groups.name()), param) == 1;

	}

	@Override
	public int findIDBy(String groupname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String findGroupByDramaId(int drama_id) {
		SqlParameterSource param = new MapSqlParameterSource("drama_id", drama_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groupname_bydramaid.name()), param,
				String.class);
	}

	@Override
	public List<DramasGroupsDTO> getDramasByGroup(String group_name) {

		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_dramasbygroups.name()), param,
				new DramaGroupsResultSetExtractor());

	
	}

	@Override
	public List<DramasGroupsDTO> getAllDramasAndGroup() {
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_dramasgroups_all.name()),
				new DramaGroupsResultSetExtractor());
	}

}
