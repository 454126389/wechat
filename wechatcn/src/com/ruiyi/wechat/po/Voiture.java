package com.ruiyi.wechat.po;

import java.util.Set;

/**
 * Voiture entity. @author MyEclipse Persistence Tools
 */
public class Voiture extends AbstractVoiture implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Voiture() {
	}

	/** minimal constructor */
	public Voiture(String type) {
		super(type);
	}

	/** full constructor */
	public Voiture(String type, Set parameters) {
		super(type, parameters);
	}

}
