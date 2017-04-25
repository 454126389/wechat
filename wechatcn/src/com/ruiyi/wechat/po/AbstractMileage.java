package com.ruiyi.wechat.po;

/**
 * AbstractMileage entity provides the base persistence definition of the
 * Mileage entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMileage implements java.io.Serializable {

	// Fields

	private MileageId id;
	private Float miles;

	// Constructors

	/** default constructor */
	public AbstractMileage() {
	}

	/** full constructor */
	public AbstractMileage(MileageId id, Float miles) {
		this.id = id;
		this.miles = miles;
	}

	// Property accessors

	public MileageId getId() {
		return this.id;
	}

	public void setId(MileageId id) {
		this.id = id;
	}

	public Float getMiles() {
		return this.miles;
	}

	public void setMiles(Float miles) {
		this.miles = miles;
	}

}