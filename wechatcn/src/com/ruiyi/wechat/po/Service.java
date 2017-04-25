package com.ruiyi.wechat.po;

import java.util.Set;

/**
 * Service entity. @author MyEclipse Persistence Tools
 */
public class Service extends AbstractService implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Service() {
	}

	/** minimal constructor */
	public Service(String name, String note, Integer cost, Boolean state,
			Short infree) {
		super(name, note, cost, state, infree);
	}

	/** full constructor */
	public Service(String name, String note, Integer cost, Boolean state,
			Short infree, Set consumes) {
		super(name, note, cost, state, infree, consumes);
	}

}
