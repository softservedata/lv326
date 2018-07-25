package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.OrderDao;
import com.softserve.edu.items.dao.UserDao;
import com.softserve.edu.items.dto.OrderDto;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.dto.UserOrdersDto;
import com.softserve.edu.items.entity.Order;
import com.softserve.edu.items.entity.User;

public class UserOrdersServise {

	private UserDao userDao;
	private OrderDao orderDao;

	// TODO This is temporary, must be delete
	public UserOrdersServise() {
		userDao = new UserDao();
		orderDao = new OrderDao();
		//userDao = IocContainer.get().getUserDao();
		//orderDao = IocContainer.get().getOrderDao();
	}

	public UserOrdersServise(UserDao userDao, OrderDao orderDao) {
		this.userDao = userDao;
		this.orderDao = orderDao;
	}

	public UserOrdersDto getUserOrders(UserDto userDto) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		UserOrdersDto userOrdersDto = new UserOrdersDto(user.getLogin());
		// TODO get fixed count
		for (Order order : orderDao.getOrderEntityByIdUser(user.getId())) {
			OrderDto orderDto = new OrderDto(order.getId(),
					order.getShop(),
					order.getAddress(),
					order.getProduction(),
					order.getScope(),
					order.getStatus()
					);
			userOrdersDto.addOrderDto(orderDto);
		}
		return userOrdersDto;
	}

}
