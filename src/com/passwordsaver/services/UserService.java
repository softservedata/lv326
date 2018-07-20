package com.passwordsaver.services;

import java.util.ArrayList;
import java.util.List;

import com.passwordsaver.dao.RoleDao;
import com.passwordsaver.dao.UserDao;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.dto.LoginDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.entity.User;

public class UserService {
	private UserDao userDao;
	private RoleDao roleDao;
	
	public UserService(){
		//userDao = new UserDao();
		//roleDao = new RoleDao();
		userDao = DaosContainer.getInstance().getUserDao();
		roleDao = DaosContainer.getInstance().getRoleDao();
	}
	public UserService(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}
	
	public int getAllUsersCount() {
		return userDao.getAll().size();
	}
	public boolean isExistUser(Long id) {
		boolean result = false;
		try {
			userDao.getById(id);
			result = true;
		}catch(RuntimeException exc) {
			return result;
		}
		return result;
	}
	public boolean isExistUser(String login) {
		boolean result = false;
		try {
			userDao.getUserEntityByLogin(login);
			result = true;
		}catch(RuntimeException exc) {
			result = false;
		}
		return result;
	}
	public boolean isValide(LoginDto loginDto) {
		boolean result = false;
		User user = null;
		try {
			user = userDao.getUserEntityByLogin(loginDto.getLogin());
		}catch(RuntimeException e) {
			return result;
		}
		if(loginDto.getPassword().equals(user.getPassword())) {
			result = true;
		}
		return result;
	}
	public boolean setUserDto(UserDto userDto, Long id) {
		boolean result = false;
		try {
			if(isExistUser(id)) {
				userDao.updateByEntity(new User(id, 
												userDto.getLogin(),
												userDto.getPassword(),
												roleDao.getRoleEntityByTitle(userDto.getRole()).getId()));
				result = true;
			}else {
				userDao.insert(new User(id,
						userDto.getLogin(),
						userDto.getPassword(),
						roleDao.getRoleEntityByTitle(userDto.getRole()).getId()));
				result = true;
			}
		}catch(RuntimeException exc) {
			result = false;
		}
		return result;
	}
	public UserDto getUserDto(LoginDto loginDto) {
		User user = null;
		user = userDao.getUserEntityByLogin(loginDto.getLogin());
		return new UserDto(user.getLogin(),
				user.getPassword(),
				roleDao.getById(user.getIdRole()).getTitle());
	}
	
	public Long getIdUserByLoginDto(LoginDto loginDto) {
		return userDao.getUserEntityByLogin(loginDto.getLogin()).getId();
	}
	public Long getIdUserByUserDto(UserDto userDto) {
		return userDao.getUserEntityByLogin(userDto.getLogin()).getId();
	}
	public AdminUsersDto getUsersDto(UserDto adminDto, int currentPage, int pageOffset, String substring){
		List<User> allUsers;
		try {
			allUsers = userDao.getAll();
		}catch(RuntimeException exc) {
			allUsers = new ArrayList<User>();
		}
		List<User> adminUsers = new ArrayList<User>();
		for(User user: allUsers) {
			if(!user.getLogin().equals(adminDto.getLogin()) && user.getLogin().indexOf(substring)!=-1) {
				adminUsers.add(user);
			}
		}
		int fromUser = (currentPage-1)*pageOffset;
		//int toUser = fromUser+pageOffset;
		int pageCount = (int)Math.ceil( (double)(adminUsers.size()) / (double)pageOffset);
		if(pageCount<1) {
			pageCount=1;
		}
		List<UserDto> adminUserDtos = new ArrayList<UserDto>();
		for(int i=fromUser; adminUserDtos.size()<pageOffset; i++) {
			if(i>=adminUsers.size()) {
				break;
			}
			String login = adminUsers.get(i).getLogin();
			String password = adminUsers.get(i).getPassword();
			String role = roleDao.getById( adminUsers.get(i).getIdRole() ).getTitle();
			if(!login.equals(adminDto.getLogin())) {
				adminUserDtos.add(new UserDto(login,password,role));
			}
		}
		AdminUsersDto usersDto = new AdminUsersDto(adminDto, 
													adminUserDtos, 
													pageCount, 
													pageOffset, 
													adminUsers.size(), 
													substring);
		return usersDto;
	}
	public Long getIdUserByLogin(String login) {
		return userDao.getUserEntityByLogin(login).getId();
	}
	public UserDto getUserDtoByLogin(String login) {
		User user = userDao.getUserEntityByLogin(login);
		return new UserDto(user.getLogin(),
				user.getPassword(),
				roleDao.getById(user.getIdRole()).getTitle());
	}
	public boolean deleteUserByLogin(String login) {
		return userDao.deleteByFieldName("login", login);
	}
}
