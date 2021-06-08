package com.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	public User(Integer id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	public User() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	private String role;

}
