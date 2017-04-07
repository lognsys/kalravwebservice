package com.lognsys.dao.users;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import com.lognsys.model.Users;

public interface UserRespository {

	/**
	 * Add user into database
	 * @param users
	 */
	public void addUser(Users users);

	
	/**
	 * Update user information, enable/disable etc.. 
	 * @param users
	 */
	public void updateUser(Users users);

	/**
	 * Get User by emailId
	 * @param emailId
	 * @return
	 */
	public Users findUserByEmailId(String emailId);

	/**
	 * Get All users
	 * @return
	 */
	public List<Users> getAllUsers();


	public void delete(Integer id);
	

	/**
	 * Get User by Id
	 * @param id
	 * @return
	 */
	public Users findUserById(Integer id);

}
