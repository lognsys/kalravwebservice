package com.lognsys.loader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.RowSeatDTO;

public class Parser {

	/**
	 * 
	 * @param auditoriumSheet
	 * @return
	 */
	public List<AuditoriumsDTO> parseAuditoriums(Sheet auditoriumSheet) {

		Iterator<Row> rowIterator = auditoriumSheet.iterator();

		List<AuditoriumsDTO> listOfAuditorium = new ArrayList<AuditoriumsDTO>();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			AuditoriumsDTO auditoriumsDTO = null;

			if (row.getRowNum() != 0) {

				auditoriumsDTO = new AuditoriumsDTO();

				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 0)
						auditoriumsDTO.setAuditorium_name(getCellValue(cell));

					if (cell.getColumnIndex() == 1)
						auditoriumsDTO.setAddress(getCellValue(cell));

					if (cell.getColumnIndex() == 2)
						auditoriumsDTO.setLatitude(getCellValue(cell));

					if (cell.getColumnIndex() == 3)
						auditoriumsDTO.setLongitude(getCellValue(cell));

				}

				if (auditoriumsDTO != null) {
					listOfAuditorium.add(auditoriumsDTO);
				}

			}

		}
		return listOfAuditorium;

	}

	/**
	 * 
	 * @param rowsSheet
	 * @return
	 */
	public List<RowSeatDTO> parseRowSeat(Sheet rowsSheet) {

		Iterator<Row> rowIterator = rowsSheet.iterator();

		List<RowSeatDTO> listOfRows = new ArrayList<RowSeatDTO>();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			RowSeatDTO rowSeatDTO = null;

			if (row.getRowNum() != 0) {

				rowSeatDTO = new RowSeatDTO();

				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					if (cell.getColumnIndex() == 0) {

						rowSeatDTO.setRow_num(Integer.parseInt(getCellValue(cell)));
					}
					if (cell.getColumnIndex() == 1) {

						rowSeatDTO.setRow_name(getCellValue(cell));
					}
					if (cell.getColumnIndex() == 2) {
						rowSeatDTO.setSeatCount(getCellValue(cell));
					}
					if (cell.getColumnIndex() == 3) {
						rowSeatDTO.setAuditorium_name(getCellValue(cell));
					}
				}

				listOfRows.add(rowSeatDTO);

			}

		}
		return listOfRows;

	}

	/**
	 * 
	 * Return Cell Value from
	 * 
	 * @param cell
	 * @return String cell value
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
			cellValue = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;

		case Cell.CELL_TYPE_NUMERIC:
			Double val = cell.getNumericCellValue();
			cellValue = Integer.toString(val.intValue());
			break;
		}

		return cellValue;

	}

}
