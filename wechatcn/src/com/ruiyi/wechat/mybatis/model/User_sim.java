package com.ruiyi.wechat.mybatis.model;

public class User_sim {
	String ICCID;
	String PHONE;
	short PLAN;
	String ADDDATE;
	String ENDDATE;
	String ALIAS;
	String IMEI;
	public String getICCID() {
		return ICCID;
	}
	public void setICCID(String iCCID) {
		ICCID = iCCID;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public short getPLAN() {
		return PLAN;
	}
	public void setPLAN(short pLAN) {
		PLAN = pLAN;
	}
	public String getADDDATE() {
		return ADDDATE;
	}
	public void setADDDATE(String aDDDATE) {
		ADDDATE = aDDDATE;
	}
	public String getENDDATE() {
		return ENDDATE;
	}
	public void setENDDATE(String eNDDATE) {
		ENDDATE = eNDDATE;
	}
	public String getALIAS() {
		return ALIAS;
	}
	public void setALIAS(String aLIAS) {
		ALIAS = aLIAS;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	@Override
	public String toString() {
		return "user_sim [ICCID=" + ICCID + ", PHONE=" + PHONE + ", PLAN="
				+ PLAN + ", ADDDATE=" + ADDDATE + ", ENDDATE=" + ENDDATE
				+ ", ALIAS=" + ALIAS + ", IMEI=" + IMEI + "]";
	}
	
}
