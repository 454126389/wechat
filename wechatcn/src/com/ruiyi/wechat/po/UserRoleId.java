package com.ruiyi.wechat.po;

/**
 * UserRoleId entity. @author MyEclipse Persistence Tools
 */
public class UserRoleId extends AbstractUserRoleId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserRoleId() {
	}

	/** full constructor */
	public UserRoleId(User user, Short roleId) {
		super(user, roleId);
	}

}
