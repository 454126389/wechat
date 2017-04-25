package com.ruiyi.wechat.po;

/**
 * AbstractUserRole entity provides the base persistence definition of the
 * UserRole entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserRole implements java.io.Serializable {

	// Fields

	private UserRoleId id;

	// Constructors

	/** default constructor */
	public AbstractUserRole() {
	}

	/** full constructor */
	public AbstractUserRole(UserRoleId id) {
		this.id = id;
	}

	// Property accessors

	public UserRoleId getId() {
		return this.id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

}