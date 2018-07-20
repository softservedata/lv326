package com.passwordsaver.dao;

import java.util.ArrayList;
import java.util.List;

import com.passwordsaver.entity.Role;
import com.passwordsaver.entity.Role.RoleEntityQueries;

public class RoleDao extends DaoCRUD<Role> {
	private final static String ROLE_FILDNAME = "title";
	public RoleDao() {
		super();
	}
	
	@Override
	protected List<String> getFields(Role role) {
		List<String> fields = new ArrayList<String>();
		fields.add(role.getId().toString());
		fields.add(role.getTitle());
		return fields;
	}

	@Override
	protected List<String> getUpdateFields(Role role) {
		List<String> fields = new ArrayList<String>();
		fields.add(0,role.getTitle());
		fields.add(1,role.getId().toString());
		return fields;
	}

	@Override
	protected Role createInstance(List<String> args) {
		return new Role(
				Long.parseLong(args.get(0)),
				args.get(1)
				);
	}

	@Override
	protected void init() {
		for(RoleEntityQueries roleEntityQuery : RoleEntityQueries.values()) {
			sqlQueries.put(roleEntityQuery.getSqlQuery(), roleEntityQuery);
		}
	}
	public Role getRoleEntityByTitle(String title) {
		return this.getByFieldName(RoleDao.ROLE_FILDNAME, title).get(0);
	}
}
