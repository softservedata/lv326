package com.softserve.edu.items.services;

import java.util.ArrayList;
import java.util.List;

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

		
	public boolean setUserDto(UserDto userDto ) {
		boolean result = true;	
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		user.setLogin(userDto.getLogin());
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
	
	public boolean setUserDtoUpdateLogin(UserDto userDto,String login ) {
		boolean result = true;	
		User user = userDao.getUserEntityByLogin(userDto.getLogin());		
		user.setLogin(login);
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
	
	public boolean insertUserDto(UserDto userDto ) {
		boolean result = true;	
		Long idRole = roleDao.getRoleEntityByName(userDto.getRole()).getId();
		User user = new User(0L,
				             userDto.getLogin(),
				             userDto.getPassword(),
				             userDto.getName(),
				             idRole);
		try {
			userDao.insert(user);
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		return result;
	}
		
	public boolean isUserExist(String login) {
		boolean result = true;
		User users = null;
		try {
			users = userDao.getUserEntityByLogin(login);
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		//result = result && (users.getPassword().equals(loginDto.getPassword())); //check if account wuth this login exist; if exist-check their pass- create acc
		return result;
	}
	
	public Long getIdUserByLogin(UserDto userDto) {
		return userDao.getUserEntityByLogin(userDto.getLogin()).getId();
	}
	
	public Long getIdUserByName(String nameUser) {
		List<User> users= userDao.getByFieldName("name", nameUser);
		return users.get(0).getId();
	}

	public UserDto getUserDto(LoginDto loginDto) {
		User user = userDao.getUserEntityByLogin(loginDto.getLogin());
		return new UserDto(user.getLogin(),
				user.getPassword(),
				user.getName(),
				roleDao.getById(user.getIdRole()).getName());
	}
	public UserDto getUserDtoByName(String userName) {
		User user = userDao.getByFieldName("name", userName).get(0); 
		return new UserDto(user.getLogin(),
				user.getPassword(),
				user.getName(),
				roleDao.getById(user.getIdRole()).getName());
	}
		
	public List<String> getAllUsersName() {
		List<String> listAllUsersName= new ArrayList<>();
		List<User> myList = userDao.getAll();
		for(int i=0; i<myList.size(); i++) {
			listAllUsersName.add(myList.get(i).getName());
		}
		return listAllUsersName; 
	}

	public Long getIdUserByLogin(LoginDto loginDto) {
		return userDao.getUserEntityByLogin(loginDto.getLogin()).getId();
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
	
	public boolean isAdmin(LoginDto loginDto) {
		boolean result = true;
		Long nuberRole=null;
		try {
			nuberRole = userDao.getUserEntityByLogin(loginDto.getLogin()).getIdRole();
		} catch (Exception e) {
			// Logging Exception
			System.out.println("RuntimeException, message: " + e.getMessage());
			result = false;
		}
		if(!nuberRole.equals(new Long(1))) {
			result=false;
		}
		return result;
	}

}
