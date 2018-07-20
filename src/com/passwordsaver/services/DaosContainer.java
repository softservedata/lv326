package com.passwordsaver.services;

import com.passwordsaver.dao.KeyDao;
import com.passwordsaver.dao.RoleDao;
import com.passwordsaver.dao.UserDao;

public class DaosContainer {
	private static volatile DaosContainer instance = null;
	
	private UserDao userDao;
	private KeyDao keyDao;
	private RoleDao roleDao;
	
	private DaosContainer() {
		userDao = new UserDao();
		keyDao = new KeyDao();
		roleDao = new RoleDao();
	}
	static public DaosContainer getInstance() {
		if(instance==null) {
			synchronized(DaosContainer.class) {
				if(instance==null) {
					instance = new DaosContainer();
				}
			}
		}
		return instance;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public KeyDao getKeyDao() {
		return keyDao;
	}
	public RoleDao getRoleDao() {
		return roleDao;
	}
}
