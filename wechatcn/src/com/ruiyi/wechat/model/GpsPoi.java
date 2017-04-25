package com.ruiyi.wechat.model;

import java.io.Serializable;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class GpsPoi implements Serializable {

	// 追踪器
	// { "no" : 2130106016571146 , "lat" : 24.6511 , "lng" : 118.1513 , "alt" :
	// 0.0 , "speed" : 0.0 , "dir" : 180 , "sims" : 100 , "time" : 1461038099 ,
	// "bat" : 3.75 , "elec" : 35 , "count" : 9}
	// 后视镜
	// { "no" : 1380106000921214 , "lat" : 24.5493 , "lng" : 118.1459 , "alt" :
	// -4.9 , "speed" : 108.4712 , "dir" : 300 , "mile" : 57.7906 , "time" :
	// 1374104861 , "count" : 16}
	// 普通电子狗
	// { "no" : 1240105036381036 , "lat" : 23.6698 , "lng" : 116.6481 , "alt" :
	// -20.0 , "speed" : 17.0 , "dir" : 270 , "mile" : 414.12 , "info" : 0 ,
	// "sims" : 65 , "time" : 1455295419 , "count" : 2}

	private static final long serialVersionUID = -1458459291183696072L;

	public GpsPoi() {
		super();
	}

	private Long no;
	private float lat;
	private float lng;
	private float alt;
	private float speed;
	private int dir;

	private int sims;
	private long time;
	private float bat;

	private float elec;
	private int count;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getAlt() {
		return alt;
	}

	public void setAlt(float alt) {
		this.alt = alt;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getSims() {
		return sims;
	}

	public void setSims(int sims) {
		this.sims = sims;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public float getBat() {
		return bat;
	}

	public void setBat(float bat) {
		this.bat = bat;
	}

	public float getElec() {
		return elec;
	}

	public void setElec(float elec) {
		this.elec = elec;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public GpsPoi(Long no, float lat, float lng, float alt, float speed,
			int dir, int sims, long time, float bat, float elec, int count) {
		super();
		this.no = no;
		this.lat = lat;
		this.lng = lng;
		this.alt = alt;
		this.speed = speed;
		this.dir = dir;
		this.sims = sims;
		this.time = time;
		this.bat = bat;
		this.elec = elec;
		this.count = count;
	}

	@Override
	public String toString() {
		return "GpsPoi [no=" + no + ", lat=" + lat + ", lng=" + lng + ", alt="
				+ alt + ", speed=" + speed + ", dir=" + dir + ", sims=" + sims
				+ ", time=" + time + ", bat=" + bat + ", elec=" + elec
				+ ", count=" + count + "]";
	}

	public static GpsPoi stringToBean(String json) {
		if (json == null) {
			return null;
		}
		JSONObject jsonObj = JSONObject.fromObject(json);
		GpsPoi par = (GpsPoi) JSONObject.toBean(jsonObj, GpsPoi.class);
		return par;
	}

}
