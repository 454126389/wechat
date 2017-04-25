package com.ruiyi.wechat.po;

import java.sql.Timestamp;

/**
 * Terminal entity. @author MyEclipse Persistence Tools
 */
public class Terminal extends AbstractTerminal implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Terminal() {
	}

	/** minimal constructor */
	public Terminal(Long id, String tkey, Boolean activate, Short useMonth,
			Boolean tag, Integer rev) {
		super(id, tkey, activate, useMonth, tag, rev);
	}

	/** full constructor */
	public Terminal(Long id, Batch batch, String tkey, Boolean activate,
			Short useMonth, Boolean tag, String ukey, String skey, Long imsi,
			Integer rev, Timestamp latlngrev, String mcurev, Long lnum,
			Boolean wifi, Long imei, String registrationId, String ticket,
			String iccid, String device, String info) {
		super(id, batch, tkey, activate, useMonth, tag, ukey, skey, imsi, rev,
				latlngrev, mcurev, lnum, wifi, imei, registrationId, ticket,
				iccid, device, info);
	}

}
