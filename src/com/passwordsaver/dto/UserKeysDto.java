package com.passwordsaver.dto;

import java.util.ArrayList;
import java.util.List;

public class UserKeysDto {
	private final int DEFAULT_PAGE_OFFSET = 10;
	private String userLogin;
	private List<KeyDto> keys;
	private int pageCount=1;
	private int pageOffset;
	private int allUserKeysCount;
	private String substring="";
	
	public UserKeysDto(String userLogin) {
		this.userLogin = userLogin;
		keys = new ArrayList<KeyDto>();
		this.pageOffset = DEFAULT_PAGE_OFFSET;
	}
	
	
	/*public UserKeysDto(String userLogin, List<KeyDto> keys) {
		this.userLogin = userLogin;
		this.keys = keys;
		pageOffset = DEFAULT_PAGE_OFFSET;
		if(keys.size()!=0) {
			pageCount =(int)Math.ceil((double)keys.size()/(double)pageOffset);
		}
	}*/
	
	public void setSubstring(String substring) {
		this.substring= substring;
	}
	public String getSubstring() {
		return substring;
	}
	public void setAllUserKeysCount(int allUserKeysCount) {
		this.allUserKeysCount = allUserKeysCount;
	}
	public void setUserLogin(String login) {
		this.userLogin = login;
	}
	public void setKeys(List<KeyDto> keys) {
		this.keys = keys;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}
	
	public String getUserLogin() {
		return userLogin;
	}
	public List<KeyDto> getKeys(){
		return keys;
	}
	public int getPageCount() {
		return pageCount;
	}
	public int getPageOffset() {
		return pageOffset;
	}
	public int getAllUserKeysCount() {
		return allUserKeysCount;
	}
	public void addKeyDto(KeyDto key) {
		keys.add(key);
	}
	
	
	@Override
	public String toString() {
		return "("
				+" userLogin="+userLogin
				+" keys="+keys
				+" pageCount="+pageCount
				+" pageOffset="+pageOffset
				+" substring="+substring
				+" )";
	}
}
