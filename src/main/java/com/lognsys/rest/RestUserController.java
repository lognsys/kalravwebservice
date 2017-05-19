package com.lognsys.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.dao.jdbc.JdbcGroupRepository;
import com.lognsys.dao.jdbc.JdbcUserRepository;
import com.lognsys.model.Users;
import com.lognsys.model.UsersTable;
import com.lognsys.service.UserService;
import com.lognsys.util.ObjectMapper;

@Produces("application/json")
@RestController
public class RestUserController {

	@Autowired
	UserService userService;


	@Autowired
	private JdbcGroupRepository jdbcGroupRepository;

	@Autowired
	private JdbcUserRepository jdbcUserRepository;
	     
	    //-------------------Retrieve All Users--------------------------------------------------------
	     
	    @RequestMapping(value = "/getallusers/", method = RequestMethod.GET)
	    public ResponseEntity<List<UsersTable>> listAllUsers() {
	    	List<UsersTable> users = ObjectMapper.mapToUserTable(jdbcGroupRepository.getAllUsersAndGroup());
			   if(users.isEmpty()){
	            return new ResponseEntity<List<UsersTable>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<UsersTable>>(users, HttpStatus.OK);
	    }
	 
	 
	    //-------------------Retrieve Single User-------------------------------------------------------
	     
	    @RequestMapping(value = "/getsingleuser/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Users> getUser(@PathVariable("id") int id) {
	        System.out.println("Fetching User with id " + id);
	        Users user = userService.getUserWithRoleAndGroup(id);
	        if (user == null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<Users>(user, HttpStatus.OK);
	    }
	 
	     
	     
	    //-------------------Create a User--------------------------------------------------------
	    @RequestMapping(value="/createuser/{id}/{realname}/{username}/{auth_id}/{phone}/{location}/{provenance}/{birthdate}/{enabled}/{notification}/{device}/{address}/{city}/{state}/{zipcode}/{company_name}/{firstname}/{lastname}/{group}/{role}", method = RequestMethod.POST)
	    public ResponseEntity<UsersDTO> createUser(@PathVariable(value = "id") int id,
	    					@PathVariable(value = "realname") String realname,
	    		           @PathVariable(value = "username") String username,
	    		           @PathVariable(value = "auth_id") String auth_id,
	    		           @PathVariable(value = "phone") String phone,
	    		           @PathVariable(value = "location") String location,
	    		           @PathVariable(value = "provenance") String provenance,
	    		           @PathVariable(value = "birthdate") String birthdate,
	    		           @PathVariable(value = "enabled") Boolean enabled,
	    		           @PathVariable(value = "notification") Boolean notification,
	    		           @PathVariable(value = "device") String device, @PathVariable(value = "address") String address,
	    		           @PathVariable(value = "city") String city,
	    		           @PathVariable(value = "state") String state,
	    		           @PathVariable(value = "zipcode") String zipcode,
	    		           @PathVariable(value = "company_name") String company_name
	    		          ) {
	    	int userId;
	    	UsersDTO usersDTO = new UsersDTO(id,realname, username,auth_id,phone,location,provenance,birthdate,enabled,notification,device,address,city,state,zipcode,company_name);
	    	System.out.println("Creating User toString " + usersDTO.toString());
	    	
	    	boolean isExists = jdbcUserRepository.isExists(usersDTO.getUsername());
	    	System.out.println("Creating User isExists " +isExists);
	    	 if (isExists) {
		            System.out.println("A User with name " + usersDTO.getRealname() + " already exist");
		            return new ResponseEntity<UsersDTO>(HttpStatus.CONFLICT);
		        }
	    	 else
	    		 userId = jdbcUserRepository.addUser(usersDTO);
	    	 	usersDTO.setId(userId);
	    		System.out.println("Creating User userId " +userId);
	    		System.out.println("Creating User usersDTO tostring after add " +usersDTO.toString());
	    	  	 //	    		 userService.addUser(user);

	    	 
	    	 //		   CREATED====201
	    	return new ResponseEntity<UsersDTO>(usersDTO, HttpStatus.CREATED);
	    } 

	     
	    //------------------- Update a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/updateuser/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<Users> updateUser(@PathVariable("id") int id, @RequestBody Users user) {
	        System.out.println("Updating User " + id);
	         
	        Users currentUser = userService.getUserWithRoleAndGroup(id);
	        System.out.println("Updating User currentUser toString " + currentUser.toString());
	          
	        if (currentUser==null) {
	            System.out.println("User with id " + id + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        System.out.println("Updating User user.getCity()" + user.getCity());
		     
	        currentUser.setCity(user.getCity());
	       
	         
	        userService.updateUser(currentUser);
	        return new ResponseEntity<Users>(currentUser, HttpStatus.OK);
	    }
	 
	    //------------------- Delete a User --------------------------------------------------------
	     
	    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<Users> deleteUser(@PathVariable("id") int id) {
	        System.out.println("Fetching & Deleting User with id " + id);
	 
	        Users user = userService.getUserWithRoleAndGroup(id);
	        System.out.println("Fetching & Deleting User with user.getId " + user.getId());
	        System.out.println("Fetching & Deleting User with user " + user);
		   	 
	        if (user == null) {
	            System.out.println("Unable to delete. User with id " + id + " not found");
	            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	        }
	        else{
	        	int [] ids={user.getId()};
		        userService.deleteUsers(ids);
		        
	        }
	        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	    }
	 
	     
	    //------------------- Delete All Users --------------------------------------------------------
	 /*    
	    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	    public ResponseEntity<Users> deleteAllUsers() {
	        System.out.println("Deleting All Users");
	        int [] ids={user.getId()};
	        userService.deleteUsers(ids);
//	        userService.deleteAllUsers();
	        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
	    }*/
}