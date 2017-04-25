package com.ruiyi.wechat.model;

public class Voiture {
	private String id,type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Voiture(String id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	

}
