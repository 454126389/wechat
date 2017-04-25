package com.ruiyi.wechat.po;

import java.util.Date;

/**
 * AbstractMileageId entity provides the base persistence definition of the
 * MileageId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMileageId implements java.io.Serializable {

	// Fields

	private Date time;
	private Long terminalId;

	// Constructors

	/** default constructor */
	public AbstractMileageId() {
	}

	/** full constructor */
	public AbstractMileageId(Date time, Long terminalId) {
		this.time = time;
		this.terminalId = terminalId;
	}

	// Property accessors

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(Long terminalId) {
		this.terminalId = terminalId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractMileageId))
			return false;
		AbstractMileageId castOther = (AbstractMileageId) other;

		return ((this.getTime() == castOther.getTime()) || (this.getTime() != null
				&& castOther.getTime() != null && this.getTime().equals(
				castOther.getTime())))
				&& ((this.getTerminalId() == castOther.getTerminalId()) || (this
						.getTerminalId() != null
						&& castOther.getTerminalId() != null && this
						.getTerminalId().equals(castOther.getTerminalId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37
				* result
				+ (getTerminalId() == null ? 0 : this.getTerminalId()
						.hashCode());
		return result;
	}

}