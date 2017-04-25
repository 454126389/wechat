package com.ruiyi.wechat.po;

/**
 * AbstractUserParameter entity provides the base persistence definition of the
 * UserParameter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserParameter implements java.io.Serializable {

	// Fields

	private UserParameterId id;

	// Constructors

	/** default constructor */
	public AbstractUserParameter() {
	}

	/** full constructor */
	public AbstractUserParameter(UserParameterId id) {
		this.id = id;
	}

	// Property accessors

	public UserParameterId getId() {
		return this.id;
	}

	public void setId(UserParameterId id) {
		this.id = id;
	}

}