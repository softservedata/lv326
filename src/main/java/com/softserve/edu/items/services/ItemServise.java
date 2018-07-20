package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.ItemDao;
import com.softserve.edu.items.dto.ItemDto;
import com.softserve.edu.items.entity.Item;

public class ItemServise {

	private ItemDao itemDao;

	// TODO This is temporary, must be delete
	public ItemServise() {
		this.itemDao = new ItemDao();
		//itemDao = IocContainer.get().getItemDao();
	}

	public ItemServise(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public ItemDto getItemDto(long id) {
		Item item = itemDao.getById(id);
		return new ItemDto(item.getId(), item.getName(), item.getDescription());
	}

	public boolean setItemDto(ItemDto itemDto, Long idUser) {
		boolean result = false;
		Item item = new Item(itemDto.getIdItem(), itemDto.getName(), itemDto.getDescription(), idUser);
		if (itemDto.getIdItem() > 0) {
			if (isExistItem(item.getId())) {
				itemDao.updateByEntity(item);
				result = true;
			}
		} else {
			itemDao.insert(item);
			result = true;
		}
		return result;
	}

	public boolean isExistItem(long id) {
		boolean result = true;
		try {
			itemDao.getById(id);
		} catch (RuntimeException e) {
			// Logging Exception
			System.out.println("Item not found, message: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean deleteItem(long id) {
		boolean result = true;
		try {
			result = result && itemDao.deleteById(id);
		} catch (RuntimeException e) {
			// Logging Exception
			result = false;
		}
		return result;
	}

}
