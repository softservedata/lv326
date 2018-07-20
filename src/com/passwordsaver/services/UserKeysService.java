package com.passwordsaver.services;

import java.util.ArrayList;
import java.util.List;

import com.passwordsaver.dao.KeyDao;
import com.passwordsaver.dao.UserDao;
import com.passwordsaver.dto.KeyDto;
import com.passwordsaver.dto.UserDto;
import com.passwordsaver.dto.UserKeysDto;
import com.passwordsaver.entity.Key;
import com.passwordsaver.entity.User;

public class UserKeysService {
	private UserDao userDao;
	private KeyDao keyDao;
	
	public UserKeysService() {
		//userDao = new UserDao();
		//keyDao = new KeyDao();
		userDao = DaosContainer.getInstance().getUserDao();
		keyDao = DaosContainer.getInstance().getKeyDao();
	}
	public UserKeysService(UserDao userDao, KeyDao keyDao) {
		this.userDao = userDao;
		this.keyDao = keyDao;
	}
	
	/*public UserKeysDto getUserKeys(UserDto userDto, int count) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		UserKeysDto userKeysDto = new UserKeysDto(userDto.getLogin());
		for(Key key: keyDao.getKeysByUserId(user.getId())) {
			if(userKeysDto.getKeys().size()>=count) {
				break;
			}
			KeyDto keyDto = new KeyDto(key.getId(),
										key.getService(),
										key.getServPassword());
			userKeysDto.addKeyDto(keyDto);
			
		}
		return userKeysDto;
	}*/
	
	
	public UserKeysDto getUserKeys(UserDto userDto, int currentPage, int pageOffset, String substring) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		List<Key> allUserKeys; 
		try {
			allUserKeys = keyDao.getKeysByUserId(user.getId());
		}catch(RuntimeException exc) {
			allUserKeys = new ArrayList<Key>();
		}
		
		if(!substring.equals("")) {
			List<Key> sortedKeys = new ArrayList<Key>();
			for(Key key: allUserKeys) {
				if(key.getService().indexOf(substring)!=-1) {
					sortedKeys.add(key);
				}
			}
			allUserKeys=sortedKeys;
		}
		
		UserKeysDto userKeysDto = new UserKeysDto(user.getLogin());
		userKeysDto.setSubstring(substring);
		userKeysDto.setAllUserKeysCount(allUserKeys.size());
		userKeysDto.setPageOffset(pageOffset);
		int pageCount = (int)Math.ceil( (double)allUserKeys.size() / (double)pageOffset);
		if(pageCount<1) {
			pageCount=1;
		}
		if(currentPage>pageCount) {
			currentPage=pageCount;
		}
		userKeysDto.setPageCount(pageCount);
		
		int fromKey = (currentPage-1)*pageOffset;
		int toKey = fromKey+pageOffset; 
		for(int i = fromKey; i<toKey ; i++) {
			if( i > (allUserKeys.size()-1) ){
				return userKeysDto;
			}
			KeyDto keyDto = new KeyDto(allUserKeys.get(i).getId(),
										allUserKeys.get(i).getService(),
										allUserKeys.get(i).getServPassword());
			userKeysDto.addKeyDto(keyDto);
		}
		
		return userKeysDto;
	}
	
	
	
	/*public UserKeysDto getUserKeys(UserDto userDto, int currentPage, int pageOffset) {
		User user = userDao.getUserEntityByLogin(userDto.getLogin());
		List<Key> allUserKeys; 
		try {
			allUserKeys = keyDao.getKeysByUserId(user.getId());
		}catch(RuntimeException exc) {
			allUserKeys = new ArrayList<Key>();
		}
		UserKeysDto userKeysDto = new UserKeysDto(user.getLogin());
		userKeysDto.setAllUserKeysCount(allUserKeys.size());
		userKeysDto.setPageOffset(pageOffset);
		int pageCount = (int)Math.ceil( (double)allUserKeys.size() / (double)pageOffset);
	
		userKeysDto.setPageCount(pageCount);
		
		int fromKey = (currentPage-1)*pageOffset;
		int toKey = fromKey+pageOffset; 
		for(int i = fromKey; i<toKey ; i++) {
			if( i > (allUserKeys.size()-1) ){
				return userKeysDto;
			}
			KeyDto keyDto = new KeyDto(allUserKeys.get(i).getId(),
										allUserKeys.get(i).getService(),
										allUserKeys.get(i).getServPassword());
			userKeysDto.addKeyDto(keyDto);
		}
		
		return userKeysDto;
	}*/
}
