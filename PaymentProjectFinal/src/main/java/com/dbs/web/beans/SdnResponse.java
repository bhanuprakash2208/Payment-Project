package com.dbs.web.beans;

public class SdnResponse {

	private String name;
	private boolean found;
	
	public SdnResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public SdnResponse(String name, boolean found) {
		super();
		this.name = name;
		this.found = found;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	@Override
	public String toString() {
		return "SdnResponse [name=" + name + ", found=" + found + "]";
	}
	
	

	

	
	
}
