package com.mazurak.cinema.dao;

import com.mazurak.cinema.entity.User;
import com.mazurak.cinema.entity.User.UserEntityQueries;

public final class UserDao extends DaoCRUDAbstract<User> {

	private final static String LOGIN_FIELDNAME = "login";
	private final static int ID_USER_FIELD = 0;
	private final static int NAME_USER_FIELD = 1;
	private final static int LOGIN_USER_FIELD = 2;
	private final static int PASSWORD_USER_FIELD = 3;
	private final static int ID_ROLE_USER_FIELD = 4;

	public UserDao() {
		init();
	}

	@Override
	protected void init() {
		for (UserEntityQueries userEntityQueries : UserEntityQueries.values()) {
			sqlQueries.put(userEntityQueries.getSqlQueries(), userEntityQueries);
		}
	}

	@Override
	protected User createInstance(String[] args) {
		return new User(Long.parseLong(args[ID_USER_FIELD] == null ? "0" : args[ID_USER_FIELD]),
				args[NAME_USER_FIELD] == null ? new String() : args[NAME_USER_FIELD],
				args[LOGIN_USER_FIELD] == null ? new String() : args[LOGIN_USER_FIELD],
				args[PASSWORD_USER_FIELD] == null ? new String() : args[PASSWORD_USER_FIELD],
				Long.parseLong(args[ID_ROLE_USER_FIELD] == null ? "0" : args[ID_ROLE_USER_FIELD]));
	}

	@Override
	protected String[] getFields(User user) {
		String[] fields = new String[5];
		fields[ID_USER_FIELD] = user.getId().toString();
		fields[NAME_USER_FIELD] = user.getName();
		fields[LOGIN_USER_FIELD] = user.getLogin();
		fields[PASSWORD_USER_FIELD] = user.getPassword();
		fields[ID_ROLE_USER_FIELD] = user.getRoleId().toString();
		return fields;
	}

	@Override
	protected String[] getUpdateFields(User user) {
		String[] result = new String[3];
		String[] allFields = getFields(user);
		result[0] = allFields[NAME_USER_FIELD];
		result[1] = allFields[PASSWORD_USER_FIELD];
		result[2] = allFields[ID_USER_FIELD];
		return result;
	}

	public User getUserEntityByLogin(String login) {
		try {
			return getByFieldName(LOGIN_FIELDNAME, login).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
}
