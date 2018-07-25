package com.softserve.edu.items.dao;

import com.softserve.edu.items.entity.User;
import com.softserve.edu.items.entity.User.UserEntityQueries;

public final class UserDao extends ADaoCRUD<User> {
	private final static String LOGIN_FIELDNAME = "Login";

	public UserDao() {
		super();
		init();
	}

	protected void init() {
		for (UserEntityQueries userEntityQueries : UserEntityQueries.values()) {
			sqlQueries.put(userEntityQueries.getSqlQuery(), userEntityQueries);
		}
	}

	// TODO Use List<String>
	protected User createInstance(String[] args) {
		return new User(
				Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
			    args[3] == null ? new String() : args[3],
			    Long.parseLong(args[4] == null ? "0" : args[4]));
	}

	protected String[] getUpdateFields(User entity) {
		String[] result = new String[5]; //Konstuctor private Long id;	private String login;	private String password;private String name;private Long idRole;
		String[] allFields = getFields(entity); //userDao.updateByEntity(new User(3L, "oleg", "qwerty11", "userOleg1", 1L));
		result[0] = allFields[1]; // login
		result[1] = allFields[2]; // pass
		result[2] = allFields[3]; // name
		result[3] = allFields[4]; //idRole
		result[4] = allFields[0];//id
		return result;
	}

	protected String[] getFields(User entity) {
		//String[] fields = new String[User.class.getDeclaredFields().length];
		String[] fields = new String[5];
		fields[0] = entity.getId().toString();
		fields[1] = entity.getLogin();
		fields[2] = entity.getPassword();
		fields[3] = entity.getName();
		fields[4] = entity.getIdRole().toString();
		return fields;
	}

	public User getUserEntityByLogin(String login) {
		return getByFieldName(LOGIN_FIELDNAME, login).get(0);
	}

}
