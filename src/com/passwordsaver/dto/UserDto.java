package com.passwordsaver.dto;

public class UserDto {
	private String login;
	private String password;
	private String role;
	public UserDto(String login, String password, String role){
		this.login = login;
		this.password = password;
		this.role = role;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	@Override
	public String toString()
	{
		return "("
				+" login="+login
				+" password="+password
				+" role="+role
				+")";
	}
}
