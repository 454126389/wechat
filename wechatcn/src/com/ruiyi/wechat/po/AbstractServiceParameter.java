package com.ruiyi.wechat.po;

import java.util.Date;

/**
 * AbstractServiceParameter entity provides the base persistence definition of
 * the ServiceParameter entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractServiceParameter implements java.io.Serializable {

	// Fields

	private Integer id;
	private Parameter parameter;
	private Integer serviceId;
	private Boolean state;
	private Date starttime;
	private Date endtime;

	// Constructors

	/** default constructor */
	public AbstractServiceParameter() {
	}

	/** minimal constructor */
	public AbstractServiceParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	/** full constructor */
	public AbstractServiceParameter(Parameter parameter, Integer serviceId,
			Boolean state, Date starttime, Date endtime) {
		this.parameter = parameter;
		this.serviceId = serviceId;
		this.state = state;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Parameter getParameter() {
		return this.parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Integer getServiceId() {
		return this.serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Boolean getState() {
		return this.state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

}