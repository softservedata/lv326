package com.softserve.edu.items.services;

import com.softserve.edu.items.dao.OrderDao;
import com.softserve.edu.items.dto.OrderDto;
import com.softserve.edu.items.entity.Order;

public class OrderServise {

	private OrderDao orderDao;

	// TODO This is temporary, must be delete
	public OrderServise() {
		this.orderDao = new OrderDao();
		//orderDao = IocContainer.get().getOrderDao();
	}

	public OrderServise(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public OrderDto geOrderDto(Long id) {
		Order order = orderDao.getById(id);
		return new OrderDto(order.getId(), order.getShop(), order.getAddress(), order.getProduction(),  order.getScope(), order.getStatus() );
	}

	public boolean setOrderDto(OrderDto orderDto, Long idUser) {//zaminuty metodu
		boolean result = false;
		Order order = new Order(orderDto.getIdOrder(), orderDto.getShop(), orderDto.getAddress(), orderDto.getProduction(),  orderDto.getScope(), orderDto.getStatus(), idUser);
		if (orderDto.getIdOrder() > 0) {
			if (isExistOrder(order.getId())) {
				orderDao.updateByEntity(order);
				result = true;
			}
		} else {
			orderDao.insert(order);
			result = true;
		}
		return result;
	}

	public boolean isExistOrder(Long id) {
		boolean result = true;
		try {
			orderDao.getById(id);
		} catch (RuntimeException e) {
			// Logging Exception
			System.out.println("Order not found, message: " + e.getMessage());
			result = false;
		}
		return result;
	    }
	
	public boolean deleteOrder(long id) {
		boolean result = true;
		try {
			result = result && orderDao.deleteById(id);
		} catch (RuntimeException e) {
			// Logging Exception
			result = false;
		}
		return result;
	}


}
