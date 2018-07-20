package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.ItemDao;
import com.softserve.edu.items.dao.RoleDao;
import com.softserve.edu.items.dao.UserDao;

public final class IocContainer {

	private static volatile IocContainer instance = null;
	//
	private UserDao userDao;
	private RoleDao roleDao;
	private ItemDao itemDao;
	//
	private UserService userService;
	private ItemServise itemServise;
	private UserItemsServise userItemsServise;

	private IocContainer() {
		initDaos();
		initServices();
	}
	
	private void initDaos() {
		userDao = new UserDao();
		roleDao = new RoleDao();
		itemDao = new ItemDao();
	}

	private void initServices() {
		userService = new UserService(userDao, roleDao);
		itemServise = new ItemServise(itemDao);
		userItemsServise = new UserItemsServise(userDao, itemDao);
	}

	public static IocContainer get() {
		if (instance == null) {
			synchronized (IocContainer.class) {
				if (instance == null) {
					instance = new IocContainer();
				}
			}
		}
		return instance;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public ItemServise getItemServise() {
		return itemServise;
	}

	public UserItemsServise getUserItemsServise() {
		return userItemsServise;
	}

}
