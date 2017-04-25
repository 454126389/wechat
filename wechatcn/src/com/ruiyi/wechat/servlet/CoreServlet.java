package com.ruiyi.wechat.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.api.push.model.Message;

import com.ruiyi.wechat.model.Batch;
import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.model.GprsParameter;
import com.ruiyi.wechat.model.News;
import com.ruiyi.wechat.model.Parameter;
import com.ruiyi.wechat.model.User;
import com.ruiyi.wechat.string.DeString;
import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.CommonUtil;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.Gps;
import com.ruiyi.wechat.util.HttpRequest;
import com.ruiyi.wechat.util.Md5PasswordEncoder;
import com.ruiyi.wechat.util.MyListener;
import com.ruiyi.wechat.util.PositionUtil;
import com.ruiyi.wechat.util.ReMsg;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.SHA1;
import com.ruiyi.wechat.util.XGUtil;
import com.ruiyi.wechat.util.XmlUtil;

//import com.baidu.bdt.java.util.json.JSONObject;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class CoreServlet extends HttpServlet {
//	 private static final long serialVersionUID = 4440739483644821986L;


	// 自定义 token
//	 private String TOKEN = "";
	private boolean flag=false;

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.addHeader("Cache-Control", "no-cache");// 浏览器和缓存服务器都不应该缓存页面信息
		response.addHeader("Cache-Control", "no-store");// 请求和响应的信息都不应该被存储在对方的磁盘系统中；
		response.addHeader("Cache-Control", "must-revalidate");// 于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
		response.flushBuffer();


		
		String action = request.getParameter("action");

		if (action.equals("test")) {

			if(!flag)
			{
				flag=true;
			new Thread(new Runnable() {
				 
			    @Override
			    public void run() {
			        // TODO Auto-generated method stub
			    	
			    	
					MyListener listener = new MyListener();
					RedisUtil.pool.getResource().subscribe(listener,
							new String[] { "fenceAlarm", "image","sim.expire" });
			    }
			}).start();
			}else
			{
				response.getWriter().print("去去去，别捣乱。");
			}
			

		}if (action.equals("test2")) {

			System.out.println("11");
			System.out.println(HttpRequest.sendGet("http://webservice.conqueror.cn:8181/sim/smsActivateCode", "phone=13163905721"));
			System.out.println("11");

		} else if (action.equals("yz")) {
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");

			String[] str = { "845C2550903CE6FA54CACDB82EAD4350", timestamp,
					nonce };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			String digest = new SHA1().getDigestOfString(bigStr.getBytes())
					.toLowerCase();

			// 确认请求来至微信
			if (digest.equals(signature)) {
				response.getWriter().print(echostr);
			}

		}

	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO 消息的接收、处理、响应

		Logger logger = Logger.getLogger(ToolsServlet.class.getName());


		// 解析xml
		StringBuffer sb = new StringBuffer();
		String line;
		Map<String, String> map = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			map = XmlUtil
					.xml2Map(new String(sb.toString().getBytes(), "UTF-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 反馈信息
		String str = null;

		// 文字反馈
		// 自动回复事件
		if ("subscribe".equals(map.get("xml.Event"))) {

			str = ReMsg.reText(map, Language.subscribe_str);

		} else if ("SCAN".equals(map.get("xml.Event"))) {
			String s = map.get("xml.EventKey");
			s = s.replaceAll("‘", "");

			if (s.length() > 0) {

				
				if (ConnectionPoolDao.checkDevice(s))
				{
					if (!ConnectionPoolDao.checkUser(s))
						str = ReMsg.reText(map,
								oneRegister(s, map.get("xml.FromUserName")));

					else
						str = ReMsg.reText(map,
								updatUser(s, map.get("xml.FromUserName")));
				}else
				{
					str = ReMsg.reText(map,"设备不存在");
				}
				


			} else
				str = ReMsg.reText(map, Language.subscribe_str);

		} else if ("location".equals(map.get("xml.MsgType"))) {

			String last = RedisUtil.hget("LASTSEND",
					map.get("xml.FromUserName"));
			if (last == null)
				last = "0";

			Long now = System.currentTimeMillis();

			if (now - Long.parseLong(last) > 10000) {
				RedisUtil.hset("LASTSEND", map.get("xml.FromUserName"), ""
						+ now);

				str = ReMsg.reText(map, "正在为您导航到" + map.get("xml.Label"));

				List<CarInfo> carlist = ConnectionPoolDao.getCarListOnMap(map
						.get("xml.FromUserName"));

				if (carlist.size() == 0) {
					str = ReMsg.reText(map, "未绑定任何设备");
				} else {
					List<CarInfo> carlist2 = new ArrayList<CarInfo>();
					for (int i = 0; i < carlist.size(); i++) {
						if (DeviceType.getCutType((carlist.get(i).getId())
								.substring(0, 3)) == 3)
							carlist2.add(carlist.get(i));
					}

					if (carlist2.size() == 0)
						str = ReMsg.reText(map, "已绑定的设备中，不支持语音导航。");
					else {

						String selectid = ConnectionPoolDao.getSelect(map
								.get("xml.FromUserName"));
						logger.info("selectid1=" + selectid);

						if (selectid == null)
							selectid = carlist2.get(0).getId();

						logger.info("selectid2=" + selectid);

						try {

							logger.info("ToUserName="
									+ map.get("xml.ToUserName"));
							logger.info("FromUserName="
									+ map.get("xml.FromUserName"));
							logger.info("MsgType=" + map.get("xml.MsgType"));
							logger.info("Location_X="
									+ map.get("xml.Location_X"));
							logger.info("Location_Y="
									+ map.get("xml.Location_Y"));
							logger.info("Scale=" + map.get("xml.Scale"));
							logger.info("Label=" + map.get("xml.Label"));

							double slat = Double.parseDouble(map
									.get("xml.Location_X"));
							double slng = Double.parseDouble(map
									.get("xml.Location_Y"));

							Gps gps = PositionUtil.gcj02_To_Bd09(slat, slng);

							// 发送车机指令
							String registartion_id = ConnectionPoolDao
									.getTerminalRegistrationId(selectid);

							if (null != registartion_id) {

								Message msg1 = Message.newBuilder()
										.setMsgContent("")
										.addExtra("type", "showWay")
										.addExtra("lat", gps.getWgLat())
										.addExtra("lon", gps.getWgLon())
										.addExtra("pushTime", "0").build();

								Message msg2 = Message.newBuilder()
										.setMsgContent("")
										.addExtra("type", "remoteCommand")
										.addExtra("lat", gps.getWgLat())
										.addExtra("lon", gps.getWgLon())
										.addExtra("text", map.get("xml.Label"))
										.build();
								CommonUtil.sendMsg(registartion_id, msg1);
								CommonUtil.sendMsg(registartion_id, msg2);

							}
							String xgtoken = RedisUtil
									.hget("xgtoken", selectid);

							if (xgtoken != null) {

								String json1 = "{\"type\":\"" + "showWay"
										+ "\"," + "\"lat\":\"" + gps.getWgLat()
										+ "\"," + "\"lon\":\"" + gps.getWgLon()
										+ "\"," + "\"pushTime\":\""
										+ System.currentTimeMillis() + "\"}";

								String json2 = "{\"type\":\"" + "remoteCommand"
										+ "\"," + "\"lat\":\"" + gps.getWgLat()
										+ "\"," + "\"lon\":\"" + gps.getWgLon()
										+ "\"," + "\"maptype\":\"" + "Bd09"
										+ "\"," + "\"text\":\""
										+ map.get("xml.Label") + "\","
										+ "\"pushTime\":\""
										+ System.currentTimeMillis() + "\"}";

								logger.info("预约导航" + json2);

								XGUtil.sendMsg(xgtoken, json1);
								XGUtil.sendMsg(xgtoken, json2);
							}

							
					
							
						} catch (Exception e) {
							logger.info("位置导航error=" + e.toString());

						}
						str = ReMsg.reText(map, "正在为您导航到"+map.get("xml.Label"));
					}
				}

			}

		}else if ("text".equals(map.get("xml.MsgType"))) {
			str = ReMsg.reText(map,"您好，有问题请联系在线客服  400-639-2999");
		}
		else if ("voice".equals(map.get("xml.MsgType"))) {

		/*	List<CarInfo> carlist = ConnectionPoolDao.getCarListOnMap(map
					.get("xml.FromUserName"));

			if (carlist.size() == 0) {
				str = ReMsg.reText(map, "未绑定任何设备");
			} else {
				List<CarInfo> carlist2 = new ArrayList<CarInfo>();
				for (int i = 0; i < carlist.size(); i++) {
					if (DeviceType.getCutType((carlist.get(i).getId())
							.substring(0, 3)) == 3) {
						carlist2.add(carlist.get(i));

					}
				}
				if (carlist2.size() == 0)
					str = ReMsg.reText(map, "已绑定的设备中，不支持语音导航。");
				else {

					String selectid = ConnectionPoolDao.getSelect(map
							.get("xml.FromUserName"));

					if (selectid == null)
						selectid = carlist2.get(0).getId();

					logger.info("selectid2=" + selectid);
					logger.info("FromUserName" + map.get("xml.FromUserName"));
					logger.info("Recognition=" + map.get("xml.Recognition"));

					String code = doSendWay(map.get("xml.FromUserName"),
							selectid, map.get("xml.Recognition"));

					logger.info("code=" + code);
					str = ReMsg.reText(map, code);

				}
			}*/

		}
		else if ("CLICK".equals(map.get("xml.Event"))) {
			if ("MYDEVICES".equals(map.get("xml.EventKey"))) {
				String num = ConnectionPoolDao.getLockCarNum(map
						.get("xml.FromUserName"));

				News new0 = new News();
				if (Integer.parseInt(num) > 0)
					new0.setTitle(Language.lock_num + ":" + num);
				else
					new0.setTitle(Language.lock_num_zero);

				new0.setDescription("Radareye Technologies, Inc.");
				new0.setUrl(DeString.url + "/jsp/lockdevice.jsp?weid="
						+ map.get("xml.FromUserName"));
				new0.setPicUrl(DeString.url + "/jsp/images/log_min.jpg");

				News new2 = new News();
				new2.setTitle("查询车辆");
				new2.setDescription("Radareye Technologies, Inc.");
				new2.setUrl(DeString.url + "/jsp/carlocationlast4.jsp?weid="
						+ map.get("xml.FromUserName"));
//				new2.setUrl("http://sim.conqueror.cn/Wechat/map/Location?weid="+map.get("xml.FromUserName"));
				
				
				
				new2.setPicUrl(DeString.url + "/jsp/images/icon_search.jpg");

				// 语音播报
				News new4 = new News();
				new4.setTitle("语音播报");
				new4.setDescription("help");
				new4.setUrl(DeString.url + "/jsp/speaknews.jsp?weid="
						+ map.get("xml.FromUserName"));
				new4.setPicUrl(DeString.url + "/jsp/images/icon_speek.jpg");

				// 设备设置
				News newsetting = new News();
				newsetting.setTitle("设备设置");
				newsetting.setDescription("");
				newsetting.setUrl(DeString.url + "/jsp/devicesetting.jsp?weid="
						+ map.get("xml.FromUserName"));
				newsetting.setPicUrl(DeString.url
						+ "/jsp/images/icon_setting.jpg");

				// 绑定设备
				News new3 = new News();
				new3.setTitle("绑定设备");
				new3.setDescription("help");
				new3.setUrl(DeString.url + "/jsp/lockdevice.jsp?weid="
						+ map.get("xml.FromUserName"));
				new3.setPicUrl(DeString.url + "/jsp/images/icon_lock.jpg");

				News newshebei = new News();
				newshebei.setTitle(Language.t11);
				newshebei.setDescription("Radareye Technologies, Inc.");
				newshebei.setUrl(DeString.url
						+ "/userRegisterServlet?action=getcartypelist&weid="
						+ map.get("xml.FromUserName"));
				newshebei.setPicUrl(DeString.url
						+ "/jsp/images/icon_register.jpg");


				List<News> newsList = new ArrayList<News>();
				newsList.add(new0);

				newsList.add(new2);// 查询车辆
				newsList.add(new4);// 语音播报
				newsList.add(newsetting);// 语音播报
				newsList.add(new3);// 绑定设备
				newsList.add(newshebei);// 设备中心

				str = ReMsg.reNews(map, newsList);
			} else if ("CARFUNCTION".equals(map.get("xml.EventKey"))) {
				News new0 = new News();
				new0.setTitle(Language.t20);
				new0.setDescription("Radareye Technologies, Inc.");
				new0.setUrl("");
				new0.setPicUrl(DeString.url + "/jsp/images/log_min.jpg");

				News new_device = new News();
				new_device.setTitle("车机功能");
				new_device.setDescription("help");
				new_device.setUrl(DeString.url + "/jsp/result.jsp?weid="
						+ map.get("xml.FromUserName"));
				new_device.setPicUrl(DeString.url
						+ "/jsp/images/icon_device.jpg");

				News new_getOne = new News();
				new_getOne.setTitle("微信接人");
				new_getOne.setDescription("help");
				new_getOne.setUrl(DeString.url + "/jsp/PhonePickup.jsp?weid="
						+ map.get("xml.FromUserName"));
				new_getOne.setPicUrl(DeString.url
						+ "/jsp/images/icon_getone.png");

				News new_gps = new News();
				new_gps.setTitle("微信接人");
				new_gps.setDescription("help");
				new_gps.setUrl(DeString.url + "/jsp/PhonePickup.jsp?weid="
						+ map.get("xml.FromUserName"));
				new_gps.setPicUrl(DeString.url + "/jsp/images/icon_getone.png");

				List<News> newsList = new ArrayList<News>();
				newsList.add(new0);
				newsList.add(new_device);
				newsList.add(new_getOne);

				str = ReMsg.reNews(map, newsList);

			} 
			else if ("BUYSERVICE".equals(map.get("xml.EventKey"))) {
				News new0 = new News();
				new0.setTitle(Language.t30);
				new0.setDescription("Radareye Technologies, Inc.");
				new0.setUrl("");
				new0.setPicUrl(DeString.url + "/jsp/images/log_min.jpg");

				News newbuyservice = new News();
				newbuyservice.setTitle("购买服务");
				newbuyservice.setDescription("");
				newbuyservice.setUrl(DeString.url + "/jsp/buy.jsp?weid="
						+ map.get("xml.FromUserName"));
				newbuyservice.setPicUrl(DeString.url
						+ "/jsp/images/icon_shop.jpg");


				List<News> newsList = new ArrayList<News>();
				newsList.add(new0);
				newsList.add(newbuyservice);
				str = ReMsg.reNews(map, newsList);
			} 
			else if ("simple_moden".equals(map.get("xml.EventKey"))) {
				str = ReMsg.reText(map, Language.subscribe_str);
			}
		}


		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(str);
	}

	private String updatUser(String id, String weid) {
		String s = null;
		if (ConnectionPoolDao.doLockCar(weid, id))
			s = "设备绑定成功";
		else
			s = "您已经绑定该设备";
		return s;
	}

	private static String oneRegister(String id, String weid) {

		StringBuffer sbstr = new StringBuffer();
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String pswmd5 = md5.encodePassword(
				id.substring(id.length() - 6, id.length()), id);
		System.out.println("帐号：" + id);
		System.out.println("密码：" + id.substring(id.length() - 6, id.length()));

		// 获得注册时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Calendar calender = Calendar.getInstance();
		String time = simpleDateFormat.format(calender.getTime()).toString();

		// 添加用户
		User user = new User(id, pswmd5, time, "192.168.1.1");

		if (!ConnectionPoolDao.addUser2(user))
			sbstr.append("添加用户失败+name=" + id + "--psw="
					+ id.substring(id.length() - 6, id.length()));

		// 取得用户id
		String userid = ConnectionPoolDao.getUserId(id);
		// 添加用户角色
		if (!ConnectionPoolDao.addUserRole(userid))
			sbstr.append("添加用户角色失败" + id);

		// 获得设备批次号
//		String batchid = ConnectionPoolDao.getBatchId(id);
		// 获得设置批次信息
//		Batch batch = ConnectionPoolDao.getBatch(batchid);
		// 增加激活设备

		// 激活标志
		if (!ConnectionPoolDao.updateActivate(1, id)) {
			sbstr.append("激活标志失败" + id);
		}

		Parameter parameter = new Parameter(id,id.substring(0, 3), weid);
		// String tic=ConnectionPoolDao.get_ticket(id);


		RedisUtil.set("LBS", id);

		if (!ConnectionPoolDao.addParameter2(parameter))
			sbstr.append("添加p设备失败" + id);

		ConnectionPoolDao.doLockCar(weid, id);

		// 添加设备用户对应表
		if (!ConnectionPoolDao.addUserParameter(userid, id))
			sbstr.append("添加设备用户对应表失败" + id);

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		Calendar c = Calendar.getInstance();
		String starttime = df.format(c.getTime()); // 返回String型的时间

		/*
		 * int addmonth = 1; if (ConnectionPoolDao.getCutType(id) == 3)
		 */
		int addmonth = 3;
		if (ConnectionPoolDao.getCutType(id) == 4
				|| ConnectionPoolDao.getCutType(id) == 3||ConnectionPoolDao.getCutType(id) == 6)
			addmonth = 12;
		c.add(Calendar.MONTH, addmonth); // 将当前日期加一个月
		String endtime1 = df.format(c.getTime()); // 返回String型的时间
		c.add(Calendar.MONTH, -1); // 将当前日期减一个月
		c.add(Calendar.YEAR, 1); // 将当前日期加一个月
		String endtime2 = df.format(c.getTime()); // 返回String型的时间

		// 添加服务状态表
		if (!ConnectionPoolDao.addServiceParameter(id, starttime, endtime1,
				endtime2))
			sbstr.append("添加服务状态应表失败" + id);

		// 放入缓存
		String key = "PAR" + id;// 终端参数KEY
		String sp = RedisUtil.get(key);
//		if (sp != null) {
//			GprsParameter p = GprsParameter.stringToBean(sp);
			GprsParameter p=new GprsParameter();
			p.setLicence("车机"+id);
			if(ConnectionPoolDao.getCutType(id)==4)
				p.setMoveInterval(Short.parseShort("17280"));
			else
			p.setMoveInterval(Short.parseShort("30"));
			p.setStopInterval(Short.parseShort("1800"));
			p.setTr_r(Short.parseShort("30"));
			p.setFortiry((short) (Short.parseShort("16")));
			p.setWeatherTag(true);
			p.setNewsOpen(true);
			p.setEmailTag(true);

			sp = GprsParameter.beanToString(p);
			RedisUtil.set(key, sp);
			String nkey = "NOT" + id;
			RedisUtil.del(nkey);
//		}

		if (sbstr.length() == 0)
			sbstr.append("设备" + id
					+ "扫码注册成功+\n帐号为id，密码为id后六位。\n为了您的帐号安全请及时登录修改密码");
		return sbstr.toString();
	}

	private static String doSendWay(String weid, String did, String code) {
		// TODO Auto-generated method stub

		// 获取用户位置
		String token = RedisUtil.get("TOKENCN");
		String uriAPI = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		uriAPI = uriAPI.replace("ACCESS_TOKEN", token);
		uriAPI = uriAPI.replace("OPENID", weid);
		String resultGet = CommonUtil.urlGet(uriAPI);
		JSONObject jsonParser = null;
		String city = null;

		try {
			jsonParser = new JSONObject(resultGet);
			city = jsonParser.getString("city");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 语意解析
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("query", code);
		paramsMap.put("category", "map");
		paramsMap.put("city", city);
		paramsMap.put("appid", "wx71460f3d267fcd41");
		paramsMap.put("uid", weid);
		
		
		

		String requestUrl = "https://api.weixin.qq.com/semantic/semproxy/search?access_token=YOUR_ACCESS_TOKEN";
		requestUrl = requestUrl.replace("YOUR_ACCESS_TOKEN",
				RedisUtil.get("TOKENCN"));

		String result = CommonUtil.httpsRequest(requestUrl, "POST",
				net.sf.json.JSONObject.fromObject(paramsMap).toString());
		JSONObject jsonParserReq = null;
		String loc_ori = null;
		org.json.JSONObject end_loc = null;
		try {
			jsonParserReq = new JSONObject(result);
			org.json.JSONObject semantic = jsonParserReq.getJSONObject("semantic");
					
			org.json.JSONObject details = semantic.getJSONObject("details");

			String intent = semantic.getString("intent");
			if ("SEARCH".equals(intent))
				end_loc = details.getJSONObject("location");
			else
				end_loc = details.getJSONObject("end_loc");

			loc_ori = end_loc.getString("loc_ori");

			// 位置经纬度解析
			String geoUrl = "http://api.map.baidu.com/telematics/v3/geocoding?keyWord="
					+ loc_ori
					+ "&cityName="
					+ city
					+ "&out_coord_type=gcj02&ak=nHRgHTN4F1Gv1lznGoOOXnqy&output=json";
			System.out.println(geoUrl);
			String resultGeo = CommonUtil.urlGet(geoUrl);
			JSONObject jsonParserGeo = null;
			String lat = "";
			String lng = "";
			try {
				jsonParserGeo = new JSONObject(resultGeo);
				org.json.JSONObject results = jsonParserGeo
						.getJSONObject("results");
				org.json.JSONObject location = results
						.getJSONObject("location");
				lat = location.getString("lat");
				lng = location.getString("lng");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 发送车机指令
			String registartion_id = ConnectionPoolDao
					.getTerminalRegistrationId(did);
			Message msg = Message.newBuilder().setMsgContent("")
					.addExtra("type", "remoteCommand").addExtra("lat", lat)
					.addExtra("lon", lng).addExtra("text", loc_ori).build();
			CommonUtil.sendMsg(registartion_id, msg);

			return "正在为您执行指令《" + code + "》，请确保您的设备已经开机且联网。";

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "导航指令识别错误";
		}

	}

	public static void main(String[] args) {

		String code = doSendWay("oISxbt5xiz3bBNA0mhnaVsaO2UCY",
				"1330106030101410", "我要去厦门北站！");

		System.out.println("code=" + code);


	}
}
