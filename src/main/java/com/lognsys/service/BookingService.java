package com.lognsys.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lognsys.dao.dto.BookedRowSeatsDTO;
import com.lognsys.dao.dto.BookedSeatsDTO;
import com.lognsys.dao.jdbc.JdbcBookedSeatsRepository;
import com.lognsys.dao.jdbc.JdbcBookingRepository;
import com.lognsys.dao.jdbc.JdbcRowSeatRepository;
import com.lognsys.model.BookedRowSeatTable;
import com.lognsys.model.Booking;
import com.lognsys.model.UsersTable;
import com.lognsys.util.ObjectMapper;

@Service("bookingService")
public class BookingService {
	@Autowired
	private JdbcBookingRepository jdbcBookingRepository;

	@Autowired
	private JdbcRowSeatRepository jdbcRowSeatRepository;

	@Autowired
	private JdbcBookedSeatsRepository jdbcBookedSeatsRepository;

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
        
        long users_id=(long) obj2.get("users_id");
        System.out.println("addBooking users_id "+users_id);
        booking.setUsers_id((int) users_id);
       
        long dramas_id=(long) obj2.get("dramas_id");
        System.out.println("addBooking dramas_id "+dramas_id);
        booking.setDramas_id((int) dramas_id);
        
        long auditoriums_id=(long) obj2.get("auditoriums_id");
        System.out.println("addBooking auditoriums_id "+auditoriums_id);
        booking.setAuditoriums_id((int) auditoriums_id);
       
        
        booking.setPrice((double) obj2.get("price"));
        
        booking.setStatus(true);
        booking.setStatus(booking.getStatus());
        seatnumber=obj2.get("seatnumber").toString();
        
        JSONArray jsonArraySeatnumber=(JSONArray)obj2.get("seatnumber");
        System.out.println("addBooking jsonArraySeatnumber "+jsonArraySeatnumber);
	      
        
        no_of_seats=obj2.get("no_of_seats").toString();
          
        String conformationcode=String.valueOf(booking.getDramas_id())+String.valueOf(booking.getUsers_id())+String.valueOf(booking.getAuditoriums_id())+booking.getBooking_date();
        // Encode using basic encoder
        uniqueCode =generateUniqueCode(conformationcode);
        booking.setConfirmation_no(uniqueCode);
        int bookingId = jdbcBookingRepository.addBooking(ObjectMapper.mapToBookingDTO(booking));
      
        addBookedSeatsDetails(bookingId,jsonArraySeatnumber,booking.getAuditoriums_id());

       	 JSONObject obj=new JSONObject();
       	 obj.put("uniqueCode", uniqueCode);
       	 obj.put("bookingId", bookingId);
		return obj;
	}


	private void addBookedSeatsDetails(int bookingId, JSONArray jsonArraySeatnumber, int auditoriums_id) {
		  System.out.println("addBookedSeatsDetails jsonArraySeatnumber "+jsonArraySeatnumber);
		  System.out.println("addBookedSeatsDetails jsonArraySeatnumber size "+jsonArraySeatnumber.size());
		  System.out.println("addBookedSeatsDetails bookingId "+bookingId);
		  System.out.println("addBookedSeatsDetails auditoriums_id "+auditoriums_id);
		       
		int seat_num;
		String row_name;
		for(int i=0;i<jsonArraySeatnumber.size();i++){

			System.out.println("addBookedSeatsDetails  jsonArraySeatnumber.get(i).toString() "+ jsonArraySeatnumber.get(i).toString());
			String seatnumber= jsonArraySeatnumber.get(i).toString();
			System.out.println("addBookedSeatsDetails  seatnumber "+seatnumber);

			int row_seats_id=0;
			
			if(seatnumber!=null){
			
				seat_num=getRowNumber(seatnumber);
				System.out.println("addBookedSeatsDetails  seat_num "+seat_num);
				
				row_name=getRowName(seatnumber);
				System.out.println("addBookedSeatsDetails  row_name "+row_name);
				
				try {
					row_seats_id=jdbcRowSeatRepository.getRowSeats(seat_num, row_name, auditoriums_id);
					System.out.println("addBookedSeatsDetails  row_seats_id "+row_seats_id);
				} catch (Exception e) {
					System.out.println("addBookedSeatsDetails  Exception ====  "+e);
					
					e.printStackTrace();
				}
				
				BookedSeatsDTO bookedSeatsDTO=new BookedSeatsDTO();
				bookedSeatsDTO.setBooking_id(bookingId);
				bookedSeatsDTO.setRow_seats_id(row_seats_id);
				bookedSeatsDTO.setSeat_status("Booked");
				if(bookedSeatsDTO!=null){
					System.out.println("addBookedSeatsDetails  bookedSeatsDTO.toString()  "+bookedSeatsDTO.toString());
					jdbcBookedSeatsRepository.addBookedSeats(bookedSeatsDTO);
				}
				
			}
		}
		
	}

	private int getRowNumber(String seatnumber) {
		System.out.println("getRowNumber  seatnumber "+seatnumber);
		int seat_num=Integer.parseInt(seatnumber.replaceAll("\\D+",""));
		System.out.println("getRowNumber  seat_num   "+seat_num);
			return seat_num;
			
	}

	private String getRowName(String seatnumber) {
		String[] words=seatnumber.split("[0-9]");//splits the string based on string
		for(String w:words){
			w=w.trim();
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

//	           // Decode
//	           byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
//	   		 System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
	        
		         return unique;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return unique;
	}


	public JSONObject getBookedSeats(String response)  throws IOException, ParseException, org.json.simple.parser.ParseException{
		System.out.println("getBookedSeats response "+response);
        
		JSONParser parser = new JSONParser();
		Object obj1 = parser.parse(response);
        
		System.out.println("getBookedSeats obj1 "+obj1);
		   
        JSONObject obj2 =new JSONObject((Map) obj1);
        System.out.println("getBookedSeats obj2 "+obj2);
        Booking booking=new Booking();
        long dramas_id=(long) obj2.get("dramas_id");
        System.out.println("getBookedSeats dramas_id "+dramas_id);
        booking.setDramas_id((int) dramas_id);
        
        long auditoriums_id=(long) obj2.get("auditoriums_id");
        System.out.println("getBookedSeats auditoriums_id "+auditoriums_id);
        booking.setAuditoriums_id((int) auditoriums_id);
        List<BookedRowSeatTable> list = ObjectMapper.mapToBookedRowSeatTable(
        		jdbcBookedSeatsRepository.getBookedRowSeatsDTO(
        				booking.getDramas_id(),
        				booking.getAuditoriums_id()
        				)
        		);

        System.out.println("getBookedSeats list "+list);
        System.out.println("getBookedSeats list size  "+list.size());
        JSONArray jsonArray=new JSONArray();
        
        for(int i=0;i<list.size();i++){
        	JSONObject jsonObject=new JSONObject();
        	jsonObject.put("i", list.get(i).getRow_num());
        	jsonObject.put("j", list.get(i).getSeat_number());
        	

            System.out.println("getBookedSeats jsonObject "+jsonObject);
        	jsonArray.add(jsonObject);
        }
        System.out.println("getBookedSeats jsonArray "+jsonArray);
        System.out.println("getBookedSeats jsonArray size "+jsonArray.size());
    	JSONObject jsonObjectfinal=new JSONObject();
        
        jsonObjectfinal.put("seatnumberdetails", jsonArray);

        System.out.println("getBookedSeats jsonObjectfinal "+jsonObjectfinal);
		return jsonObjectfinal;
	}
	
}
