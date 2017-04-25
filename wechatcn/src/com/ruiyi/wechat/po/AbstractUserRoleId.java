package com.ruiyi.wechat.po;

/**
 * AbstractUserRoleId entity provides the base persistence definition of the
 * UserRoleId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserRoleId implements java.io.Serializable {

	// Fields

	private User user;
	private Short roleId;

	// Constructors

	/** default constructor */
	public AbstractUserRoleId() {
	}

	/** full constructor */
	public AbstractUserRoleId(User user, Short roleId) {
		this.user = user;
		this.roleId = roleId;
	}

	// Property accessors

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Short getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Short roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractUserRoleId))
			return false;
		AbstractUserRoleId castOther = (AbstractUserRoleId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null
				&& castOther.getUser() != null && this.getUser().equals(
				castOther.getUser())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		return result;
	}

}