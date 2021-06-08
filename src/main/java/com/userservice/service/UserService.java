package com.userservice.service;

import java.util.List;

import com.userservice.model.User;

public interface UserService {

	List<User> findAllUsers();

	User findUserById(Integer id);

	void deleteAllUsers();

	void deleteUserById(Integer id);

	void updateUser(User userById);

	void save(User user);

}
