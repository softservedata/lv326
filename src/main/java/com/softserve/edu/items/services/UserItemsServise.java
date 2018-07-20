package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.ItemDao;
import com.softserve.edu.items.dao.UserDao;
import com.softserve.edu.items.dto.ItemDto;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.dto.UserItemsDto;
import com.softserve.edu.items.entity.Item;
import com.softserve.edu.items.entity.User;

public class UserItemsServise {

	private UserDao userDao;
	private ItemDao itemDao;

	// TODO This is temporary, must be delete
	public UserItemsServise() {
		userDao = new UserDao();
		itemDao = new ItemDao();
		//userDao = IocContainer.get().getUserDao();
		//itemDao = IocContainer.get().getItemDao();
	}

	public UserItemsServise(UserDao userDao, ItemDao itemDao) {
		this.userDao = userDao;
		this.itemDao = itemDao;
	}

	public UserItemsDto getUserItems(UserDto userDto) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		UserItemsDto userItemsDto = new UserItemsDto(user.getLogin());
		// TODO get fixed count
		for (Item item : itemDao.getItemEntityByIdUser(user.getId())) {
			ItemDto itemDto = new ItemDto(item.getId(),
					item.getName(),
					item.getDescription());
			userItemsDto.addItemDto(itemDto);
		}
		return userItemsDto;
	}

}
