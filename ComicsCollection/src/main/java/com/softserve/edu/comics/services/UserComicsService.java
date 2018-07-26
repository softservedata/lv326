package com.softserve.edu.comics.services;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.dao.ComicsDao;
import com.softserve.edu.comics.dao.UserDao;
import com.softserve.edu.comics.dto.ComicsDto;
import com.softserve.edu.comics.dto.UserComicsDto;
import com.softserve.edu.comics.dto.UserDto;
import com.softserve.edu.comics.entity.Comics;
import com.softserve.edu.comics.entity.User;

public class UserComicsService {

    private UserDao userDao;
    private ComicsDao comicsDao;

    public UserComicsService() {
        this.userDao = new UserDao();
        this.comicsDao = new ComicsDao();
    }

    public UserComicsDto getUserComics(UserDto userDto){
        User user;
        try {
            user = userDao.getByFieldName(FieldName.LOGIN, userDto.getLogin()).get(0);
            UserComicsDto userComicsDto = new UserComicsDto(user.getLogin());
            for (Comics comics : comicsDao.getAll()){
                if (comics.getIdUser() == user.getId()){
                    ComicsDto comicsDto = new ComicsDto(comics.getId(),
                            comics.getTitle(),
                            comics.getAuthor(),
                            comics.getPublishingOffice(),
                            comics.getDescription(),
                            comics.getIdUser());
                    userComicsDto.addComicsDto(comicsDto);
                }
            }
            return userComicsDto;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
