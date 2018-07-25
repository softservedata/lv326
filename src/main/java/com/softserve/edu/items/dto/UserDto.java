package com.softserve.edu.items.dto;

public class UserDto {

    private String login;
    private String password;
    private String name;
    private String role;
    
	public UserDto(String login, String password, String name, String role) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.role = role;
	}

	// setters

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// getters

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "(" + "login=" + login 
				+ " password=" + password 
				+ " name=" + name 
				+ " role=" + role 
				+ ")";
	}

}
