package com.ruiyi.wechat.model;

import java.io.Serializable;
import java.util.Date;

import com.ruiyi.wechat.util.JsonDateValueProcessor;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

public class GprsParameter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1458459291183696072L;
	 //终端编号
    private long no;
    //版本
    private int rev;
    //语言
    private String language;
    //车牌号
    private String licence;
    //短信状态(是否激活)
    private boolean smsTag;
    //邮件状态(是否激活)
    private boolean emailTag;
    //
    private boolean trackTag = true;
    //天气服务状态
    private boolean weatherTag = true;
    //设防
    private short fortiry = 16;
    //短信报警发送记录
    private int sendInfo;
    //邮件报警发送记录
    private int emailInfo;
    //主号
    private String zh_number;
    //副号
    private String fu_number;
    //短信报警开（位运算）,越界报警开
    private short alarm;
    //圆形围栏
    private String roundRail;
    //是否注册标识
    private boolean registeredFlag = false;
    //坐标版本号
    private Date latlngRev = new Date(0);
    //GPRS是否开启
    private boolean gprsOpen = true;
    //News开关
    private boolean newsOpen = true;
    //行驶间隔
    private short moveInterval = 30;
    //停车间隔
    private short stopInterval = 1800;
    //数据获取半径（公里）
    private short rmap = 50;
    //路况信息间隔（分钟）
    private short tr_r = 30;
    //voice 开关
    private short spru = 0;
    //imsi卡号
    private long imsi;
    //ICCID
    private String iccid;
    
    private boolean sosOpen;
    
    private short stzs=8;//时区设置
    private short cgps=30;//拐点上传
    private String mphn="";//监听号码(逗号分割)  
    private String fphn="";//亲情号码(逗号分割)
    private boolean rest=false;//回复出厂设置
    
    private short deviceType=0; //设备类型 product CUT字段
    private String vaTime="22-06";//震动报警时间段 parameter VATIME字段
    
    
    
    
    //追踪器
    private boolean lsen=false;
    
    
	public boolean isLsen() {
		return lsen;
	}

	public void setLsen(boolean lsen) {
		this.lsen = lsen;
	}

	public short getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(short deviceType) {
		this.deviceType = deviceType;
	}

	public String getVaTime() {
		return vaTime;
	}

	public void setVaTime(String vaTime) {
		this.vaTime = vaTime;
	}

	public boolean isSosOpen() {
		return sosOpen;
	}

	public void setSosOpen(boolean sosOpen) {
		this.sosOpen = sosOpen;
	}

	public short getStzs() {
		return stzs;
	}

	public void setStzs(short stzs) {
		this.stzs = stzs;
	}

	public short getCgps() {
		return cgps;
	}

	public void setCgps(short cgps) {
		this.cgps = cgps;
	}

	public String getMphn() {
		return mphn;
	}

	public void setMphn(String mphn) {
		this.mphn = mphn;
	}

	public String getFphn() {
		return fphn;
	}

	public void setFphn(String fphn) {
		this.fphn = fphn;
	}

	public boolean isRest() {
		return rest;
	}

	public void setRest(boolean rest) {
		this.rest = rest;
	}


	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}

	public boolean isNewsOpen() {
		return newsOpen;
	}

	public void setNewsOpen(boolean newsOpen) {
		this.newsOpen = newsOpen;
	}

	public short getSpru() {
		return spru;
	}

	public void setSpru(short spru) {
		this.spru = spru;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public boolean isSmsTag() {
		return smsTag;
	}

	public void setSmsTag(boolean smsTag) {
		this.smsTag = smsTag;
	}

	public boolean isEmailTag() {
		return emailTag;
	}

	public void setEmailTag(boolean emailTag) {
		this.emailTag = emailTag;
	}

	public short getFortiry() {
		return fortiry;
	}

	public void setFortiry(short fortiry) {
		this.fortiry = fortiry;
	}

	public int getSendInfo() {
		return sendInfo;
	}

	public void setSendInfo(int sendInfo) {
		this.sendInfo = sendInfo;
	}

	public String getZh_number() {
		return zh_number;
	}

	public void setZh_number(String zh_number) {
		this.zh_number = zh_number;
	}

	public String getFu_number() {
		return fu_number;
	}

	public void setFu_number(String fu_number) {
		this.fu_number = fu_number;
	}

	public String getRoundRail() {
		return roundRail;
	}

	public void setRoundRail(String roundRail) {
		this.roundRail = roundRail;
	}

	public short getAlarm() {
		return alarm;
	}

	public void setAlarm(short alarm) {
		this.alarm = alarm;
	}

	public int getEmailInfo() {
		return emailInfo;
	}

	public void setEmailInfo(int emailInfo) {
		this.emailInfo = emailInfo;
	}

	public boolean isRegisteredFlag() {
		return registeredFlag;
	}

	public void setRegisteredFlag(boolean registeredFlag) {
		this.registeredFlag = registeredFlag;
	}

	public Date getLatlngRev() {
		return latlngRev;
	}

	public void setLatlngRev(Date latlngRev) {
		this.latlngRev = latlngRev;
	}

	public boolean isGprsOpen() {
		return gprsOpen;
	}

	public void setGprsOpen(boolean gprsOpen) {
		this.gprsOpen = gprsOpen;
	}

	public short getMoveInterval() {
		return moveInterval;
	}

	public void setMoveInterval(short moveInterval) {
		this.moveInterval = moveInterval;
	}

	public short getStopInterval() {
		return stopInterval;
	}

	public void setStopInterval(short stopInterval) {
		this.stopInterval = stopInterval;
	}
	

	public boolean isTrackTag() {
		return trackTag;
	}

	public void setTrackTag(boolean trackTag) {
		this.trackTag = trackTag;
	}

	public boolean isWeatherTag() {
		return weatherTag;
	}

	public void setWeatherTag(boolean weatherTag) {
		this.weatherTag = weatherTag;
	}



	public short getRmap() {
		return rmap;
	}

	public void setRmap(short rmap) {
		this.rmap = rmap;
	}

	public short getTr_r() {
		return tr_r;
	}

	public void setTr_r(short tr_r) {
		this.tr_r = tr_r;
	}

	public long getImsi() {
		return imsi;
	}

	public void setImsi(long imsi) {
		this.imsi = imsi;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public static String beanToString(GprsParameter par) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,
				new JsonDateValueProcessor());
		JSONObject jsonObject = JSONObject.fromObject(par, jsonConfig);
		return jsonObject.toString();
	}

	public static GprsParameter stringToBean(String json) {
		if (json == null) {
			return null;
		}
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd" }));
		JSONObject jsonObj = JSONObject.fromObject(json);
		GprsParameter par = (GprsParameter) JSONObject.toBean(jsonObj, GprsParameter.class);
		return par;
	}
}
