package com.lognsys.dao.jdbc.users;

import java.util.List;
import com.lognsys.model.Users;

public interface UserRespository {

	/**
	 * Add user into database
	 * 
	 * @param users
	 */
	public void addUser(Users users);
	
	
	/**
	 * Check if User exists by username
	 * @param username
	 * @return
	 */
	public boolean isExists(String username);

	/**
	 * Update user information, enable/disable etc..
	 * 
	 * @param users
	 */
	public void updateUser(Users users);


	/**
	 * Get All users
	 * 
	 * @return
	 */
	public List<Users> getAllUsers();

	/**
	 * Delete user by id
	 * @param id
	 */
	public boolean deleteUserBy(Integer id);

	/**
	 * Get User by Id
	 * 
	 * @param id
	 * @return
	 */
	public Users findUserById(Integer id);

}
