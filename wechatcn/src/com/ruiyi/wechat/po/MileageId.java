package com.ruiyi.wechat.po;

import java.util.Date;

/**
 * MileageId entity. @author MyEclipse Persistence Tools
 */
public class MileageId extends AbstractMileageId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public MileageId() {
	}

	/** full constructor */
	public MileageId(Date time, Long terminalId) {
		super(time, terminalId);
	}

}
