package com.passwordsaver.services;

import com.passwordsaver.dao.KeyDao;
import com.passwordsaver.dao.UserDao;
import com.passwordsaver.dto.KeyDto;
import com.passwordsaver.entity.Key;

public class KeyService {
	private KeyDao keyDao;
	private UserDao userDao;
	
	/*public KeyService() {
		this.keyDao = new KeyDao();
		this.userDao = new UserDao();
	}*/
	public KeyService() {
		keyDao = DaosContainer.getInstance().getKeyDao();
		userDao = DaosContainer.getInstance().getUserDao();
	}
	public KeyService(KeyDao keyDao, UserDao userDao) {
		this.keyDao = keyDao;
		this.userDao = userDao;
	}
	
	public boolean isExistKey(Long id) {
		boolean result = false;
		try {
			keyDao.getById(id);
			result = true;
		}catch(RuntimeException ex) {
			result = false;
		}
		return result;
	}
	
	public boolean setKeyDto(KeyDto keyDto, Long idUser) {
		boolean result = false;
		Key key = new Key(keyDto.getIdKey(),
							keyDto.getService(),
							keyDto.getServPassword(),
							idUser);
		try{
			if(isExistKey(keyDto.getIdKey())) {
				userDao.getById(idUser);
				keyDao.updateByEntity(key);
				result = true;
			}else {
				userDao.getById(idUser);
				keyDao.insert(key);
				result = true;
			}
		}catch(RuntimeException exc) {
			result = false;
		}
		return result;
	}
	
	public KeyDto getKeyDto(Long id) {
		Key key = keyDao.getById(id);
		return new KeyDto(key.getId(),
							key.getService(),
							key.getServPassword());
	}
	public boolean deleteKeyByDto(KeyDto keyDto) {
		return keyDao.deleteById(keyDto.getIdKey());
	}
	public Long getIdUserByIdKey(Long idKey) {
		return keyDao.getById(idKey).getIdUser();
	}
}
