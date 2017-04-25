package com.ruiyi.wechat.po;

import java.util.Date;

/**
 * AbstractSim entity provides the base persistence definition of the Sim
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSim implements java.io.Serializable {

	// Fields

	private Long phone;
	private String iccid;
	private Long imsi;
	private Integer free;
	private String serial;
	private Boolean activation;
	private Date adate;
	private Short bsimId;
	private Date mdate;
	private Date atime;
	private Date activationDate;
	private Short addup;
	private Boolean tag;
	private String password;
	private Date logoutDate;
	private Date inDate;

	// Constructors

	/** default constructor */
	public AbstractSim() {
	}

	/** minimal constructor */
	public AbstractSim(Long phone) {
		this.phone = phone;
	}

	/** full constructor */
	public AbstractSim(Long phone, String iccid, Long imsi, Integer free,
			String serial, Boolean activation, Date adate, Short bsimId,
			Date mdate, Date atime, Date activationDate, Short addup,
			Boolean tag, String password, Date logoutDate, Date inDate) {
		this.phone = phone;
		this.iccid = iccid;
		this.imsi = imsi;
		this.free = free;
		this.serial = serial;
		this.activation = activation;
		this.adate = adate;
		this.bsimId = bsimId;
		this.mdate = mdate;
		this.atime = atime;
		this.activationDate = activationDate;
		this.addup = addup;
		this.tag = tag;
		this.password = password;
		this.logoutDate = logoutDate;
		this.inDate = inDate;
	}

	// Property accessors

	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public Long getImsi() {
		return this.imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Integer getFree() {
		return this.free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Boolean getActivation() {
		return this.activation;
	}

	public void setActivation(Boolean activation) {
		this.activation = activation;
	}

	public Date getAdate() {
		return this.adate;
	}

	public void setAdate(Date adate) {
		this.adate = adate;
	}

	public Short getBsimId() {
		return this.bsimId;
	}

	public void setBsimId(Short bsimId) {
		this.bsimId = bsimId;
	}

	public Date getMdate() {
		return this.mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public Date getAtime() {
		return this.atime;
	}

	public void setAtime(Date atime) {
		this.atime = atime;
	}

	public Date getActivationDate() {
		return this.activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public Short getAddup() {
		return this.addup;
	}

	public void setAddup(Short addup) {
		this.addup = addup;
	}

	public Boolean getTag() {
		return this.tag;
	}

	public void setTag(Boolean tag) {
		this.tag = tag;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLogoutDate() {
		return this.logoutDate;
	}

	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}

	public Date getInDate() {
		return this.inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

}