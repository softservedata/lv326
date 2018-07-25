package com.softserve.edu.items.dao;

import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.entity.Role.RoleEntityQueries;

public final class RoleDao extends ADaoCRUD<Role> {
	private final static String ROLE_FIELDNAME = "name";


	public RoleDao() {
		super();
		init();
	}
	
	protected void init() {
		for (RoleEntityQueries roleEntityQueries : RoleEntityQueries.values()) {
			sqlQueries.put(roleEntityQueries.getSqlQuery(), roleEntityQueries);
		}
	}

	protected Role createInstance(String[] args) {
		return new Role(
				Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1]);
	}

	protected String[] getUpdateFields(Role entity) {
		String[] result = new String[2];
		String[] allFields = getFields(entity);
		result[0] = allFields[1]; // name
		result[1] = allFields[0]; // id
		return result;
	}

	protected String[] getFields(Role entity) {
		//String[] fields = new String[Role.class.getDeclaredFields().length];
		String[] fields = new String[2];
		fields[0] = entity.getId().toString();
		fields[1] = entity.getName();
		return fields;
	}
	
	public Role getRoleEntityByName(String name) {
		return getByFieldName(ROLE_FIELDNAME, name).get(0);
	}

}
