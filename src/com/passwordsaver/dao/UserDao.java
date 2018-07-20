package com.passwordsaver.dao;
import java.util.ArrayList;
import java.util.List;

import com.passwordsaver.entity.User;
import com.passwordsaver.entity.User.UserEntityQueries;
public final class UserDao extends DaoCRUD<User> {
	private static final String LOGIN_FIELDNAME = "login";
	public UserDao() {
		super();
	}
	
	@Override
	protected List<String> getFields(User entity) {
		List<String> fields = new ArrayList<String>();
		fields.add(entity.getId().toString());
		fields.add(entity.getLogin());
		fields.add(entity.getPassword());
		fields.add(entity.getIdRole().toString());
		return fields;
	}

	@Override
	protected List<String> getUpdateFields(User entity) {
		List<String> fields = new ArrayList<String>();
		fields.add(0, entity.getLogin());
		fields.add(1, entity.getPassword());
		fields.add(2, entity.getIdRole().toString());
		fields.add(3, entity.getId().toString());
		return fields;
	}

	@Override
	protected User createInstance(List<String> args) {
		return new User(
				Long.parseLong(args.get(0)==null ? "0" : args.get(0)),
				args.get(1),
				args.get(2),
				Long.parseLong(args.get(3)==null ? "0" : args.get(3))
				);
	}
	

	@Override
	protected void init() {
		for(UserEntityQueries userEntityQuery: UserEntityQueries.values()) {
			sqlQueries.put(userEntityQuery.getSqlQuery(), userEntityQuery);
		}
	}
	public User getUserEntityByLogin(String login) {
		return this.getByFieldName(UserDao.LOGIN_FIELDNAME, login).get(0);
	}
	
	
}
