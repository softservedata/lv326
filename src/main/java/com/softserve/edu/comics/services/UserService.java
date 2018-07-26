package com.softserve.edu.comics.services;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.dao.RoleDao;
import com.softserve.edu.comics.dao.UserDao;
import com.softserve.edu.comics.dto.LoginDto;
import com.softserve.edu.comics.dto.UserDto;
import com.softserve.edu.comics.entity.User;

import java.util.List;

public class UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserService() {
        this.userDao = new UserDao();
        this.roleDao = new RoleDao();
    }

    public UserDto getUserDto(LoginDto loginDto) {
        User user = userDao.getUserEntityByLogin(loginDto.getLogin());
        return new UserDto(user.getLogin(),
                user.getPassword(),
                user.getName(),
                roleDao.getById(user.getIdRole()).getName());
    }

    public boolean createUser(UserDto userDto){
        return userDao.insert(new User(0L, userDto.getLogin(), userDto.getPassword(), userDto.getName(),2L));
    }

    public boolean updateUser(UserDto userDto, Long userId){
        return userDao.updateByEntity(new User(userId, userDto.getLogin(), userDto.getPassword(), userDto.getName(), 2L));
    }

    public Long getIdUserByLogin(UserDto userDto) {
        return userDao.getUserEntityByLogin(userDto.getLogin()).getId();
    }

    public boolean isLogged(String login, String password){
        List<User> users;
        users = userDao.getByFieldName(FieldName.LOGIN, login);
        if (users.size() == 1){
            if (users.get(0).getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

}
