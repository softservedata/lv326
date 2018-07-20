package com.passwordsaver.controllers.securities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.passwordsaver.controllers.AttributeNames;
import com.passwordsaver.controllers.RoleNames;
import com.passwordsaver.dto.AdminUsersDto;
import com.passwordsaver.dto.UserDto;

public class AdminSecurity {
	public static boolean isLoginedAdmin(HttpServletRequest request) {
		boolean result = false;
		if(UserSecurity.isLoginedUser(request)) {
			
			UserDto userDto = (UserDto)request.getSession().getAttribute(AttributeNames.USER_DTO.toString());
			if(userDto.getRole().equals(RoleNames.ADMINISTRATOR.toString())) {
				result=true;
			}
		}
		return result;
	}
}
