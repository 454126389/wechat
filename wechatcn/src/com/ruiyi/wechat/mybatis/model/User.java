package com.ruiyi.wechat.mybatis.model;

public class User {
	private int user_id;
	private String username;
	private String password;
	private short activate;
	private String email;
	private String phone;
	private String creation_date;
	private String creation_ip;
	private float balance;
	private String simnum;
	private String qq;
	private String id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public short getActivate() {
		return activate;
	}
	public void setActivate(short activate) {
		this.activate = activate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getCreation_ip() {
		return creation_ip;
	}
	public void setCreation_ip(String creation_ip) {
		this.creation_ip = creation_ip;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getSimnum() {
		return simnum;
	}
	public void setSimnum(String simnum) {
		this.simnum = simnum;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", password=" + password + ", activate=" + activate
				+ ", email=" + email + ", phone=" + phone + ", creation_date="
				+ creation_date + ", creation_ip=" + creation_ip + ", balance="
				+ balance + ", simnum=" + simnum + ", qq=" + qq + ", id=" + id
				+ "]";
	}
	
}
