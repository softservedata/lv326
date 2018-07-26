package com.softserve.edu.comics.controllers.user;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.PageTitles;
import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.dto.UsersListDto;
import com.softserve.edu.comics.services.IocContainer;
import com.softserve.edu.comics.services.UsersListServise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.USERS_LIST_SERVLET)
public class UsersList extends HttpServlet {

    private UsersListServise usersListServise;

    public UsersList() {
        this.usersListServise = IocContainer.getContainer().getUsersListServise();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.USERS_LIST_PAGE);
        UsersListDto usersListDto = usersListServise.getUserList();
        req.setAttribute(FieldName.USER_LIST_DTO, usersListDto);
        req.getRequestDispatcher(WebPath.USERS_LIST_JSP).forward(req, resp);
    }

}
