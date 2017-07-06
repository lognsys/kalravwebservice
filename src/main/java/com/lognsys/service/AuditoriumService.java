package com.lognsys.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public JSONArray getAuditoriumList(int dramas_id) {
		List<AuditoriumsDTO> auditoriumsDTOsList=jdbcAuditoriumRepository.findAuditoriumBy(dramas_id);
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
				
					if(comparedDateWithCurrrentDate(auditoriumsDTO)){
						
						List<AuditoriumsDTO> auditoriumsPriceDTOsList= jdbcAuditoriumRepository.getAuditoriumListBy(auditoriumsDTO.getId(),dramas_id);
						
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
					
					}else{
					auditoriumsDTOsList.remove(auditoriumsDTO);
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
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // 2015-01-20-2015 
         Date auditoriumDatabaseDate=null;
//         Date currentDate=new Date();
         Calendar currentDate = Calendar.getInstance();
      String today=   getToday("yyyy-MM-dd hh:mm:ss ");
      String[] todaysArray=today.split(" ");
      System.out.println("today splite "+today.split(" "));
  	try {
		String date1 = auditoriumsDTODate.getDate();//"2016-07-15";
		String time1 = auditoriumsDTODate.getTime();//"11:00 AM";
		String date2 =todaysArray[0];//"2016-07-17";
		String time2 =todaysArray[1];//"12:15 AM";
		System.out.println("\n\n #comparedDateWithCurrrentDate  date1 "+ date1+"   time1"+time1);
		System.out.println("\n\n #comparedDateWithCurrrentDate  date2 "+ date2+"   time2"+time2);
		
		Date dateObj1 = sdf.parse(date1 + " " + time1);
		Date dateObj2 = sdf.parse(date2 + " " + time2);
		System.out.println(dateObj1);
		System.out.println(dateObj2 + "\n");
	
		System.out.println("both  are dateObj2 : " + (dateObj2) +"  dateObj1  :  "+dateObj1);
		
		System.out.println("both  are same : " + (dateObj2.getDate()==dateObj1.getDate()));
		
		if(dateObj2.getDate()==dateObj1.getDate()){// both date are same
			System.out.println("both  are dateObj2.getTime() < dateObj1.getTime() : " + (dateObj2.getTime() < dateObj1.getTime()));
			
			System.out.println("both  are dateObj2.getTime() "+dateObj2.getTime() +"  dateObj1.getTime() : " + ( dateObj1.getTime()));
			
			if(dateObj2.getTime() < dateObj1.getTime()){// not expired
                return true;
            }else if(dateObj2.getTime() >= dateObj1.getTime()){//expired
                return false;
            }
           }
		else if(dateObj2.getDate()<=dateObj1.getDate()){// both date are same
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
