package com.userservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.userservice.model.User;
import com.userservice.service.UserService;



@RestController
@RequestMapping("/userapi")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/")
	@HystrixCommand(fallbackMethod = "getDefaultUsers")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> users = userService.findAllUsers();
		
		if(users.isEmpty()) {
			
			return new ResponseEntity<List<User>>(users, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
  public ResponseEntity<List<User>> getDefaultUsers(){
		
		List<User> users = new ArrayList<User>();
		users.add(new User(1,"ABC","Naveen"));
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	
	@GetMapping("/user/{id}")
	@HystrixCommand(fallbackMethod = "getUser")
	public ResponseEntity<?> getUser(@PathVariable Integer id){
		
		User user = userService.findUserById(id);
		
		if(user == null) {
			
			return new ResponseEntity<>("User not Found with Id", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	
	public ResponseEntity<?> getDeafultUser(@PathVariable Integer id){
		User user = new User(7,"Naveen","Not Found");
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping("/user/")
	public ResponseEntity<?> createUser(@RequestBody User user){
		
		userService.save(user);
		
		return new ResponseEntity<>(user,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Integer id,@RequestBody User user){
		
		User userById = userService.findUserById(id);
		
		if(userById == null) {
			
			return new ResponseEntity<>("Unable to Update",HttpStatus.NOT_FOUND);
			
		}
		
		
		userService.updateUser(userById);
		return new ResponseEntity<>(userById, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
		
		User user = userService.findUserById(id);
		
		
		if(user == null) {
			
			return new ResponseEntity<>("not Found",HttpStatus.NOT_FOUND);
		}
		
		userService.deleteUserById(id);
		
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping("/users/")
	public ResponseEntity<?> deleteUsers(){
	
		userService.deleteAllUsers();
		
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}

	
}
