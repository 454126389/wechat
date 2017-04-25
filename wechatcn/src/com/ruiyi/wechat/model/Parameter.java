package com.ruiyi.wechat.model;

public class Parameter {
private String id;
private String Languge;
private String zh_no;
private String alias;
private String product_id;
private String voiture_id;//1
private String sms_open;  //0
private String email_open;  //0
private String fortiry;  //0
private String sgps;  //1,30,1800
private String rmap;  //50
private String tr_t;  //30

private String wechat_id;  //30
private String spru;  //0
private boolean sos_open;

private String imei;
private String registration_id;

public String getRegistration_id() {
	return registration_id;
}




public void setRegistration_id(String registration_id) {
	this.registration_id = registration_id;
}




public String getImei() {
	return imei;
}




public void setImei(String imei) {
	this.imei = imei;
}




public Parameter(String id, String languge, String zhNo, String alias,
		String productId, String voitureId, String smsOpen, String emailOpen,
		String fortiry, String sgps, String rmap, String trT,String wechat_id, String spru) {
	super();
	this.id = id;
	Languge = languge;
	zh_no = zhNo;
	this.alias = alias;
	product_id = productId;
	voiture_id = voitureId;
	sms_open = smsOpen;
	email_open = emailOpen;
	this.fortiry = fortiry;
	this.sgps = sgps;
	this.rmap = rmap;
	tr_t = trT;
	this.wechat_id=wechat_id;
	this.spru = spru;
}




public Parameter(String id, String product_id, String wechat_id) {
	super();
	this.id = id;
	this.product_id = product_id;
	this.wechat_id = wechat_id;
}

public Parameter(String id, String product_id,String registration_id, String imei) {
	super();
	this.id = id;
	this.product_id=product_id;
	this.registration_id = registration_id;
	this.imei = imei;
}



public String getWechat_id() {
	return wechat_id;
}

public void setWechat_id(String wechatId) {
	wechat_id = wechatId;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLanguge() {
	return Languge;
}
public void setLanguge(String languge) {
	Languge = languge;
}
public String getZh_no() {
	return zh_no;
}
public void setZh_no(String zhNo) {
	zh_no = zhNo;
}
public String getAlias() {
	return alias;
}
public void setAlias(String alias) {
	this.alias = alias;
}
public String getProduct_id() {
	return product_id;
}
public void setProduct_id(String productId) {
	product_id = productId;
}
public String getVoiture_id() {
	return voiture_id;
}
public void setVoiture_id(String voitureId) {
	voiture_id = voitureId;
}
public String getSms_open() {
	return sms_open;
}
public void setSms_open(String smsOpen) {
	sms_open = smsOpen;
}
public String getEmail_open() {
	return email_open;
}
public void setEmail_open(String emailOpen) {
	email_open = emailOpen;
}
public String getFortiry() {
	return fortiry;
}
public void setFortiry(String fortiry) {
	this.fortiry = fortiry;
}
public String getSgps() {
	return sgps;
}
public void setSgps(String sgps) {
	this.sgps = sgps;
}
public String getRmap() {
	return rmap;
}
public void setRmap(String rmap) {
	this.rmap = rmap;
}
public String getTr_t() {
	return tr_t;
}
public void setTr_t(String trT) {
	tr_t = trT;
}
public String getSpru() {
	return spru;
}
public void setSpru(String spru) {
	this.spru = spru;
}

public boolean isSos_open() {
	return sos_open;
}

public void setSos_open(boolean sosOpen) {
	sos_open = sosOpen;
}




}
