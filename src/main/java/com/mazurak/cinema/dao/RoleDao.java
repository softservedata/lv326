package com.mazurak.cinema.dao;

import com.mazurak.cinema.entity.Role;
import com.mazurak.cinema.entity.Role.RoleEntityQueries;

public final class RoleDao extends DaoCRUDAbstract<Role> {

	private final static String ROLE_FIELDNAME = "name";
	private static final int ID_ROLE_FIELD = 0;
	private static final int NAME_ROLE_FIELD = 1;

	public RoleDao() {
		init();
	}

	@Override
	protected void init() {
		for (RoleEntityQueries roleEntityQueries : RoleEntityQueries.values()) {
			sqlQueries.put(roleEntityQueries.getSqlQueries(), roleEntityQueries);
		}
	}

	@Override
	protected String[] getFields(Role role) {
		String[] fields = new String[2];
		fields[ID_ROLE_FIELD] = role.getId().toString();
		fields[NAME_ROLE_FIELD] = role.getName();
		return fields;
	}

	@Override
	protected String[] getUpdateFields(Role role) {
		String[] result = new String[2];
		String[] allFields = getFields(role);
		result[0] = allFields[NAME_ROLE_FIELD]; // name
		result[1] = allFields[ID_ROLE_FIELD]; // id
		return result;
	}

	@Override
	protected Role createInstance(String[] args) {
		return new Role(Long.parseLong(args[ID_ROLE_FIELD] == null ? "0" : args[ID_ROLE_FIELD]),
				args[NAME_ROLE_FIELD] == null ? new String() : args[NAME_ROLE_FIELD]);
	}

	public Role getRoleEntityByName(String name) {
		try {
			return getByFieldName(ROLE_FIELDNAME, name).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}
