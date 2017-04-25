package com.ruiyi.wechat.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.servlet.ToolsServlet;

import cn.jpush.api.push.model.Message;

public class t4 {
	public static void main(String[] args) {
		
		
/*		String xgtoken=RedisUtil.hget("xgtokenapp","1340106112191138");
		System.out.println(xgtoken);
		String url="http://webservice.conqueror.cn:8181/videos/5b927687-9639-4c09-b305-08a5621ecb36.mp4";

		if (xgtoken != null) {
			String json = "{\"type\":\"" + "app_command_video"
					+ "\"," + "\"video_url\":\""
					+ url + "\","
					+ "\"pushTime\":\""
					+ System.currentTimeMillis() + "\"}";
			System.out.println("XGUtil-getVideo=" + "---"
					+ XGUtil.sendMsgApp(xgtoken, json));
		}*/
		
//		System.out.println(RedisUtil.get("PAR" + "2130106000301183"));
		
//		String batchid = ConnectionPoolDao.getBatchId("352544072191159");
//		System.out.println();
//		
//		String s;
//		try {
//			s = URLDecoder.decode("%E9%80%9B%E9%80%9B",   "utf-8");
//			  System.out.println(s);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}   
	
		
		
		System.out.println("xgtoken="+RedisUtil.hget("xgtoken","1270106051191236"));
		
	}
}
