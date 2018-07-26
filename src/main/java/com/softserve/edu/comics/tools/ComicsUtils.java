package com.softserve.edu.comics.tools;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.dto.ComicsDto;
import com.softserve.edu.comics.dto.UserDto;
import com.softserve.edu.comics.services.IocContainer;

import javax.servlet.http.HttpServletRequest;

public class ComicsUtils {

    // CREATE COMICS
    public static ComicsDto createComics(HttpServletRequest req){
        String login = ((UserDto) req.getSession().getAttribute(FieldName.USER_DTO)).getLogin();
        UserDto userDto = new UserDto(login,
                req.getParameter(FieldName.PASSWORD),
                req.getParameter(FieldName.NAME),
                FieldName.USER_ROLE);
        Long userId = IocContainer.getContainer().getUserService().getIdUserByLogin(userDto);
        ComicsDto comicsDto = new ComicsDto(1L, req.getParameter(FieldName.TITLE),
                req.getParameter(FieldName.AUTHOR),
                req.getParameter(FieldName.PUBLISHING_OFFICE),
                req.getParameter(FieldName.DESCRIPTION),
                userId);
        IocContainer.getContainer().getComicsServise().createComics(comicsDto);
        return comicsDto;
    }

    // EDIT COMICS
    public static ComicsDto editComics(HttpServletRequest req){
        String login = ((UserDto) req.getSession().getAttribute(FieldName.USER_DTO)).getLogin();
        UserDto userDto = new UserDto(login,
                req.getParameter(FieldName.PASSWORD),
                req.getParameter(FieldName.NAME),
                FieldName.USER_ROLE);
        Long userId = IocContainer.getContainer().getUserService().getIdUserByLogin(userDto);
        ComicsDto comicsDto = new ComicsDto(Long.parseLong(req.getParameter(FieldName.ID_COMICS)), req.getParameter(FieldName.TITLE),
                req.getParameter(FieldName.AUTHOR),
                req.getParameter(FieldName.PUBLISHING_OFFICE),
                req.getParameter(FieldName.DESCRIPTION),
                userId);
        IocContainer.getContainer().getComicsServise().setComicsDto(comicsDto, userId);
        return comicsDto;
    }

}
