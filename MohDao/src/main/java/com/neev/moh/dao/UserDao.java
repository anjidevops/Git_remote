package com.neev.moh.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;


import com.neev.moh.model.User;

public interface UserDao {

	Integer createUser(User user) throws DataAccessException, Exception;

	User getUserById(int id);

	User getUserByEmail(String email);

	User getUserByMobile(String mobile);

	List<User> listUsers();

	void updateUser(User user);

	void deleteUser(User user);

	List<User> searchUsersByEmailOrMobile(String searchText);

}