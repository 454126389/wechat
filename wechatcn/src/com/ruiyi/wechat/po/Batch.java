package com.ruiyi.wechat.po;

import java.util.Date;
import java.util.Set;

/**
 * Batch entity. @author MyEclipse Persistence Tools
 */
public class Batch extends AbstractBatch implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Batch() {
	}

	/** minimal constructor */
	public Batch(Product product, Short timeZone, Short validity, Date time,
			Short num, String language, Integer agentId) {
		super(product, timeZone, validity, time, num, language, agentId);
	}

	/** full constructor */
	public Batch(Product product, Short timeZone, Short validity, Date time,
			Short num, String language, Integer agentId, Set terminals) {
		super(product, timeZone, validity, time, num, language, agentId,
				terminals);
	}

}
