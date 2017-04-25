package com.ruiyi.wechat.po;

import java.sql.Timestamp;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password) {
		super(username, password);
	}

	/** full constructor */
	public User(User user, String username, String password, Boolean activate,
			String email, String phone, Timestamp creationDate,
			String creationIp, Float balance, Short simnum, String qq,
			String wechat, Set userParameters, Set consumes, Set userRoles,
			Set users) {
		super(user, username, password, activate, email, phone, creationDate,
				creationIp, balance, simnum, qq, wechat, userParameters,
				consumes, userRoles, users);
	}

}
