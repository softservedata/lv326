package com.passwordsaver.controllers.securities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;

public class UserSecurity {
	public static boolean isLoginedUser(HttpServletRequest request) {
		boolean isLoginedUser = false;
		HttpSession session = request.getSession(false);
		Cookie idSessionCookie = null;
		for(Cookie currentCookie : request.getCookies()) {
			if(currentCookie.getName().equals("id_session")){
				idSessionCookie = currentCookie;
				break;
			}
		}
		
		isLoginedUser = session!=null
				&& idSessionCookie!=null
				&& session.getAttribute(AttributeNames.USER_DTO.toString())!=null
				&& idSessionCookie.getValue().equals(session.getId());
		return isLoginedUser;
	}
}
