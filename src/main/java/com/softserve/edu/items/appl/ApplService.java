package com.softserve.edu.items.appl;

import com.softserve.edu.items.dto.ItemDto;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.UserItemsDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.ItemServise;
import com.softserve.edu.items.services.UserItemsServise;
import com.softserve.edu.items.services.UserService;

public class ApplService {
	public static void main(String[] args) {
		//UserService userService = new UserService();
		//UserItemsServise userItemsServise = new UserItemsServise();
		//ItemServise itemServise = new ItemServise();
		UserService userService = IocContainer.get().getUserService();
		UserItemsServise userItemsServise = IocContainer.get().getUserItemsServise();
		ItemServise itemServise = IocContainer.get().getItemServise();
		//
		// User Items
		LoginDto loginDto = new LoginDto("ivan", "qwerty");
		if (userService.isValid(loginDto)) {
			System.out.println(userItemsServise
					.getUserItems(userService.getUserDto(loginDto)));
		} else {
			System.out.println("Invalid User Name or Password");
		}
		//
		// Update Item
		//LoginDto loginDto = new LoginDto("ivan", "qwerty");
		//UserItemsDto userItemsDto = userItemsServise
		//		.getUserItems(userService.getUserDto(loginDto));
		//ItemDto itemDto = userItemsDto.getItems().get(1);
		//itemDto.setName("bebebe");
		//itemDto.setDescription("beDescript");
		//itemServise.setItemDto(itemDto, userService.getIdUserByLogin(loginDto));
		// print
		//System.out.println(userItemsServise
		//		.getUserItems(userService.getUserDto(loginDto)));
		//
		// Create Item
		//LoginDto loginDto = new LoginDto("ivan", "qwerty");
		//ItemDto itemDto = new ItemDto(0L, "new my name", "new Ivan Descript");
		//itemServise.setItemDto(itemDto, userService.getIdUserByLogin(loginDto));
		// print
		//System.out.println(userItemsServise
		//		.getUserItems(userService.getUserDto(loginDto)));
	}

}
