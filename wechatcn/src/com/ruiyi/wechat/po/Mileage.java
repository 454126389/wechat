package com.ruiyi.wechat.po;

/**
 * Mileage entity. @author MyEclipse Persistence Tools
 */
public class Mileage extends AbstractMileage implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Mileage() {
	}

	/** full constructor */
	public Mileage(MileageId id, Float miles) {
		super(id, miles);
	}

}
