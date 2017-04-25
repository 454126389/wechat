package com.ruiyi.wechat.model;

import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.UserSendImageMessageUtil;

public class DeviceType {
	// 后视镜

	// public static String hsj="133";

	// 测试用车机ID
	// public static String testcarid="13163905721";

	public static int getCutType(String id) {
	/*	Boolean is = false;
		if (ConnectionPoolDao.getCutType(id) == 3)
			is = true;
		return is;*/
		return ConnectionPoolDao.getCutType(id);
	}

	
	
	
	
	public static Boolean isOnline(String pid) {
		Boolean isOnline = false;
/*		if (DeviceType.getCutType(pid)) {
			if (RedisUtil2.isNotField("online", pid + "_M"))
				isOnline = true;
		} else {*/
			if (RedisUtil.isNotField("online", pid+ "_M"))
				isOnline = true;
		/*}*/
		return isOnline;

	}

	/*
	 * public static String[] getTypeArray() { String[] aArray = new String[2];
	 * aArray[0]="133"; aArray[1]="134"; return aArray; }
	 */

	// public static String[] aArray ={"133","134"};

}
