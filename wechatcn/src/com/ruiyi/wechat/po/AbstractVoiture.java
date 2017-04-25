package com.ruiyi.wechat.po;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractVoiture entity provides the base persistence definition of the
 * Voiture entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractVoiture implements java.io.Serializable {

	// Fields

	private Short id;
	private String type;
	private Set parameters = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractVoiture() {
	}

	/** minimal constructor */
	public AbstractVoiture(String type) {
		this.type = type;
	}

	/** full constructor */
	public AbstractVoiture(String type, Set parameters) {
		this.type = type;
		this.parameters = parameters;
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

	public Set getParameters() {
		return this.parameters;
	}

	public void setParameters(Set parameters) {
		this.parameters = parameters;
	}

}