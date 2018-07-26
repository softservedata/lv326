package com.softserve.edu.comics.controllers.comics;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.constants.PageTitles;
import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.tools.ComicsUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.ADD_COMICS_SERVLET)
public class AddComics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.ADD_COMICS_PAGE);
        req.getRequestDispatcher(WebPath.ADD_COMICS_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (ComicsUtils.createComics(req) != null){
                resp.sendRedirect(req.getContextPath() + WebPath.USER_COMICS_SERVLET);
            }else {
                req.setAttribute(FieldName.ERROR, Message.PASSWORDS_DONOT_MATCH);
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(WebPath.ADD_COMICS_JSP)
                        .forward(req, resp);
            }
        }catch (Exception e){
            req.setAttribute(FieldName.ERROR, Message.COMICS_EXISTS);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(WebPath.ADD_COMICS_JSP)
                    .forward(req, resp);
        }
    }

}

