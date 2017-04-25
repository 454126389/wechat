package com.ruiyi.wechat.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractParameter entity provides the base persistence definition of the
 * Parameter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractParameter implements java.io.Serializable {

	// Fields

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
	private Set userParameters = new HashSet(0);
	private Set serviceParameters = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractParameter() {
	}

	/** minimal constructor */
	public AbstractParameter(Long id, String language, Boolean smsOpen,
			Boolean emailOpen, Short fortiry, String sgps, Short rmap,
			Short trT, Boolean spru, Boolean newsopen, Boolean wifi) {
		this.id = id;
		this.language = language;
		this.smsOpen = smsOpen;
		this.emailOpen = emailOpen;
		this.fortiry = fortiry;
		this.sgps = sgps;
		this.rmap = rmap;
		this.trT = trT;
		this.spru = spru;
		this.newsopen = newsopen;
		this.wifi = wifi;
	}

	/** full constructor */
	public AbstractParameter(Long id, Voiture voiture, String language,
			String zhNo, String FNo, String alias, String fence, Date nextDl,
			Date nextPdc, Short MMileage, Short productId, Boolean smsOpen,
			Boolean emailOpen, Short fortiry, String sgps, Short rmap,
			Short trT, Long imsi, Boolean mainDevice, String wechatId,
			Boolean spru, Boolean newsopen, Boolean wifi, Boolean sosOpen,
			Boolean choice, Short stzs, Short cgps, String mphn, String fphn,
			Boolean rest, Set userParameters, Set serviceParameters) {
		this.id = id;
		this.voiture = voiture;
		this.language = language;
		this.zhNo = zhNo;
		this.FNo = FNo;
		this.alias = alias;
		this.fence = fence;
		this.nextDl = nextDl;
		this.nextPdc = nextPdc;
		this.MMileage = MMileage;
		this.productId = productId;
		this.smsOpen = smsOpen;
		this.emailOpen = emailOpen;
		this.fortiry = fortiry;
		this.sgps = sgps;
		this.rmap = rmap;
		this.trT = trT;
		this.imsi = imsi;
		this.mainDevice = mainDevice;
		this.wechatId = wechatId;
		this.spru = spru;
		this.newsopen = newsopen;
		this.wifi = wifi;
		this.sosOpen = sosOpen;
		this.choice = choice;
		this.stzs = stzs;
		this.cgps = cgps;
		this.mphn = mphn;
		this.fphn = fphn;
		this.rest = rest;
		this.userParameters = userParameters;
		this.serviceParameters = serviceParameters;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Voiture getVoiture() {
		return this.voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getZhNo() {
		return this.zhNo;
	}

	public void setZhNo(String zhNo) {
		this.zhNo = zhNo;
	}

	public String getFNo() {
		return this.FNo;
	}

	public void setFNo(String FNo) {
		this.FNo = FNo;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFence() {
		return this.fence;
	}

	public void setFence(String fence) {
		this.fence = fence;
	}

	public Date getNextDl() {
		return this.nextDl;
	}

	public void setNextDl(Date nextDl) {
		this.nextDl = nextDl;
	}

	public Date getNextPdc() {
		return this.nextPdc;
	}

	public void setNextPdc(Date nextPdc) {
		this.nextPdc = nextPdc;
	}

	public Short getMMileage() {
		return this.MMileage;
	}

	public void setMMileage(Short MMileage) {
		this.MMileage = MMileage;
	}

	public Short getProductId() {
		return this.productId;
	}

	public void setProductId(Short productId) {
		this.productId = productId;
	}

	public Boolean getSmsOpen() {
		return this.smsOpen;
	}

	public void setSmsOpen(Boolean smsOpen) {
		this.smsOpen = smsOpen;
	}

	public Boolean getEmailOpen() {
		return this.emailOpen;
	}

	public void setEmailOpen(Boolean emailOpen) {
		this.emailOpen = emailOpen;
	}

	public Short getFortiry() {
		return this.fortiry;
	}

	public void setFortiry(Short fortiry) {
		this.fortiry = fortiry;
	}

	public String getSgps() {
		return this.sgps;
	}

	public void setSgps(String sgps) {
		this.sgps = sgps;
	}

	public Short getRmap() {
		return this.rmap;
	}

	public void setRmap(Short rmap) {
		this.rmap = rmap;
	}

	public Short getTrT() {
		return this.trT;
	}

	public void setTrT(Short trT) {
		this.trT = trT;
	}

	public Long getImsi() {
		return this.imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Boolean getMainDevice() {
		return this.mainDevice;
	}

	public void setMainDevice(Boolean mainDevice) {
		this.mainDevice = mainDevice;
	}

	public String getWechatId() {
		return this.wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Boolean getSpru() {
		return this.spru;
	}

	public void setSpru(Boolean spru) {
		this.spru = spru;
	}

	public Boolean getNewsopen() {
		return this.newsopen;
	}

	public void setNewsopen(Boolean newsopen) {
		this.newsopen = newsopen;
	}

	public Boolean getWifi() {
		return this.wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean getSosOpen() {
		return this.sosOpen;
	}

	public void setSosOpen(Boolean sosOpen) {
		this.sosOpen = sosOpen;
	}

	public Boolean getChoice() {
		return this.choice;
	}

	public void setChoice(Boolean choice) {
		this.choice = choice;
	}

	public Short getStzs() {
		return this.stzs;
	}

	public void setStzs(Short stzs) {
		this.stzs = stzs;
	}

	public Short getCgps() {
		return this.cgps;
	}

	public void setCgps(Short cgps) {
		this.cgps = cgps;
	}

	public String getMphn() {
		return this.mphn;
	}

	public void setMphn(String mphn) {
		this.mphn = mphn;
	}

	public String getFphn() {
		return this.fphn;
	}

	public void setFphn(String fphn) {
		this.fphn = fphn;
	}

	public Boolean getRest() {
		return this.rest;
	}

	public void setRest(Boolean rest) {
		this.rest = rest;
	}

	public Set getUserParameters() {
		return this.userParameters;
	}

	public void setUserParameters(Set userParameters) {
		this.userParameters = userParameters;
	}

	public Set getServiceParameters() {
		return this.serviceParameters;
	}

	public void setServiceParameters(Set serviceParameters) {
		this.serviceParameters = serviceParameters;
	}

}