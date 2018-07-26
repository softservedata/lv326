package com.softserve.edu.comics.controllers.common;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.PageTitles;
import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.dto.UserComicsDto;
import com.softserve.edu.comics.dto.UserDto;
import com.softserve.edu.comics.services.IocContainer;
import com.softserve.edu.comics.services.UserComicsService;
import com.softserve.edu.comics.tools.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.USER_COMICS_SERVLET)
public class UserComics extends HttpServlet {

    private UserComicsService userComicsService;

    public UserComics() {
        this.userComicsService = IocContainer.getContainer().getUserComicsService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.COMICS_LIST_PAGE);
        if (UserUtils.isActiveSession(req)){
            UserDto userDto = (UserDto) req.getSession().getAttribute(FieldName.USER_DTO);
            UserComicsDto userComicsDto = userComicsService.getUserComics(userDto);
            req.setAttribute(FieldName.USER_COMICS_DTO, userComicsDto);
            req.getRequestDispatcher(WebPath.USER_COMICS_JSP).forward(req, resp);
        }else {
            resp.sendRedirect(WebPath.LOGIN_SERVLET);
        }
    }

}
