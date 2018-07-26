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

@WebServlet(WebPath.CHANGE_PASSW_SERVLET)
public class EditUserPassw extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.CHANGE_PASSW_PAGE);
        req.getRequestDispatcher(WebPath.CHANGE_PASSW_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (UserUtils.changePassw(req) != null){
                resp.sendRedirect(req.getContextPath() + WebPath.USER_PROFILE_SERVLET);
            }else {
                req.setAttribute(FieldName.ERROR, Message.CHANGE_PASSW_ERROR);
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(WebPath.CHANGE_PASSW_JSP)
                        .forward(req, resp);
            }

        }catch (Exception e){
            req.setAttribute(FieldName.ERROR, Message.CHANGE_PASSW_ERROR);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(WebPath.CHANGE_PASSW_JSP)
                    .forward(req, resp);
        }
    }

}
