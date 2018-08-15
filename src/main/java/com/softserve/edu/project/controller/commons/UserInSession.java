package com.softserve.edu.project.controller.commons;

import com.softserve.edu.project.controller.ControllerUrls;
import com.softserve.edu.project.dto.LoginDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInSession {
     public static final boolean  isUserInCurrectSession(HttpServletRequest request, HttpServletResponse response){
         boolean isActiveSession = true;
         HttpSession session = request.getSession(false); // Do not Create new Session
         Cookie idSessionCookie = null;
         for (Cookie currentCookie : request.getCookies()) {
             if (currentCookie.getName().equals("id_session")) {
                 idSessionCookie = currentCookie;
                 break;
             }
         }
         isActiveSession = isActiveSession && (session != null)
                 && (session.getAttribute("loginDto") != null)
                 && (((LoginDto) (session.getAttribute("loginDto"))).getUsername() != null)
                 && (idSessionCookie != null);
         return   isActiveSession
                 && (idSessionCookie.getValue().equals(session.getId()));
    }

}
