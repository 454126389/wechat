package com.ruiyi.wechat.po;

import java.sql.Timestamp;

/**
 * Consume entity. @author MyEclipse Persistence Tools
 */
public class Consume extends AbstractConsume implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Consume() {
	}

	/** minimal constructor */
	public Consume(User user, Service service, Timestamp consumeTime,
			Float consumeFee, Long parameterId) {
		super(user, service, consumeTime, consumeFee, parameterId);
	}

	/** full constructor */
	public Consume(User user, Service service, Timestamp consumeTime,
			Float consumeFee, Long parameterId, Float overage, Long phone) {
		super(user, service, consumeTime, consumeFee, parameterId, overage,
				phone);
	}

}
