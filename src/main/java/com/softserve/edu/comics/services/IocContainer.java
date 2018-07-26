package com.softserve.edu.comics.services;

import com.softserve.edu.comics.dao.ComicsDao;
import com.softserve.edu.comics.dao.RoleDao;
import com.softserve.edu.comics.dao.UserDao;
import lombok.Getter;

@Getter
public class IocContainer {

    private static volatile IocContainer container = null;

    private UserDao userDao;
    private RoleDao roleDao;
    private ComicsDao comicsDao;
    private UserService userService;
    private ComicsServise comicsServise;
    private UserComicsService userComicsService;
    private UsersListServise usersListServise;

    private IocContainer(){
        initDao();
        initService();
    }

    private void initDao(){
        userDao = new UserDao();
        roleDao = new RoleDao();
        comicsDao = new ComicsDao();
    }

    private void initService(){
        userService = new UserService();
        comicsServise = new ComicsServise();
        userComicsService = new UserComicsService();
        usersListServise = new UsersListServise();
    }

    public static IocContainer getContainer(){
        if(container == null){
            synchronized (IocContainer.class){
                if (container == null){
                    container = new IocContainer();
                }
            }
        }
        return container;
    }

}
