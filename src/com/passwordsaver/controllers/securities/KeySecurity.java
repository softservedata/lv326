package com.passwordsaver.controllers.securities;

import com.passwordsaver.controllers.ServicesContainer;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.services.KeyService;
import com.passwordsaver.services.UserService;

public class KeySecurity {

	static public boolean isUrerKey(UserService userService, KeyService keyService, UserDto userDto, Long idKey) {
		boolean result = false;
		
		Long idUserFromKey = keyService.getIdUserByIdKey(idKey);
		Long idUserFromUserDto = userService.getIdUserByUserDto(userDto);
		
		result = idUserFromKey.equals(idUserFromUserDto);
		
		return result;
	}
}
