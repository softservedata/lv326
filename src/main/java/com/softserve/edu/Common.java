package com.softserve.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.jws.WebService;

public class Common {
	private final String USER_NOT_FOUND = "ERROR, user not found";
	private final String USER_LOCKED = "ERROR, user locked";
	private final Long COOL_DOWN_TIME = 3 * 60 * 1000L; // 3 minutes
	private final Long TOKEN_LIFE_TIME = 5 * 60 * 1000L; // 5 minutes

	private Map<String, String> tokens;
	private Map<String, String> users;
	private Map<String, HashMap<Integer,String>> items;
	private Map<String, List<Long>> failedLogins;
	private Map<String, Long> tokensLife;
	private List<String> admins;
	private Long coolDownTime;
	private Long tokenLifeTime;

	public Common() {
		initComponents();
		initUsers();
		initUserItems();
		initAdmins();
	}

	private void initComponents() {
		tokens = new HashMap<String, String>();
		users = new HashMap<String, String>();
		items = new HashMap<String, HashMap<Integer, String>>();
		failedLogins = new HashMap<String, List<Long>>();
		tokensLife = new HashMap<String, Long>();
		admins = new ArrayList<String>();
		coolDownTime = COOL_DOWN_TIME;
		tokenLifeTime = TOKEN_LIFE_TIME;
	}
	
	private void initUsers() {
		users.put("admin", "qwerty");
		users.put("akimatc", "qwerty");
		users.put("khalaktc", "qwerty");
		users.put("kilinatc", "qwerty");
		users.put("OKonokhtc", "qwerty");
		users.put("otlumtc", "qwerty");
		users.put("slototc", "qwerty");
		users.put("vbudktc", "qwerty");
		users.put("vvasylystc", "qwerty");
	}

	private void initAdmins() {
		admins.add("admin");
	}

	private void initUserItems() {
		for (String currentName : users.keySet()) {
			items.put(currentName, new HashMap<Integer,String>());
		}
	}

	private String generateToken() {
		String result = "";
		int nextRandom;
		Random rand = new Random();
		for (int i = 0; i < 32; i++) {
			nextRandom = rand.nextInt(36);
			if (nextRandom < 10) {
				result = result + String.valueOf(nextRandom);
			} else {
				result = result + (char)(nextRandom + 55);
			}
		}
		return result;
	}

	private boolean isLocked(String name) {
		boolean result = false;
		if ((failedLogins.containsKey(name))
				&& (failedLogins.get(name) != null)) {
			int loginCounts = failedLogins.get(name).size();
			if (loginCounts > 2) {
				result = failedLogins.get(name).get(loginCounts-1)
							- failedLogins.get(name).get(loginCounts-3) < COOL_DOWN_TIME;
				result = result
							&& System.currentTimeMillis() - failedLogins.get(name).get(loginCounts-1) < coolDownTime; 
			}
		}
		return result;
	}

	private boolean isTokenAlive(String token) {
		boolean result = tokensLife.containsKey(token)
				&& tokensLife.get(token) != null;
		if (result 
				&& (System.currentTimeMillis() - tokensLife.get(token) > tokenLifeTime)) {
			tokensLife.remove(token);
			result = false;
		}
		return result;
	}

	private boolean isLogged(String token) {
		return isTokenAlive(token) && getUserName(token).length() > 0;
	}

	private boolean isAdmin(String name) {
		boolean result = false;
		for (String currentName : admins) {
			if (currentName.equals(name)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean resetServiceToInitialState() {
		initComponents();
		initUsers();
		initUserItems();
		initAdmins();
		
		return true;
	}

	public String login(String name, String password) {
		String result = USER_NOT_FOUND;
		if (isLocked(name)) {
			result = USER_LOCKED;
		}
		else {
			boolean isLoggedIn = false;
			for (String currentName : users.keySet()) {
				if ((currentName.equals(name))
						&& (users.get(currentName).equals(password))) {
					isLoggedIn = true;
					break;
				}
			}
			if (isLoggedIn) {
				result = generateToken();
				tokens.put(name, result);
				tokensLife.put(result, System.currentTimeMillis());
				if ((failedLogins.containsKey(name)) 
						&& (failedLogins.get(name) != null)) {
					failedLogins.get(name).clear();
				}
			} else {
				if (failedLogins.get(name) == null) {
					failedLogins.put(name, new ArrayList<Long>());
				}
				failedLogins.get(name).add(System.currentTimeMillis());
			}
		}
		return result;
	}
	
	public boolean logout(String name, String token) {
		boolean result = false;
		for (String currentName : tokens.keySet()) {
			if ((currentName.equals(name))
					&& (tokens.get(currentName).equals(token))) {
				tokensLife.remove(tokens.get(currentName));
				tokens.remove(currentName);
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean changePassword(String token, String oldPassword, String newPassword) {
		String userName = getUserName(token);
		boolean result = isLogged(token) && users.get(userName).equals(oldPassword);
		if (result) {
			users.replace(userName, newPassword);
		}
		return result;
	}
	
	public String getUserName(String token) {
		String userName = "";
		boolean result = isTokenAlive(token);
		if(result) 
		{
			for (String currentName : tokens.keySet()) {
				if (tokens.get(currentName).equals(token)) {
					userName = currentName;
					break;
				}
			}
		}
		return userName;
	}

	public Long getCoolDownTime() {
		return coolDownTime;
	}

	public Long getTokenLifeTime() {
		return tokenLifeTime;
	}

	public boolean setCoolDownTime(String adminToken, Long newCoolDownTime) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		if (result) {
			coolDownTime = newCoolDownTime;
		}
		return result;
	}

	public boolean setTokenLifeTime(String adminToken, Long newTokenLifeTime) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		//System.out.println("setTokenLifeTime() result= " + result);
		if (result) {
			tokenLifeTime = newTokenLifeTime;
		}
		return result;
	}

	public boolean createUser(String adminToken, String newName, String newPassword, boolean adminRights) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		if (result) {
			users.put(newName, newPassword);
			if (adminRights) {
				admins.add(newName);
			}
		}
		return result;
	}
	
	public boolean removeUser(String adminToken, String removedName) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		if (result) {
			users.remove(removedName);
		}
		return result;
	}

	public String getAllAdmins(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentAdminName : admins) {
				result = result
						+ String.valueOf(index)
						+ " \t" + currentAdminName + "\n";
				index++;
			}
		}
		return result;
	}

	public String getLoginedAdmins(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentUserName : tokens.keySet()) {
				if (isTokenAlive(tokens.get(currentUserName))
						&& isAdmin(currentUserName)) {
					result = result
							+ String.valueOf(index)
							+ " \t" + currentUserName + "\n";
					index++;
				}
			}
		}
		return result;
	}

	public String getLockedAdmins(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentUserName : users.keySet()) {
				if (isLocked(currentUserName)
						&& isAdmin(currentUserName)) {
					result = result
							+ String.valueOf(index)
							+ " \t" + currentUserName + "\n";
					index++;
				}
			}
		}
		return result;
	}

	public String getAllUsers(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentUserName : users.keySet()) {
				result = result
						+ String.valueOf(index)
						+ " \t" + currentUserName + "\n";
				index++;
			}
		}
		return result;
	}
	
	public String getLoginedUsers(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentUserName : tokens.keySet()) {
				if (isTokenAlive(tokens.get(currentUserName))) {
					result = result
							+ String.valueOf(index)
							+ " \t" + currentUserName + "\n";
					index++;
				}
			}
		}
		return result;
	}

	public String getAliveTockens(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentUserName : tokens.keySet()) {
				if (isTokenAlive(tokens.get(currentUserName))) {
					result = result
							+ String.valueOf(index)
							+ " \t" + tokens.get(currentUserName) + "\n";
					index++;
				}
			}
		}
		return result;
	}

	public String getLockedUsers(String adminToken) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		int index = 0;
		if (isValidAdmin) {
			for (String currentUserName : users.keySet()) {
				if (isLocked(currentUserName)) {
					result = result
							+ String.valueOf(index)
							+ " \t" + currentUserName + "\n";
					index++;
				}
			}
		}
		return result;
	}
	
	public boolean lockUser(String adminToken, String name) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		if (result) {
			if (failedLogins.get(name) == null) {
				failedLogins.put(name, new ArrayList<Long>());
			}
			Long currentTime = System.currentTimeMillis();
			failedLogins.get(name).add(currentTime-3);
			failedLogins.get(name).add(currentTime-2);
			failedLogins.get(name).add(currentTime-1);
		}
		return result;
	}

	public boolean unlockUser(String adminToken, String name) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		if (result && isLocked(name)) {
			failedLogins.get(name).clear();
		}
		return result;
	}

	public boolean unlockAllUsers(String adminToken) {
		String userName = getUserName(adminToken);
		boolean result = isLogged(adminToken) && isAdmin(userName);
		if (result) {
			for (String currentUserName : failedLogins.keySet()) {
				failedLogins.get(currentUserName).clear();
			}
		}
		return result;
	}

	public String getUserItems(String adminToken, String name) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		if (isValidAdmin) {
			for (int itemKey : items.get(name).keySet()) {
				result = result
						+ String.valueOf(itemKey)
						+ " \t" + items.get(name).get(itemKey) + "\n";
			}
		}
		return result;
	}
	
	public String getUserItem(String adminToken, String name, int index) {
		String result = "";
		String userName = getUserName(adminToken);
		boolean isValidAdmin = isLogged(adminToken) && isAdmin(userName);
		if (isValidAdmin) {
			return items.get(adminToken).get(index);
		}
		return result;
	}
	
	public boolean addItem(String token, String item, int index) {
		String userName = getUserName(token);
		boolean result = isLogged(token);
		if (result) {
			items.get(userName).put(index, item);
		}
		return result;
	}

	public boolean deleteItem(String token, int index) {
		String userName = getUserName(token);
		boolean result = isLogged(token);
		//result = result && (index < items.get(userName).size()); // for List
		result = result && (items.get(userName).get(index) != null);
		if (result) {
			items.get(userName).remove(index);
		}
		return result;
	}

	public boolean updateItem(String token, int index, String item) {
		String userName = getUserName(token);
		boolean result = isLogged(token) && deleteItem(token, index);
		if (result) {
			if (items.get(userName) == null) {
				items.put(userName, new HashMap<Integer,String>());
			}
			items.get(userName).put(Integer.valueOf(index), item);
		}
		return result;
	}
	
	public String getAllItems(String token) {
		String userName = getUserName(token);
		String result = "";
		if (isLogged(token)) {
			for (int itemIndex : items.get(userName).keySet()) {
				result = result
						+ String.valueOf(itemIndex)
						+ " \t" + items.get(userName).get(itemIndex) + "\n";
			}
		}
		return result;
	}
	
	public String getAllItemsIndexes(String token) {
		String userName = getUserName(token);
		String result = "";
		if (isLogged(token)) {
			for (int itemIndex : items.get(userName).keySet()) {
				result = result
						+ String.valueOf(itemIndex)
						+ " ";
			}
		}
		return result;
	}
	
	public String getItem(String token, int index) {
		String result = "";
		String userName = getUserName(token);
		if (isLogged(token)) {
			result = items.get(userName).get(index);
		}
		return result;
	}

}