package com.ruiyi.wechat.po;

/**
 * AbstractOffset entity provides the base persistence definition of the Offset
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOffset implements java.io.Serializable {

	// Fields

	private OffsetId id;
	private Short offsetX;
	private Short offsetY;
	private Float offsetLng;
	private Float offsetLat;

	// Constructors

	/** default constructor */
	public AbstractOffset() {
	}

	/** full constructor */
	public AbstractOffset(OffsetId id, Short offsetX, Short offsetY,
			Float offsetLng, Float offsetLat) {
		this.id = id;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetLng = offsetLng;
		this.offsetLat = offsetLat;
	}

	// Property accessors

	public OffsetId getId() {
		return this.id;
	}

	public void setId(OffsetId id) {
		this.id = id;
	}

	public Short getOffsetX() {
		return this.offsetX;
	}

	public void setOffsetX(Short offsetX) {
		this.offsetX = offsetX;
	}

	public Short getOffsetY() {
		return this.offsetY;
	}

	public void setOffsetY(Short offsetY) {
		this.offsetY = offsetY;
	}

	public Float getOffsetLng() {
		return this.offsetLng;
	}

	public void setOffsetLng(Float offsetLng) {
		this.offsetLng = offsetLng;
	}

	public Float getOffsetLat() {
		return this.offsetLat;
	}

	public void setOffsetLat(Float offsetLat) {
		this.offsetLat = offsetLat;
	}

}