package com.softserve.edu.items.dao;

import java.util.List;


import com.softserve.edu.items.entity.Order;
import com.softserve.edu.items.entity.Order.OrderEntityQueries;

public class OrderDao extends ADaoCRUD<Order> {
	private final static String ID_USER_FIELDNAME = "idUser";

	public OrderDao() {
		super();
		init();
	}

	protected void init() {
		for (OrderEntityQueries orderEntityQueries : OrderEntityQueries.values()) {
			sqlQueries.put(orderEntityQueries.getSqlQuery(), orderEntityQueries);
		}
	}

	protected Order createInstance(String[] args) {
		return new Order(
				Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
				args[3] == null ? new String() : args[3],
				Integer.parseInt(args[4] == null ? "0" : args[4]),
				args[5] == null ? new String() : args[5],
				Long.parseLong(args[6] == null ? "0" : args[6]));
	}

	protected String[] getUpdateFields(Order entity) {//???? переробити через ентіті.гет...  + прінтлін по полях що виводиться
		String[] result = new String[6];//Konstructor  Long id; String shop; String address;String production;int scope;Long idUser;
		String[] allFields = getFields(entity);//"UPDATE orders SET shop = '%s',address = '%s', production = '%s', scope=%s WHERE id = %s;"
		result[0] = allFields[1]; // shop
		result[1] = allFields[2]; // address
		result[2] = allFields[3]; // production
		result[3] = allFields[4];//scope
		result[4] = allFields[5];//status
		result[5] = allFields[0];//id
		return result;
	}

	protected String[] getFields(Order entity) {
		//String[] fields = new String[Item.class.getDeclaredFields().length];
		String[] fields = new String[7];
		fields[0] = entity.getId().toString();
		fields[1] = entity.getShop();
		fields[2] = entity.getAddress();
		fields[3] = entity.getProduction();
		fields[4] = String.valueOf(entity.getScope());
		fields[5] = entity.getStatus();
		fields[6] = entity.getIdUser().toString();
		return fields;
	}
	
	public List<Order> getOrderEntityByIdUser(Long idUser) {
		return getByFieldName(ID_USER_FIELDNAME, idUser.toString());
	}
}
