package com.ruiyi.wechat.po;

/**
 * Offset entity. @author MyEclipse Persistence Tools
 */
public class Offset extends AbstractOffset implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Offset() {
	}

	/** full constructor */
	public Offset(OffsetId id, Short offsetX, Short offsetY, Float offsetLng,
			Float offsetLat) {
		super(id, offsetX, offsetY, offsetLng, offsetLat);
	}

}
