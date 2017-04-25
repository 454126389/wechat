package com.ruiyi.wechat.po;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractProduct entity provides the base persistence definition of the
 * Product entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractProduct implements java.io.Serializable {

	// Fields

	private Short id;
	private String type;
	private String done;
	private Short cut;
	private String remrak;
	private Set batchs = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractProduct() {
	}

	/** minimal constructor */
	public AbstractProduct(String type) {
		this.type = type;
	}

	/** full constructor */
	public AbstractProduct(String type, String done, Short cut, String remrak,
			Set batchs) {
		this.type = type;
		this.done = done;
		this.cut = cut;
		this.remrak = remrak;
		this.batchs = batchs;
	}

	// Property accessors

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDone() {
		return this.done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public Short getCut() {
		return this.cut;
	}

	public void setCut(Short cut) {
		this.cut = cut;
	}

	public String getRemrak() {
		return this.remrak;
	}

	public void setRemrak(String remrak) {
		this.remrak = remrak;
	}

	public Set getBatchs() {
		return this.batchs;
	}

	public void setBatchs(Set batchs) {
		this.batchs = batchs;
	}

}