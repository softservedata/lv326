package com.softserve.edu;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	private final String defaultToken = "0123456789ABCDEF0123456789ABCDEF";
	private final String defaultItem = "empty";
	private final String defaultNum = "1000";
	private final String defaultBool = "false";
	private Common common;
	
	public CommonController() {
		//defaultToken = "0123456789ABCDEF0123456789ABCDEF";
		common = new Common();
	}

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResultList index() {
		return new ResultList()
			.addText("URL=/reset, method=GET resetServiceToInitialState")
			.addText("URL=/login, method=POST login, PARAMETERS= name, password")
			.addText("URL=/logout, method=POST logout, PARAMETERS= name, token")
			.addText("URL=/user, method=PUT changePassword, PARAMETERS= token, oldPassword, newPassword")
			.addText("URL=/user, method=GET getUserName, PARAMETERS= token")
			.addText("URL=/cooldowntime, method=GET getCoolDownTime")
			.addText("URL=/tokenlifetime, method=GET getTokenLifeTime")
			.addText("URL=/cooldowntime, method=PUT setCoolDownTime, PARAMETERS= adminToken, newCoolDownTime")
			.addText("URL=/tokenlifetime, method=PUT setTokenLifeTime, PARAMETERS= adminToken, newTokenLifeTime")
			.addText("URL=/user, method=POST createUser, PARAMETERS= adminToken, newName, newPassword, adminRights")
			.addText("URL=/user, method=DELETE removeUser, PARAMETERS= adminToken, removedName")
			.addText("URL=/admins, method=GET getAllAdmins, PARAMETERS= adminToken")
			.addText("URL=/login/admins, method=GET getLoginedAdmins, PARAMETERS= adminToken")
			.addText("URL=/locked/admins, method=GET getLockedAdmins, PARAMETERS= adminToken")
			.addText("URL=/users, method=GET getAllUsers, PARAMETERS= adminToken")
			.addText("URL=/login/users, method=GET getLoginedUsers, PARAMETERS= adminToken")
			.addText("URL=/login/tockens, method=GET getAliveTockens, PARAMETERS= adminToken")
			.addText("URL=/locked/users, method=GET getLockedUsers, PARAMETERS= adminToken")
			.addText("URL=/locked/user/{name}, method=POST lockUser, PARAMETERS= adminToken, name")
			.addText("URL=/locked/user/{name}, method=PUT unlockUser, PARAMETERS= adminToken, name")
			.addText("URL=/locked/reset, method=PUT unlockAllUsers, PARAMETERS= adminToken")
			.addText("URL=/item/user/{name}, method=GET getUserItems, PARAMETERS= adminToken, name")
			.addText("URL=/item/{index}/user/{name}, method=GET getUserItem, PARAMETERS= adminToken, name, index")
			.addText("URL=/item/{index}, method=POST addItem, PARAMETERS= token, item, index")
			.addText("URL=/item/{index}, method=DELETE deleteItem, PARAMETERS= token, index")
			.addText("URL=/item/{index}, method=PUT updateItem, PARAMETERS= token, index, item")
			.addText("URL=/items, method=GET getAllItems, PARAMETERS= token")
			.addText("URL=/itemindexes, method=GET getAllItemsIndexes, PARAMETERS= token")
			.addText("URL=/item/{index}, method=GET getItem, PARAMETERS= token, index");
	}

	@RequestMapping(value="/reset", method=RequestMethod.GET)
	public ResultBool resetServiceToInitialState() {
		return new ResultBool(common.resetServiceToInitialState());
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResultText login(
			@RequestParam(value = "name", required = false, defaultValue = defaultItem)
			String name,
			@RequestParam(value = "password", required = false, defaultValue = defaultItem)
			String password) {
		return new ResultText(common.login(name, password));
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public ResultBool logout(
			@RequestParam(value = "name", required = false, defaultValue = defaultItem)
			String name,
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token) {
		return new ResultBool(common.logout(name, token));
	}
	
	@RequestMapping(value="/user", method=RequestMethod.PUT)
	public ResultBool changePassword(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token,
			@RequestParam(value = "oldpassword", required = false, defaultValue = defaultItem)
			String oldPassword,
			@RequestParam(value = "newpassword", required = false, defaultValue = defaultItem)
			String newPassword) {
		return new ResultBool(common.changePassword(token, oldPassword, newPassword));
	}
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public ResultText getUserName(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token) {
		return new ResultText(common.getUserName(token));
	}

	@RequestMapping(value="/cooldowntime", method=RequestMethod.GET)
	public ResultNum getCoolDownTime() {
		return new ResultNum(common.getCoolDownTime());
	}

	@RequestMapping(value="/tokenlifetime", method=RequestMethod.GET)
	public ResultNum getTokenLifeTime() {
		return new ResultNum(common.getTokenLifeTime());
	}

	@RequestMapping(value="/cooldowntime", method=RequestMethod.PUT)
	public ResultBool setCoolDownTime(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@RequestParam(value = "time", required = false, defaultValue = defaultNum)
			Long newCoolDownTime) {
		return new ResultBool(common.setCoolDownTime(adminToken, newCoolDownTime));
	}

	@RequestMapping(value="/tokenlifetime", method=RequestMethod.PUT)
	public ResultBool setTokenLifeTime(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@RequestParam(value = "time", required = false, defaultValue = defaultNum)
			Long newTokenLifeTime) {
		return new ResultBool(common.setTokenLifeTime(adminToken, newTokenLifeTime));
	}

	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResultBool createUser(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@RequestParam(value = "name", required = false, defaultValue = defaultItem)
			String newName,
			@RequestParam(value = "password", required = false, defaultValue = defaultItem)
			String newPassword,
			@RequestParam(value = "rights", required = false, defaultValue = defaultBool)
			boolean adminRights) {
		return new ResultBool(common.createUser(adminToken,
				newName, newPassword, adminRights));
	}
	
	@RequestMapping(value="/user", method=RequestMethod.DELETE)
	public ResultBool removeUser(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@RequestParam(value = "name", required = false, defaultValue = defaultItem)
			String removedName) {
		return new ResultBool(common.removeUser(adminToken, removedName));
	}

	@RequestMapping(value="/admins", method=RequestMethod.GET)
	public ResultText getAllAdmins(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getAllAdmins(adminToken));
	}

	@RequestMapping(value="/login/admins", method=RequestMethod.GET)
	public ResultText getLoginedAdmins(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getLoginedAdmins(adminToken));
	}

	@RequestMapping(value="/locked/admins", method=RequestMethod.GET)
	public ResultText getLockedAdmins(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getLockedAdmins(adminToken));
	}

	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResultText getAllUsers(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getAllUsers(adminToken));
	}
	
	@RequestMapping(value="/login/users", method=RequestMethod.GET)
	public ResultText getLoginedUsers(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getLoginedUsers(adminToken));
	}

	@RequestMapping(value="/login/tockens", method=RequestMethod.GET)
	public ResultText getAliveTockens(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getAliveTockens(adminToken));
	}

	@RequestMapping(value="/locked/users", method=RequestMethod.GET)
	public ResultText getLockedUsers(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultText(common.getLockedUsers(adminToken));
	}
	
	@RequestMapping(value="/locked/user/{name}", method=RequestMethod.POST)
	public ResultBool lockUser(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@PathVariable("name")
			String name) {
		return new ResultBool(common.lockUser(adminToken, name));
	}

	@RequestMapping(value="/locked/user/{name}", method=RequestMethod.PUT)
	public ResultBool unlockUser(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@PathVariable("name")
			String name) {
		return new ResultBool(common.unlockUser(adminToken, name));
	}

	@RequestMapping(value="/locked/reset", method=RequestMethod.PUT)
	public ResultBool unlockAllUsers(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken) {
		return new ResultBool(common.unlockAllUsers(adminToken));
	}

	@RequestMapping(value="/item/user/{name}", method=RequestMethod.GET)
	public ResultText getUserItems(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@PathVariable("name")
			String name) {
		return new ResultText(common.getUserItems(adminToken, name));
	}
	
	@RequestMapping(value="/item/{index}/user/{name}", method=RequestMethod.GET)
	public ResultText getUserItem(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String adminToken,
			@PathVariable("name")
			String name,
			@PathVariable("index")
			int index) {
		return new ResultText(common.getUserItem(adminToken, name, index));
	}

	@RequestMapping(value="/item/{index}", method=RequestMethod.POST)
	public ResultBool addItem(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token,
			@RequestParam(value = "item", required = false, defaultValue = defaultItem)
			String item,
			@PathVariable("index") 
			int index) {
		// TODO
		return new ResultBool(common.addItem(token, item, index));
	}

	@RequestMapping(value="/item/{index}", method=RequestMethod.DELETE)
	public ResultBool deleteItem(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token,
			@PathVariable("index") 
			int index) {
		// TODO
		return new ResultBool(common.deleteItem(token, index));
	}

	@RequestMapping(value="/item/{index}", method=RequestMethod.PUT)
	public ResultBool updateItem(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token,
			@PathVariable("index") 
			int index,
			@RequestParam(value = "item", required = false, defaultValue = defaultItem)
			String item) {
		// TODO
		return new ResultBool(common.updateItem(token, index, item));
	}
	
	@RequestMapping(value="/items", method=RequestMethod.GET)
	public ResultText getAllItems(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token) {
		// TODO
		return new ResultText(common.getAllItems(token));
	}
	
	@RequestMapping(value="/itemindexes", method=RequestMethod.GET)
	public ResultText getAllItemsIndexes(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token) {
		// TODO
		return new ResultText(common.getAllItemsIndexes(token));
	}
	
	@RequestMapping(value="/item/{index}", method=RequestMethod.GET)
	public ResultText getItem(
			@RequestParam(value = "token", required = false, defaultValue = defaultToken)
			String token,
			@PathVariable("index") 
			int index) {
		// TODO
		return new ResultText(common.getItem(token, index));
	}

}
