package com.ruiyi.wechat.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.servlet.ToolsServlet;

import redis.clients.jedis.JedisPubSub;

public class MyListener extends JedisPubSub {
	// 取得订阅的消息后的处理
	public void onMessage(String channel, String message) {

		Logger logger = Logger.getLogger(ToolsServlet.class.getName());
		logger.info("channel=" + channel + "\n" + message);
		
		

		
//		1300106104571663$$111.7365-36.569,2016-08-16 00:11:55,车机1300106104571663,震动报警,山西省临汾市霍州市开元街
		
		try {
			switch (channel) {
			case "fenceAlarm":

				// 记录信息
				String carMess[] = message.toString().split("\\$\\$");
				
				List<String> weidlist = ConnectionPoolDao.getweidByNo(carMess[0]);

					String msg2 = carMess[1].toString();
					String carMess2[] = msg2.split(",");
					RedisUtil.set("SHOCK" + carMess[0], carMess2[1]);
					
					
					String lon = null;
					String lat=null;
					if (carMess2[0]!=null) {
						 lon=carMess2[0].split("-")[0];
						 lat=carMess2[0].split("-")[1];
					}
					
					List<String> latlnglist = SignUtil.changegps(lat, lon);
					
//					1320106030351232$$118.1513-24.651,2016-11-30 13:57:55,车机1320106030351232,震动报警,福建省厦门市同安区海翔大道辅路
					
					String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
//					String loadurl="http://wechat.conqueror.cn/jsp/map.html?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
					String loadurl="http://apis.map.qq.com/uri/v1/geocoder?coord="+latlnglist.get(0)+","+latlnglist.get(1)+"&referer=myapp";
					logger.info("loadurl=" + loadurl);
					try {
						for (int i = 0; i < weidlist.size(); i++)
							WechatSend.sendNews(weidlist.get(i), "Conqueror", carMess[1],loadurl,imaUrl);

					} catch (Exception e) {
						e.printStackTrace();
					}
//				} else {
			
			/*		String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
					String loadurl="http://wechat.conqueror.cn/jsp/map.html?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
					try {

						for (int i = 0; i < weidlist.size(); i++)
							// if (DeviceType.isOnline(carMess[0]))
							UserSendImageMessageUtil.createMenu(weidlist.get(i),
									carMess[1], RedisUtil.get("TOKENCN"));

					} catch (Exception e) {
						// logger.error("MyListener解析数字出现异常",e);
						e.printStackTrace();
					}
					
					try {

						for (int i = 0; i < weidlist.size(); i++)
							// if (DeviceType.isOnline(carMess[0]))
							UserSendImageMessageUtil.createMenu(weidlist.get(i),
									carMess[1], RedisUtil.get("TOKENCN"));

					} catch (Exception e) {
						// logger.error("MyListener解析数字出现异常",e);
						e.printStackTrace();
					}*/
//				}
				break;
			case "image":
				logger.info("image=");

				try {
					JSONObject jsonObject = new JSONObject(message);
					String url = jsonObject.getString("url");
					List<String> weidlistImage = ConnectionPoolDao.getweidByNo(""
							+ jsonObject.getLong("id"));

					for (int i = 0; i < weidlistImage.size(); i++) {
						
						
						logger.info("image="+url);
						
						// 新版本
						WechatSend.sendNews(weidlistImage.get(i), "Conqueror", "",
								"http://wechat.conqueror.cn/jsp/image.jsp?url="
										+ url, url);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case "sim.expire":
//				weid+"_"+iccid+"_"+"友情提醒：尊敬的征卡用户，您的征卡"+alais+"剩余流量已不多，请及时购买流量！具体流量信息请从微信公众号\"征卡服务平台\"查询。";
				String simMess[] = message.toString().split("_");
				
				break;

			default:
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.info("mychannel=" + "订阅报错" + "\n" + e.toString());
		}
		
	}

	// 初始化订阅时候的处理
	public void onSubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// 取消订阅时候的处理
	public void onUnsubscribe(String channel, int subscribedChannels) {
		// System.out.println(channel + "=" + subscribedChannels);
	}

	// 初始化按表达式的方式订阅时候的处理
	public void onPSubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// 取消按表达式的方式订阅时候的处理
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		// System.out.println(pattern + "=" + subscribedChannels);
	}

	// 取得按表达式的方式订阅的消息后的处理
	public void onPMessage(String pattern, String channel, String message) {
		System.out.println(pattern + "=" + channel + "=" + message);
	}

}
