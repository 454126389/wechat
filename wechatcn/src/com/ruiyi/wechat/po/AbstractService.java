package com.ruiyi.wechat.po;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractService entity provides the base persistence definition of the
 * Service entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractService implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String note;
	private Integer cost;
	private Boolean state;
	private Short infree;
	private Set consumes = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractService() {
	}

	/** minimal constructor */
	public AbstractService(String name, String note, Integer cost,
			Boolean state, Short infree) {
		this.name = name;
		this.note = note;
		this.cost = cost;
		this.state = state;
		this.infree = infree;
	}

	/** full constructor */
	public AbstractService(String name, String note, Integer cost,
			Boolean state, Short infree, Set consumes) {
		this.name = name;
		this.note = note;
		this.cost = cost;
		this.state = state;
		this.infree = infree;
		this.consumes = consumes;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getCost() {
		return this.cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Short getInfree() {
		return this.infree;
	}

	public void setInfree(Short infree) {
		this.infree = infree;
	}

	public Set getConsumes() {
		return this.consumes;
	}

	public void setConsumes(Set consumes) {
		this.consumes = consumes;
	}

}