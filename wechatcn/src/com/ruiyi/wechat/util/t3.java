package com.ruiyi.wechat.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.ruiyi.wechat.model.GprsParameter;
import com.ruiyi.wechat.servlet.ToolsServlet;
import com.ruiyi.wechat.string.Language;
import com.tencent.xinge.XingeApp;
import com.utils.CommonUtil;
import com.utils.GetWxOrderno;




public class t3 {
	public static void main(String[] args) {
		
		
//				2017-03-04 17:50:39,173-did=1050105112001030
//				2017-03-04 17:50:39,173-money=12000
//				2017-03-04 17:50:39,173-out_trade_no=wx71460f3d267fcd411488621016
//				String did=1050105112001030;
		
		String did="1050105112001030";
//		ConnectionPoolDao.add_payrec(did, "1488621038", "4", "120",
//				"wx71460f3d267fcd411488621016");
		DateFormat fmt;
		int addyear = 1;
		fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date endTime = fmt.parse(ConnectionPoolDao
					.getServiceEndTime(did));
			if (endTime.before(new Date())) {
				ConnectionPoolDao.updateServiceNow(did, addyear);
			} else {
				ConnectionPoolDao.updateService(did, addyear);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		buy();
		
//		System.out.println(ConnectionPoolDao.getICCIDById("1120104043071942)"));
		
//		GprsParameter p = null;
//			p = new GprsParameter();
//
//		System.out.println(GprsParameter.beanToString(p)); 
		
//		String time_end="20161107150024";
//	    SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");  
//	    Date date;
//		try {
//			date = format.parse(time_end);
//		    System.out.print("Format To times:"+date.getTime()/1000); 
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
	 
//		     int timeStemp = date.getTime();
		
////		1320106030351232$$118.1513-24.651,2016-11-30 13:57:55,车机1320106030351232,震动报警,福建省厦门市同安区海翔大道辅路
//		String imaUrl="http://st.map.qq.com/api?size=256*256&center="+"24.651"+","+"118.1513"+"&zoom=16&markers="+"24.651"+","+"118.1513"+",red";
////		String loadurl="http://wechat.conqueror.cn/jsp/map.html?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
//		String loadurl="http://apis.map.qq.com/uri/v1/geocoder?coord="+"24.651"+","+"118.1513"+"&referer=myapp";
//		
//		WechatSend.sendNews("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "Conqueror", "test",loadurl,imaUrl);
//		
//		

		// 请求微信发送图片
		// if (null !=
		// request.getParameter("openId")&&"".equals(request.getParameter("openId")))
		// {
//		String openId = "oISxbt5A1Dn9GTYi-2bfq-tgTRGA";
//		if (openId.length() > 3) {
////			logger.info("请求图片" + new Date() + "\n:"
////					+ request.getParameter("openId") + "--url="
////					+ request.getParameter("RESULT_STR"));
//
//			Pattern pattern = Pattern
//					.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
//			Matcher matcher = pattern.matcher("http://webservice.conqueror.cn:8181/pics/170e6764-50c7-4105-b3b6-3bcd2192add2.jpg");
//
//			if (matcher.find()) {
//
//
//					// 新版本
//					WechatSend.sendNews("oISxbt5A1Dn9GTYi-2bfq-tgTRGA",
//							"Conqueror", "",
//							"http://wechat.conqueror.cn/jsp/image.jsp?url="
//									+ "http://webservice.conqueror.cn:8181/pics/170e6764-50c7-4105-b3b6-3bcd2192add2.jpg",
//							"http://webservice.conqueror.cn:8181/pics/170e6764-50c7-4105-b3b6-3bcd2192add2.jpg");
//			} else {
//
//				WechatSend.sendImage("oISxbt5A1Dn9GTYi-2bfq-tgTRGA",
//						"http://webservice.conqueror.cn:8181/pics/170e6764-50c7-4105-b3b6-3bcd2192add2.jpg");
//				// 旧版本
//			}
//
//		} else {

//			List<String> weidlist = ConnectionPoolDao.getweidByNo(request
//					.getParameter("did"));
//
//			for (int i = 0; i < weidlist.size(); i++) {
//				logger.info("震动报警" + new Date() + "\n:" + weidlist.get(i)
//						+ "--url=" + request.getParameter("RESULT_STR"));
//
//				if (null != weidlist.get(i)) {
//					if (request.getParameter("RESULT_STR").substring(0, 4)
//							.equals("http")) {
//						logger.info("new");
//						WechatSend
//								.sendNews(
//										weidlist.get(i),
//										"Conqueror",
//										"",
//										"http://wechat.conqueror.cn/jsp/image.jsp?url="
//												+ request
//														.getParameter("RESULT_STR"),
//										request.getParameter("RESULT_STR"));
//
//					} else {
//						logger.info("old");
//						WechatSend.sendImage(weidlist.get(i),
//								request.getParameter("RESULT_STR"));
//						// 旧版本
//						// WechatSend.sendText(request.getParameter("openId"),
//						// "检测到您的设备版本过低，请升级您的设备版本，设备更新完成后会进行一次重启");
//					}
//				}
//
//				SimpleDateFormat df = new SimpleDateFormat(
//						"yyyy-MM-dd HH:mm:ss");// 设置日期格式
//				String id = ConnectionPoolDao.getIdByImei(request
//						.getParameter("imei"));
//
//				String url = "http://cloud.conqueror.cn/webservice/sendSMS";
//				String msg1 = null;
//				try {
//					msg1 = URLEncoder.encode(df.format(new Date()) + ",设备"
//							+ id + "震动报警", "UTF-8");
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				String param = "key=44CQ55Ge5b%2BG56eR5oqA44CRY29ucXVlcm9y&id="
//						+ id + "&mess=" + msg1;
//				System.out.println(HttpRequest.sendPost(url, param));
//
//			}

//		}


	
		
		
		
		
		
		
		
		
		
		
		
		
		
	
//				String content="12111111111111113";
//		    	//id,内容
//				String bm=Language.broadcast+":"+content;
//				try {
//					MainClient.sendNewsMessage(1000104010971208l, bm.getBytes("GBK"));
//				} catch (UnsupportedEncodingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println();
		
		
//		System.out.println(RedisUtil.hget("out_trade_no","wx71460f3d267fcd411479287703"));
		
		
//				2016-11-16 20:19:37,756-did=1280106039281664
//				2016-11-16 20:19:37,756-money=6000
//				2016-11-16 20:19:37,756-out_trade_no=wx71460f3d267fcd411479287703
//				2016-11-16 20:19:37,756-time_end=20161116171521
//				2016-11-16 20:19:37,855-channel=fenceAlarm
		
		
//				ConnectionPoolDao.add_payrec("1280106039281664", "20161116171521", "6", "60",
//						"wx71460f3d267fcd411479287703");
//
//				ConnectionPoolDao.updateServiceliuliang("1280106039281664", 1);
//				String imsi = ConnectionPoolDao.getImsiById("1280106039281664");
//				ConnectionPoolDao.updateSimMadte(imsi, 1);
		
//		System.out.println("aa");
		
//		String xml="<xml><appid><![CDATA[wx71460f3d267fcd41]]></appid><attach><![CDATA[b88001]]></attach><bank_type><![CDATA[ICBC_CREDIT]]></bank_type><cash_fee><![CDATA[12000]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[1317179801]]></mch_id><nonce_str><![CDATA[0211401557]]></nonce_str><openid><![CDATA[oISxbt9mh8fzGocr3Mt7y9h7A4NQ]]></openid><out_trade_no><![CDATA[wx71460f3d267fcd411479233499]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[4ED46C3399C099277F7F59472AB5DAA0]]></sign><time_end><![CDATA[20161116021201]]></time_end><total_fee>12000</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[4007292001201611169865209123]]></transaction_id></xml>";
//	     GetWxOrderno getWxOrderno= new GetWxOrderno();
//	      String return_code=getWxOrderno .getKey(xml, "return_code");
//	        if(return_code.equals("SUCCESS"))
//	        {
//	        	System.out.println("return_code="+return_code);
//	        }else 
//	        	System.out.println("error+"+return_code);
//		System.out.println("xgtoken="+RedisUtil.hget("xgtoken","1330106510281301"));
//		buy();
		
//		 System.out.println(RedisUtil.get("TOKENCN"));
//		Long no=2160106931461542L;
//		String content =new String("play test");
//		
//		try {
//			MainClient.sendNewsMessage(no, content.getBytes("GBK"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		buy();
/*		try {
			JSONObject jsonObject = new JSONObject("{'id':'1500106020971133','url':'http://webservice.conqueror.cn:8181/pics/c39937db-4b80-4865-b77b-d4f544063d7f.jpg'}");
			String url = jsonObject.getString("url");
			List<String> weidlistImage = ConnectionPoolDao.getweidByNo(""
					+ jsonObject.getLong("id"));

			for (int i = 0; i < weidlistImage.size(); i++) {
				
				
				
				// 新版本
				WechatSend.sendNews(weidlistImage.get(i), "Conqueror", "",
						"http://wechat.conqueror.cn/jsp/image.jsp?url="
								+ url, url);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      */                                         
		
		
		
//		System.out.println("xgtoken="+RedisUtil.hget("xgtoken","1490106020301064"));
		
		
		
		
//		System.out.println(System.currentTimeMillis());
//		1473226304534
//		056fcb23e32ce36946ca96deaaf5e6a2548940a7
/*		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String pswmd5 = md5.encodePassword("123456", "1330106617281305");
		System.out.println(pswmd5);*/
		
		
/*//	String message="1300106104571663$$111.7365-36.569,2016-08-16 00:11:55,车机1300106104571663,震动报警,山西省临汾市霍州市开元街";
	String message="1330106004411464$$104.8945-26.5643,2016-08-25 15:39:31,,进围栏提醒！您的车辆已进入围栏区域!,贵州省六盘水市钟山区凉都大道";
	// 记录信息
	String carMess[] = message.toString().split("\\$\\$");
	String weid="oISxbtyMDjzmDSX7Mn3VSvQKdAMQ";
	
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
	
	String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
	String loadurl="http://wechat.conqueror.cn/jsp/map.html?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
	
	try {
			WechatSend.sendNews(weid, "Conqueror", "",loadurl,imaUrl);
//		System.out.println(imaUrl);
//		System.out.println(loadurl);

	} catch (Exception e) {
		e.printStackTrace();
	}*/
		
//		buy();
//		System.out.println("a");
//		WechatSend.sendNews("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "Conqueror", "",loadurl,imaUrl);
		
		
		/*String message="1330106101571702$$120.0649-30.3308,2016-08-06 10:49:59,,越界报警,车辆已超出围栏!,浙江省杭州市西湖区池华街";
		String channel="fenceAlarm";
		
		try {
			switch (channel) {
			case "fenceAlarm":

				// 记录信息
				String carMess[] = message.toString().split("\\$\\$");
				
				
				List<String> weidlist = ConnectionPoolDao
						.getweidByNo(carMess[0]);
				
				if (ConnectionPoolDao.getCutType(carMess[0]) == 5) {
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
					
					String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
					String loadurl="http://wechat.conqueror.cn/jsp/map.jsp?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
					
					try {

						for (int i = 0; i < weidlist.size(); i++)
							WechatSend.sendNews(weidlist.get(i), "Conqueror", "",loadurl,imaUrl);

					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
				
					
				} else {
			
					try {

						for (int i = 0; i < weidlist.size(); i++)
							// if (DeviceType.isOnline(carMess[0]))
							UserSendImageMessageUtil.createMenu(weidlist.get(i),carMess[1], RedisUtil.get("TOKENCN"));
									

					} catch (Exception e) {
						// logger.error("MyListener解析数字出现异常",e);
						e.printStackTrace();
					}
					
					String msg2 = carMess[1].toString();
					String carMess2[] = msg2.split(",");
					String lon = null;
					String lat=null;
					if (carMess2[0]!=null) {
						 lon=carMess2[0].split("-")[0];
						 lat=carMess2[0].split("-")[1];
					}
					
					List<String> latlnglist = SignUtil.changegps(lat, lon);
					
					String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
					String loadurl="http://wechat.conqueror.cn/jsp/map.jsp?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
					
					try {

						for (int i = 0; i < weidlist.size(); i++)
							WechatSend.sendNews(weidlist.get(i), "Conqueror", "",loadurl,imaUrl);

					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				break;

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		
//		{"type":"remoteCommand","lat":"24.658274335456774","lon":"118.16442835578758","maptype":"Bd09","text":"","pushTime":"1470444972392"}
/*		String xgtoken=RedisUtil.hget("xgtoken","1330105050121288");
		String json2 = "{\"type\":\"" + "remoteCommand"
		
				+ "\"," + "\"lat\":\"" + "24.658274335456774"
				+ "\"," + "\"lon\":\"" + "118.16442835578758"
				+ "\"," + "\"maptype\":\"" + "Bd09"
				+ "\"," + "\"text\":\""
				+ "厦门北站" + "\","
				+ "\"pushTime\":\""
				+ System.currentTimeMillis() + "\"}";


		XGUtil.sendMsg(xgtoken, json2);*/
		
		
//		buy();
		
//		System.out.println(ConnectionPoolDao.checkDevice("1380106013521132"));

		
		
//		System.out.println(RedisUtil.hget("xgtoken","1380106013521132")); fa50415beabc1baa459ba96678fdf33e2e2ab590		
		
//		System.out.println("xgtoken="+RedisUtil.hget("xgtoken","1330106101571702"));
//		System.out.println(RedisUtil.hget("xgtoken","1330106113081297"));
		
//		System.out.println(RedisUtil.hgetAll("xgtoken"));
/*		String json = "{\"type\":\"" + "app_command_photo" + "\","
				+ "\"image_url\":\""
				+ "http://webservice.conqueror.cn:8181/pics/98a7f604-36b3-43cf-88b1-4a8a174de047.jpg" + "\","
				+ "\"pushTime\":\""
				+ System.currentTimeMillis() + "\"}";
		
		System.out.println(XGUtil.sendMsgApp("9e43712682b327d34b33b6a7f69ed6d0faf397ac", json));*/
		
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		String pswmd5 = md5.encodePassword("123456", "kiluya");
//		System.out.println(pswmd5);
		
//		
//		String imaUrl="http://st.map.qq.com/api?size=256*256&center="+84.9252+","+45.5749+"&zoom=16&markers="+84.9252+","+45.5749+",red";		
//		WechatSend.sendNews("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "Conqueror", "",imaUrl,imaUrl);
		
		
//		System.out.println(RedisUtil.hget("xgtokenapp","1330106011471791"));
		
	/*	
	   try {
//			{ "no" : 1330106117051295 , "lat" : 24.6511 , "lng" : 118.1514 , "alt" : 75.7228 , "speed" : 0.0 , "dir" : 30 , "mile" : 0.7355 , "time" : 1468313743 , "count" : 44}
			String sgps = RedisUtil.get("GPS" + "1330106117051295");
			DBObject gpsnow = (DBObject) com.mongodb.util.JSON.parse(sgps);
			
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		*/
		
		
//		System.out.println(RedisUtil.get("GPS1380106123021349"));
//		Map<String,String> list=RedisUtil.hgetAll("xgtoken");
//		System.out.println(list.size());
//		System.out.println(RedisUtil.hgetAll("xgtoken"));
//		System.out.println(RedisUtil.hgetAll("xgtokenapp"));
		
//		System.out.println(RedisUtil.hget("xgtokenapp","1330106020031327"));
		
//		0a34a0559666662346e896bb7cafe27edebb2e871330106020031327
		
		
		/*String sgps = RedisUtil.get("GPS" + "2130106016591148");
		System.out.println(sgps);
		try {
			JSONObject json=new JSONObject(sgps);
			System.out.println(json.get("lat"));
			DBObject fen=new BasicDBObject(); 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		/*try{
		long stime=	Long.parseLong("1467820800000");
		long etime= Long.parseLong("1467907200000");
		String no= "2130106016591148";
		long st=stime/1000;
		long et=etime/1000;
		
		
	

		System.out.println(""+st);
		System.out.println(""+et);
		
		long vt=1463760000;//2016-05-21 00:00:00
		
		DBObject obj = new BasicDBObject();
		obj.put("no", Long.parseLong(no));
		obj.put("time", new BasicDBObject("$gte", st).append("$lte", et));
				
		DBObject obj1 = new BasicDBObject();
		obj1.put("_id", 0);
		obj1.put("no", 0);
		obj1.put("alt", 0);
		obj1.put("info", 0);
		obj1.put("sims", 0);

		List<DBObject> list;
		if(et<vt){
			list= MongoDBManager.getInstance().find("gps", obj, obj1);//2.128
		}else if(st>vt){
			list= MongoDBManager2.getInstance().find("gps", obj, obj1);//2.64
		}else{
			list= MongoDBManager2.getInstance().find("gps", obj, obj1);//2.64
			list.addAll(MongoDBManager.getInstance().find("gps", obj, obj1));//2.128
		}
		
		System.out.println(list);*/
		
		/*boolean stopFlag = false;
		double speed = 0;
		
		long startStopTime=0l;
		long endStopTime;
		List<DBObject> stop = new ArrayList<DBObject>();
		List<DBObject> over = new ArrayList<DBObject>();
		List<DBObject> nor = new ArrayList<DBObject>();
		List<DBObject> geofence = new ArrayList<DBObject>();
		DBObject dbo=null;
		
		
		for (int i = (list.size() - 1); i >= 0; i--) {
			
	
			double slat = Double.parseDouble(list.get(i).get("lat")
					.toString());
			double slng = Double.parseDouble(list.get(i).get("lng")
					.toString());
	
			double[] result=changegps(slat,slng);

			
			
			list.get(i).put("lat", result[0]);
			list.get(i).put("lng", result[1]);
			
			
			try {
				speed = Double.parseDouble(list.get(i).get("speed")
						.toString());
			} catch (Exception e) {
				// TODO: handle exception
				// System.out.println(i);
				// 基站定位
				speed = -1;
			}
			if (stopFlag && speed == 0.0&&i!=0)
				continue;
			nor.add(list.get(i));
			if (speed < 0.2) {
				startStopTime = Long.parseLong(list.get(i).get("time").toString());
				stopFlag = true;
				dbo=list.get(i);
				
			} else {
				if (stopFlag) {
					endStopTime = Long.parseLong(list.get(i).get("time").toString());
					long time = endStopTime - startStopTime;
					
					System.out.println(time);
					
					if (time > 300) {
						dbo.put("stopTime", time);
						stop.add(dbo);
					}
				}
				stopFlag = false;
			}
			
			




			if (null != list.get(i).get("overSpeed"))
			{
				over.add(list.get(i));
			}
			


		}
		
			//围栏
			String fence= ConnectionPoolDao.getroundrail(no);
		
			if(fence!=null)
			{
				double[] offsetMap = changegps(Double.parseDouble(fence.split(",")[0]), Double.parseDouble(fence.split(",")[1]));
				DBObject fen=new BasicDBObject(); 
				fen.put("lat",""+offsetMap[0]);
				fen.put("lng",""+offsetMap[1]);
				fen.put("mile",""+fence.split(",")[2]);
				geofence.add(fen);
			}
				
			List<Object> objectList=new ArrayList<Object>();
			objectList.add(nor);
			objectList.add(stop);
			objectList.add(over);
			objectList.add(geofence);
			
		JSONArray jsonArray = JSONArray.fromObject(objectList);
		System.out.println(jsonArray.toString());*/
	/*	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}


public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

	PrintWriter out = response.getWriter();

	String type = request.getParameter("type");

	String result = "result=";

	if (type.equals("save_registrationId")) {

		String deviceId = request.getParameter("deviceId");
		// String deviceId=DeviceType.testcarid;
		String registrationId = request.getParameter("registrationId");

		String db_registartion = ConnectionPoolDao
				.getTerminalRegistrationId(deviceId);
		String imei = request.getParameter("imei");

		if (db_registartion == null
				|| !registrationId.equals("" + db_registartion)) {

			if (!ConnectionPoolDao.addTerminalRegistrationId(deviceId,
					registrationId, imei)) {
				result = "添加registrationId失败" + registrationId;
			} else
				result = "添加registrationId成功" + registrationId;
		} else {
			result = "registrationId不需要更新" + registrationId;
		}

	}
		
//		ConnectionPoolDao.doUnlockCar("1330106117051295");
		
		/*
		Date nsDate;
		try {
//			1467820800
//			86400
//			1467907200
		
//			1467820800
//			1467907140
//			SLF4J: Class path contains multiple SLF4J bindings.
//			SLF4J: Found binding in [jar:file:/F:/bg/2016%e5%b9%b46%e6%9c%8817%e6%97%a5%20101250/wechat_cn/wechat_cn/WebRoot/WEB-INF/lib/slf4j-log4j12-1.6.1.jar!/org/slf4j/impl/StaticLoggerBinder.class]
//			SLF4J: Found binding in [jar:file:/D:/Program%20Files/eclipse/plugins/com.genuitec.eclipse.j2eedt.core_13.0.0.me201406122219/data/libraryset/EE_6/bean-validator.jar!/org/slf4j/impl/StaticLoggerBinder.class]
//			SLF4J: Found binding in [jar:file:/D:/Program%20Files/eclipse/plugins/com.genuitec.eclipse.j2eedt.core_13.0.0.me201406122219/data/libraryset/EE_6/weld-osgi-bundle.jar!/org/slf4j/impl/StaticLoggerBinder.class]
//			SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
//			log4j:WARN No appenders could be found for logger (org.mongodb.driver.cluster).
//			log4j:WARN Please initialize the log4j system properly.
//			log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
//			75
			
			
			
		long stime=	1467820800000l;
		long etime= 1467907200000l;
		long st=stime/1000;
		long et=etime/1000;
		

		String no="1330106719261306";
		
	

		System.out.println(""+st);
		System.out.println(""+et);
		
		long vt=1463760000;//2016-05-21 00:00:00
		
		DBObject obj = new BasicDBObject();
		obj.put("no", Long.parseLong(no));
		obj.put("time", new BasicDBObject("$gte", st).append("$lte", et));
				
		DBObject obj1 = new BasicDBObject();
		obj1.put("_id", 0);
		obj1.put("no", 0);
		obj1.put("alt", 0);
		obj1.put("mile", 0);
		obj1.put("info", 0);
		obj1.put("sims", 0);
		// obj1.put("time", 0);

		List<DBObject> list;
		if(et<vt){
			list= MongoDBManager.getInstance().find("gps", obj, obj1);//2.128
		}else if(st>vt){
			list= MongoDBManager2.getInstance().find("gps", obj, obj1);//2.64
		}else{
			list= MongoDBManager2.getInstance().find("gps", obj, obj1);//2.64
			list.addAll(MongoDBManager.getInstance().find("gps", obj, obj1));//2.128
		}
		
		
		System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
//		
//		String json = "{\"type\":\"" + "speakWords" + "\","
//				+ "\"words\":\"" + "广播消息:" + "tst" + "\"}";
//		XGUtil.sendMsg("aa3456e57c3ad53f46a08ecd7241b41ac403ff7c", json);
		
//		System.out.println(System.currentTimeMillis());
		
//		if(System.currentTimeMillis()-last>2000)
//		String s=RedisUtil.get("TOKENCN");
//		
//		System.out.println("s"+s);
		
		
//		buy();
//		RedisUtil.hset("xgtoken", "1380106000941213","sdfasdf");
		
//		RedisUtil.hset("xgtoken", "1380106000941212", "a");
//		RedisUtil.del("xgtoken");
//		
		
//		RedisUtil.hset("xgtoken", "1380106011531138", "241b914a53006d054b8db34625ef65dc9232b4a1");
//		RedisUtil.hset("xgtoken", "1380106011521139", "cd073c8b2c7010c24d998e4f149d8368083dadbc");
//		RedisUtil.hset("xgtoken", "1380106013571135", "3a08c95c5614d2e34c28875f9288ac12c370e58d");
//		RedisUtil.hset("xgtoken", "1380106011591132", "544aa9b00c74006443d498fbac94236eb05cb761");
//		
//		System.out.println(RedisUtil.hgetAll("xgtoken"));
//		System.out.println(RedisUtil.hgetAll("xgtokenapp"));
		
//		TPushRegisterMessage [accessId=2100206243, deviceId=ae09796a381f698d98862aa1bb77d080, account=null, ticket=null, ticketType=0, token=aa3456e57c3ad53f46a08ecd7241b41ac403ff7c]
//		String json = "{\"type\":\"" + "getWarn" + "\","
//				+ "\"openId\":\"" + "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"+ "\","
//				+ "\"path\":\"" +""+ "\","
//				+ "\"pushTime\":\""
//				+ System.currentTimeMillis() + "\"}";
//		System.out.println(XGUtil.sendMsg("aa3456e57c3ad53f46a08ecd7241b41ac403ff7c",json));
		
//		
//		WechatSend.sendNews("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ",
//				"Conqueror", "",
//				"http://wechat.conqueror.cn/jsp/image.jsp?url="
//						+ "http://webservice.conqueror.cn:8181/pics/94a4cbce-472d-4ce0-ab03-ad9cc645f4dd.jpeg",
//				"http://webservice.conqueror.cn:8181/pics/94a4cbce-472d-4ce0-ab03-ad9cc645f4dd.jpeg");
		
		
//		String s="1380106013571135@time=1467266925817";
//		String xgtoken = RedisUtil.hget("xgtoken",
//				s.split("@")[0]);
//		System.out.println(xgtoken);
		
//		{=320c555ad408cbb6443d9c09e7555a3fac9ef7e6, 1380106011531138=241b914a53006d054b8db34625ef65dc9232b4a1, 1380106011521139=cd073c8b2c7010c24d998e4f149d8368083dadbc, 1380106013571135=3a08c95c5614d2e34c28875f9288ac12c370e58d, 1380106011591132=544aa9b00c74006443d498fbac94236eb05cb761}
//		{1380106013571135=fbca125d5f27b5534c2b9aa94d6fa52da460348a}

		
		
//		String xgtoken = RedisUtil.hget("xgtokenapp","1380106013571135");
//		String json = "{\"type\":\"" + "app_command_photo" + "\","
//				+ "\"image_url\":\""
//				+ "http://webservice.conqueror.cn:8181/pics/489f759e-9e13-4548-bb33-2b53d376b9fd.jpeg" + "\","
//				+ "\"pushTime\":\""
//				+ System.currentTimeMillis() + "\"}";
//		XGUtil.sendMsgApp(xgtoken, json);
		
		
/*		String xgtoken=RedisUtil.hget("xgtoken","1380106013571135");
		if(xgtoken!=null)
		{
		String json = "{\"type\":\"" + "speakWords" + "\","
				+ "\"words\":\"" + "广播消息:" + "aa"
				+ "\"}";
		
		System.out.println(XGUtil.sendMsg(xgtoken, json));
		}*/
		
		
//		System.out.println(RedisUtil.hget("xgtoken", "1380106000941212"));
		
//	       RedisUtil.hset("key", "1234", "56478");
//	       System.out.println(RedisUtil.hget("key", "1234"));
		
	}
		// SimpleDateFormat df = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		/*
		 * System.out.println();// new Date()为获取当前系统时间
		 * System.out.println(ConnectionPoolDao
		 * .getWeid("357941052892332","ALIAS")+"震动报警");
		 */
		// String
		// url="http://192.168.1.46:8080/conqueror-cn/webservice/sendSMS";
		// String msg="ttt";

		/*
		 * String id=ConnectionPoolDao.getWeid("357941052892332","ID"); String
		 * url="http://cloud.conqueror.cn/webservice/sendSMS"; String msg1 =
		 * null; try { msg1 = URLEncoder.encode(df.format(new
		 * Date())+","+ConnectionPoolDao
		 * .getWeid("357941052892332","ALIAS")+"震动报警松岛枫爱迪生发生的","UTF-8"); } catch
		 * (UnsupportedEncodingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * String
		 * param="key=44CQ55Ge5b%2BG56eR5oqA44CRY29ucXVlcm9y&id="+id+"&mess="
		 * +msg1; System.out.println(HttpRequest.sendPost(url, param));
		 */

		// 2015-09-15 16:12:51,新E62322震动报警,新*疆维吾尔自治区博尔塔拉蒙古自治州精河县G312

		/*
		 * HttpPost post = new
		 * HttpPost("http://localhost:8080/wechat_cn/ToolsServlet"
		 * +"?type=save_registrationId"); List<NameValuePair> params = new
		 * ArrayList<NameValuePair>(); params.add(new BasicNameValuePair("imei",
		 * "111")); params.add(new BasicNameValuePair("deviceId",
		 * "1330105050121288")); params.add(new
		 * BasicNameValuePair("registrationId", "222")); HttpParams
		 * httpParameters = new BasicHttpParams();
		 * HttpConnectionParams.setSoTimeout(httpParameters, 30000); HttpClient
		 * httpClient = new DefaultHttpClient(httpParameters); try {
		 * post.setEntity(new UrlEncodedFormEntity(params)); HttpResponse
		 * response = httpClient.execute(post); if
		 * (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { //
		 * String msg = EntityUtils.toString(response.getEntity());
		 * System.out.println(response.getEntity());
		 * //PreferenceUtils.setPrefString(pContext,
		 * PreferenceConstants.REGISTRATION_ID_CODE, registrationId); } } catch
		 * (Exception e) { e.printStackTrace(); }
		 */

		/*
		 * String access_token=RedisUtil.get("TOKENCN"); try {
		 * System.out.println
		 * (""+UserSendImageMessageUtil.createTypeMenu(access_token)); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// String registrationId="6000";
		// System.out.println(registrationId.substring(0,1).equals("6"));

		// SimpleHibernateDao daoImp=new SimpleHibernateDao();
		// daoImp.findBy("WIFI", "1");
		//

		/*
		 * Service s=ConnectionPoolDao.getService(4, "1340105050141298");
		 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); try {
		 * 
		 * Date dt1 = df.parse(s.getENDTIME()); Date dt2=new Date();
		 * 
		 * System.out.println(dt1.getTime()); System.out.println(dt2.getTime());
		 * } catch (Exception e) { // TODO: handle exception }
		 */

		/*
		 * Session s = null; Transaction ts = null; try { s =
		 * HibUtil.getSession(); ts= s.beginTransaction();
		 * 
		 * Criteria c=s.createCriteria(Parameter.class);
		 * c.add(Restrictions.eq("zhNo","18960958528"));//eq是等于，gt是大于，lt是小于,or是或
		 * 
		 * List<Parameter> list=c.list(); for(Parameter admin:list){
		 * System.out.println(admin.getAlias()); } }finally{ if(s!=null){
		 * s.close(); } }
		 */

		/*
		 * ParameterDaoImp daoImp=new ParameterDaoImp(); Parameter
		 * parameter=daoImp.findByParameterId("1010104410161161");
		 * System.out.println(parameter.getAlias());
		 */

		/*
		 * String s=null; if(s!=null&&s.equals("qrcode"))
		 * System.out.println("1 "); else System.out.println("2");
		 */

		// String
		// openId=ConnectionPoolDao.getWeid("1330105050151286","WECHAT_ID");
		/*
		 * System.out.println(RedisUtil.get("JSAPI"));
		 * 
		 * System.out.println(UserSendImageMessageUtil.getJsapi_Ticket());
		 */

		/*
		 * ApplicationContext ctx = new ClassPathXmlApplicationContext(
		 * "applicationContext.xml"); ParameterDaoImp adh = (ParameterDaoImp)
		 * ctx .getBean("articleHibernateDao");
		 */

		/*
		 * Configuration conf=new Configuration().configure(); SessionFactory
		 * sf=conf.buildSessionFactory(); Session session =sf.openSession();
		 * Parameter parameter=(Parameter) session.get(Parameter.class, 1);
		 * System.out.println(""+parameter.getAlias());
		 */

		// RegisterDevice
		// d=ConnectionPoolDao.RegisterDeviceSelect("1330105050151286");

		/*
		 * String id="1140104021591096"; String gpskeys = "GPS" + id; String
		 * sgps = RedisUtil.get(gpskeys); System.out.println(sgps);
		 */

		/* System.out.println(UserSendImageMessageUtil.getAccess_token()); */

		/*
		 * Md5PasswordEncoder md5 = new Md5PasswordEncoder(); String pswmd5 =
		 * md5.encodePassword("123456", "qfjzxl");
		 * System.out.println("pswmd5="+pswmd5);
		 */



		/*
		 * try { UserSendImageMessageUtil.getServiceMsg2(
		 * "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ",
		 * "http://webservice.conqueror.cn:8181/videos/1d283034d98eca45c99ec7da6f3ea4f3.mp4"
		 * , RedisUtil.get("TOKENCN"), "news"); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// 发送文字消息
		/*
		 * String token = RedisUtil.get("TOKENCN"); String strJson =
		 * "{\"touser\" :\"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ\","; strJson +=
		 * "\"msgtype\":\"text\","; strJson += "\"text\":{"; strJson +=
		 * "\"content\":\"Hello World\""; strJson += "}}";
		 */

		// 发送news
		/*
		 * String token = RedisUtil.get("TOKENCN"); String strJson =
		 * "{\"touser\" :\"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ\","; strJson +=
		 * "\"msgtype\":\"news\","; strJson += "\"news\":{"; strJson +=
		 * "\"articles\": ["; strJson += " {"; strJson += "\"title\":\"视频回传\",";
		 * strJson += "\"description\":\"点击查看\","; strJson +=
		 * "\"url\":\"http://webservice.conqueror.cn:8181/videos/1d283034d98eca45c99ec7da6f3ea4f3.mp4\","
		 * ; strJson +=
		 * "\"picurl\":\"http://wechat.conqueror.cn/jsp/images/log_min.jpg\"";
		 * strJson += "}"; strJson += "]"; strJson += "}}";
		 */

		// String url =
		// "https://api.weixin.qq.com/cgi-bin/message/custom/send?&body=0&access_token="
		// + token;
		// WechatSend.sendText("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ",
		// "http://wechat.conqueror.cn/jsp/images/log_min.jpg");

		// WechatSend.sendNews("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "Conqueror",
		// "","http://wechat.conqueror.cn/jsp/image.jsp?url="+"http://wechat.conqueror.cn/jsp/images/log_min.jpg",
		// "http://wechat.conqueror.cn/jsp/images/log_min.jpg");

		// System.out.println(ConnectionPoolDao.getCarListOnMap("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"));
		// System.out.println(RedisUtil.get("LOC2130106000481366"));

		/*
		 * String url="https://api.mch.weixin.qq.com/pay/unifiedorder";
		 * 
		 * String param=
		 * "appid=wx2421b1c4370ec43b&attach=支付测试&body=JSAPI支付测试&mch_id=10000100&nonce_str=1add1a30ac87aa2db72f57a2375d8fec&"
		 * + "notify_url=http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php&" +
		 * "openid=oUpF8uMuAJO_M2pxb1Q9zNjWeS6o&" + "out_trade_no=1415659990&" +
		 * "spbill_create_ip=14.23.150.211&" + "total_fee=1&" +
		 * "sign=0CB01533B8C1EF103065174F50BCA001";
		 * 
		 * System.out.println(HttpRequest.sendPost(url, param));
		 */

		/*
		 * Md5PasswordEncoder md5 = new Md5PasswordEncoder(); String nonce_str =
		 * md5.encodePassword("123456", Math.random()); long
		 * out_trade_no=System.currentTimeMillis(); //String
		 * s=request.getParameter("id");
		 * 
		 * String rqestXml = "<xml>" + "<appid>wx71460f3d267fcd41</appid>" +
		 * "<attach>支付测试</attach>" + "<body>JSAPI支付测试</body>" +
		 * "<mch_id>1317179801</mch_id>" +
		 * "<nonce_str>"+nonce_str+"</nonce_str>" +
		 * "<notify_url>http://wechat.conqueror.cn/jsp/buyresult.jsp</notify_url>"
		 * + "<openid>oISxbtyMDjzmDSX7Mn3VSvQKdAMQ</openid>" +
		 * "<out_trade_no>"+out_trade_no+"</out_trade_no>" +
		 * "<spbill_create_ip>11.11.11.11</spbill_create_ip>" +
		 * "<total_fee>1</total_fee>" + "<trade_type>JSAPI</trade_type>" +
		 * "<sign>0CB01533B8C1EF103065174F50BCA001</sign>" + "</xml>";
		 * 
		 * String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		 */

		// HttpRequest.sendPostXml(url, rqestXml);

		// String id="3010105040751282";
		// RedisUtil.publish("cloud.close","{\"id\":\""+id+"\",\"saveFlag\":true}");
		// RedisUtil.publish("cloud.close","{\"id\":\""+id+"\",\"saveFlag\":true}");
		// RedisUtil.publish("cloud.pic","{\"id\":\""+id+"\",\"saveFlag\":true}");
		// System.out.println("s");

		/*
		 * String message2="{\"id\":\"111\",\"url\":\"11111\"}";
		 * 
		 * try { JSONObject jsonObject = new JSONObject(message2);
		 * System.out.println(jsonObject.getString("url"));
		 * 
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// System.out.println(ConnectionPoolDao.getCutType("1380106000931215"));

		// System.out.println(RedisUtil.get("JSAPI"));

		// String
		// url="http://wechat.conqueror.cn/jsp/PhonePickup.jsp?weid=oISxbt7MrHeNhe3KvX2Cz9Bn2TPY&time=1458097971105";
		// url=url.substring(0,url.indexOf("&"));
		// System.out.println(url);

		// RedisUtil.publish("image", "665555");
		// System.out.println("start"+ConnectionPoolDao.getSelect("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"));
		/*
		 * String
		 * seltid=ConnectionPoolDao.getSelect("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");
		 * if(seltid==null) System.out.println("null");
		 */

		// ConnectionPoolDao.getSelect("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");

		// Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		// String nonce_str = md5.encodePassword("123456",Math.random());
		// System.out.println(nonce_str);
		// long out_trade_no = System.currentTimeMillis();
		// long out_trade_no = System.currentTimeMillis();
		// System.out.println(out_trade_no);

		/*
		 * String nonce_str = "ba82ca650d392baff36b9905ecf5cec5";
		 * 
		 * String out_trade_no = "1458185783693"; String spbill_create_ip =
		 * "61.131.68.157";
		 * 
		 * String stringA =
		 * "appid=wx71460f3d267fcd41&body=JSAPI支付测试&mch_id=1317179801&nonce_str="
		 * + nonce_str + "&" +
		 * "notify_url=http://wechat.conqueror.cn/jsp/buyresult.jsp&" +
		 * "openid=oISxbtyMDjzmDSX7Mn3VSvQKdAMQ&" + "out_trade_no=" +
		 * out_trade_no + "&" + "spbill_create_ip=" + spbill_create_ip + "&" +
		 * "total_fee=1&" + "trade_type=JSAPI";
		 * 
		 * String stringB=
		 * "appid=wx71460f3d267fcd41&body=JSAPI支付测试&mch_id=1317179801&nonce_str=ba82ca650d392baff36b9905ecf5cec5&notify_url=http://wechat.conqueror.cn/jsp/buyresult.jsp&openid=oISxbtyMDjzmDSX7Mn3VSvQKdAMQ&out_trade_no=1458185783693&spbill_create_ip=61.131.68.157&total_fee=1&trade_type=JSAPI"
		 * ;
		 * 
		 * System.out.println(stringA); System.out.println(stringB);
		 */

		/*
		 * String x1=
		 * "<xml><appid>wx71460f3d267fcd41</appid><body>JSAPI支付测试</body><mch_id>1317179801</mch_id><nonce_str>f777dcf4a63b63c35896cd88ea8bbf11</nonce_str><notify_url>http://wechat.conqueror.cn/jsp/buyresult.jsp</notify_url><openid>oISxbtyMDjzmDSX7Mn3VSvQKdAMQ</openid><out_trade_no>1458192128562</out_trade_no><spbill_create_ip>61.131.68.157</spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type><sign>C09BF8D260D881FE7FEF2FDB0FE0E306</sign></xml>"
		 * ; String x2=
		 * "<xml><appid>wx71460f3d267fcd41</appid><body>JSAPI支付测试</body><mch_id>1317179801</mch_id><nonce_str>f777dcf4a63b63c35896cd88ea8bbf11</nonce_str><notify_url>http://wechat.conqueror.cn/jsp/buyresult.jsp</notify_url><openid>oISxbtyMDjzmDSX7Mn3VSvQKdAMQ</openid><out_trade_no>1458192128562</out_trade_no><spbill_create_ip>61.131.68.157</spbill_create_ip><total_fee>1</total_fee><trade_type>JSAPI</trade_type><sign>C09BF8D260D881FE7FEF2FDB0FE0E306</sign></xml>"
		 * ;
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * System.out.println(x1); System.out.println(x2);
		 */
		// JPushClient jpushClient = new JPushClient("87ef4d1c8a14a7b36a62917c",
		// "7f9e6b577ae991b2d706dd98", 3);
		/*
		 * try {
		 * System.out.println(jpushClient.getUserOnlineStatus("0302807ef40")); }
		 * catch (APIConnectionException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (APIRequestException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/*
		 * String []s=ConnectionPoolDao.getPidByUid("2");
		 * 
		 * for (int i = 0; i < s.length; i++) {
		 * System.out.println("getPidByUid="+s[i]);; }
		 */

		/*
		 * String selectid =
		 * ConnectionPoolDao.getSelect("oISxbt7MrHeNhe3KvX2Cz9Bn2TPY");
		 * if(selectid==null) System.out.println("null"); else
		 * System.out.println(selectid);
		 */

		// System.out.println(ConnectionPoolDao.getCutType("1380106000931215"));

		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		 * Calendar c = Calendar.getInstance(); String starttime =
		 * df.format(c.getTime()); // 返回String型的时间
		 * 
		 * c.add(Calendar.MONTH, 3); // 将当前日期加一个月 String endtime1 =
		 * df.format(c.getTime()); // 返回String型的时间 c.add(Calendar.MONTH, -1); //
		 * 将当前日期减一个月 c.add(Calendar.YEAR, 1); // 将当前日期加一个月 String endtime2 =
		 * df.format(c.getTime()); // 返回String型的时间
		 * 
		 * System.out.println(starttime); System.out.println(endtime1);
		 * System.out.println(endtime2);
		 */

		/*
		 * try { UserSendImageMessageUtil.createTypeMenu(""); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		// String id="1111";
		// String key = "PAR" + id;// 终端参数KEY
		// String sp = RedisUtil.get(key);
		// // if (sp != null) {
		//
		//
		// // GprsParameter p = GprsParameter.stringToBean(sp);
		//
		// GprsParameter p=new GprsParameter();
		//
		// p.setMoveInterval(Short.parseShort("30"));
		// p.setStopInterval(Short.parseShort("1800"));
		// p.setTr_r(Short.parseShort("30"));
		// p.setFortiry((short) (Short.parseShort("16")));
		// p.setWeatherTag(true);
		// p.setNewsOpen(true);
		// p.setEmailTag(true);
		//
		// sp = GprsParameter.beanToString(p);
		// // RedisUtil.set(key, sp);
		//
		// System.out.println(sp);
		//
		// String nkey = "NOT" + id;
		// // RedisUtil.del(nkey);
		//
		// System.out.println(nkey);
		// //
		// {"alarm":0,"cgps":30,"deviceType":0,"emailInfo":0,"emailTag":true,"fortiry":16,"fphn":"","fu_number":"","gprsOpen":true,"iccid":"","imsi":0,"language":"","latlngRev":"1970-01-01 08:00:00","licence":"","moveInterval":30,"mphn":"","newsOpen":true,"no":0,"registeredFlag":false,"rest":false,"rev":0,"rmap":50,"roundRail":"","sendInfo":0,"smsTag":false,"sosOpen":false,"spru":0,"stopInterval":1800,"stzs":8,"tr_r":30,"trackTag":true,"vaTime":"22-06","weatherTag":true,"zh_number":""}
		// // NOT1111

		// }

		// Parameter parameter = new Parameter("1330105050111282", "133",
		// "www");
		// System.out.println(ConnectionPoolDao.addParameter2(parameter));

		// 2016-03-26 17:39:53,093-pay_suc!!!!!!!!!!!!!!!!!!
		// 2016-03-26 17:39:53,093-id=1380106000921214
		// 2016-03-26 17:39:53,093-time=1458985176
		// 2016-03-26 17:39:53,093-service_id=4
		// 2016-03-26 17:39:53,094-money=260
		// 2016-03-26 17:39:53,094-serial=wx71460f3d267fcd411458985175

		// 添加记录
//		ConnectionPoolDao.add_payrec("1380106000921214", "1458985178", "4","260", "wx71460f3d267fcd411458985175");
		 

		// ConnectionPoolDao.updateSimNum("1380106000921214",2);

		// String starttime = df.format(c.getTime()); // 返回String型的时间

		// ConnectionPoolDao.updateService("1380106000921214",1);
		/*
		 * String id = "1380106000921214"; String time = "1459135757"; String
		 * service_id = "4"; String money = "120"; String serial =
		 * "wx71460f3d267fcd411459135756"; // 添加记录 //
		 * ConnectionPoolDao.add_payrec(id, time, service_id, money, serial); //
		 * 购买短信服务 if (service_id.equals("3")) { int num = 0; switch (money) {
		 * case "10": num = 100; break; case "20": num = 200; break; case "50":
		 * num = 500; break;
		 * 
		 * default: break; } ConnectionPoolDao.updateSimNum(id, num); } if
		 * (service_id.equals("4")) { // 购买流量服务 int addyear = 0; switch (money)
		 * { case "120": addyear = 1; break; case "200": addyear = 2; break;
		 * case "260": addyear = 3; break;
		 * 
		 * default: break; }
		 * 
		 * ConnectionPoolDao.updateService(id, addyear); }
		 */

//		String access_token = RedisUtil.get("TOKENCN");
	/*	try {
			System.out.println(""+ UserSendImageMessageUtil.createTypeMenu(access_token));
					
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
//		System.out.println(Md5PasswordEncoder.encodePassword("1", "ss"));
		
//		try {
//			UserSendImageMessageUtil.getServiceMsg("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ","hvqLYl_i_oL3nWq1tHq4gLtfqTw8y9ZBTV3Y7LwoxN15ufXTrWYSPqUzdITkSmQq",RedisUtil.get("TOKENCN"), "image");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
				 
				 
				 
//		System.out.println(RedisUtil.get("TOKENCN"));
		
//		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//		
//		try {
//			Date endTime = fmt.parse(ConnectionPoolDao.getServiceEndTime("1330105050111282"));
//			if(endTime.before(new Date()))
//				System.out.println("过期了");
//			else
//				System.out.println("还没到");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		System.out.println(ConnectionPoolDao.getServiceEndTime("1380106000921214"));
		
		
//		System.out.println(ConnectionPoolDao.getWeid("1330106101551610", "WECHAT_ID"));
//		List<String> list=ConnectionPoolDao.getweidByNo("1380106000921214");
//		System.out.println(list.size());
							
//		System.out.println(RedisUtil.get("TOKENCN"));
//		System.out.println(RedisUtil.get("JSAPI"));
		
//		Q-gq-v0C5kNtunZXQR7n3apQxYRxQFWnAwR7Nf6AIh-wDnVnowswapg9mThGFB6_QipRBa1K6jp6nl3SBsjbE--akkvizxHKbh8zTekmbzpp6osMVEdBNPPFfkb4u8h1ZUXhADAKFI
//		sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYuNWAqWp0GjU03PNzWW5SeOxC7nuViXI0tyjAWlYQtAsA
		
		
//		Q-gq-v0C5kNtunZXQR7n3apQxYRxQFWnAwR7Nf6AIh-wDnVnowswapg9mThGFB6_QipRBa1K6jp6nl3SBsjbE--akkvizxHKbh8zTekmbzpp6osMVEdBNPPFfkb4u8h1ZUXhADAKFI
//		sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYuNWAqWp0GjU03PNzWW5SeOxC7nuViXI0tyjAWlYQtAsA
		
		
//		IUUhBevPF_iQtVfnp0z27pwJQAwX8Jkpr4NlYZdd5eLKaE-_cSizMaGQ9-CKNMTFjNzC-_K4Qll0SfRRt1rfloi3dW0msE4eHyLxWTGA1WwfSBEbRZ1dcrkAt2LyFGkvFQXgAAAKVC
//		sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYuNWAqWp0GjU03PNzWW5SeOxC7nuViXI0tyjAWlYQtAsA
		
//		System.out.println(ConnectionPoolDao.getImsiById("1330105050111282"));
//		if(ConnectionPoolDao.getImeiById("286331152")==null)
//			System.out.println("1");
		
		
//		System.out.println(ConnectionPoolDao.doLockCar("oISxbt4_KtLJ7ar9BLNRXJ3AyrHA", "1330106002381151"));
		
//		getTimeStampInt
		
/*		String time=Sha1Util.getTimeStamp();
		String id="1330105050111282";
		
//		System.out.println(ConnectionPoolDao.getGiftById("1330105050111282"));
		if(Integer.parseInt(ConnectionPoolDao.getGiftById(id))<1)
		{
			System.out.println("赠送");
			ConnectionPoolDao.updateGift(id, time);
			gift(id);
		}else
			System.out.println("不在活动范围");*/
//		
		
//		String access_token=RedisUtil.get("TOKENCN"); 
//		System.out.println(access_token);
		
		
		/*String id = "1300106204371125";
		String time = "1459602162";
		String service_id = "3";
		String money = "10";
		String serial = "wx71460f3d267fcd411459602148";
		String key = "8050799461aa9cb8d60d908f66989bfc";
		
		
		if (key.equals(Md5PasswordEncoder.encodePassword(time
				+ service_id + money + id + "godloveyou", "iso"))) {

			// 添加记录
			ConnectionPoolDao.add_payrec(id, time, service_id, money,
					serial);
			// 购买短信服务
			if (service_id.equals("3")) {
				int num = 0;
				switch (money) {
				case "10":
					num = 100;
					break;
				case "20":
					num = 200;
					break;
				case "50":
					num = 500;
					break;

				default:
					break;
				}
				ConnectionPoolDao.updateSimNum(id, num);
			} else if (service_id.equals("4")) { // 购买流量服务
				int addyear = 0;
				switch (money) {
				case "120":
					addyear = 1;
					break;
				case "200":
					addyear = 2;
					break;
				case "260":
					addyear = 3;
					break;

				default:
					break;
				}

				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				try {
					Date endTime = fmt.parse(ConnectionPoolDao
							.getServiceEndTime(id));
					if (endTime.before(new Date())) {
						System.out.println("过期了");
						ConnectionPoolDao.updateServiceNow(id, addyear);
					} else {
						System.out.println("还没到");
						ConnectionPoolDao.updateService(id, addyear);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (service_id.equals("6")) {
				
			}
		} else
			System.out.println("key验证失败");*/
		
		
//		String imsi=ConnectionPoolDao.getImsiById("1030104226451054");
//		System.out.println(imsi);
//		System.out.println(ConnectionPoolDao.doLockCar("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "1030104226451054"));	
		
/*		String id="1030104226451054";
		ConnectionPoolDao.updateServiceliuliang(id, 1);
		String imsi=ConnectionPoolDao.getImsiById(id);
		ConnectionPoolDao.updateSimMadte(imsi, 1);*/

//		JSONArray carPosiListJson = JSONArray.fromObject(carPosiList);
		
/*		1120105010381287
		
		ConnectionPoolDao.updateServiceliuliang(id, 1);
		String imsi=ConnectionPoolDao.getImsiById(id);
		ConnectionPoolDao.updateSimMadte(imsi, 1);*/
		
//		buy();
		
/*		String message="2130106016571146$$24.0-118.5,这是一个GPS追踪器的测试报警！";
		String carMess[] = message.toString().split("\\$\\$");
		List<String> weidlist = ConnectionPoolDao.getweidByNo(carMess[0]);
		try {

			for (int i = 0; i < weidlist.size(); i++)
					UserSendImageMessageUtil.createMenu(weidlist.get(i),
							carMess[1], RedisUtil.get("TOKENCN"));

		} catch (Exception e) {
			// logger.error("MyListener解析数字出现异常",e);
			e.printStackTrace();
		}*/
		
	/*	Pattern pattern = Pattern
				.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
		Matcher matcher = pattern.matcher("http://webservice.conqueror.cn:8181/pics/7127a0c3-a958-45a2-83d5-c7b59f92a5f2.jpg");
		System.out.println(matcher.find());*/
		
		
/*		WechatSend
		.sendNews(
				"oISxbt5xiz3bBNA0mhnaVsaO2UCY",
				"Conqueror",
				"",
				"http://wechat.conqueror.cn/jsp/image.jsp?url="
						+ "http://webservice.conqueror.cn:8181/pics/7127a0c3-a958-45a2-83d5-c7b59f92a5f2.jpg",
				"http://webservice.conqueror.cn:8181/pics/7127a0c3-a958-45a2-83d5-c7b59f92a5f2.jpg");*/

/*		2016-04-17 12:24:32,620-震动报警Sun Apr 17 12:24:32 CST 2016
		:oISxbt_xAEVkX8r98FdWlmPAMWBI--url=http://webservice.conqueror.cn:8181/pics/7127a0c3-a958-45a2-83d5-c7b59f92a5f2.jpg
		2016-04-17 12:24:32,907-发送结果Sun Apr 17 12:24:32 CST 2016
		{"errcode":40007,"errmsg":"invalid media_id hint: [16COdA0082ge12]"}*/
		
		
		/*logger.info("震动报警" + new Date() + "\n:" + weidlist.get(i) + "--url=" + request.getParameter("RESULT_STR"));
		String weid="oISxbt5xiz3bBNA0mhnaVsaO2UCY";

		if (null != weid) {
			if (matcher.find()) {
				WechatSend
						.sendNews(
								weid,
								"Conqueror",
								"",
								"http://wechat.conqueror.cn/jsp/image.jsp?url="
										+ request
												.getParameter("RESULT_STR"),
								request.getParameter("RESULT_STR"));

			} else {

				WechatSend.sendImage(weid,
						request.getParameter("RESULT_STR"));
				// 旧版本
				// WechatSend.sendText(request.getParameter("openId"),
				// "检测到您的设备版本过低，请升级您的设备版本，设备更新完成后会进行一次重启");
			}
		}

		SimpleDateFormat df = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String id = ConnectionPoolDao.getIdByImei(request
				.getParameter("imei"));

		String url = "http://cloud.conqueror.cn/webservice/sendSMS";
		String msg1 = null;
		try {
			msg1 = URLEncoder.encode(df.format(new Date()) + ",设备"
					+ id + "震动报警", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String param = "key=44CQ55Ge5b%2BG56eR5oqA44CRY29ucXVlcm9y&id="
				+ id + "&mess=" + msg1;
		System.out.println(HttpRequest.sendPost(url, param));*/
		
//		2016-04-17 12:24:32,620-震动报警Sun Apr 17 12:24:32 CST 2016
//		:oISxbt_xAEVkX8r98FdWlmPAMWBI--url=http://webservice.conqueror.cn:8181/pics/7127a0c3-a958-45a2-83d5-c7b59f92a5f2.jpg
//		2016-04-17 12:24:32,907-发送结果Sun Apr 17 12:24:32 CST 2016
//		{"errcode":40007,"errmsg":"invalid media_id hint: [16COdA0082ge12]"}
		
//		2016-04-17 12:24:54,427-震动报警Sun Apr 17 12:24:54 CST 2016
//		:oISxbt2UsNF5brOo2EtvLk5j2_vc--url=http://webservice.conqueror.cn:8181/pics/1c8749a4-981a-4def-901e-ac33c8b63d45.jpg
//		2016-04-17 12:24:54,791-发送结果Sun Apr 17 12:24:54 CST 2016
//		{"errcode":0,"errmsg":"ok"}
		
		
//		String url="http://webservice.conqueror.cn:8181/pics/14be96a3-8c13-4130-8645-c3b7c0508751.jpg";
/*		Pattern pattern = Pattern
				.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
		Matcher matcher = pattern.matcher(url);*/
		
//		System.out.println(url.substring(0,4).equals("http"));
		
/*		String did = "2130106016571146";
		String gpskeys;
		gpskeys = "GPS" + did;
		String  sgps = RedisUtil.get(gpskeys);
		
		GpsPoi p = GpsPoi.stringToBean(sgps);
		
		System.out.println("["+sgps+"]");*/

//		 System.out.println(RedisUtil.get("TOKENCN"));
		
/*		String device_id = "1340106000471553";
		String ticket = ConnectionPoolDao.get_ticket(device_id);
		String json = null;
		if (ticket == null) {
			ticket = UserSendImageMessageUtil
					.getWeChatTicket(device_id);
			ConnectionPoolDao.UPDATEticket(device_id, ticket);
		}

		json = "{\"ticket\":'" + ticket + "'}";
		System.out.println(json);*/
		
/*		WechatSend
		.sendText(
				"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ",	
				"接收到震动报警"
						+ "<a href='http://wechat.conqueror.cn/jsp/getwarn.jsp?weid="
						+ "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"+"&did="+"1330106019311655"  + "'"+">点击获取图片</a>");*/
		
		
/*		WechatSend
		.sendText(
				"oISxbt1hPzivS2ajEimDYFVknjFo",	
				"看到请回复,曝光测试");*/
		
//		Gps g=PositionUtil.gcj_To_Gps84(24.6509143620,118.1511507426);

//		String fence=ConnectionPoolDao.getroundrail("1010106000801142");
//		System.out.println(fence);
//		System.out.println(fence.split(",")[0]); 
//		System.out.println(fence.split(",")[1]); 
//		Gps g2=PositionUtil.gps84_To_Gcj02(Double.parseDouble(fence.split(",")[0]), Double.parseDouble(fence.split(",")[1]));
//		System.out.println(g2.getWgLat());
//		System.out.println(g2.getWgLon());
//		
//		System.out.println(g2.getWgLat()+","+g2.getWgLon()+","+fence.split(",")[2]+","+fence.split(",")[3]);
		
		
		
//		String sgps = RedisUtil.get("1330106019311655");
//		System.out.println(sgps);
//		buy();
		
//		System.out.println(RedisUtil.get("GPS1330106012271375"));
		
		
//		Service s = ConnectionPoolDao.getService(4, "1350106100901255");
//		System.out.println(s.getENDTIME());
		
		

//		List<CarInfo> carlist = ConnectionPoolDao.getCarListOnMap("oISxbt0TaFj2-HFBNkrJoU6OtX5E");
//		System.out.println(carlist);
		
		
		//mybatis的配置文件
/*        String resource = "conf.xml";
        InputStream is = t3.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        String statement = "com.ruiyi.wechat.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        com.ruiyi.wechat.date.User user = session.selectOne(statement, "kjx123");
        System.out.println(user);*/
		
//		buy();
		
		
	/*	String apprid=RedisUtil.get("apprid"+request.getParameter("did"));

		Message msg = Message.newBuilder().setMsgContent("")
				.addExtra("type", "app_image")
				.addExtra("RESULT_STR", request.getParameter("RESULT_STR"))
				.addExtra("pushTime", "0").build();

		CommonUtil.sendMsg(apprid, msg);*/


		

//		Message msg = Message.newBuilder().setMsgContent("")
//				.addExtra("type", "showWay")
//				.addExtra("lat", gps.getWgLat())
//				.addExtra("lon", gps.getWgLon())
//				.addExtra("pushTime", "0").build();
//
//		CommonUtil.sendMsg(registartion_id, msg);
		
		
/*		String registartion_id ="170976fa8a80de6b7e6";
		
		Message msg = Message.newBuilder().setMsgContent("")
				.addExtra("type", "app_command_photo")
				.addExtra("image_url", "http://img0.imgtn.bdimg.com/it/u=60840276,3830062080&fm=21&gp=0.jpg")
				.addExtra("pushtime", System.currentTimeMillis()).build();

		CommonUtil.AppsendMsg(registartion_id, msg);*/
		
		
//		String registartion_id ="010afcbdf33";
//
//		String content = new String("后台测试连接");
//				
//		Message msg = Message.newBuilder().setMsgContent("")
//				.addExtra("type", "speakWords")
//				.addExtra("words", "广播消息:" + content).build();
//
//		CommonUtil.sendMsg(registartion_id, msg);
		
		
	/*	String apprid=RedisUtil.get("apprid"+"1330106117051295");
		System.out.println(apprid);

//		String apprid=RedisUtil.get("apprid"+request.getParameter("did"));

		Message msg = Message.newBuilder().setMsgContent("")
				.addExtra("type", "app_command_video")
				.addExtra("video_url","http://webservice.conqueror.cn:8181/videos/33f727a6-b577-41b9-8020-2c7ced366ea1.mp4")
				.addExtra("pushtime", System.currentTimeMillis()).build();

		CommonUtil.AppsendMsg(apprid, msg);*/
		
		
		
//		System.out.println(RedisUtil.get("TOKENCN"));
//		ong accessId,String secretKey,String title,String content,String token 
		
//		[XGPushManager] XG register push success with token : 12e38a86ebd722be4b5d8ec8f8d464f76ae955ad
//		TPushRegisterMessage [accessId=2100206243, deviceId=0b5de1952fba8946f5b6176c82507149, account=null, ticket=null, ticketType=0, token=12e38a86ebd722be4b5d8ec8f8d464f76ae955ad]
		
//		32
//		XGPushShowedResult [msgId=1599084576, title=云平台SERVICE, content=ss, customContent=, activity=cn.wl.cogoo.service.MainActivity, notificationActionType1]
		
//		XGPushShowedResult [msgId=2, title=标题, content=大家好!, customContent=, activity=cn.wl.cogoo.service.MainActivity, notificationActionType1]
		
//		System.out.println(XingeApp.pushTokenAndroid(2100206243, "94339527f27d20e0f1cc4a766ec3a9fe", "标题", "大家好!", "12e38a86ebd722be4b5d8ec8f8d464f76ae955ad"));
//		System.out.println(RedisUtil.get("xgtoken1380106000941212"));
//		http://wechat.conqueror.cn/ToolsServlet
//		[type=saveXGtoken, xgtoken=12e38a86ebd722be4b5d8ec8f8d464f76ae955ad, did=1330106117051295]
		
//		12e38a86ebd722be4b5d8ec8f8d464f76ae955ad
//		[{"type":"getPhoto","openId":"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"pushTime":"1467004511006"}]
		
//		XingeApp.pushTokenAndroid(2100206243, "94339527f27d20e0f1cc4a766ec3a9fe", "标题", "大家好!", "12e38a86ebd722be4b5d8ec8f8d464f76ae955ad")
		
//		String json ="[{'type':'getPhoto','openId':'oISxbtyMDjzmDSX7Mn3VSvQKdAMQ','pushTime':'1467004511006'}]";
		
//		try {
//			XingeApp.pushTokenAndroid(2100206243, "94339527f27d20e0f1cc4a766ec3a9fe", "标题", json, "12e38a86ebd722be4b5d8ec8f8d464f76ae955ad");
//		} catch (JSONException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		System.out.println(RedisUtil.get("xgtoken1380106000941212"));

		
//		XGPushShowedResult [msgId=9, title=标题, content=[{"type":"getPhoto","openId":"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ","pushTime":"1467007255256"}], customContent=, activity=cn.wl.cogoo.service.MainActivity, notificationActionType1]
//		android.app.ReceiverRestrictedContext@41bacf10
//	}
	
	
//2016-10-29 15:46:20,901-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1477727180&nonceStr=1546157722&package=prepay_id=wx20161029154620cd5fd49ca40070518842&sign=03C119A169C06FA5D23DF05FEAD959A5&
//did=1150106000361215&money=60&service_id=6&
//orderNo=wx71460f3d267fcd411477727174&weid=oISxbt5YotW4h3i-dKHXSQqRPVRg&key=439b35bed14a6578e57b4e933606fa93

//2016-11-16 14:07:48,110-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1479276468&nonceStr=1407421550&package=prepay_id=wx20161116140747ccbd24a8ce0044879882&sign=9F7703A76D56E5520B8B51CAA916FE5B&
//did=1320105152221399&money=60&service_id=6&orderNo=wx71460f3d267fcd411479276462&weid=oISxbtwge-T0xV1kFD-dhJcqgYPY&
//key=2610504e8deef0715ef074e9d71fd27f


//sp/pay.jsp?appid=wx71460f3d267fcd41&t
//imeStamp=1478101500&nonceStr=2344555177&package=prepay_id=wx2016110223450088c22e89790659123430&
//sign=8F489C6F28B8734818D8E29F45B5BE8F&
//did=1320106219601243&money=60&service_id=6&
//orderNo=wx71460f3d267fcd411478101494&weid=oISxbt0ZtC1Ig9vd0US3AjDs9ghU&key=2949fa07bf68e6d5d5c48ed88127fe63


//2016-10-04 20:07:27,623-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1475582847&nonceStr=2007271742&package=prepay_id=wx20161004200727273541299c0910890677&sign=2F02F4294964F3DA5BAB7822EFB9E6BD&
//did=1150105041701255&money=60&service_id=6&orderNo=wx71460f3d267fcd411475582846&weid=oISxbt4HNyOqICdWdH39gAmw9P28&
//key=b7ffed539c852584d15680bc154e7b5f
//2016-10-04 19:55:53,638-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1475582153&nonceStr=1955482724&package=prepay_id=wx20161004195553ed8ad5733b0290839256&sign=0D83810CD3190F614ADAACA0127D59C3&
//did=1150105041701255&money=120&service_id=4&orderNo=wx71460f3d267fcd411475582147&weid=oISxbt4HNyOqICdWdH39gAmw9P28&
//key=576752d58be0b49391e10409b7d7ecc3


//2016-10-05 10:55:51,504-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1475636151&nonceStr=1055456420&package=prepay_id=wx201610051055512f0e3df99f0373627434&sign=10312F846017064F9A58BF2EE1DEE77F&
//did=1040105033881101&money=60&service_id=6&orderNo=wx71460f3d267fcd411475636144&weid=oISxbt1uoe1iOXEJ3-Svov0Qs_b8&
//key=446350742ea1e69c23e34f7fb5e9c9b6


//2016-10-10 22:20:08,306-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1476109208&nonceStr=2220021364&package=prepay_id=wx20161010222008d67ba16d470337195496&sign=3237BFF5A735BF3BAF248F9DEAC75148&
//did=2060105347121031&money=60&service_id=6&orderNo=wx71460f3d267fcd411476109201&weid=oISxbtwL99TJ85h_uhyMsQQjTpFc&
//key=8272641899954a73231419d73ee696eb

//2016-10-14 22:38:32,391-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1476455912&nonceStr=2238264579&package=prepay_id=wx201610142238320f96ed45410947822758&sign=C2B4858CC16BF399A2D650DB95E246F3&
//did=1230105040721242&money=60&service_id=6&orderNo=wx71460f3d267fcd411476455905&weid=oISxbt431yTdDrgcO_V5UgPIMQjo&
//key=329ea49a8e1825dbbd20eaff03d48c34

//2016-10-14 08:35:33,239-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1476405333&nonceStr=0835275165&package=prepay_id=wx20161014083533cf6b29197f0759355669&sign=2004004E80C7F1D155CE1100310B5255&
//did=2070105046821364&money=60&service_id=6&orderNo=wx71460f3d267fcd411476405326&weid=oISxbt14mNF6xOL-xXOmbLOViGdI&
//key=f033ac965b112ff0208aaded8fe15242

//2016-10-29 15:46:20,901-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1477727180&nonceStr=1546157722&package=prepay_id=wx20161029154620cd5fd49ca40070518842&sign=03C119A169C06FA5D23DF05FEAD959A5&
//did=1150106000361215&money=60&service_id=6&orderNo=wx71460f3d267fcd411477727174&weid=oISxbt5YotW4h3i-dKHXSQqRPVRg&
//key=439b35bed14a6578e57b4e933606fa93


//2016-09-08 19:50:16,269-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1473335416&nonceStr=1950101262&package=prepay_id=wx20160908195858dbc4bdd2d80078568473&sign=ADD806A38DEDEF103DCB3203149B61CD&
//did=1310106001481484&money=60&service_id=6&orderNo=wx71460f3d267fcd411473335409&weid=oISxbt-pR_uEqM1813Lj8RkymXxU&
//key=11bee6bb096f78faabb64f286c2ba777


//2016-09-05 18:12:31,214-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&
//timeStamp=1473070351&nonceStr=1812258194&package=prepay_id=wx2016090518211092c6bb35890334711490&sign=55EC81681F82485BA64F656CF5E43CFC&
//did=2150106436611710&money=60&service_id=6&orderNo=wx71460f3d267fcd411473070344&weid=oISxbtwzde3ssF7bBkcWQTCDYb5Q&
//key=bfd0af61afcebc1d289e7bcacfe74972


//11月2笔测试
//9月1笔测试
//8月1笔测试 2016-08-05 17:47:22

//
//		2017-03-03 15:16:32,838-Topay值=jsp/pay.jsp?
//		appid=wx71460f3d267fcd41&timeStamp=1488525392
//		&nonceStr=1516277383
//		&package=prepay_id=wx2017030315163288856228ef0285531575
//		&sign=5705BB2615EC98A252C60947E450C204&did=1150104143582345
//		&money=120&service_id=4
//		&orderNo=wx71460f3d267fcd411488525387&weid=oISxbtwqD-Z7imIx1NhGoUhplfTQ&key=05155eb10f808c612eb2c4e18a34317c



//2017-03-03 15:16:32,838-Topay值=jsp/pay.jsp?
//appid=wx71460f3d267fcd41&
//timeStamp=1488525392&nonceStr=1516277383
//&package=prepay_id=wx2017030315163288856228ef0285531575
//&sign=5705BB2615EC98A252C60947E450C204&did=1150104143582345
//&money=120&service_id=4&orderNo=wx71460f3d267fcd411488525387
//&weid=oISxbtwqD-Z7imIx1NhGoUhplfTQ
//&key=05155eb10f808c612eb2c4e18a34317c



//2017-03-04 17:50:22,743-Topay值=jsp/pay.jsp?appid=wx71460f3d267fcd41&

//		  1488621038
//
//timeStamp=1488621022&nonceStr=1750175242&package=prepay_id=wx20170304175022bdf639738f0776622722
//&sign=C0B4EC99B06E71EF3F7CEE8899EEF35D&did=1050105112001030&money=120&service_id=4&
//orderNo=wx71460f3d267fcd411488621016
//&weid=oISxbt40Sy_ppek-QuK4T0muvFEg
//&key=0bacab4bfab983b1fad92d489632a9bc



	private static void buy()
	{
		String id = "1050105112001030";
		String time = "1488621038";
		String service_id = "4";
		String money = "12000";
		String serial = "wx71460f3d267fcd411488621016";
		String key = "05155eb10f808c612eb2c4e18a34317c";
		
		System.out.println("id="+id);

		if (key.equals(Md5PasswordEncoder.encodePassword(time
				+ service_id + money + id + "godloveyou", "iso"))) {

			// 添加记录
			ConnectionPoolDao.add_payrec(id, time, service_id, money,
					serial);
			// 购买短信服务
			if (service_id.equals("3")) {
				int num = 0;
				switch (money) {
				case "10":
					num = 100;
					break;
				case "20":
					num = 200;
					break;
				case "50":
					num = 500;
					break;

				default:
					break;
				}
				ConnectionPoolDao.updateSimNum(id, num);
			} else if (service_id.equals("4")) { // 购买流量服务
				int addyear = 0;
				switch (money) {
				case "120":
					addyear = 1;
					break;
				case "200":
					addyear = 2;
					break;
				case "260":
					addyear = 3;
					break;

				default:
					break;
				}

				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

				try {
					Date endTime = fmt.parse(ConnectionPoolDao
							.getServiceEndTime(id));
					if (endTime.before(new Date())) {
						ConnectionPoolDao.updateServiceNow(id, addyear);
					} else {
						ConnectionPoolDao.updateService(id, addyear);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (service_id.equals("6")) {
				ConnectionPoolDao.updateServiceliuliang(id, 1);
				String imsi=ConnectionPoolDao.getImsiById(id);
				ConnectionPoolDao.updateSimMadte(imsi, 1);
				
			}
		}
	}
	
	
	private static void gift(String id)
	{
		int addMounth = 1;

		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date endTime = fmt.parse(ConnectionPoolDao.getServiceEndTime(id));
			System.out.println(ConnectionPoolDao.getServiceEndTime(id));
					
			if (endTime.before(new Date())) {
				ConnectionPoolDao.updateServiceGiftNow(id, addMounth);
				System.out.println("过期了");
			} else {
				System.out.println("还没到");
				ConnectionPoolDao.updateServiceGift(id, addMounth);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	private static void t() {
		try {

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String nonce_str = md5.encodePassword("123456", Math.random());
			long out_trade_no = System.currentTimeMillis();
			String spbill_create_ip = "11.11.11.11";
			// String spbill_create_ip = request.getParameter("id");

			String stringA = "appid=wx71460f3d267fcd41&body=JSAPI支付测试&mch_id=1317179801&nonce_str="
					+ nonce_str
					+ "&"
					+ "notify_url=http://wechat.conqueror.cn/jsp/buyresult.jsp&"
					+ "openid=oISxbtyMDjzmDSX7Mn3VSvQKdAMQ&"
					+ "out_trade_no="
					+ out_trade_no
					+ "&"
					+ "spbill_create_ip="
					+ spbill_create_ip
					+ "&"
					+ "total_fee=1&"
					+ "trade_type=JSAPI";

			String stringSignTemp = stringA
					+ "&key=11111111222222223333333344444444";
			String sign = MD5.MD5Encode(stringSignTemp).toUpperCase();

			String rqestXml = "<xml>"
					+ "<appid>wx71460f3d267fcd41</appid>"
					+ "<body>JSAPI支付测试</body>"
					+ "<mch_id>1317179801</mch_id>"
					+ "<nonce_str>"
					+ nonce_str
					+ "</nonce_str>"
					+ "<notify_url>http://wechat.conqueror.cn/jsp/buyresult.jsp</notify_url>"
					+ "<openid>oISxbtyMDjzmDSX7Mn3VSvQKdAMQ</openid>"
					+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
					+ "<spbill_create_ip>" + spbill_create_ip
					+ "</spbill_create_ip>" + "<total_fee>1</total_fee>"
					+ "<trade_type>JSAPI</trade_type>" + "<sign>" + sign
					+ "</sign>" + "</xml>";

			String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

			System.out.println(rqestXml);

			String xmlresult = HttpRequest.sendPostXml(url, rqestXml);

			Map<String, String> map = XmlUtil.xml2Map(new String(xmlresult
					.toString().getBytes(), "UTF-8"));

			// {xml.mch_id=1317179801, xml.trade_type=JSAPI, xml.return_msg=OK,
			// xml.prepay_id=wx201603121323160e316a96630082583515,
			// xml.result_code=SUCCESS, root.name=xml,
			// xml.nonce_str=12mpxHY9RAiOlX3j,
			// xml.sign=A3A6745CB5B73FDEA7C90C6E2EE5B04E,
			// xml.return_code=SUCCESS, xml.appid=wx71460f3d267fcd41}
			System.out.println(map);

			if (map.get("xml.return_msg").equals("OK")) {
				/*
				 * String url = DeString.url + "/buy.jsp?weid=" +
				 * request.getParameter("weid"); Map<String, String> ret =
				 * sign(RedisUtil.get("JSAPI"), url);
				 * 
				 * request.setAttribute("appid", "wx71460f3d267fcd41");
				 * request.setAttribute("jsapi_ticket",
				 * ret.get("jsapi_ticket")); request.setAttribute("timestamp",
				 * ret.get("timestamp")); request.setAttribute("nonceStr",
				 * ret.get("nonceStr")); request.setAttribute("signature",
				 * ret.get("signature"));
				 * 
				 * 
				 * request.getRequestDispatcher("jsp/lockdevice.jsp").forward(
				 * request, response);
				 */

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public static double[] changegps(double slat,double slng) throws Exception
    {
		double[] result=new double[2];
		boolean flag = false;
		double offsetLat = 0.0, offsetLng = 0.0;
		double oldLat = 0.0, oldLng = 0.0;

		
		if (Math.abs(slat - oldLat) > 0.1
				|| Math.abs(slng - oldLng) > 0.1) {
			flag = true;
			oldLat = slat;
			oldLng = slng;
		}
		
		if (flag) {
			if (18.2 <= slat && slat <= 53.5 && 73.6 <= slng
					&& slng <= 134.7) {
//				double[] offsetMap = changegps(slat, slng);
				
				
				
				double[] latlng=new double[2];
				double nlat = Math.round(slat * 100) / 100.0;
				double nlng = Math.round(slng * 100) / 100.0;
				DBObject obj=new BasicDBObject();
				obj.put("lng", nlng);
				obj.put("lat",nlat);
				DBObject obj1=new BasicDBObject();
				obj1.put("_id", 0);
				obj1.put("offsetlng", 1);
				obj1.put("offsetlat", 1);
				List<DBObject> list=MongoDBManager3.getInstance().find("offset", obj,obj1);
				 double offset_lng=0,offset_lat=0;
				 if(list.size()>0){
		        offset_lng =Double.parseDouble(list.get(0).get("offsetlng").toString());;

		        offset_lat =Double.parseDouble(list.get(0).get("offsetlat").toString());
				}
		        latlng[0]= offset_lat;
		        latlng[1] =offset_lng;
				
				
				
				// 118.2 24.7
				if (null != latlng) {
					try {
						offsetLat =latlng[0];
						offsetLng = latlng[1];
						
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("offsetLng_null");
					}

				}
			}
			flag = false;
		}
		
		result[0]=slat + offsetLat;
		
		result[1]=slng + offsetLng;
    	
		return result;
    }
}
