package com.passwordsaver.dto;

public class KeyDto {
	private Long idKey;
	private String service;
	private String serv_password;
	public KeyDto(Long idKey, String service, String serv_password){
		this.idKey = idKey;
		this.service = service;
		this.serv_password = serv_password;
	}
	
	public void setIdKey(Long idKey) {
		this.idKey = idKey;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void setServPassword(String serv_password) {
		this.serv_password = serv_password;
	}
	
	public Long getIdKey(){
		return idKey;
	}
	public String getService() {
		return service;
	}
	public String getServPassword() {
		return serv_password; 
	}
	
	@Override
	public String toString() {
		return "("
				+" idKey="+idKey
				+" service="+service
				+" serv_password"+serv_password
				+" )";
	}
	}
