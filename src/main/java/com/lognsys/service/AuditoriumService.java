package com.lognsys.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	public JSONArray getAuditoriumList(int dramas_id,String strDate) {
		   System.out.println("String date "+strDate);  
	    System.out.println("dramas id"+dramas_id);  
	    Date datenew  = null;
		
	    Date date1 = null;
		try {
			if(strDate!=null)
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			else{
				datenew=new Date();
				strDate=new SimpleDateFormat("yyyy-MM-dd").format(datenew);
				date1 = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
						
			}
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		List<AuditoriumsDTO> auditoriumsDTOsList=jdbcAuditoriumRepository.findAuditoriumBy(dramas_id,date1);
//		System.out.println("#AuditoriumService getAuditoriumList  lists.size() "+ auditoriumsDTOsList.size());
		
		JSONArray mainArray=new JSONArray();

		if(auditoriumsDTOsList!=null && auditoriumsDTOsList.size()>0){
			for(int i=0 ;i<auditoriumsDTOsList.size();i++){
				
				JSONArray jsonArray=new JSONArray();
				AuditoriumsDTO auditoriumsDTO=auditoriumsDTOsList.get(i);
	//			System.out.println("#AuditoriumService getAuditoriumList  lists  auditoriumsDTO.toString()  "+ auditoriumsDTO.toString());
			
				if(auditoriumsDTO.getId()!=0
						&& auditoriumsDTO.getDate()!= null
						&& auditoriumsDTO.getDate().length()>0){
					System.out.println("#AuditoriumService comparedDateWithCurrrentDate auditoriumsDTO.getDate() "+ auditoriumsDTO.getDate());
					System.out.println("#AuditoriumService comparedDateWithCurrrentDate comparedDateWithCurrrentDate(auditoriumsDTOsList.get(i)) "+ comparedDateWithCurrrentDate(auditoriumsDTOsList.get(i)));
					
					if(comparedDateWithCurrrentDate(auditoriumsDTOsList.get(i))==true){
						List<AuditoriumsDTO> auditoriumsPriceDTOsList= jdbcAuditoriumRepository.getAuditoriumListBy(auditoriumsDTOsList.get(i).getId(),dramas_id,auditoriumsDTO.getDate());
						
						JSONObject jsonObject=new JSONObject();
					
						jsonObject.put("id", auditoriumsDTO.getId());
						jsonObject.put("auditorium_name", auditoriumsDTO.getAuditorium_name());
						jsonObject.put("date", auditoriumsDTO.getDate());
						jsonObject.put("time", auditoriumsDTO.getTime());
						
							if(auditoriumsPriceDTOsList!=null && auditoriumsPriceDTOsList.size()>0){
			//					System.out.println("#AuditoriumService getAuditoriumList lists auditoriumsPriceDTOsList.size() "+ auditoriumsPriceDTOsList.size());
								
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

						return mainArray;
					}
					else{

						List<AuditoriumsDTO> auditoriumsPriceDTOsList= jdbcAuditoriumRepository.getAuditoriumListBy(auditoriumsDTOsList.get(i).getId(),dramas_id,auditoriumsDTO.getDate());
						System.out.println("#AuditoriumService comparedDateWithCurrrentDate auditoriumsPriceDTOsList size "+ auditoriumsPriceDTOsList.size());
						
						JSONObject jsonObject=new JSONObject();
					
						jsonObject.put("id", auditoriumsDTO.getId());
						jsonObject.put("auditorium_name", auditoriumsDTO.getAuditorium_name());
						jsonObject.put("date", auditoriumsDTO.getDate());
						jsonObject.put("time", auditoriumsDTO.getTime());
						
							if(auditoriumsPriceDTOsList!=null && auditoriumsPriceDTOsList.size()>0){
			//					System.out.println("#AuditoriumService getAuditoriumList lists auditoriumsPriceDTOsList.size() "+ auditoriumsPriceDTOsList.size());
								
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
						System.out.println("#AuditoriumService comparedDateWithCurrrentDate mainArray size "+ mainArray.size());
						System.out.println("#AuditoriumService comparedDateWithCurrrentDate mainArray toString() "+ mainArray.toString());
						
						
						return mainArray;
					}
			}
		}
		return mainArray;
		}
		else{
			return null;

		}
		
			
	}
	private boolean comparedDateWithCurrrentDate(AuditoriumsDTO auditoriumsDTODate) {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm a"); // 2015-01-20-2015 
       
      String today=   getToday("yyyy-MM-dd hh:mm a ");
      System.out.println("today "+today);
    	 String[] todaysArray=today.split(" ");
    try {
		String dateFromDB = auditoriumsDTODate.getDate();//"2016-07-15";
		String timeFromDB = auditoriumsDTODate.getTime();//"11:00 AM";
		String date2 =todaysArray[0];//"2016-07-17";
		String time2 =todaysArray[1];//"12:15 AM";
		System.out.println("\n\n #comparedDateWithCurrrentDate dateFromDB "+dateFromDB+"  timeFromDB  "+timeFromDB);
		System.out.println("\n\n #comparedDateWithCurrrentDate todays date date2 "+ date2+"   time2"+time2);
		
		Date dateObjFromDB = sdf.parse(dateFromDB + " " + timeFromDB);
		Date dateObj2 = sdf.parse(date2 + " " + time2);
	
		System.out.println("both  are dateObj2 : " + (dateObj2) +" \n dateObjFromDB  :  "+dateObjFromDB);
		
		System.out.println("both  are same : " + (dateObj2.getDate()==dateObjFromDB.getDate()));
		
		if(dateObj2.getDate()==dateObjFromDB.getDate()){// both date are same
			System.out.println("both  are dateObj2.getTime() < dateObjFromDB.getTime() : " + (dateObj2.getTime() < dateObjFromDB.getTime()));
			
			System.out.println("both  are dateObj2.getTime() "+dateObj2.getTime() +"  dateObjFromDB.getTime() : " + ( dateObjFromDB.getTime()));
			
			if(dateObj2.getTime() < dateObjFromDB.getTime()){// not expired
                return true;
            }else if(dateObj2.getTime() >= dateObjFromDB.getTime()){//expired
                return false;
            }
           }
		else if(dateObj2.getDate()<=dateObjFromDB.getMonth()){// comparing months
			      return true;
           }
		else{
			return false;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	}
	public static String getToday(String format){
	     Date date = new Date();
	    
	     return new SimpleDateFormat(format).format(date);
	   
	 }
}
