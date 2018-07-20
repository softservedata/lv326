package com.passwordsaver.dto;

public class LoginDto {
	private String login;
	private String password;
	
	public LoginDto(String login, String password){
		this.login = login;
		this.password = password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "("
				+" login="+login
				+" password="+password
				+")";
	}
}
