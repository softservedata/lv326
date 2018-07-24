package com.mazurak.cinema.service;

import java.util.ArrayList;
import java.util.List;

import com.mazurak.cinema.dao.RoleDao;
import com.mazurak.cinema.dao.UserDao;
import com.mazurak.cinema.dto.LoginDto;
import com.mazurak.cinema.dto.UserDto;
import com.mazurak.cinema.entity.Role;
import com.mazurak.cinema.entity.User;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class UserService {

	private UserDao userDao;
	private RoleDao roleDao;

	public UserService() {
		userDao = new UserDao();
		roleDao = new RoleDao();
	}

	public void saveUserToDataBase(UserDto userDto) {
		User user = new User(0L, 
				userDto.getName(),
				userDto.getLogin(),
				userDto.getPassword(),
				1L);
		userDao.insert(user);
	}

	public boolean isAlreadyExists(UserDto userDto) {
		boolean result = false;
		try {
			userDao.getUserEntityByLogin(userDto.getLogin());
			result = false;
		} catch (Exception e) {
			saveUserToDataBase(userDto);
			result = true;
		}
		return result;
	}

	public boolean setUserDto(UserDto userDto) {
		boolean result = true;
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		try {
			user.setRoleId(roleDao.getRoleEntityByName(userDto.getRole()).getId());
		} catch (Exception e) {
			System.out.println("RuntimeException, message:" + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public UserDto getUserDto(Long idUser) {
		User user = userDao.getById(idUser);
		return  new UserDto(
				user.getId(),
				user.getName(),
				user.getLogin(),
				user.getPassword(),
				roleDao.getById(user.getRoleId()).getName());
	} 
	
	public boolean isValid(LoginDto loginDto) {
		boolean result = true;
		User users = null;
		try {
			users = userDao.getUserEntityByLogin(loginDto.getLogin());
		} catch (Exception e) {
			System.out.println(("RuntmeException, message" + e.getMessage()));
			result = false;
		}
		result = result && (users.getPassword().equals(loginDto.getPassword()));
		return result;
	}

	public String getUserNameByLoginDto(LoginDto loginDto) {
		User user = userDao.getUserEntityByLogin(loginDto.getLogin());
		return user.getName();
	}

	public Long getIdUserByLogin(LoginDto loginDto) {
		return userDao.getUserEntityByLogin(loginDto.getLogin()).getId();
	}
	// Get All Users for Admin page
	public List<UserDto> getAllUsers() {
		List<UserDto> listUsersDto = new ArrayList<>();
		for (User user : userDao.getAll()) {
			UserDto userDto = new UserDto(
					user.getId(),
					user.getName(),
					user.getLogin(),
					user.getPassword(),
					user.getRoleId().toString());

			listUsersDto.add(userDto);
		}
		return listUsersDto;
	}

	public boolean deleteUserFromDB(Long idUser) {
		return userDao.deleteById(idUser);
	}

	public Long getUserRole(LoginDto loginDto) {
		User user = userDao.getUserEntityByLogin(loginDto.getLogin());
		Long roleId = user.getRoleId();
		return roleId;
	}
	
	public String getUserRole(Long idUser) {
		User user = userDao.getById(idUser);
		Role role = roleDao.getById(user.getRoleId());
		return role.getName();
	}
	
	public boolean updateUserRoleByAdmin(String newRole, Long idUser) {
		System.out.println(roleDao.updateByFieldName("name", newRole, "name", 
				getUserRole(idUser)));
		return roleDao.updateByFieldName("name", newRole, "name", 
				getUserRole(idUser));
	}
}
