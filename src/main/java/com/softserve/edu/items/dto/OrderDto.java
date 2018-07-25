package com.softserve.edu.items.dto;

public class OrderDto {

	private Long idOrder;
	private String shop;
	private String address;
	private String production;
	private int scope;
	private String status;
	
	
	
	
	public OrderDto(Long idOrder, String shop, String address, String production, int scope, String status) {
		super();
		this.idOrder = idOrder;
		this.shop = shop;
		this.address = address;
		this.production = production;
		this.scope = scope;
		this.status = status;
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "(" + "idOrder=" + idOrder  
				+ " shop=" + shop 
				+ " address=" + address 
				+ " production=" + production 
				+ " scope=" + scope 
				+ " status=" + status 
				+ ")";
	}
	
}
