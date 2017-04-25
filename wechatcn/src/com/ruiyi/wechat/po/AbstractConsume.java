package com.ruiyi.wechat.po;

import java.sql.Timestamp;

/**
 * AbstractConsume entity provides the base persistence definition of the
 * Consume entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractConsume implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Service service;
	private Timestamp consumeTime;
	private Float consumeFee;
	private Long parameterId;
	private Float overage;
	private Long phone;

	// Constructors

	/** default constructor */
	public AbstractConsume() {
	}

	/** minimal constructor */
	public AbstractConsume(User user, Service service, Timestamp consumeTime,
			Float consumeFee, Long parameterId) {
		this.user = user;
		this.service = service;
		this.consumeTime = consumeTime;
		this.consumeFee = consumeFee;
		this.parameterId = parameterId;
	}

	/** full constructor */
	public AbstractConsume(User user, Service service, Timestamp consumeTime,
			Float consumeFee, Long parameterId, Float overage, Long phone) {
		this.user = user;
		this.service = service;
		this.consumeTime = consumeTime;
		this.consumeFee = consumeFee;
		this.parameterId = parameterId;
		this.overage = overage;
		this.phone = phone;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Timestamp getConsumeTime() {
		return this.consumeTime;
	}

	public void setConsumeTime(Timestamp consumeTime) {
		this.consumeTime = consumeTime;
	}

	public Float getConsumeFee() {
		return this.consumeFee;
	}

	public void setConsumeFee(Float consumeFee) {
		this.consumeFee = consumeFee;
	}

	public Long getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public Float getOverage() {
		return this.overage;
	}

	public void setOverage(Float overage) {
		this.overage = overage;
	}

	public Long getPhone() {
		return this.phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

}