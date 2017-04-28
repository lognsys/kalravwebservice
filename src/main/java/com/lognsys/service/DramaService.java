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

import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Drama;
import com.lognsys.model.DramasTable;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.util.CommonUtilities;
import com.lognsys.util.Constants;
import com.lognsys.util.ObjectMapper;
import com.lognsys.util.WriteJSONToFile;

@Service("dramaService")
public class DramaService {

	Logger LOG = Logger.getLogger(this.getClass());

	@Autowired
	private JdbcDramaRepository jdbcDramaRepository;

	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;
	
	@Autowired
	private JdbcAuditoriumRepository jdbcAuditoriumRepository;

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
	public void addDrama(Drama dramas) {
		String dramaTitle = dramas.getTitle();
		System.out.println("DramaService addDrama  dramaTitle "+dramaTitle);
		
		DramasDTO dramasDTO = ObjectMapper.mapToDramasDTO(dramas);
		System.out.println("DramaService addDrama  dramasDTO "+dramasDTO);
		
		try {
			boolean isExists = jdbcDramaRepository.isExists(dramaTitle);
			System.out.println("DramaService addDrama  isExists "+isExists);
			
			if (isExists) {
				LOG.info("Found drama in database with dramaTitle - " + dramaTitle);

			} else {
				System.out.println("DramaService addDrama  Adding drama in database with "+dramaTitle);
				
				LOG.info("#addUser - " + "Adding drama in database with - " + dramaTitle);
				jdbcDramaRepository.addDrama(dramasDTO);

			}
		} catch (DataAccessException dae) {
			System.out.println("DramaService addDrama  Exception "+dae);
			
			throw new IllegalStateException("Error : Failed to add drama!");
		}
	}
	
	/**
	 * Synchronize drama from mysql to json files.
	 *
	 * @return
	 * @throws IOException
	 */
	public void refreshDramaList() throws IOException {
		List<DramasTable> dramas = ObjectMapper.mapToDramasTable(jdbcGroupRepository.getAllDramasAndGroup());
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.drama_filename.name()));

		String list = CommonUtilities.convertToJSON(dramas);
		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Synchronize drama from mysql to json files.
	 *
	 * @return
	 * @throws IOException
	 */
	public void refreshDramaListForAuditorium() throws IOException {
		List<DramasTable> dramas = ObjectMapper.mapToDramasAuditoriumTable(jdbcAuditoriumRepository.getAllDramasAndAuditorium());
		ResourceLoader resourceLoader = new FileSystemResourceLoader();
		Resource resource = resourceLoader
				.getResource(applicationProperties.getProperty(Constants.JSON_FILES.drama_filename.name()));

		String list = CommonUtilities.convertToJSON(dramas);
		try {
			WriteJSONToFile.getInstance().write(resource, list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	*//**
	 * Delete drama from database
	 * 
	 * @return
	 */
	public void deleteDramas(int[] ids) {
		LOG.info("#deleteDrama - " + "Deleting total number of dramas from database - " + ids.length);

		for (int id : ids) {
			try {

				boolean isDelete = jdbcDramaRepository.deleteDramaBy(id);

				if (!isDelete)
					LOG.info("#deleteDrama - " + "failed to delete dramas with ID - " + id);

			} catch (DataAccessException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete dramas!");
			}

		}
	}
	/*
	*//**
	 * Delete drama from database
	 * @param String title
	 * @return
	 */
	public void deleteDramas(String[] titles) {
		LOG.info("#deleteDrama - " + "Deleting total number of dramas from database - " + titles.length);

		for (String title : titles) {
			try {

				System.out.println("title "+title);
				boolean isDelete = jdbcDramaRepository.deleteDramaBy(title);

				if (!isDelete)
					LOG.info("#deleteDrama - " + "failed to delete dramas with title - " + title);

			} catch (DataAccessException dae) {

				LOG.error(dae.getMessage());
				throw new IllegalStateException("Error : Failed to delete dramas!");
			}

		}
	}
	/*
	 * update drama
	 */

	public void updateDrama(Drama drama) {

	}
	/*
	*//**
	 * @return returns the list of drama from database
	 */
	public List<DramasDTO> getDramas() {

		LOG.info("#getDramas - Get All Dramas from database");
		List<DramasDTO> dramaList;

		try {
			dramaList = jdbcDramaRepository.getAllDramas();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalStateException("Error : Failed to add dramaList !");
		}
		return dramaList;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public DramasDTO findByDrama(int id) {

		try {
			return jdbcDramaRepository.findDramaById(id);
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Failed to get drama from database with ID - " + id);
		}
	}

	
}
