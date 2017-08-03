package com.lognsys.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lognsys.dao.jdbc.JdbcBookingRepository;
import com.lognsys.model.Booking;
import com.lognsys.util.ObjectMapper;

@Service("bookingService")
public class BookingService {
	@Autowired
	private JdbcBookingRepository jdbcBookingRepository;

	
	/**
	 * Add user to database.. Check if user already exists in db
	 * 
	 * TODO : Add rollbackFor is users exists TODO : Add exception for users and
	 * roles and groups which has unqieu constraints
	 * 
	 * @return
	 * @throws IOException
	 * @throws org.json.simple.parser.ParseException 
	 */
	@Transactional(rollbackFor = IllegalArgumentException.class)
	public JSONObject addBooking(String response) throws IOException, ParseException, org.json.simple.parser.ParseException {
		String uniqueCode=null;
		String seatnumber=null;
		String no_of_seats=null;
		
		System.out.println("addBooking response "+response);
	         
		JSONParser parser = new JSONParser();
		Object obj1 = parser.parse(response);
        
		System.out.println("addBooking obj1 "+obj1);
		   
        JSONObject obj2 =new JSONObject((Map) obj1);
        System.out.println("addBooking obj2 "+obj2);
        Booking booking=new Booking();
        booking.setBooking_date(obj2.get("booking_date").toString());
        booking.setUsers_id((int) obj2.get("users_id"));
        booking.setDramas_id((int) obj2.get("dramas_id"));
        booking.setAuditorium_id((int) obj2.get("auditorium_id"));
        booking.setPrice((double) obj2.get("price"));
        booking.setStatus(true);
        booking.setStatus(booking.getStatus());
        seatnumber=obj2.get("seatnumber").toString();
        JSONArray jsonArraySeatnumber=(JSONArray)obj2.get("seatnumber");
        no_of_seats=obj2.get("no_of_seats").toString();
          
        String conformationcode=String.valueOf(booking.getDramas_id())+String.valueOf(booking.getUsers_id())+String.valueOf(booking.getAuditorium_id())+booking.getBooking_date();
        // Encode using basic encoder
        uniqueCode =generateUniqueCode(conformationcode);
//           // Decode
//           byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
//   		 System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
        
        booking.setConfirmation_no(uniqueCode);
        int bookingId = jdbcBookingRepository.addBooking(ObjectMapper.mapToBookingDTO(booking));
       addBookedSeatsDetails(bookingId,jsonArraySeatnumber,booking.getAuditorium_id());
//        jdbcBookingRepository.addBooking(ObjectMapper.mapToBookedSeatsDTO(bookedSeats));
       	 JSONObject obj=new JSONObject();
       	 obj.put("uniqueCode", uniqueCode);
       	 obj.put("bookingId", bookingId);
      
    	
		return obj;
	}
	/*public boolean contains(String str, char chr ) {

        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == chr)
                return true;
        return false;
    }*/

	private void addBookedSeatsDetails(int bookingId, JSONArray jsonArraySeatnumber, int auditoriums_id) {
		String row_number,row_name;
		for(int i=0;i<jsonArraySeatnumber.size();i++){
			String seatnumber= jsonArraySeatnumber.get(i).toString();
			if(seatnumber.length()>0){
				row_number=getRowNumber(seatnumber);
				row_name=getRowName(seatnumber);
//				jdbcBookingRepository.checkRow_Seats();
			}
		}
		
	}

	private String getRowNumber(String seatnumber) {
		String[] words=seatnumber.split("[A-Z]");//splits the string based on string
		for(String w:words){  
			System.out.println(w);  
			return w;
			}
		return null;  
	}

	private String getRowName(String seatnumber) {
		String[] words=seatnumber.split("[0-9]");//splits the string based on string
		for(String w:words){  
			System.out.println(w);  
			return w;
			}
		return null;  
	}
	private String generateUniqueCode(String conformationcode ) {
		String base64encodedString = null;
		 String unique=null;
		try {
			System.out.println("Base64 Encoded String (conformationcode) :" + conformationcode);
	     
			base64encodedString = Base64.getUrlEncoder().encodeToString(conformationcode.getBytes("utf-8"));
			 System.out.println("Base64 Encoded String (Basic) :" + base64encodedString);
		         unique=base64encodedString;
		         
		         return unique;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return unique;
	}
	
}
