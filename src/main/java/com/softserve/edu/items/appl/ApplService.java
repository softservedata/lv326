package com.softserve.edu.items.appl;

import com.softserve.edu.items.dto.OrderDto; //змінити в іменах пакетів на ордер
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.UserOrdersDto;
import com.softserve.edu.items.services.IocContainer;
import com.softserve.edu.items.services.OrderServise;
import com.softserve.edu.items.services.UserOrdersServise;
import com.softserve.edu.items.services.UserService;

public class ApplService {
	public static void main(String[] args) {
		//UserService userService = new UserService();
		//UserOrdersServise userOrdersServise = new UserOrdersServise();
		//OrderServise orderServise = new OrderServise();
		
		UserService userService = IocContainer.get().getUserService();
		UserOrdersServise userItemsServise = IocContainer.get().getUserOrdersServise();
		OrderServise orderServise = IocContainer.get().getOrderServise();
		
		// User Items
		/*LoginDto loginDto = new LoginDto("1@1", "qwerty");
		if (userService.isValid(loginDto)) {
			System.out.println(userItemsServise
					.getUserOrders(userService.getUserDto(loginDto)));
		} else {
			System.out.println("Invalid User Name or Password");
		}*/
		//
		// Update Item
		//LoginDto loginDto = new LoginDto("ivan", "qwerty");//передтим як роботи апдейт перевіритичиєтакий юзер
		//UserOrdersDto userOrdersDto = userOrdersServise
		//		.getUserOrders(userService.getUserDto(loginDto));
		//OrderDto orderDto = userOrdersDto.getOrders().get(1);
		//orderDto.setShop("Bluzenko");
		//orderDto.setAddress("str.Chevchenka");
		//orderDto.setProduction("Borjomi");
		//orderDto.setScope(7);
		//orderDto.setStatus("no done");
		//orderServise.setOrderDto(orderDto, userService.getIdUserByLogin(loginDto));
		// print
		//System.out.println(userOrdersServise
		//		.getUserOrders(userService.getUserDto(loginDto)));
		
		// Create Item
		//LoginDto loginDto = new LoginDto("ivan", "qwerty");
		//OrderDto orderDto = new OrderDto(0L, "Supermatket", "str.Svoboda","Roshen", 100,"no done");
		//orderServise.setOrderDto(orderDto, userService.getIdUserByLogin(loginDto));
		// print
		//System.out.println(userItemsServise
		//		.getUserOrders(userService.getUserDto(loginDto)));
	}

}
