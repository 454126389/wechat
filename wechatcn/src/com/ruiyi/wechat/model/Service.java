package com.ruiyi.wechat.model;

public class Service {
	int STATE;
	String STARTTIME;
	String ENDTIME;
	
	public Service(int sTATE, String sTARTTIME, String eNDTIME) {
		super();
		STATE = sTATE;
		STARTTIME = sTARTTIME;
		ENDTIME = eNDTIME;
	}


	public int getSTATE() {
		return STATE;
	}

	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}

	public String getSTARTTIME() {
		return STARTTIME;
	}

	public void setSTARTTIME(String sTARTTIME) {
		STARTTIME = sTARTTIME;
	}

	public String getENDTIME() {
		return ENDTIME;
	}

	public void setENDTIME(String eNDTIME) {
		ENDTIME = eNDTIME;
	}
	
	
	
}
