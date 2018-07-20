package com.passwordsaver.dao;

import java.util.ArrayList;
import java.util.List;

import com.passwordsaver.entity.Key;
import com.passwordsaver.entity.Key.KeyEntityQueries;

public class KeyDao extends DaoCRUD<Key> {
	private final static String ID_USER_FIELD = "idUser";
	public KeyDao() {
		super();
	}
	@Override
	protected List<String> getFields(Key key) {
		List<String> fields = new ArrayList<String>();
		fields.add(key.getId().toString());
		fields.add(key.getService());
		fields.add(key.getServPassword());
		fields.add(key.getIdUser().toString());
		return fields;
	}
	@Override
	protected List<String> getUpdateFields(Key entity) {
		List<String> fields = new ArrayList<String>();
		fields.add(0, entity.getService());
		fields.add(1,entity.getServPassword());
		fields.add(2, entity.getIdUser().toString());
		fields.add(3, entity.getId().toString());
		return fields;
	}
	@Override
	protected Key createInstance(List<String> args) {
		return new Key(
				Long.parseLong(args.get(0)),
				args.get(1),
				args.get(2),
				Long.parseLong(args.get(3))
				);
	}
	
	@Override
	protected void init() {
		for(KeyEntityQueries keyEntityQuery: KeyEntityQueries.values()) {
			sqlQueries.put(keyEntityQuery.getSqlQuery(), keyEntityQuery);
		}
	}
	public List<Key> getKeysByUserId(Long idUser) {
		return this.getByFieldName(KeyDao.ID_USER_FIELD, idUser.toString());
	}
	
}
