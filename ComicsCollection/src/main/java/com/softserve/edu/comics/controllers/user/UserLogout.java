package com.softserve.edu.comics.controllers.user;

import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.tools.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.LOGOUT_SERVLET)
public class UserLogout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (UserUtils.isActiveSession(request)) {
            request.getSession().invalidate();
        }
        response.sendRedirect(WebPath.LOGIN_SERVLET);
    }

}
