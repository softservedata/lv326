package com.softserve.edu.comics.controllers.user;

import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.tools.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.USER_CANCEL_SERVLET)
public class UserCancelUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserUtils.isActiveSession(req)) {
            resp.sendRedirect(WebPath.USER_COMICS_SERVLET);
        } else {
            resp.sendRedirect(WebPath.HOME_SERVLET);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
