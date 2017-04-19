package com.lognsys.dao.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.lognsys.dao.UserRespository;
import com.lognsys.dao.dto.UsersDTO;
import com.lognsys.model.Users;

@Repository
public class MongoUserRepository implements UserRespository  {

	@Autowired
	private MongoTemplate mongoTemplate;


	public void addUser(UsersDTO users) {
		this.mongoTemplate.insert(users);

	}

	
	public boolean isExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}


	public void updateUser(Users users) {

	}

	public List<UsersDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean deleteUserBy(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public UsersDTO findUserById(Integer id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoTemplate.findOne(query, UsersDTO.class);
	}


	@Override
	public void updateUser(UsersDTO users) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean deleteUserBy(String emailID) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public UsersDTO findUserById(String emailID) {
		// TODO Auto-generated method stub
		return null;
	}

}
