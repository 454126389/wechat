package com.ruiyi.wechat.po;

import java.util.Date;
import java.util.Set;

/**
 * Parameter entity. @author MyEclipse Persistence Tools
 */
public class Parameter extends AbstractParameter implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Parameter() {
	}

	/** minimal constructor */
	public Parameter(Long id, String language, Boolean smsOpen,
			Boolean emailOpen, Short fortiry, String sgps, Short rmap,
			Short trT, Boolean spru, Boolean newsopen, Boolean wifi) {
		super(id, language, smsOpen, emailOpen, fortiry, sgps, rmap, trT, spru,
				newsopen, wifi);
	}

	/** full constructor */
	public Parameter(Long id, Voiture voiture, String language, String zhNo,
			String FNo, String alias, String fence, Date nextDl, Date nextPdc,
			Short MMileage, Short productId, Boolean smsOpen,
			Boolean emailOpen, Short fortiry, String sgps, Short rmap,
			Short trT, Long imsi, Boolean mainDevice, String wechatId,
			Boolean spru, Boolean newsopen, Boolean wifi, Boolean sosOpen,
			Boolean choice, Short stzs, Short cgps, String mphn, String fphn,
			Boolean rest, Set userParameters, Set serviceParameters) {
		super(id, voiture, language, zhNo, FNo, alias, fence, nextDl, nextPdc,
				MMileage, productId, smsOpen, emailOpen, fortiry, sgps, rmap,
				trT, imsi, mainDevice, wechatId, spru, newsopen, wifi, sosOpen,
				choice, stzs, cgps, mphn, fphn, rest, userParameters,
				serviceParameters);
	}

}
