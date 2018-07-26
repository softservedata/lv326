package com.softserve.edu;

public class Honey {
    private Long id;
    private String name;
    private String taste;
    
	public Honey() {
	}
	
	public Honey(Long id, String name, String taste) {
		this.id = id;
		this.name = name;
		this.taste = taste;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaste() {
		return taste;
	}

	public void setTaste(String taste) {
		this.taste = taste;
	}

}
