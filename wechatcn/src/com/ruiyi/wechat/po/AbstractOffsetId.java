package com.ruiyi.wechat.po;

/**
 * AbstractOffsetId entity provides the base persistence definition of the
 * OffsetId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOffsetId implements java.io.Serializable {

	// Fields

	private Float lng;
	private Float lat;

	// Constructors

	/** default constructor */
	public AbstractOffsetId() {
	}

	/** full constructor */
	public AbstractOffsetId(Float lng, Float lat) {
		this.lng = lng;
		this.lat = lat;
	}

	// Property accessors

	public Float getLng() {
		return this.lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Float getLat() {
		return this.lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOffsetId))
			return false;
		AbstractOffsetId castOther = (AbstractOffsetId) other;

		return ((this.getLng() == castOther.getLng()) || (this.getLng() != null
				&& castOther.getLng() != null && this.getLng().equals(
				castOther.getLng())))
				&& ((this.getLat() == castOther.getLat()) || (this.getLat() != null
						&& castOther.getLat() != null && this.getLat().equals(
						castOther.getLat())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getLng() == null ? 0 : this.getLng().hashCode());
		result = 37 * result
				+ (getLat() == null ? 0 : this.getLat().hashCode());
		return result;
	}

}