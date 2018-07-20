package com.passwordsaver.controllers;

import com.passwordsaver.services.KeyService;
import com.passwordsaver.services.UserKeysService;
import com.passwordsaver.services.UserService;

public class ServicesContainer {
	
	private static volatile ServicesContainer instance=null;
	
	private UserService userService;
	private KeyService keyService;
	private UserKeysService userKeysServices;
	
	private ServicesContainer(){
		userService = new UserService();
		keyService = new KeyService();
		userKeysServices = new UserKeysService();
	}
	
	static public ServicesContainer getInstance() {
		if(instance==null) {
			synchronized(ServicesContainer.class) {
				if(instance==null) {
					instance = new ServicesContainer();
				}
			}
		}
		return instance;
	}
	public UserService getUserService() {
		return userService;
	}
	public KeyService getKeyService() {
		return keyService;
	}
	public UserKeysService getUserKeysService() {
		return userKeysServices;
	}
}
