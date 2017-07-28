package com.lognsys.service;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.dto.GroupsDTO;
import com.lognsys.dao.dto.RatingsDTO;
import com.lognsys.dao.dto.RolesDTO;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.dao.jdbc.JdbcDramaRepository;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcRatingsRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Drama;
import com.lognsys.model.DramasTable;
import com.lognsys.model.Ratings;
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
	private JdbcRatingsRepository jdbcRatingsRepository;

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
	 * TODO 
	 * @return
	 */
	@Transactional
	public int addDrama(Drama dramas) {
		String dramaTitle = dramas.getTitle();
		System.out.println("DramaService addDrama  dramaTitle "+dramaTitle);
		
		DramasDTO dramasDTO = ObjectMapper.mapToDramasDTO(dramas);
		System.out.println("DramaService addDrama  dramasDTO "+dramasDTO);
		
		try {
				int dramaID =jdbcDramaRepository.addDrama(dramasDTO);
				System.out.println("#addUser - " + "Adding drama in database with dramaID - " + dramaID);
				
				System.out.println("#addUser - " + "Adding DRAMA to corresponding GROUP - " + dramas.getGroup());
				jdbcDramaRepository.addDramaAndGroup(dramaID, dramas.getGroup());

				System.out.println("#addUser - " + "Adding DRAMA to corresponding Auditorium - " + dramas.getAuditorium());
				jdbcDramaRepository.addDramaAndAuditorium(dramaID, dramas.getAuditorium());

				return dramaID;
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
	public void deleteDramas(Integer[] ids) {
		System.out.println("manageDrama deleteDramas ids length "+ids.length);
		
		for (int id : ids) {
			try {
				System.out.println("manageDrama deleteDramas id "+id);
				
				boolean isDelete = jdbcDramaRepository.deleteDramaBy(id);
				System.out.println("manageDrama deleteDramas isDelete "+isDelete);
				
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

	public boolean updateDrama(int id,DramasDTO drama) {
		
		return jdbcDramaRepository.updateDrama(drama);

	}
	/*
	*//**
	 * @return returns the list of drama from database
	 */
	public List<DramasDTO> getDramas() {

//		LOG.info("#getDramas - Get All Dramas from database");
		List<DramasDTO> dramaList;

		try {
			dramaList = jdbcDramaRepository.getAllDramas();
			System.out.println("showDramas --> getDramas dramaList  "+dramaList);
			System.out.println("showDramas --> getDramas dramaList  size "+dramaList.size());
			
			ResourceLoader resourceLoader = new FileSystemResourceLoader();
			Resource resource = resourceLoader
					.getResource(applicationProperties.getProperty(Constants.JSON_FILES.drama_filename.name()));
			String list = CommonUtilities.convertToJSON(dramaList);
			System.out.println("showDramas --> getDramas list  "+list);
			 
			try {
				
				WriteJSONToFile.getInstance().write(resource, list);
			} catch (IOException e) {
				System.out.println("showDramas --> getDramas IOException  "+e);
				
				e.printStackTrace();
			}
		} catch (DataAccessException dae) {
			System.out.println("showDramas --> getDramas DataAccessException  "+dae);
			
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
		} catch (Exception dae) {
			return jdbcDramaRepository.findDramaById(id);
//			throw new IllegalAccessError("Failed to get drama from database with ID - " + id);
		}
		

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

	/**
	 * 
	 * @return
	 */
	public List<AuditoriumsDTO> getAllAuditoriums() {

		try {
			return jdbcAuditoriumRepository.getAllAuditoriums();
		} catch (DataAccessException dae) {
			LOG.error(dae.getMessage());
			throw new IllegalAccessError("Error: All Auditoriums cannot be retrieved");
		}

	}
	
	public List<DramasDTO> getDramasByGroup() {

		LOG.info("#getDramas - Get All Dramas from database");
		List<DramasDTO> dramaList;

		try {
			dramaList = jdbcDramaRepository.getAllDramas();
			
			ResourceLoader resourceLoader = new FileSystemResourceLoader();
			Resource resource = resourceLoader
					.getResource(applicationProperties.getProperty(Constants.JSON_FILES.drama_filename.name()));
			String list = CommonUtilities.convertToJSON(dramaList);
//			System.out.println("showDramas --> getDramas list  "+list);
			
			try {
				System.out.println("showDramas --> getDramas WriteJSONToFile  ");
				
				WriteJSONToFile.getInstance().write(resource, list);
			} catch (IOException e) {
				System.out.println("showDramas --> getDramas IOException  "+e);
				
				e.printStackTrace();
			}
		} catch (DataAccessException dae) {
			System.out.println("showDramas --> getDramas DataAccessException  "+dae);
			
			LOG.error(dae.getMessage());
			throw new IllegalStateException("Error : Failed to add dramaList !");
		}
		return dramaList;
	}

	
}
