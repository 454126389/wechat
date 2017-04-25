package com.ruiyi.wechat.mybatis.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ruiyi.wechat.po.Voiture;

public class Parameter {
	private Long id;
	private Voiture voiture;
	private String language;
	private String zhNo;
	private String FNo;
	private String alias;
	private String fence;
	private Date nextDl;
	private Date nextPdc;
	private Short MMileage;
	private Short productId;
	private Boolean smsOpen;
	private Boolean emailOpen;
	private Short fortiry;
	private String sgps;
	private Short rmap;
	private Short trT;
	private Long imsi;
	private Boolean mainDevice;
	private String wechatId;
	private Boolean spru;
	private Boolean newsopen;
	private Boolean wifi;
	private Boolean sosOpen;
	private Boolean choice;
	private Short stzs;
	private Short cgps;
	private String mphn;
	private String fphn;
	private Boolean rest;
//	private Set userParameters = new HashSet(0);
//	private Set serviceParameters = new HashSet(0);
	
	
	
	@Override
	public String toString() {
		return "Parameter [id=" + id + ", voiture=" + voiture + ", language="
				+ language + ", zhNo=" + zhNo + ", FNo=" + FNo + ", alias="
				+ alias + ", fence=" + fence + ", nextDl=" + nextDl
				+ ", nextPdc=" + nextPdc + ", MMileage=" + MMileage
				+ ", productId=" + productId + ", smsOpen=" + smsOpen
				+ ", emailOpen=" + emailOpen + ", fortiry=" + fortiry
				+ ", sgps=" + sgps + ", rmap=" + rmap + ", trT=" + trT
				+ ", imsi=" + imsi + ", mainDevice=" + mainDevice
				+ ", wechatId=" + wechatId + ", spru=" + spru + ", newsopen="
				+ newsopen + ", wifi=" + wifi + ", sosOpen=" + sosOpen
				+ ", choice=" + choice + ", stzs=" + stzs + ", cgps=" + cgps
				+ ", mphn=" + mphn + ", fphn=" + fphn + ", rest=" + rest + "]";
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Voiture getVoiture() {
		return voiture;
	}



	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}



	public String getLanguage() {
		return language;
	}



	public void setLanguage(String language) {
		this.language = language;
	}



	public String getZhNo() {
		return zhNo;
	}



	public void setZhNo(String zhNo) {
		this.zhNo = zhNo;
	}



	public String getFNo() {
		return FNo;
	}



	public void setFNo(String fNo) {
		FNo = fNo;
	}



	public String getAlias() {
		return alias;
	}



	public void setAlias(String alias) {
		this.alias = alias;
	}



	public String getFence() {
		return fence;
	}



	public void setFence(String fence) {
		this.fence = fence;
	}



	public Date getNextDl() {
		return nextDl;
	}



	public void setNextDl(Date nextDl) {
		this.nextDl = nextDl;
	}



	public Date getNextPdc() {
		return nextPdc;
	}



	public void setNextPdc(Date nextPdc) {
		this.nextPdc = nextPdc;
	}



	public Short getMMileage() {
		return MMileage;
	}



	public void setMMileage(Short mMileage) {
		MMileage = mMileage;
	}



	public Short getProductId() {
		return productId;
	}



	public void setProductId(Short productId) {
		this.productId = productId;
	}



	public Boolean getSmsOpen() {
		return smsOpen;
	}



	public void setSmsOpen(Boolean smsOpen) {
		this.smsOpen = smsOpen;
	}



	public Boolean getEmailOpen() {
		return emailOpen;
	}



	public void setEmailOpen(Boolean emailOpen) {
		this.emailOpen = emailOpen;
	}



	public Short getFortiry() {
		return fortiry;
	}



	public void setFortiry(Short fortiry) {
		this.fortiry = fortiry;
	}



	public String getSgps() {
		return sgps;
	}



	public void setSgps(String sgps) {
		this.sgps = sgps;
	}



	public Short getRmap() {
		return rmap;
	}



	public void setRmap(Short rmap) {
		this.rmap = rmap;
	}



	public Short getTrT() {
		return trT;
	}



	public void setTrT(Short trT) {
		this.trT = trT;
	}



	public Long getImsi() {
		return imsi;
	}



	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}



	public Boolean getMainDevice() {
		return mainDevice;
	}



	public void setMainDevice(Boolean mainDevice) {
		this.mainDevice = mainDevice;
	}



	public String getWechatId() {
		return wechatId;
	}



	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}



	public Boolean getSpru() {
		return spru;
	}



	public void setSpru(Boolean spru) {
		this.spru = spru;
	}



	public Boolean getNewsopen() {
		return newsopen;
	}



	public void setNewsopen(Boolean newsopen) {
		this.newsopen = newsopen;
	}



	public Boolean getWifi() {
		return wifi;
	}



	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}



	public Boolean getSosOpen() {
		return sosOpen;
	}



	public void setSosOpen(Boolean sosOpen) {
		this.sosOpen = sosOpen;
	}



	public Boolean getChoice() {
		return choice;
	}



	public void setChoice(Boolean choice) {
		this.choice = choice;
	}



	public Short getStzs() {
		return stzs;
	}



	public void setStzs(Short stzs) {
		this.stzs = stzs;
	}



	public Short getCgps() {
		return cgps;
	}



	public void setCgps(Short cgps) {
		this.cgps = cgps;
	}



	public String getMphn() {
		return mphn;
	}



	public void setMphn(String mphn) {
		this.mphn = mphn;
	}



	public String getFphn() {
		return fphn;
	}



	public void setFphn(String fphn) {
		this.fphn = fphn;
	}



	public Boolean getRest() {
		return rest;
	}



	public void setRest(Boolean rest) {
		this.rest = rest;
	}
	
	
	
}
