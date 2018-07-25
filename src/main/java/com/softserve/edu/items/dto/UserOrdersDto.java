package com.softserve.edu.items.dto;

import java.util.ArrayList;
import java.util.List;

public class UserOrdersDto {

	private final int DEFAULT_PAGE_OFFSET = 10;//kil6kist6 items yaki pokaz na stor
	private String userLogin;
	private List<OrderDto> orders;
	private int pageCount;
	private int pageOffset;
	
	public UserOrdersDto(String userLogin) {
		this.userLogin = userLogin;
		this.orders = new ArrayList<>();
		this.pageCount = orders.size() / DEFAULT_PAGE_OFFSET +2;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}

	public UserOrdersDto(String userLogin, List<OrderDto> orders) {
		this.userLogin = userLogin;
		this.orders = orders;
		this.pageCount = orders.size() / DEFAULT_PAGE_OFFSET + 2;
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}
	
	// setters

		public void setUserLogin(String userLogin) {
			this.userLogin = userLogin;
		}

		public void setItems(List<OrderDto> orders) {
			this.orders = orders;
		}

		public void addOrderDto(OrderDto order) {
			orders.add(order);
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

		public List<OrderDto> getOrders() {
			return orders;
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
					+ " items=" + orders.toString() 
					+ " pageCount=" + pageCount
					+ " pageOffset=" + pageOffset
					+ ")";
		}
	
}
