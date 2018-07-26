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

@WebServlet(WebPath.REGISTER_SERVLET)
public class UserRegister extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.REGISTER_PAGE);
        req.getRequestDispatcher(WebPath.REGISTER_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (UserUtils.createUser(req, resp) != null){
                resp.sendRedirect(req.getContextPath() + WebPath.LOGIN_SERVLET);
            }else {
                req.setAttribute(FieldName.ERROR, Message.PASSWORDS_DONOT_MATCH);
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(WebPath.REGISTER_JSP)
                        .forward(req, resp);
            }
        }catch (Exception e){
            req.setAttribute(FieldName.ERROR, Message.LOGIN_EXISTS);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(WebPath.REGISTER_JSP)
                    .forward(req, resp);
        }
    }

}
