package com.ruiyi.wechat.model;

import java.util.List;

public class SettingInfo {
	private String id;
	private String alias;
	private String tr_t;
	private String sgps;
	private String fortiry;
	private String email_open;
	private String weather_state;
	private String news_state;
	private int wifi;
	private String wifimsg;
	private String sos_open;
	//时区
	private String STZS;
	//拐点角度
	private String CGPS;
	//监听号码
	private String MPHN;
	//亲情号码
	private String FPHN;
	//恢复出厂设置
	private String REST;
	
	//停车监控生效时间
	private String VATIME;
	
	private String LSEN;
	

	
	public String getVATIME() {
		return VATIME;
	}

	public void setVATIME(String vATIME) {
		VATIME = vATIME;
	}

	public String getSos_open() {
		return sos_open;
	}

	public void setSos_open(String sosOpen) {
		sos_open = sosOpen;
	}

	public String getWifimsg() {
		return wifimsg;
	}

	public void setWifimsg(String wifimsg) {
		this.wifimsg = wifimsg;
	}

	public int getWifi() {
		return wifi;
	}

	public void setWifi(int wifi) {
		this.wifi = wifi;
	}

	public String getNews_state() {
		return news_state;
	}

	public void setNews_state(String newsState) {
		news_state = newsState;
	}

	public String getWeather_state() {
		return weather_state;
	}

	public void setWeather_state(String weatherState) {
		weather_state = weatherState;
	}

	public String getEmail_open() {
		return email_open;
	}

	public void setEmail_open(String emailOpen) {
		email_open = emailOpen;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTr_t() {
		return tr_t;
	}

	public void setTr_t(String trT) {
		tr_t = trT;
	}

	public String getSgps() {
		return sgps;
	}

	public void setSgps(String sgps) {
		this.sgps = sgps;
	}

	public String getFortiry() {
		return fortiry;
	}

	public void setFortiry(String fortiry) {
		this.fortiry = fortiry;
	}

	public SettingInfo(String id,String alias,String fortiry, String sgps, String tr_t,String email_open,int wifi,String wifimsg,String sos_open,String sTZS, String cGPS, String mPHN, String fPHN, String rEST,String vATIME,String lSEN) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.alias=alias;
		this.tr_t = tr_t;
		this.sgps = sgps;
		this.fortiry = fortiry;
		this.email_open = email_open;
		this.wifi = wifi;
		this.wifimsg = wifimsg;
		this.sos_open=sos_open;
		STZS = sTZS;
		CGPS = cGPS;
		MPHN = mPHN;
		FPHN = fPHN;
		REST = rEST;
		VATIME=vATIME;
		this.LSEN=lSEN;
	
	}
	public String getLSEN() {
		return LSEN;
	}

	public void setLSEN(String lSEN) {
		LSEN = lSEN;
	}

	public SettingInfo(String id,String fortiry, String sgps, String tr_t,String email_open,String sos_open) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.tr_t = tr_t;
		this.sgps = sgps;
		this.fortiry = fortiry;
		this.email_open = email_open;
		this.sos_open=sos_open;

	}

	public String getSTZS() {
		return STZS;
	}

	public void setSTZS(String sTZS) {
		STZS = sTZS;
	}

	public String getCGPS() {
		return CGPS;
	}

	public void setCGPS(String cGPS) {
		CGPS = cGPS;
	}

	public String getMPHN() {
		return MPHN;
	}

	public void setMPHN(String mPHN) {
		MPHN = mPHN;
	}

	public String getFPHN() {
		return FPHN;
	}

	public void setFPHN(String fPHN) {
		FPHN = fPHN;
	}

	public String getREST() {
		return REST;
	}

	public void setREST(String rEST) {
		REST = rEST;
	}

	public SettingInfo(String id, String alias, String tr_t, String sgps,
			String fortiry, String email_open, String weather_state,
			String news_state, int wifi, String wifimsg, String sos_open,
			String sTZS, String cGPS, String mPHN, String fPHN, String rEST) {
		super();
		this.id = id;
		this.alias = alias;
		this.tr_t = tr_t;
		this.sgps = sgps;
		this.fortiry = fortiry;
		this.email_open = email_open;
		this.weather_state = weather_state;
		this.news_state = news_state;
		this.wifi = wifi;
		this.wifimsg = wifimsg;
		this.sos_open = sos_open;
		STZS = sTZS;
		CGPS = cGPS;
		MPHN = mPHN;
		FPHN = fPHN;
		REST = rEST;
	}
	
	
	
}
