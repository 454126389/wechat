package com.ruiyi.wechat.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractBatch entity provides the base persistence definition of the Batch
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBatch implements java.io.Serializable {

	// Fields

	private Integer id;
	private Product product;
	private Short timeZone;
	private Short validity;
	private Date time;
	private Short num;
	private String language;
	private Integer agentId;
	private Set terminals = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractBatch() {
	}

	/** minimal constructor */
	public AbstractBatch(Product product, Short timeZone, Short validity,
			Date time, Short num, String language, Integer agentId) {
		this.product = product;
		this.timeZone = timeZone;
		this.validity = validity;
		this.time = time;
		this.num = num;
		this.language = language;
		this.agentId = agentId;
	}

	/** full constructor */
	public AbstractBatch(Product product, Short timeZone, Short validity,
			Date time, Short num, String language, Integer agentId,
			Set terminals) {
		this.product = product;
		this.timeZone = timeZone;
		this.validity = validity;
		this.time = time;
		this.num = num;
		this.language = language;
		this.agentId = agentId;
		this.terminals = terminals;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Short getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(Short timeZone) {
		this.timeZone = timeZone;
	}

	public Short getValidity() {
		return this.validity;
	}

	public void setValidity(Short validity) {
		this.validity = validity;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Short getNum() {
		return this.num;
	}

	public void setNum(Short num) {
		this.num = num;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Integer getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Set getTerminals() {
		return this.terminals;
	}

	public void setTerminals(Set terminals) {
		this.terminals = terminals;
	}

}