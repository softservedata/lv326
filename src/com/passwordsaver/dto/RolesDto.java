package com.passwordsaver.dto;

import java.util.ArrayList;
import java.util.List;

public class RolesDto {
	private List<String> roles;
	
	public RolesDto(){
		roles = new ArrayList<String>();
	}
	public RolesDto(List<String> roles) {
		this.roles = roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public List<String> getRoles(){
		return roles;
	}
	
	public void addRole(String role) {
		roles.add(role);
	}
	
	@Override
	public String toString() {
		return "( roles="+roles+")";
	}
}
