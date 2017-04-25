package com.ruiyi.wechat.model;

public class CarInfo {
	private String id;
	private String alias;
	private String fence;
	private Boolean online;
	private String choice;
	
	
	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public String getFence() {
		return fence;
	}

	public void setFence(String fence) {
		this.fence = fence;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public CarInfo(String id, String alias) {
		// TODO Auto-generated constructor stub
		this.setAlias(alias);
		this.setId(id);
	}
	public CarInfo(String id, String alias,String fence) {
		// TODO Auto-generated constructor stub
		this.setAlias(alias);
		this.setId(id);
		this.setFence(fence);
	}
	
	public CarInfo(String id, String choice,String alias,String fence) {
		// TODO Auto-generated constructor stub
		this.setAlias(alias);
		this.setChoice(choice);
		this.setId(id);
		this.setFence(fence);
	}

}
