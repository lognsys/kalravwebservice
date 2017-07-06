package com.lognsys.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.lognsys.dao.dto.AuditoriumsDTO;
import com.lognsys.dao.dto.DramasDTO;
import com.lognsys.dao.jdbc.JdbcAuditoriumRepository;
import com.lognsys.util.ObjectMapper;

@Service("auditoriumService")
public class AuditoriumService {


	@Autowired
	private JdbcAuditoriumRepository jdbcAuditoriumRepository;
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public JSONArray getAuditoriumList(int dramas_id) {
		List<AuditoriumsDTO> auditoriumsDTOsList=jdbcAuditoriumRepository.findAuditoriumBy(dramas_id);
		
		System.out.println("#AuditoriumService getAuditoriumList  lists.size() "+ auditoriumsDTOsList.size());
		
		JSONArray mainArray=new JSONArray();
		if(auditoriumsDTOsList!=null && auditoriumsDTOsList.size()>0){
			
		for(int i=0 ;i<auditoriumsDTOsList.size();i++){
			JSONArray jsonArray=new JSONArray();
			
			AuditoriumsDTO auditoriumsDTO=auditoriumsDTOsList.get(i);
			
			System.out.println("#AuditoriumService getAuditoriumList  lists  auditoriumsDTO.toString()  "+ auditoriumsDTO.toString());
			
			if(auditoriumsDTO.getId()!=0){
				
				List<AuditoriumsDTO> auditoriumsPriceDTOsList= jdbcAuditoriumRepository.getAuditoriumListBy(auditoriumsDTO.getId(),dramas_id);
				
				JSONObject jsonObject=new JSONObject();
			
				jsonObject.put("id", auditoriumsDTO.getId());
				jsonObject.put("auditorium_name", auditoriumsDTO.getAuditorium_name());
				jsonObject.put("date", auditoriumsDTO.getDate());
				jsonObject.put("time", auditoriumsDTO.getTime());
				
				if(auditoriumsPriceDTOsList!=null && auditoriumsPriceDTOsList.size()>0){
					System.out.println("#AuditoriumService getAuditoriumList lists auditoriumsPriceDTOsList.size() "+ auditoriumsPriceDTOsList.size());
					
					for(int j=0;j<auditoriumsPriceDTOsList.size();j++){
						AuditoriumsDTO auditsDTO=auditoriumsPriceDTOsList.get(j);
						JSONObject jsonPricelist=new JSONObject();
						if(auditsDTO!=null && auditsDTO.getIstart() !=0 && auditsDTO.getIend()!=0 && auditsDTO.getPrice()!=0){
							jsonPricelist.put("istart", auditsDTO.getIstart());
							jsonPricelist.put("iend", auditsDTO.getIend());
							jsonPricelist.put("price", auditsDTO.getPrice());
							jsonArray.add(jsonPricelist);
		
						}
					}
				}
				if(jsonArray!=null && jsonArray.size()>0){
					jsonObject.put("auditoriumpricelist",jsonArray);
					mainArray.add(jsonObject);
				}
				
			}

		}
		System.out.println("\n\n #AuditoriumService getAuditoriumList  mainArray "+ mainArray.toJSONString());
		return mainArray;

		}
		else{
			return null;

		}
		
			
	}
}
