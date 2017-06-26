/**
 * This program is used to populate babycare data from excelsheet. This used spring-core container for dependency
 * injection of DataSource and Resources like "XLS" . "XML"
 * 
 * @PJD - 06-13-14 Change Log: 07-02-14
 */

package com.lognsys.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.RowSeatDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;

@Component
public class IngestXLSData implements Ingest {

	private static Logger log = Logger.getLogger(IngestXLSData.class);

	private Sheet sheet = null;

	private Resource resource;

	private Properties sqlProperty;

	private NamedParameterJdbcTemplate jdbcTemplate;

	// static fields
	private static String ARG = "";

	private static final int EXIT_ERROR = 1;

	private static final int EXIT_OK = 0;

	private static Parser parser = new Parser();

	@Autowired
	private JdbcAuditoriumRepository jdbcAuditoriumRepository;

	/**
	 * dependency injection of resource and datasource.
	 * 
	 * @param resource
	 * @param dataSource
	 */
	public IngestXLSData(Resource resource, DataSource dataSource, Properties sqlProperty) {
		this.sqlProperty = sqlProperty;
		this.resource = resource;
	}

	// print usage
	public static void usage() {
		System.out.println("Usage : java IngestXLSdata (auditorium | row_seat)");
	}

	/**
	 * This method parses data from excel sheet, builds object from data parsed
	 * from excel sheet loads specific sheet passed as global argument
	 */
	@Override
	public void parseData() {

		Sheet sheet = getSheet();

		String sheetname = sheet.getSheetName();

		switch (EXCELSHEETS.valueOf(sheetname)) {

		case auditorium:
			System.out.println("Parsing Auditoriums Sheet.....");
			List<AuditoriumsDTO> listOfAuditoriums = parser.parseAuditoriums(sheet);

			break;

		case row_seat:
			System.out.println("Parsing Row_Seat Sheet.....");
			List<RowSeatDTO> listOfRowSeat = parser.parseRowSeat(sheet);

			break;

		default:
			System.err.println("Sheet not found");
			break;

		}

	}

	/**
	 * 
	 * @param listOfData
	 */
//	public void loadAuditorium(List<AuditoriumsDTO> listOfData) {
//
//		for()
//	}

//	/**
//	 * List<NutritionalVO> param passed. ingest table ayurbaby_nutritionalfood
//	 * ingest table ayurbaby_stage_nutritionalfood
//	 * 
//	 * @param listOfData
//	 * @return no. of rows loaded
//	 */
//	public void loadNutritionalFood(List<?> listOfData) {
//
//		int totalRowCount = 0;
//		for (Object obj : listOfData) {
//			try {
//				NutritionalVO nutVo = ((NutritionalVO) obj);
//				// ingest ayurbaby_nutritionalfood
//				BeanPropertySqlParameterSource nutVoBeanParam = new BeanPropertySqlParameterSource(nutVo);
//				KeyHolder nutVoKeyHolder = new GeneratedKeyHolder();
//				int row = jdbcTemplate.update(this.sqlProperty.getProperty("INSERT_NUTRITIONALFOOD"), nutVoBeanParam,
//						nutVoKeyHolder);
//
//				totalRowCount = totalRowCount + row;
//
//				// ingest ayurbaby_stages_nutritionalfood
//				int[] monthArr = Util.normalizeMonths(nutVo.getMonth(), ",");
//
//				for (int month : monthArr) {
//					MapSqlParameterSource params = new MapSqlParameterSource();
//					params.addValue("stage_id", month);
//					params.addValue("nutritionalfood_id", nutVoKeyHolder.getKey());
//					jdbcTemplate.update(this.sqlProperty.getProperty("INSERT_STAGE_NUTRITIONALFOOD"), params);
//
//				}
//
//			} catch (DataAccessException dae) {
//				dae.printStackTrace();
//			}
//
//			nutrtionalFoodRowCount = totalRowCount;
//
//		}
//
//	}
//
//	public void load(List<?> listOfData) {
//
//		int rowCount = 0;
//		for (Object obj : listOfData) {
//			try {
//				// type casting
//				RecipesVO recipeVo = ((RecipesVO) obj);
//
//				// ingest recipe
//				BeanPropertySqlParameterSource recipeVoBeanParam = new BeanPropertySqlParameterSource(recipeVo);
//				KeyHolder recipeVoKeyHolder = new GeneratedKeyHolder();
//				int row = jdbcTemplate.update(this.sqlProperty.getProperty("INSERT_RECIPE"), recipeVoBeanParam,
//						recipeVoKeyHolder);
//
//				rowCount = rowCount + row;
//
//				// ingest ayurbaby_stages_ayurvedicfood
//				int[] monthArr = Util.normalizeMonths(recipeVo.getMonth(), ",");
//
//				for (int month : monthArr) {
//					MapSqlParameterSource params = new MapSqlParameterSource();
//					params.addValue("stage_id", month);
//					params.addValue("recipe_id", recipeVoKeyHolder.getKey());
//					jdbcTemplate.update(this.sqlProperty.getProperty("INSERT_STAGE_RECIPE"), params);
//
//				}
//
//			} catch (DataAccessException dae) {
//				dae.printStackTrace();
//			}
//
//			recipeRowCount = rowCount;
//		}
//
//	}
//
//	/**
//	 * List<AyurvedicVO> obj param passed for load
//	 * 
//	 * @param listOfData
//	 * @return no. of rows loaded
//	 */
//	public void loadAyurved(List<?> listOfData) {
//		int rowCount = 0;
//		for (Object obj : listOfData) {
//			try {
//				// type casting
//				AyurvedicVO ayurVo = ((AyurvedicVO) obj);
//				// ingest ayurvedic
//				BeanPropertySqlParameterSource ayurVoBeanParam = new BeanPropertySqlParameterSource(ayurVo);
//				KeyHolder ayurVoKeyHolder = new GeneratedKeyHolder();
//				int row = jdbcTemplate.update(this.sqlProperty.getProperty("INSERT_AYURVED"), ayurVoBeanParam,
//						ayurVoKeyHolder);
//
//				rowCount = rowCount + row;
//
//				// ingest ayurbaby_stages_ayurvedicfood
//				int[] monthArr = Util.normalizeMonths(ayurVo.getMonth(), ",");
//
//				for (int month : monthArr) {
//					MapSqlParameterSource params = new MapSqlParameterSource();
//					params.addValue("stage_id", month);
//					params.addValue("ayurved_id", ayurVoKeyHolder.getKey());
//					jdbcTemplate.update(this.sqlProperty.getProperty("INSERT_STAGE_AYURVED"), params);
//
//				}
//
//			} catch (DataAccessException dae) {
//				dae.printStackTrace();
//			}
//
//		}
//
//		ayurvedicRowCount = rowCount;
//	}
//
//	/**
//	 * listOfFunFacts obj param passed for load
//	 * 
//	 * @param listOfFunFacts
//	 * @return no. of rows loaded
//	 */
//	public int loadFunfacts(List<?> listOfFunFacts) {
//		int[] rowCount = null;
//
//		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(listOfFunFacts.toArray());
//
//		try {
//			rowCount = jdbcTemplate.batchUpdate(this.sqlProperty.getProperty("INSERT_FUNFACTS"), params);
//		} catch (DataAccessException dae) {
//			log.error(dae.getMessage());
//		}
//
//		return rowCount.length;
//
//	}
//
//	/**
//	 * @return no. of rows loaded
//	 */
//	public int loadOrganicCompunds() {
//
//		List<CompoundsVO> listOfCompounds = new ArrayList<CompoundsVO>();
//
//		for (String compound : organicCompounds) {
//
//			listOfCompounds.add(new CompoundsVO(compound));
//
//		}
//
//		int[] rowCount = null;
//		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(listOfCompounds.toArray());
//
//		{
//			try {
//				System.out.println("In - SQL INSERT COMPOUND");
//				rowCount = jdbcTemplate.batchUpdate(this.sqlProperty.getProperty("INSERT_COMPOUND"), params);
//			} catch (DataAccessException dae) {
//				log.error(dae.getMessage());
//				System.out.println(dae.getMessage());
//			}
//		}
//		return rowCount.length;
//	}
//
//	@Override
//	public void printReport() {
//
//		System.out.println("\nParsed Summary:");
//		List<String> parsed = Parser.getStats();
//		for (String stats : parsed) {
//			System.out.println(stats);
//		}
//
//		System.out.println("\n\nDatabase Summary:");
//		System.out.println("Nutritional Table rows loaded - " + nutrtionalFoodRowCount);
//		System.out.println("Ayurvedic Table rows loaded - " + ayurvedicRowCount);
//		System.out.println("Funfacts Table rows loaded - " + funFactsRowCount);
//		System.out.println("Organic Compound Table rows loaded - " + organiCompoundRowCount);
//		System.out.println("Recipe Table rows loaded - " + recipeRowCount);
//		System.out.println();
//
//	}

	/**
	 * run method instantiated
	 */
	@Override
	public int run() {

		// check if the resource injected exists.
		if (!resource.exists()) {
			log.error("File not found - " + resource.getDescription());
			throw new IllegalArgumentException("ERROR: File not found - " + resource.getDescription());

		}

		// java 7 try-with-resource will close the inputstream after exiting the
		// block-scope
		try (InputStream is = resource.getInputStream()) {
			// Get the workbook instance for XLS file
			Workbook workBook = WorkbookFactory.create(is);

			/*
			 * int noOfSheets = workBook.getNumberOfSheets(); if (noOfSheets !=
			 * 3) throw new IllegalArgumentException(
			 * "ERROR: AyurBaby ExcelSheet does not meet requirements."); // if
			 * no argument then load all the sheets if
			 * (IngestXLSData.ARG.isEmpty()) { for (int i = 0; i < noOfSheets;
			 * i++) { // Get sheets from the workbook Sheet sheet =
			 * workBook.getSheetAt(i); // Set sheet setSheet(sheet); // call
			 * parsedata after setting sheet for parsing parseData(); } }
			 */

			// if argument then load all the sheets
			if (!IngestXLSData.ARG.isEmpty()) {
				Sheet sheet = workBook.getSheet(IngestXLSData.ARG);

				if (sheet == null) {

					throw new IllegalArgumentException("ERROR :  argument not found... Please see the usage");

				}
				setSheet(sheet);

				parseData();

			}

			printReport();
		} catch (InvalidFormatException | IOException e) {
			log.error("Error occured while trying to read the file");
			e.printStackTrace();
		}

		return EXIT_OK;
	}

	/**
	 * @return Sheet set from workbook
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * Sets the sheet for parsing
	 * 
	 * @param sheet
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public static void main(String[] args) throws IOException {
		Date date = new Date();

		System.out.println("Kalrav Data Ingest program started - " + date.toString());
		long startMilli = System.currentTimeMillis();

		// checks args.length = 1
		if (args.length != 1) {
			usage();
			System.err.println("Argument required...");
			System.exit(EXIT_ERROR);
		}

		IngestXLSData.ARG = args[0];

		// validate argument with excelsheet name using enum
		boolean isArgValid = false;
		for (EXCELSHEETS sheet : EXCELSHEETS.values()) {
			if (sheet.name().equals(IngestXLSData.ARG)) {
				isArgValid = true;
			}
		}

		if (!isArgValid) {
			usage();
			System.err.println("Invalid Excel file....sheet not found");
			System.exit(EXIT_ERROR);
		}

		System.setProperty("spring.profiles.active", "dev");

		// Spring Application context loads
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:loader-context.xml" });
		//
		// ctx.getEnvironment().setActiveProfiles("dev");

		// ctx.refresh();

		IngestXLSData ingest = ctx.getBean("xlsResource", IngestXLSData.class);

		// Run ingest using run program
		ingest.run();

		date = new Date();
		long elapsedTimeMillis = System.currentTimeMillis() - startMilli;
		// Get elapsed time in seconds
		float elapsedTimeSec = elapsedTimeMillis / 1000F;

		ctx.close();
		System.out.println();
		System.out.println(
				"AyurBaby Ingest program ended " + date.toString() + " Elapsed time = " + elapsedTimeSec + " seconds.");

	}

	@Override
	public void printReport() {
		// TODO Auto-generated method stub
		
	}
}
