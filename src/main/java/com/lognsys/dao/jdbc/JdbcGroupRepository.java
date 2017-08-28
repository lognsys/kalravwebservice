package com.lognsys.dao.jdbc;

/**
 * Description :  Groups DTO Object is mapped with fields in MySQL table
 * 
 * Update: 
 * PJD - 18/07/17 : Fields is_subgroup & parent_id is added to fields
 * 
 */
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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

	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	// Injecting resource sql.properties.
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	/**
	 * Returns all Groups & SubGroups
	 */
	@Override
	public List<GroupsDTO> getAllGroups() throws DataAccessException {

		List<GroupsDTO> groupslist = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groups_all.name()),
				new BeanPropertyRowMapper<GroupsDTO>(GroupsDTO.class));

		return groupslist;
	}

	/**
	 * Returns name from groups
	 * 
	 * @param user_id
	 * 
	 */
	@Override
	public String findGroupBy(int users_id) throws DataAccessException {
		SqlParameterSource param = new MapSqlParameterSource("users_id", users_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groupname_byuserid.name()), param,
				String.class);
	}

	/**
	 * Returns list of users by group/sub_group name
	 * 
	 * @param group_name
	 */
	@Override
	public List<UsersGroupsDTO> getUsersByGroup(String group_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_usersbygroups.name()), param,
				new UserGroupsResultSetExtractor());

	}

	/**
	 * 
	 * 
	 * Returns all users to the corresponding to that Group
	 * @return List<UsersGroupDTO>
	 */
	@Override
	public List<UsersGroupsDTO> getAllUsersAndGroup() throws DataAccessException {

		List<UsersGroupsDTO> listOfUsersWithGroup = namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_usersgroups_all.name()),
				new UserGroupsResultSetExtractor());

		//th
		if(listOfUsersWithGroup.isEmpty())
			throw new EmptyResultDataAccessException(0);
		
		return listOfUsersWithGroup;

	}

	@Override
	public int findIDBy(String groupname) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Returns Group of particular drama
	 * 
	 * @param drama_id
	 */
	@Override
	public String findGroupByDramaId(int drama_id) throws DataAccessException {
		SqlParameterSource param = new MapSqlParameterSource("drama_id", drama_id);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groupname_bydramaid.name()), param,
				String.class);
	}

	/**
	 * 
	 * Returns all Dramas of particular group
	 */
	@Override
	public List<DramasGroupsDTO> getDramasByGroup(String group_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_dramasbygroups.name()), param,
				new DramaGroupsResultSetExtractor());

	}

	/**
	 * Returns all drama of a prticule group
	 */
	@Override
	public List<DramasGroupsDTO> getAllDramasAndGroup() throws DataAccessException {

		return namedParamJdbcTemplate.query(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_dramasgroups_all.name()),
				new DramaGroupsResultSetExtractor());
	}

	/**
	 * Returns true if group_name exists
	 * 
	 * @param group_name
	 */
	@Override
	public boolean isExists(String group_name) throws DataAccessException {

		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_groups_exists.name()), param,
				Integer.class) == 1;

	}

	/**
	 * Add GroupsDTO Object into groups table
	 * 
	 * Returns Group_id
	 * 
	 * @param GroupsDTO
	 */
	@Override
	public int addGroup(GroupsDTO groupsDTO) throws DuplicateKeyException, DataAccessException {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(groupsDTO);
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.GROUP_QUERIES.insert_groups.name()), params,
				keyHolder);
		return keyHolder.getKey().intValue();
	}

	/**
	 * Update group of a user
	 * 
	 * @param userName
	 * @param group_name
	 * @return
	 */
	@Override
	public boolean updateGroupOfUser(String userName, String group_name) throws DataAccessException {
		SqlParameterSource param = new MapSqlParameterSource().addValue("username", userName).addValue("group_name",
				group_name);
		return namedParamJdbcTemplate
				.update(sqlProperties.getProperty(Constants.GROUP_QUERIES.update_group_byuser.name()), param) == 1;
	}

	/**
	 * Get total count of Groups and its corresponding subgroup
	 */
	@Override
	public int getGroupCount() {
		SqlParameterSource param = new MapSqlParameterSource(null);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_count_groups.name()), param, Integer.class);
	}

	/**
	 * delete group
	 */
	@Override
	public boolean deleteGroup(String group_name) throws DataAccessException {
		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.update(sqlProperties.getProperty(Constants.GROUP_QUERIES.delete_groups.name()),
				param) == 1;
	}

	/**
	 * 
	 */
	@Override
	public boolean hasSubgroups(String group_name) {
		SqlParameterSource param = new MapSqlParameterSource("group_name", group_name);
		return namedParamJdbcTemplate.queryForObject(
				sqlProperties.getProperty(Constants.GROUP_QUERIES.select_has_subgroup.name()), param,
				Integer.class) > 0;
	}

}
