package com.ruiyi.wechat.po;

/**
 * AbstractUserParameterId entity provides the base persistence definition of
 * the UserParameterId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUserParameterId implements java.io.Serializable {

	// Fields

	private User user;
	private Parameter parameter;

	// Constructors

	/** default constructor */
	public AbstractUserParameterId() {
	}

	/** full constructor */
	public AbstractUserParameterId(User user, Parameter parameter) {
		this.user = user;
		this.parameter = parameter;
	}

	// Property accessors

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Parameter getParameter() {
		return this.parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractUserParameterId))
			return false;
		AbstractUserParameterId castOther = (AbstractUserParameterId) other;

		return ((this.getUser() == castOther.getUser()) || (this.getUser() != null
				&& castOther.getUser() != null && this.getUser().equals(
				castOther.getUser())))
				&& ((this.getParameter() == castOther.getParameter()) || (this
						.getParameter() != null
						&& castOther.getParameter() != null && this
						.getParameter().equals(castOther.getParameter())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUser() == null ? 0 : this.getUser().hashCode());
		result = 37 * result
				+ (getParameter() == null ? 0 : this.getParameter().hashCode());
		return result;
	}

}