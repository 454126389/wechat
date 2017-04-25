package com.ruiyi.wechat.po;

/**
 * UserParameterId entity. @author MyEclipse Persistence Tools
 */
public class UserParameterId extends AbstractUserParameterId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserParameterId() {
	}

	/** full constructor */
	public UserParameterId(User user, Parameter parameter) {
		super(user, parameter);
	}

}
