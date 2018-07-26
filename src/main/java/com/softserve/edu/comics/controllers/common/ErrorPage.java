package com.softserve.edu.comics.controllers.common;

import com.softserve.edu.comics.constants.WebPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.ERROR_PAGE_SERVLET)
public class ErrorPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebPath.ERROR_JSP).forward(req, resp);
    }
}
