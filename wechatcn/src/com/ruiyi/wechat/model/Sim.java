package com.ruiyi.wechat.model;

public class Sim {
	private String phone;
	private String bsim_id;
	private String mdate;
	private String flow;
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBsim_id() {
		return bsim_id;
	}

	public void setBsim_id(String bsim_id) {
		this.bsim_id = bsim_id;
	}

	public String getMdate() {
		return mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	public Sim(String phone, String bsim_id, String mdate) {
		super();
		this.phone = phone;
		this.bsim_id = bsim_id;
		this.mdate = mdate;
	}
	public Sim(String phone, String bsim_id, String mdate,String flow) {
		super();
		this.phone = phone;
		this.bsim_id = bsim_id;
		this.mdate = mdate;
		this.flow = flow;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}


}
