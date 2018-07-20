package com.softserve.edu.items.dto;

import java.util.ArrayList;
import java.util.List;

public class UserItemsDto {
	private final int DEFAULT_PAGE_OFFSET = 10;
	private String userLogin;
	private List<ItemDto> items;
	private int pageCount;
	private int pageOffset;

	public UserItemsDto(String userLogin) {
		this.userLogin = userLogin;
		this.items = new ArrayList<>();
		this.pageCount = items.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	public UserItemsDto(String userLogin, List<ItemDto> items) {
		this.userLogin = userLogin;
		this.items = items;
		this.pageCount = items.size() / DEFAULT_PAGE_OFFSET + 1;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	// setters

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setItems(List<ItemDto> items) {
		this.items = items;
	}

	public void addItemDto(ItemDto item) {
		items.add(item);
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	// getters

	public String getUserLogin() {
		return userLogin;
	}

	public List<ItemDto> getItems() {
		return items;
	}

	public int getPageCount() {
		return  pageCount;
	}

	public int getPageOffset() {
		return  pageOffset;
	}

	@Override
	public String toString() {
		return "(" + "userLogin=" + userLogin  
				+ " items=" + items.toString() 
				+ " pageCount=" + pageCount
				+ " pageOffset=" + pageOffset
				+ ")";
	}

}
