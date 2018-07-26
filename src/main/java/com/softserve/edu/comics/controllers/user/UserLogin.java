package com.softserve.edu.comics.controllers.user;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.constants.PageTitles;
import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.tools.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.LOGIN_SERVLET)
public class UserLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.LOGIN_PAGE);
        req.getRequestDispatcher(WebPath.LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserUtils.isActiveSession(req)) {
            req.getSession().invalidate();
        }
        if (UserUtils.isLogged(req)) {
            UserUtils.createSession(req);
            resp.sendRedirect(WebPath.USER_COMICS_SERVLET);
        } else {
            req.setAttribute(FieldName.ERROR, Message.BAD_LOGIN);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(WebPath.LOGIN_JSP)
                    .forward(req, resp);
        }
    }

}
