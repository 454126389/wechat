package com.ruiyi.wechat.model;

public class CarIdWeid {
	String id;
	String weid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWeid() {
		return weid;
	}
	public void setWeid(String weid) {
		this.weid = weid;
	}
	public CarIdWeid(String id, String weid) {
		super();
		this.id = id;
		this.weid = weid;
	}
	

}
