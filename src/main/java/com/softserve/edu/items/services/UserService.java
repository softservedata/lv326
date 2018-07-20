package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.RoleDao;
import com.softserve.edu.items.dao.UserDao;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.entity.User;

public class UserService {

	private UserDao userDao;
	private RoleDao roleDao;

	// TODO Temporary Constructor, must be delete
	public UserService() {
		userDao = new UserDao();
		roleDao = new RoleDao();
		//userDao = IocContainer.get().getUserDao();
		//roleDao = IocContainer.get().getRoleDao();
	}

	public UserService(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	public boolean setUserDto(UserDto userDto) {
		boolean result = true;
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		try {
			user.setIdRole(roleDao.getRoleEntityByName(userDto.getRole()).getId());
			userDao.updateByEntity(user);
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public UserDto getUserDto(LoginDto loginDto) {
		User user = userDao.getUserEntityByLogin(loginDto.getLogin());
		return new UserDto(user.getLogin(),
				user.getPassword(),
				user.getName(),
				roleDao.getById(user.getIdRole()).getName());
	}

	public Long getIdUserByLogin(LoginDto loginDto) {
		return userDao.getUserEntityByLogin(loginDto.getLogin()).getId();
	}

	public Long getIdUserByLogin(UserDto userDto) {
		return userDao.getUserEntityByLogin(userDto.getLogin()).getId();
	}

	public boolean isValid(LoginDto loginDto) {
		boolean result = true;
		User users = null;
		try {
			users = userDao.getUserEntityByLogin(loginDto.getLogin());
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		result = result && (users.getPassword().equals(loginDto.getPassword()));
		return result;
	}

}
