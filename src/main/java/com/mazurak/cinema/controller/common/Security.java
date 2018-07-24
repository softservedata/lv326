package com.mazurak.cinema.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mazurak.cinema.constant.AttributeName;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.dto.LoginDto;

public class Security {

	public static boolean isActiveSession(HttpServletRequest req, HttpServletResponse resp) {
		boolean isActiveSession = true;
		HttpSession session = req.getSession(false); // Do not Create new Session
		Cookie idSessionCookie = null;
		for (Cookie currentCookie : req.getCookies()) {
			if (currentCookie.getName().equals(AttributeName.SESSION_ID)) {
				idSessionCookie = currentCookie;
				break;
			}
		}
		isActiveSession = (session != null)
				&& (session.getAttribute(AttributeName.SESSION_LOGINDTO_ATTRIBUTE) != null)
				&& (((LoginDto) (session.getAttribute(AttributeName.SESSION_LOGINDTO_ATTRIBUTE)))
						.getLogin() != null)
				&& (idSessionCookie != null) && (idSessionCookie.getValue().equals(session.getId()));
		if (!isActiveSession) {
			try {
				req.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString()).forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isActiveSession;
	}
}
