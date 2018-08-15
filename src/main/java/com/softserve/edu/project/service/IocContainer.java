package com.softserve.edu.project.service;


import com.softserve.edu.project.dao.*;


public final class IocContainer {

	private static volatile IocContainer instance = null;
	//
	private UserDao userDao;
	private RoleDao roleDao;
	private UserHasTeamsDao userHasTeamsDao;
	private TeamDao teamDao;
	//
	private UserService userService;
	private TeamService teamService;
	private UserHasTeamsService userHasTeamsService;
	private RoleService roleService;

	private IocContainer() {
		initDaos();
		initServices();
	}
	
	private void initDaos() {
		userDao = new UserDao();
		roleDao = new RoleDao();
		userHasTeamsDao=new UserHasTeamsDao();
		teamDao=new TeamDao();
	}

	private void initServices() {
		userService = new UserService(userDao, roleDao);
		teamService=new TeamService(teamDao,userHasTeamsDao);
		userHasTeamsService=new UserHasTeamsService();
		roleService=new RoleService(roleDao);
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
	public static IocContainer getInstance() {
		return instance;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public UserHasTeamsDao getUserHasTeamsDao() {
		return userHasTeamsDao;
	}

	public TeamDao getTeamDao() {
		return teamDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public TeamService getTeamService() {
		return teamService;
	}


	public RoleService getRoleService() {
		return roleService;
	}
}
