package com.lognsys.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Hashtable<String, String[]> getAuditoriumList(int dramas_id) {
		List<AuditoriumsDTO> lists=new ArrayList<>();
		lists=jdbcAuditoriumRepository.findAuditoriumBy(dramas_id);
		
		System.out.println("#Service AuditoriumsDTO auditoriumsDTO lists "+ lists.size());
		
		Hashtable<String, String[]> hashtable=ObjectMapper.mapToAuditoriumDTO(lists);
		
		try {
			   System.out.println("#Service AuditoriumsDTO lihashtablests "+ hashtable.size());
				
			return hashtable;
			 /*  Enumeration values;
			   String key;

				System.out.println("#Service AuditoriumsDTO dramas_id "+ dramas_id);   
			AuditoriumsDTO auditoriumsDTO=jdbcAuditoriumRepository.findAuditoriumBy(dramas_id);
			System.out.println("#Service AuditoriumsDTO auditoriumsDTO toString "+ auditoriumsDTO.toString());
			
			Hashtable<String, String> hashtable=ObjectMapper.mapToAuditoriumDTO(auditoriumsDTO);
			
			   System.out.println("#Service AuditoriumsDTO hashtable "+ hashtable.size());
				
			if(hashtable!= null && hashtable.size()>0){
				values = hashtable.keys();
				   while(values.hasMoreElements()) {
				      AuditoriumsDTO auditoriumsDTO2=new   AuditoriumsDTO();
					 
				      key = (String) values.nextElement();
				      System.out.println("Key: " +key+ " & Value: " + hashtable.get(key));
				    
				      if(key.equalsIgnoreCase("auditorium_name"));
				      {
				    	  auditoriumsDTO2.setAuditorium_name((hashtable.get(key)));
				      }
				      if(key.equalsIgnoreCase("date")){
				    	  auditoriumsDTO2.setDate((hashtable.get(key)));
					  }
				      if(key.equalsIgnoreCase("time")){
				    	  auditoriumsDTO2.setTime((hashtable.get(key)));
					  }
				      if(key.equalsIgnoreCase("price")){
				    	  auditoriumsDTO2.setPrice(Double.parseDouble(hashtable.get(key)));
					  }
				      if(key.equalsIgnoreCase("istart")){
				    	  auditoriumsDTO2.setIstart(Integer.parseInt(hashtable.get(key)));
					  }
				      if(key.equalsIgnoreCase("iend")){
				    	  auditoriumsDTO2.setIend(Integer.parseInt(hashtable.get(key)));
					  }	
				      lists.add(auditoriumsDTO2);
				   }
				   System.out.println("#Service AuditoriumsDTO lists "+ lists.size());
					
				   return lists;
			}*/
			
			
		} catch (Exception dae) {
			System.out.println("#Service AuditoriumsDTO Exception "+ dae);
			System.out.println("#Service AuditoriumsDTO Exception hashtable "+ hashtable);
			
		}
		return hashtable;
		

	}
}
