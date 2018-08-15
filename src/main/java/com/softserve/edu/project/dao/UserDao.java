package com.softserve.edu.project.dao;


import com.softserve.edu.project.entity.User;

public final class UserDao extends ADaoCRUD<User> {
	private final static String LOGIN_FIELDNAME = "username";

	public UserDao() {
		super();
		init();
	}


	protected void init() {
		for (User.UserEntityQueries userEntityQueries : User.UserEntityQueries.values()) {
			sqlQueries.put(userEntityQueries.getSqlQuery(), userEntityQueries);
		}
	}


	protected User createInstance(String[] args) {
		return new User(
				Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
				args[3] == null ? new String() : args[3],
				args[4] == null ? new String() : args[4],
				Long.parseLong(args[5] == null ? "0" : args[5]));
	}

	protected String[] getUpdateFields(User entity) {
		String[] result = new String[5];
		String[] allFields = getFields(entity);
		result[0] = allFields[2]; // passsword
		result[1] = allFields[3]; // name
		result[2] = allFields[4]; // surname
		result[3] = allFields[5]; // idRole
		result[4] = allFields[0]; // id
		return result;
	}

	protected String[] getFields(User entity) {
		//String[] fields = new String[User.class.getDeclaredFields().length];
		String[] fields = new String[6];
		fields[0] = entity.getId().toString();
		fields[1] = entity.getUsername();
		fields[2] = entity.getPassword();
		fields[3] = entity.getName();
		fields[4] = entity.getSurname();
		fields[5] = entity.getIdRole().toString();
		return fields;
	}

	public User getUserEntityByLogin(String login) {
		return getByFieldName(LOGIN_FIELDNAME, login).get(0);
	}

}
