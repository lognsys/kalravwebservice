package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class DramasAuditoriumsDTO {

	@Id
	private int id;

	@DBRef
	private AuditoriumsDTO auditoriums;

	@DBRef
	private DramasDTO dramasDTO;

	public DramasAuditoriumsDTO(int id, AuditoriumsDTO auditoriums, DramasDTO dramasDTO) {
		super();
		this.id = id;
		this.auditoriums = auditoriums;
		this.dramasDTO = dramasDTO;
	}

	public DramasAuditoriumsDTO() {

	}

	
	public AuditoriumsDTO getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(AuditoriumsDTO auditoriums) {
		this.auditoriums = auditoriums;
	}

	public DramasDTO getDrama() {
		return dramasDTO;
	}

	public void setDrama(DramasDTO dramasDTO) {
		this.dramasDTO = dramasDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
