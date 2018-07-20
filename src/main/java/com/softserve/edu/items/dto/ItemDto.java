package com.softserve.edu.items.dto;

public class ItemDto {

	private Long idItem;
	private String name;
	private String description;
	
	public ItemDto(Long idItem, String name, String description) {
		this.idItem = idItem;
		this.name = name;
		this.description = description;
	}

	// setters

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// getters
	
	public long getIdItem() {
		return idItem;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "(" + "idItem=" + idItem  
				+ " name=" + name 
				+ " description=" + description 
				+ ")";
	}

}
