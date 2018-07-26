package com.softserve.edu.comics.controllers.comics;

import com.softserve.edu.comics.constants.FieldName;
import com.softserve.edu.comics.constants.Message;
import com.softserve.edu.comics.constants.PageTitles;
import com.softserve.edu.comics.constants.WebPath;
import com.softserve.edu.comics.dto.ComicsDto;
import com.softserve.edu.comics.services.ComicsServise;
import com.softserve.edu.comics.services.IocContainer;
import com.softserve.edu.comics.tools.ComicsUtils;
import com.softserve.edu.comics.tools.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(WebPath.EDIT_COMICS_SERVLET)
public class EditComics extends HttpServlet {

    private ComicsServise comicsServise;

    public EditComics() {
        this.comicsServise = IocContainer.getContainer().getComicsServise();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute(PageTitles.PAGE_TITLE, PageTitles.EDIT_COMICS_PAGE);
        Long userId = UserUtils.getUserId(req);
        ComicsDto comicsDto = comicsServise.getComicsDto(Long.parseLong(req.getParameter(FieldName.ID_COMICS)), userId);
        req.setAttribute(FieldName.COMICS_DTO, comicsDto);
        req.getRequestDispatcher(WebPath.EDIT_COMICS_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComicsUtils.editComics(req);
            resp.sendRedirect(WebPath.USER_COMICS_SERVLET);
        }catch (Exception e){
            req.setAttribute(FieldName.ERROR, Message.COMICS_EXISTS);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(WebPath.EDIT_COMICS_JSP)
                    .forward(req, resp);
        }
    }

}
