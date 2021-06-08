package com.userservice.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.dao.UserRepository;
import com.userservice.model.User;
import com.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	@Override
	public User findUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		
		return user.isPresent() ? user.get() : user.get();
	}

	@Override
	public void deleteAllUsers() {
		
		userRepository.deleteAll();
		
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public void updateUser(User userById) {
		userById.setName("Naveen");
		userById.setRole("Admin");
		userRepository.save(userById);
		
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
		
	}

}
