package com.passwordsaver.dto;

import java.util.List;

public class AdminUsersDto {
	private UserDto adminDto;
	private List<UserDto> userDtos;
	private int pageCount;
	private int pageOffset;
	private int allAdminUsersCount;
	private String substring;
	
	public AdminUsersDto(UserDto adminDto,List<UserDto> userDtos, int pageCount, int pageOffset, int allAdminUsersCount, String substring) {
		this.adminDto = adminDto;
		this.userDtos = userDtos;
		this.pageCount = pageCount;
		this.pageOffset = pageOffset;
		this.allAdminUsersCount = allAdminUsersCount;
		this.substring = substring;
	}
	public String getSubstring() {
		return substring;
	}
	public int getAllAdminUsersCount() {
		return allAdminUsersCount;
	}
	public UserDto getAdminDto() {
		return adminDto;
	}
	public List<UserDto> getUserDtos(){
		return userDtos;
	}
	public int getPageCount() {
		return pageCount;
	}
	public int getPageOffset() {
		return pageOffset;
	}

	public String toString() {
		return "$(admin="+adminDto
				+" pageCount="+pageCount
				+" pageOffSet="+pageOffset
				+" substring="+substring
				+")"
				+"userDtos="+userDtos+"$";
	}	
}
