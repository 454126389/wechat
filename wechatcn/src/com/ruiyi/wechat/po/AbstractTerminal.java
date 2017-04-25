package com.ruiyi.wechat.po;

import java.sql.Timestamp;

/**
 * AbstractTerminal entity provides the base persistence definition of the
 * Terminal entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTerminal implements java.io.Serializable {

	// Fields

	private Long id;
	private Batch batch;
	private String tkey;
	private Boolean activate;
	private Short useMonth;
	private Boolean tag;
	private String ukey;
	private String skey;
	private Long imsi;
	private Integer rev;
	private Timestamp latlngrev;
	private String mcurev;
	private Long lnum;
	private Boolean wifi;
	private Long imei;
	private String registrationId;
	private String ticket;
	private String iccid;
	private String device;
	private String info;

	// Constructors

	/** default constructor */
	public AbstractTerminal() {
	}

	/** minimal constructor */
	public AbstractTerminal(Long id, String tkey, Boolean activate,
			Short useMonth, Boolean tag, Integer rev) {
		this.id = id;
		this.tkey = tkey;
		this.activate = activate;
		this.useMonth = useMonth;
		this.tag = tag;
		this.rev = rev;
	}

	/** full constructor */
	public AbstractTerminal(Long id, Batch batch, String tkey,
			Boolean activate, Short useMonth, Boolean tag, String ukey,
			String skey, Long imsi, Integer rev, Timestamp latlngrev,
			String mcurev, Long lnum, Boolean wifi, Long imei,
			String registrationId, String ticket, String iccid, String device,
			String info) {
		this.id = id;
		this.batch = batch;
		this.tkey = tkey;
		this.activate = activate;
		this.useMonth = useMonth;
		this.tag = tag;
		this.ukey = ukey;
		this.skey = skey;
		this.imsi = imsi;
		this.rev = rev;
		this.latlngrev = latlngrev;
		this.mcurev = mcurev;
		this.lnum = lnum;
		this.wifi = wifi;
		this.imei = imei;
		this.registrationId = registrationId;
		this.ticket = ticket;
		this.iccid = iccid;
		this.device = device;
		this.info = info;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Batch getBatch() {
		return this.batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public String getTkey() {
		return this.tkey;
	}

	public void setTkey(String tkey) {
		this.tkey = tkey;
	}

	public Boolean getActivate() {
		return this.activate;
	}

	public void setActivate(Boolean activate) {
		this.activate = activate;
	}

	public Short getUseMonth() {
		return this.useMonth;
	}

	public void setUseMonth(Short useMonth) {
		this.useMonth = useMonth;
	}

	public Boolean getTag() {
		return this.tag;
	}

	public void setTag(Boolean tag) {
		this.tag = tag;
	}

	public String getUkey() {
		return this.ukey;
	}

	public void setUkey(String ukey) {
		this.ukey = ukey;
	}

	public String getSkey() {
		return this.skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public Long getImsi() {
		return this.imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public Integer getRev() {
		return this.rev;
	}

	public void setRev(Integer rev) {
		this.rev = rev;
	}

	public Timestamp getLatlngrev() {
		return this.latlngrev;
	}

	public void setLatlngrev(Timestamp latlngrev) {
		this.latlngrev = latlngrev;
	}

	public String getMcurev() {
		return this.mcurev;
	}

	public void setMcurev(String mcurev) {
		this.mcurev = mcurev;
	}

	public Long getLnum() {
		return this.lnum;
	}

	public void setLnum(Long lnum) {
		this.lnum = lnum;
	}

	public Boolean getWifi() {
		return this.wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Long getImei() {
		return this.imei;
	}

	public void setImei(Long imei) {
		this.imei = imei;
	}

	public String getRegistrationId() {
		return this.registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getTicket() {
		return this.ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getIccid() {
		return this.iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}