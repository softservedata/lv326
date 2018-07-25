package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.OrderDao;
import com.softserve.edu.items.dao.RoleDao;
import com.softserve.edu.items.dao.UserDao;

public final class IocContainer {

	private static volatile IocContainer instance = null;
	//
	private UserDao userDao;
	private RoleDao roleDao;
	private OrderDao orderDao;
	//
	private UserService userService;
	private OrderServise orderServise;
	private UserOrdersServise userOrdersServise;

	private IocContainer() {
		initDaos();
		initServices();
	}
	
	private void initDaos() {
		userDao = new UserDao();
		roleDao = new RoleDao();
		orderDao = new OrderDao();
	}

	private void initServices() {
		userService = new UserService(userDao, roleDao);
		orderServise = new OrderServise(orderDao);
		userOrdersServise = new UserOrdersServise(userDao, orderDao);
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

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public OrderServise getOrderServise() {
		return orderServise;
	}

	public UserOrdersServise getUserOrdersServise() {
		return userOrdersServise;
	}

}
