package com.ruiyi.wechat.model;

public class Mileage {
	String time;
	String miles;
	public Mileage(String time, String miles) {
		super();
		this.time = time;
		this.miles = miles;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMiles() {
		return miles;
	}
	public void setMiles(String miles) {
		this.miles = miles;
	}
	
	
}
