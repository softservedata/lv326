package com.softserve.edu.comics.controllers.user;

import com.softserve.edu.comics.constants.PageTitles;
import com.softserve.edu.comics.constants.WebPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.USER_PROFILE_SERVLET)
public class UserProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.USER_PROFILE_PAGE);
        req.getRequestDispatcher(WebPath.USER_PROFILE_JSP).forward(req, resp);
    }
}
