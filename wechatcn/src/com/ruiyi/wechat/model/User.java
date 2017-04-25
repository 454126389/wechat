package com.ruiyi.wechat.model;

public class User {
private String username;
private String password;
private String activate;
private String email;
private String phone;
private String creation_date;
private String creation_ip;
private float balance;
private String simnum;
private String qq;
private String id;






public User(String username, String password,String activate,String email,String phone,String creation_date,String creation_ip,float balance,String simnum,String QQ,String id) {
	// TODO Auto-generated constructor stub
	this.username=username;
	this.password=password;
	this.activate=activate;
	this.email=email;
	
	this.phone=phone;
	this.creation_date=creation_date;
	this.creation_ip=creation_ip;
	this.balance=balance;
	this.simnum=simnum;
	this.qq=QQ;
	this.id=id;
}





public User(String username, String password, String creation_date,
		String creation_ip) {
	super();
	this.username = username;
	this.password = password;
	this.creation_date = creation_date;
	this.creation_ip = creation_ip;
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





public String getQQ() {
	return qq;
}





public void setQQ(String qQ) {
	qq = qQ;
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

public String getActivate() {
	return activate;
}

public void setActivate(String activate) {
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

public void setCreation_date(String creationDate) {
	creation_date = creationDate;
}

public String getCreation_ip() {
	return creation_ip;
}

public void setCreation_ip(String creationIp) {
	creation_ip = creationIp;
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

}
