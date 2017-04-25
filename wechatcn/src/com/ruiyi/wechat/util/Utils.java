package com.ruiyi.wechat.util;

public class Utils {

	public static boolean selectType(String registrationId)
	{
		return registrationId.substring(0,1).equals("6");
	}
}
