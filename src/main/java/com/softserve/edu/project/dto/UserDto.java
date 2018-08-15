package com.softserve.edu.project.dto;

public class UserDto {

	private String username;
	private String password;
	private String name;
	private String surname;
	private String role;

	public UserDto(String username, String password, String name, String surname, String role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
	}

	public UserDto() {
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "(" + "login=" + username
				+ " password=" + password
				+ " name=" + name
				+ " role=" + role
				+ ")";
	}

}
