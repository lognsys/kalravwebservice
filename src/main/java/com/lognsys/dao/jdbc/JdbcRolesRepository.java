package com.lognsys.dao.jdbc;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.RoleRepository;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.util.Constants;

@Repository
public class JdbcRolesRepository implements RoleRepository {

	// Injecting namedParamterTemplate
	@Autowired
	private NamedParameterJdbcTemplate namedParamJdbcTemplate;

	/**
	 * Injecting resource sql.properties.
	 */
	@Resource(name = "sqlProperties")
	private Properties sqlProperties;

	@Override
	public List<RolesDTO> getAllRoles() {
		// TODO Auto-generated method stub

		return namedParamJdbcTemplate.query(sqlProperties.getProperty(Constants.ROLES_QUERIES.select_roles_all.name()),
				new BeanPropertyRowMapper<RolesDTO>(RolesDTO.class));

	}

}
