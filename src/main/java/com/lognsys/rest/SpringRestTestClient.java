package com.lognsys.rest;


import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.web.client.RestTemplate;

import com.lognsys.model.Users;


public class SpringRestTestClient {

   public static final String REST_SERVICE_URI = "http://localhost:8080/";
    
   /* GET */
   @SuppressWarnings("unchecked")
   private static void listAllUsers(){
       System.out.println("Testing listAllUsers API-----------");
        
       RestTemplate restTemplate = new RestTemplate();
       List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/getallusers/", List.class);
        
       if(usersMap!=null){
           for(LinkedHashMap<String, Object> map : usersMap){
               System.out.println("User : id="+map.get("id")+", username="+map.get("username")+", realname ="+map.get("realname")
               +", phone="+map.get("phone") +", location="+map.get("location") +", provenance="+map.get("provenance")
               +", birthdate="+map.get("birthdate")
               +", enabled="+map.get("enabled") +", notification="+map.get("notification") +", device="+map.get("device")
               +", address="+map.get("address")
               +", city="+map.get("city")
               +", state="+map.get("state")
               +", zipcode="+map.get("zipcode")
               +", company_name="+map.get("company_name")
               );
           }
       }else{
           System.out.println("No user exist----------");
       }
   }
    
   /* GET */
   private static void getUser(){
       System.out.println("Testing getsingleuser API----------");
       RestTemplate restTemplate = new RestTemplate();
       Users user = restTemplate.getForObject(REST_SERVICE_URI+"/getsingleuser/1", Users.class);
       System.out.println(user);
   }
    
   /* POST */

   private static void createUser() {
       System.out.println("Testing create User API----------");
       RestTemplate restTemplate = new RestTemplate();
      
 
       
       
       String url = "http://localhost:8080/createuser/{id}/{auth_id}/{username}/{realname}/{phone}/{location}/{provenance}/{birthdate}/{enabled}/{notification}/{device}/{address}/{city}/{state}/{zipcode}/{company_name}/{firstname}/{lastname}/{group}/{role}";
       HashMap<String, String> map = new HashMap<String, String>();
       map.put("id", "23");
       map.put("auth_id", "afadasfasad");
       map.put("username", "vishali@gmail.com");
       map.put("realname", "vishali sharma "); 
       map.put("phone", "8097526387");
       map.put("location", "location");  map.put("provenance", "provenance");map.put("birthdate", "");  map.put("enabled", "1");
       map.put("notification", "1");  map.put("device", "device"); map.put("address", "address");  map.put("city", "city"); map.put("state", "state");  map.put("zipcode", "123412");
       map.put("company_name", "company name");  map.put("firstname", "vishali");
       map.put("lastname", "lastname");  map.put("group", "group");
       map.put("role", "role");
     Users users= restTemplate.postForObject(url, Users.class, null, map);
       System.out.println(users.getId());
       System.out.println(users.getAddress());
       
       
       //       System.out.println("Location : "+uri.toASCIIString());
   }

   /* PUT */
   private static void updateUser() {
       System.out.println("Testing update User API----------");
       RestTemplate restTemplate = new RestTemplate();
       Users user  = new Users(20,"xyzdta", 
    		   "rest@g.com","rest sharma",
    		   "8097526387",
    		   "provenance","2017-05-17 09:25:07",
				true,true,"device",
				"address","city",
				"state","123456",
				"rest","sharma",
				"critics","Admin");
       restTemplate.put(REST_SERVICE_URI+"/updateuser/20", user);
       System.out.println(user);
   }

   /* DELETE */
   private static void deleteUser() {
       System.out.println("Testing delete User API----------");
       RestTemplate restTemplate = new RestTemplate();
       restTemplate.delete(REST_SERVICE_URI+"/deleteuser/3");
   }


  /*  DELETE 
   private static void deleteAllUsers() {
       System.out.println("Testing all delete Users API----------");
       RestTemplate restTemplate = new RestTemplate();
       restTemplate.delete(REST_SERVICE_URI+"/user/");
   }*/

   public static void main(String args[]){
//       listAllUsers();
//       getUser();
//       createUser();
//       listAllUsers();
//       updateUser();
//       listAllUsers();
//       deleteUser();
//       listAllUsers();
////       deleteAllUsers();
//       listAllUsers();
//  
       }
}