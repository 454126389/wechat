package com.ruiyi.wechat.model;

public class Batch {
	private String id,time_zome,validity,time,num,language,agent_id,product_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime_zome() {
		return time_zome;
	}

	public void setTime_zome(String timeZome) {
		time_zome = timeZome;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agentId) {
		agent_id = agentId;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String productId) {
		product_id = productId;
	}

	public Batch(String id, String timeZome, String validity, String time,
			String num, String language, String agentId, String productId) {
		super();
		this.id = id;
		time_zome = timeZome;
		this.validity = validity;
		this.time = time;
		this.num = num;
		this.language = language;
		agent_id = agentId;
		product_id = productId;
	}
	

}
