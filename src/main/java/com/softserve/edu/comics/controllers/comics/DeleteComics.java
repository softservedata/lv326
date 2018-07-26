package com.softserve.edu.comics.controllers.comics;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.services.ComicsServise;
import com.softserve.edu.comics.services.IocContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.DELETE_COMICS_SERVLET)
public class DeleteComics extends HttpServlet {

    private ComicsServise comicsServise;

    public DeleteComics() {
        comicsServise = IocContainer.getContainer().getComicsServise();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        comicsServise.deleComicsDtoById(Long.parseLong(req.getParameter(FieldName.ID_COMICS)));
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(WebPath.USER_COMICS_SERVLET)
                .forward(req, resp);
    }

}
