package com.softserve.edu.items.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDto {
	private List<String> roles;

	public RoleDto() {
		this.roles = new ArrayList<>();
	}

	public RoleDto(List<String> roles) {
		this.roles = roles;
	}

	// setters

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public void addRole(String role) {
		roles.add(role);
	}

	// getters

	public List<String> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "(" + "roles=" + roles.toString() + ")";
	}
	
}
