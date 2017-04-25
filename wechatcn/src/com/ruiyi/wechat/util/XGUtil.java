package com.ruiyi.wechat.util;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.mongodb.util.JSON;
import com.ruiyi.wechat.servlet.ToolsServlet;
import com.tencent.xinge.XingeApp;

public class XGUtil {
	public static JSONObject sendMsg(String xgtoken, String msg) {
		
		Logger logger = Logger.getLogger(ToolsServlet.class.getName());
		JSONObject js = null;
		try {
//			String last=RedisUtil.hget("LASTSEND", xgtoken);
//			if(last==null)
//				last="0";
				
			
			
//			Long now=System.currentTimeMillis();
//			if(now-Long.parseLong(last)>5000)
//			{
//				RedisUtil.hset("LASTSEND", xgtoken,""+now);
				js=XingeApp.pushTokenAndroid(2100206243,
						"94339527f27d20e0f1cc4a766ec3a9fe", "标题", msg, xgtoken);
				
//			}
//			else
//			{
//				logger.info("XingeApp error" + "请求太频繁，请等5S");
//				js= new JSONObject("{'rec':'error'}");
//			}
			return js;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
	
			logger.info("XingeApp error" + xgtoken + "---" + msg);
			String json = "{'rec':'error'}";
	
			try {
				js = new JSONObject(json);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return js;
		}
		// return json;
	}
	
	public static JSONObject sendMsgApp(String xgtoken, String msg) {
		try {
			return XingeApp.pushTokenAndroid(2100205950,
					"fa5dfb548ee0713b42a7072d2e0a795a", "标题", msg, xgtoken);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Logger logger = Logger.getLogger(ToolsServlet.class.getName());
			logger.info("XingeApp error" + xgtoken + "---" + msg);
			String json = "[{'rec':'error'}]";
			JSONObject js = null;
			try {
				js = new JSONObject(json);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return js;
		}
		// return json;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(XingeApp.pushTokenAndroid(2100206243,
					"94339527f27d20e0f1cc4a766ec3a9fe", "标题", "test", "0702b0c44ea"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}