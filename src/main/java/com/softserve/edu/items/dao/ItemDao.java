package com.softserve.edu.items.dao;

import java.util.List;

import com.softserve.edu.items.entity.Item;
import com.softserve.edu.items.entity.Item.ItemEntityQueries;

public final class ItemDao extends ADaoCRUD<Item> {
	private final static String ID_USER_FIELDNAME = "idUser";

	public ItemDao() {
		super();
		init();
	}

	// TODO Create abstract method in ADao
	protected void init() {
		for (ItemEntityQueries itemEntityQueries : ItemEntityQueries.values()) {
			sqlQueries.put(itemEntityQueries.getSqlQuery(), itemEntityQueries);
		}
	}

	protected Item createInstance(String[] args) {
		return new Item(
				Long.parseLong(args[0] == null ? "0" : args[0]),
				args[1] == null ? new String() : args[1],
				args[2] == null ? new String() : args[2],
				Long.parseLong(args[3] == null ? "0" : args[3]));
	}

	protected String[] getUpdateFields(Item entity) {
		String[] result = new String[4];
		String[] allFields = getFields(entity);
		result[0] = allFields[1]; // name
		result[1] = allFields[2]; // descriptions
		result[2] = allFields[0]; // id
		return result;
	}

	protected String[] getFields(Item entity) {
		//String[] fields = new String[Item.class.getDeclaredFields().length];
		String[] fields = new String[4];
		fields[0] = entity.getId().toString();
		fields[1] = entity.getName();
		fields[2] = entity.getDescription();
		fields[3] = entity.getIdUser().toString();
		return fields;
	}

	public List<Item> getItemEntityByIdUser(Long idUser) {
		return getByFieldName(ID_USER_FIELDNAME, idUser.toString());
	}

}
