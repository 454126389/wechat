package com.ruiyi.wechat.po;

import java.util.Date;

/**
 * ServiceParameter entity. @author MyEclipse Persistence Tools
 */
public class ServiceParameter extends AbstractServiceParameter implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ServiceParameter() {
	}

	/** minimal constructor */
	public ServiceParameter(Parameter parameter) {
		super(parameter);
	}

	/** full constructor */
	public ServiceParameter(Parameter parameter, Integer serviceId,
			Boolean state, Date starttime, Date endtime) {
		super(parameter, serviceId, state, starttime, endtime);
	}

}
