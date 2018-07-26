package com.softserve.edu.comics.services;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.dao.UserDao;
import com.softserve.edu.comics.dto.UserDto;
import com.softserve.edu.comics.dto.UsersListDto;
import com.softserve.edu.comics.entity.User;

public class UsersListServise {

    private UserDao userDao;

    public UsersListServise() {
        this.userDao = new UserDao();
    }

    public UsersListDto getUserList(){
        UsersListDto usersListDto = new UsersListDto();
        for (User user : userDao.getAll()){
            UserDto userDto = new UserDto(user.getLogin(),
                    user.getPassword(),
                    user.getName(),
                    FieldName.USER_ROLE);
            usersListDto.addUserDtoToList(userDto);
        }
        return usersListDto;
    }

}
