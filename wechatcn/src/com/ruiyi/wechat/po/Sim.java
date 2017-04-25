package com.ruiyi.wechat.po;

import java.util.Date;

/**
 * Sim entity. @author MyEclipse Persistence Tools
 */
public class Sim extends AbstractSim implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Sim() {
	}

	/** minimal constructor */
	public Sim(Long phone) {
		super(phone);
	}

	/** full constructor */
	public Sim(Long phone, String iccid, Long imsi, Integer free,
			String serial, Boolean activation, Date adate, Short bsimId,
			Date mdate, Date atime, Date activationDate, Short addup,
			Boolean tag, String password, Date logoutDate, Date inDate) {
		super(phone, iccid, imsi, free, serial, activation, adate, bsimId,
				mdate, atime, activationDate, addup, tag, password, logoutDate,
				inDate);
	}

}
