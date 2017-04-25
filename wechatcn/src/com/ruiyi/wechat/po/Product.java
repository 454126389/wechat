package com.ruiyi.wechat.po;

import java.util.Set;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */
public class Product extends AbstractProduct implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(String type) {
		super(type);
	}

	/** full constructor */
	public Product(String type, String done, Short cut, String remrak,
			Set batchs) {
		super(type, done, cut, remrak, batchs);
	}

}
