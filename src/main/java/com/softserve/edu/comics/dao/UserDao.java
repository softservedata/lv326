package com.softserve.edu.comics.dao;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.entity.User;
import com.softserve.edu.comics.entity.User.UserQueries;

public final class UserDao extends ADaoCRUD<User> {

	public UserDao() {
		init();
	}

	protected void init() {
		for (UserQueries userQueries : UserQueries.values()) {
			sqlQueries.put(userQueries.getSqlQuery(), userQueries);
		}
	}

	protected User createInstance(String[] args) {
		return new User(
				Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
			    args[3] == null ? new String() : args[3],
			    Long.parseLong(args[4] == null ? "0" : args[4]));
	}

	protected String[] getUpdateFields(User entity) {
		String[] result = new String[3];
		result[0] = entity.getPassword();
		result[1] = entity.getName();
		result[2] = entity.getId().toString();
		return result;
	}

	protected String[] getFields(User entity) {
		String[] fields = new String[5];
		fields[0] = entity.getId().toString();
		fields[1] = entity.getLogin();
		fields[2] = entity.getPassword();
		fields[3] = entity.getName();
		fields[4] = entity.getIdRole().toString();
		return fields;
	}

	public User getUserEntityByLogin(String login) {
		return getByFieldName(FieldName.LOGIN, login).get(0);
	}

}
