package com.mazurak.cinema.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
public class RoleDto {

	private List<String> roles;

	public RoleDto() {
		this.roles = new ArrayList<String>();
	}
	public void addRole(String role) {
		roles.add(role);
	}
}
	