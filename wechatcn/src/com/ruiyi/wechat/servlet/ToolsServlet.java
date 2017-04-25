package com.ruiyi.wechat.servlet;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.MimetypesFileTypeMap;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.api.push.model.Message;

import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.model.Sim;
import com.ruiyi.wechat.mybatis.inter.IUserOperation;
import com.ruiyi.wechat.mybatis.model.User_sim;
import com.ruiyi.wechat.string.DeString;
import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.CommonUtil;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.Gps;
import com.ruiyi.wechat.util.HttpRequest;
import com.ruiyi.wechat.util.MD5;
import com.ruiyi.wechat.util.Md5PasswordEncoder;
import com.ruiyi.wechat.util.PositionUtil;
import com.ruiyi.wechat.util.ReMsg;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.SignUtil;
import com.ruiyi.wechat.util.UserSendImageMessageUtil;
import com.ruiyi.wechat.util.WechatSend;
import com.ruiyi.wechat.util.XGUtil;
import com.ruiyi.wechat.util.XmlUtil;
import com.utils.Sha1Util;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class ToolsServlet extends HttpServlet {

	static SqlSessionFactory factory;
	static {
		try {
			String resource = "conf.xml";
			Reader reader = null;
			try {
				reader = Resources.getResourceAsReader(resource);
			} catch (IOException e) {
				e.printStackTrace();
			}
			factory = new SqlSessionFactoryBuilder().build(reader);
			factory.getConfiguration().addMapper(IUserOperation.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String savePath;
	/** 这里的名字和html的名字必须对称 */
	private File img;
	/** 要上传的文件类型 */
	private String imgContentType;
	/** 文件的名称 */
	private String imgFileName;

	private String orderId;
	/**
	 * 指定的上传类型 zip 和 图片格式的文件
	 */
	private static final String[] types = { "application/x-zip-compressed",
			"ZIP", "image/pjpeg", "image/x-png" }; // "application/octet-stream; charset=utf-8",

	static int i = 0;

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		initLog4j();
	}

	private static void initLog4j() {
		Properties prop = new Properties();

		prop.setProperty("log4j.rootLogger",
				"info, ServerDailyRollingFile, stdout");
		prop.setProperty("log4j.appender.ServerDailyRollingFile",
				"org.apache.log4j.DailyRollingFileAppender");

		prop.setProperty("log4j.appender.ServerDailyRollingFile.DatePattern",
				"'.'yyyy-MM-dd");
		prop.setProperty("log4j.appender.ServerDailyRollingFile.File",
				"'.'logs/notify-subscription.log");

		prop.setProperty("log4j.appender.ServerDailyRollingFile.layout",
				"org.apache.log4j.PatternLayout");
		prop.setProperty(
				"log4j.appender.ServerDailyRollingFile.layout.ConversionPattern",
				"%d-%m%n");
		prop.setProperty("log4j.appender.ServerDailyRollingFile.Append", "true");

		prop.setProperty("log4j.appender.stdout",
				"org.apache.log4j.ConsoleAppender");
		prop.setProperty("log4j.appender.stdout.layout",
				"org.apache.log4j.PatternLayout ");
		prop.setProperty("log4j.appender.stdout.layout.ConversionPattern",
				"%d{yyyy-MM-dd HH:mm:ss} %p [%c] %m%n");

		PropertyConfigurator.configure(prop);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		// response.setContentType("text/xml;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		// response.setContentType("application/json; charset=utf-8");

		SqlSession sqlSession;

		String action = request.getParameter("action");

		Logger logger = Logger.getLogger(ToolsServlet.class.getName());

		PrintWriter out = null;
		out = response.getWriter();
		try {

			if (action.equals("getVersion")) {
				String json = "{\"appname\":\"jtapp12\",\"apkname\":\"jtapp-12-updateapksamples.apk\",\"verName\":1.0.1,\"verCode\":2}";
				out = response.getWriter();
				out.write(json);
			} else if (action.equals("getWechatPic")) {

				String token = RedisUtil.get("TOKENCN");
				String json = "{\"token\":'" + token + "'}";
				out = response.getWriter();
				out.write(json);

			}
			if (action.equals("carChoise")) {

				/*
				 * String did=request.getParameter("did"); String
				 * weid=request.getParameter("weid");
				 * ConnectionPoolDao.updateChoice1(weid);
				 * ConnectionPoolDao.updateChoice2(did);
				 */

			} else if (action.equals("get_device_id")) {

				String device_id = request.getParameter("device_id");
				String ticket = ConnectionPoolDao.get_ticket(device_id);
				String json = null;
				if (ticket == null) {
					ticket = UserSendImageMessageUtil
							.getWeChatTicket(device_id);
					ConnectionPoolDao.UPDATEticket(device_id, ticket);
				}
				// else{
				//
				// ticket = UserSendImageMessageUtil.getWeChatTicket(device_id);
				// ConnectionPoolDao.ADDticket(device_id, ticket);
				// }

				json = "{\"ticket\":'" + ticket + "'}";
				out = response.getWriter();
				out.write(json);

			}
			// else if(action.equals("save_registration_id"))
			// {
			// String registration_id = request.getParameter("registration");
			//
			// }
			else if (action.equals("getXGToken")) {
				String did = request.getParameter("did");
				String xgtoken = RedisUtil.hget("xgtoken", did);

				String json = "{\"xgtoken\":'" + xgtoken + "'}";
				out.write(json);

			} else if (action.equals("test")) {

			
				String channel="fenceAlarm";
				String message="1130106010101151$$121.3993-31.2879,2016-11-11 16:27:05,车机1150106124201701,震动报警,上海市宝山区真北路3777弄-95号房";
				
				
				try {
					switch (channel) {
					case "fenceAlarm":

						
						logger.info("mychannel=1");
						
						// 记录信息
						String carMess[] = message.toString().split("\\$\\$");
						
						List<String> weidlist = ConnectionPoolDao.getweidByNo(carMess[0]);

						logger.info("mychannel=2");
						
							String msg2 = carMess[1].toString();
							String carMess2[] = msg2.split(",");
							RedisUtil.set("SHOCK" + carMess[0], carMess2[1]);
							
							logger.info("mychannel=3");	
							String lon = null;
							String lat=null;
							if (carMess2[0]!=null) {
								 lon=carMess2[0].split("-")[0];
								 lat=carMess2[0].split("-")[1];
							}
							
							logger.info("mychannel=4");
							List<String> latlnglist = SignUtil.changegps(lat, lon);
							
							String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
//							String loadurl="http://wechat.conqueror.cn/jsp/map.html?lat="+latlnglist.get(0)+"&lng="+latlnglist.get(1);
							String loadurl="http://apis.map.qq.com/uri/v1/geocoder?coord="+latlnglist.get(1)+","+latlnglist.get(0)+"&referer=myapp";
							logger.info("mychannel=5");
							
							try {
								
								logger.info("mychannel=6");
								
								for (int i = 0; i < weidlist.size(); i++)
									WechatSend.sendNews(weidlist.get(i), "Conqueror", carMess[1],loadurl,imaUrl);

								
								logger.info("mychannel=7");
								
							} catch (Exception e) {
								logger.info("mychannel=8");
								e.printStackTrace();
							}
							
							logger.info("mychannel=9");
							
						break;
				
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					logger.info("mychannel=" + "订阅报错" + "\n" + e.toString());
				}

			} else if (action.equals("saveid")) {
				/*
				 * Logger logger =
				 * Logger.getLogger(ToolsServlet.class.getName());
				 * logger.info("saveid_start"); logger.info("saveid=" +
				 * request.getParameter("registration_id"));
				 */
			} else if (action.equals("check_pick_up")) {
				// ConnectionPoolDao.UPDATEticket(device_id, ticket);
			} else if (action.equals("getWarn")) {

				String weid = request.getParameter("weid");
				String last = RedisUtil.hget("LASTSEND", weid);
				if (last == null)
					last = "0";
				Long now = System.currentTimeMillis();
				if (now - Long.parseLong(last) > 10000) {
					RedisUtil.hset("LASTSEND", weid, "" + now);

					String registartion_id = ConnectionPoolDao
							.getTerminalRegistrationId(request
									.getParameter("did"));

					String path = request.getParameter("path");
					String time = request.getParameter("time");
					String ver ="i5X_CN";
							try {
								ver=request.getParameter("ver");
							} catch (Exception e) {
								// TODO: handle exception
							}
							

					logger.info("jpush-getWarn=--1"
							+ request.getParameter("did") + "--path=" + path
							+ "---time=" + time);

					if (null == path) {
						logger.info("jpush-getWarn=--" + 2);
						path = "";
					}

					if (null != registartion_id) {
						logger.info("jpush-getWarn=--" + 3);
						Message msg = Message
								.newBuilder()
								.setMsgContent("")
								.addExtra("type", "getWarn")
								.addExtra("openId",
										request.getParameter("weid"))
								.addExtra("path", path)
								.addExtra("pushTime", "0").build();

						logger.info("jpush-getWarn=--"
								+ CommonUtil.sendMsg(registartion_id, msg));
					}

					logger.info("jpush-getWarn=--" + 4);

					String did = request.getParameter("did");
					did = did.split("@")[0];
					String xgtoken = RedisUtil.hget("xgtoken", did);

					logger.info("jpush-getWarn=--" + 5 + "xgtoken=" + xgtoken
							+ "--did=" + did);

					if (xgtoken != null) {
						logger.info("jpush-getWarn=--" + 6
								+ request.getParameter("weid"));

						String json = "{\"type\":\"" + "getWarn" + "\","
								+ "\"openId\":\""
								+ request.getParameter("weid") + "\","
								+ "\"path\":\"" + path + "\","
								+ "\"ver\":\"" + ver + "\","
								+ "\"pushTime\":\""
								+ System.currentTimeMillis() + "\"}";
						logger.info(path + "jpush-getWarn=--" + json);

						try {
							XGUtil.sendMsg(xgtoken, json).toString();
						} catch (Exception e) {
							// TODO: handle exception
						}

						logger.info("jpush-getWarn=--" + 8);
					}
				}

				response.sendRedirect("./jsp/warnresult.jsp");

			} else if (action.equals("getPhoto")) {

				String xgtoken;
				if (request.getParameter("weid").equals("android")) {
					RedisUtil.hset("xgtokenapp", request.getParameter("did"),
							request.getParameter("xgtoken"));
				}

				// 请求车机发送图片
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(request.getParameter("did"));

				if (null != registartion_id) {
					Message msg = Message.newBuilder().setMsgContent("")
							.addExtra("type", "getPhoto")
							.addExtra("openId", request.getParameter("weid"))
							.addExtra("pushTime", System.currentTimeMillis())
							.build();

					String result = CommonUtil.sendMsg(registartion_id, msg);
					logger.info("jspush-getPhoto=" + "---" + result);
				}

				// 来自app

				xgtoken = RedisUtil
						.hget("xgtoken", request.getParameter("did"));
				if (xgtoken != null) {
					String json = "{\"type\":\"" + "getPhoto" + "\","
							+ "\"openId\":\"" + request.getParameter("weid")
							+ "\"," + "\"pushTime\":\""
							+ System.currentTimeMillis() + "\"}";
					logger.info("XGUtil-getPhoto=" + "---"
							+ XGUtil.sendMsg(xgtoken, json));
				}

			} else if (action.equals("getVideo")) {

				
				logger.info("jpush-getVideo=" + "--1--" +request.getParameter("weid"));
				
				String xgtoken;
				if (request.getParameter("weid").equals("android")) {
					RedisUtil.hset("xgtokenapp", request.getParameter("did"),
							request.getParameter("xgtoken"));
				}

				// 请求车机发送视频
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(request.getParameter("did"));

				if (null != registartion_id) {
					Message msg = Message.newBuilder().setMsgContent("")
							.addExtra("type", "getVideo")
							.addExtra("openId", request.getParameter("weid"))
							.addExtra("pushTime", System.currentTimeMillis())
							.build();

					String result = CommonUtil.sendMsg(registartion_id, msg);

					logger.info("jpush-getVideo=" + "---" + result);
				}

				xgtoken = RedisUtil
						.hget("xgtoken", request.getParameter("did"));

				logger.info("jpush-getVideo=" + "--2--" +request.getParameter("weid")+"--"+xgtoken);
				
				if (xgtoken != null) {
					
					
					logger.info("jpush-getVideo=" + "--3--" +request.getParameter("weid"));
					
					String json = "{\"type\":\"" + "getVideo" + "\","
							+ "\"openId\":\"" + request.getParameter("weid")
							+ "\"," + "\"pushTime\":\""
							+ System.currentTimeMillis() + "\"}";
					logger.info("XGUtil-getVideo=" + "---"
							+ XGUtil.sendMsg(xgtoken, json)+"--4--"+request.getParameter("weid"));
				}

			} else if (action.equals("showWay")) {

				/*
				 * // 好友引路 double slat =
				 * Double.parseDouble(request.getParameter("lat")); double slng
				 * = Double.parseDouble(request.getParameter("lon")); String
				 * text = ""; try {
				 * 
				 * text = new String(request.getParameter("address").getBytes(
				 * "ISO8859_1"), "UTF-8");
				 * 
				 * logger.info("slat=" + slat + "--slng=" + slng + "--text=" +
				 * text); } catch (Exception e) { // TODO: handle exception }
				 * 
				 * Gps gps = PositionUtil.gcj02_To_Bd09(slat, slng);
				 * 
				 * if (request.getParameter("weid").equals("android")) {
				 * RedisUtil.hset("xgtokenapp",request
				 * .getParameter("did"),request .getParameter("xgtoken")); }
				 * 
				 * String registartion_id = ConnectionPoolDao
				 * .getTerminalRegistrationId(request .getParameter("did"));
				 * 
				 * if (null != registartion_id) { Message msg =
				 * Message.newBuilder().setMsgContent("") .addExtra("type",
				 * "showWay") .addExtra("lat", gps.getWgLat()) .addExtra("lon",
				 * gps.getWgLon()) .addExtra("pushTime", "0").build();
				 * 
				 * CommonUtil.sendMsg(registartion_id, msg);
				 * 
				 * Message msg2 = Message.newBuilder().setMsgContent("")
				 * .addExtra("type", "remoteCommand") .addExtra("lat",
				 * gps.getWgLat()) .addExtra("lon", gps.getWgLon())
				 * .addExtra("maptype", "Bd09") // for易建联 .addExtra("text",
				 * text) .addExtra("pushTime", "0").build();
				 * 
				 * CommonUtil.sendMsg(registartion_id, msg2);
				 * 
				 * }
				 * 
				 * String xgtoken = RedisUtil.hget("xgtoken",
				 * request.getParameter("did"));
				 * 
				 * if (xgtoken != null) { String json = "{\"type\":\"" +
				 * "showWay" + "\"," + "\"lat\":\"" + gps.getWgLat() + "\"," +
				 * "\"lon\":\"" + gps.getWgLon() + "\"," + "\"pushTime\":\"" +
				 * System.currentTimeMillis() + "\"}";
				 * logger.info("XGUtil-showWay=" + "---" +
				 * XGUtil.sendMsg(xgtoken, json));
				 * 
				 * String json2 = "{\"type\":\"" + "remoteCommand" + "\"," +
				 * "\"lat\":\"" + gps.getWgLat() + "\"," + "\"lon\":\"" +
				 * gps.getWgLon() + "\"," + "\"maptype\":\"" + "Bd09" + "\"," +
				 * "\"text\":\"" + text + "\"," + "\"pushTime\":\"" +
				 * System.currentTimeMillis() + "\"}";
				 * logger.info("XGUtil-showWay=" + "---" +
				 * XGUtil.sendMsg(xgtoken, json2)); }
				 */

				String weid = request.getParameter("weid");
				String did = request.getParameter("did");
				String lat = request.getParameter("lat");
				String lon = request.getParameter("lon");
				String text = new String(request.getParameter("text").getBytes("ISO8859_1"), "UTF-8");

				String last = RedisUtil.hget("LASTSEND", weid);

				if (last == null)
					last = "0";

				Long now = System.currentTimeMillis();

				if (now - Long.parseLong(last) > 10000) {
					RedisUtil.hset("LASTSEND", weid, "" + now);

					try {

						double slat = Double.parseDouble(lat);
						double slng = Double.parseDouble(lon);

						Gps gps = PositionUtil.gcj02_To_Bd09(slat, slng);

						// 发送车机指令
						String registartion_id = ConnectionPoolDao
								.getTerminalRegistrationId(did);

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
									.addExtra("text", text).build();
							CommonUtil.sendMsg(registartion_id, msg1);
							CommonUtil.sendMsg(registartion_id, msg2);

						}
						String xgtoken = RedisUtil.hget("xgtoken", did);

						if (xgtoken != null) {

							String json1 = "{\"type\":\"" + "showWay" + "\","
									+ "\"lat\":\"" + gps.getWgLat() + "\","
									+ "\"lon\":\"" + gps.getWgLon() + "\","
									+ "\"pushTime\":\""
									+ System.currentTimeMillis() + "\"}";

							String json2 = "{\"type\":\"" + "remoteCommand"
									+ "\"," + "\"lat\":\"" + gps.getWgLat()
									+ "\"," + "\"lon\":\"" + gps.getWgLon()
									+ "\"," + "\"maptype\":\"" + "Bd09" + "\","
									+ "\"text\":\"" + text + "\","
									+ "\"pushTime\":\""
									+ System.currentTimeMillis() + "\"}";

							logger.info("预约导航" + json2);

							XGUtil.sendMsg(xgtoken, json1);
							XGUtil.sendMsg(xgtoken, json2);
						}

					} catch (Exception e) {
						logger.info("位置导航error=" + e.toString());

					}

				}

			} else if (action.equals("closeSystem")) {
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(request.getParameter("did"));

				if (null != registartion_id) {
					Message msg = Message.newBuilder().setMsgContent("")
							.addExtra("type", "closeSystem")
							.addExtra("pushTime", "0").build();

					CommonUtil.sendMsg(registartion_id, msg);
				}

				String xgtoken = RedisUtil.hget("xgtoken",
						request.getParameter("did"));
				if (xgtoken != null) {
					String json = "{\"type\":\"" + "closeSystem" + "\","
							+ "\"pushTime\":\"" + System.currentTimeMillis()
							+ "\"}";
					logger.info("XGUtil-closeSystem=" + "---"
							+ XGUtil.sendMsg(xgtoken, json));
				}
			} else if (action.equals("speakWords")) {

				String xgtoken;
				String content = new String(request.getParameter("words")
						.getBytes("ISO8859_1"), "UTF-8");

				if (request.getParameter("weid").equals("android")) {
					RedisUtil.hset("xgtokenapp", request.getParameter("did"),
							request.getParameter("xgtoken"));
				}

				// 语音播报
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(request.getParameter("did"));

				if (null != registartion_id) {

					Message msg = Message.newBuilder().setMsgContent("")
							.addExtra("type", "speakWords")
							.addExtra("words", "广播消息:" + content).build();

					CommonUtil.sendMsg(registartion_id, msg);
				}

//				1270106125591346
				
				xgtoken = RedisUtil
						.hget("xgtoken", request.getParameter("did"));
				if (xgtoken != null) {
					String json = "{\"type\":\"" + "speakWords" + "\","
							+ "\"words\":\"" + "广播消息:" + content + "\"}";

					logger.info("XGUtil-closeSystem=" + "---"
							+ XGUtil.sendMsg(xgtoken, json));
				}

			} else if (action.equals("getPhoto_loc")) {
				// 定位器拍照
				String id = request.getParameter("did");
				RedisUtil.publish("cloud.pic", "{\"id\":\"" + id
						+ "\",\"saveFlag\":true}");

			} else if (action.equals("getPhoto_loc_shock")) {
				// 定位器拍照
				String id = request.getParameter("did");
				RedisUtil.publish("cloud.shock", "{\"id\":\"" + id
						+ "\",\"saveFlag\":true}");

			} else if (action.equals("closeSystem_loc")) {
				// 定位器关机
				String id = request.getParameter("did");
				RedisUtil.publish("cloud.close", "{\"id\":\"" + id
						+ "\",\"saveFlag\":true}");

			} else if (action.equals("getRoads")) {

				String origin = request.getParameter("origin");
				String destination = request.getParameter("destination");

				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("origin", origin);
				parameters.put("destination", destination);
				parameters
						.put("key", "AIzaSyAeEMBoxHAF19fSyuNGWC80yQPX9urbp44");

				String json = sendGet(
						"https://maps.google.cn/maps/api/directions/json",
						parameters);
				out = response.getWriter();
				out.write(json);
			} else if (action.equals("test2")) {
				Map<String, String> paramsMap = new HashMap<String, String>();
				paramsMap
						.put("RESULT_STR",
								"83oSWApcD7NmfyPIZ3Evq9ojxbYY6TP1SPqSthVKUqdXMTYquQaLZMN3d5Ushbnj");
				paramsMap.put("type", "image");
				paramsMap.put("openId", "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");

				sendPost(paramsMap,
						"http://localhost:8080/wechat_cn/ToolsServlet");
			} else if (action.equals("test3")) {

				// 获取用户位置
				String token = RedisUtil.get("TOKENCN");

				String uriAPI = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

				uriAPI = uriAPI.replace("ACCESS_TOKEN", token);
				uriAPI = uriAPI.replace("OPENID",
						"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");

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
				paramsMap.put("appid", "wx71460f3d267fcd41");
				paramsMap.put("uid", "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");
				paramsMap.put("category", "map");
				paramsMap.put("city", city);
				paramsMap.put("query", "导航去厦门北站");

				String requestUrl = "https://api.weixin.qq.com/semantic/semproxy/search?access_token=YOUR_ACCESS_TOKEN";
				requestUrl = requestUrl.replace("YOUR_ACCESS_TOKEN", token);

				String result = CommonUtil
						.httpsRequest(requestUrl, "POST",
								net.sf.json.JSONObject.fromObject(paramsMap)
										.toString());

				JSONObject jsonParserReq = null;
				String loc_ori = null;

				try {
					jsonParserReq = new JSONObject(result);
					org.json.JSONObject semantic = jsonParserReq
							.getJSONObject("semantic");
					org.json.JSONObject details = semantic
							.getJSONObject("details");
					org.json.JSONObject end_loc = details
							.getJSONObject("end_loc");

					loc_ori = end_loc.getString("loc_ori");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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
						.getTerminalRegistrationId("1234567890000003");
				Message msg = Message.newBuilder().setMsgContent("")
						.addExtra("type", "remoteCommand").addExtra("lat", lat)
						.addExtra("lon", lng).addExtra("text", loc_ori).build();
				CommonUtil.sendMsg(registartion_id, msg);
				out = response.getWriter();
				out.write(lat + lng);

			} else if (action.equals("buyservice")) {
				try {

					Md5PasswordEncoder md5 = new Md5PasswordEncoder();
					String nonce_str = md5.encodePassword("123456",
							Math.random());

					long out_trade_no = System.currentTimeMillis();
					// String spbill_create_ip = "61.131.68.157";
					String spbill_create_ip = getRemoteHost(request);
					// String spbill_create_ip = request.getParameter("id");

					String stringA = "appid=wx71460f3d267fcd41&body=JSAPI支付测试&device_info=web&&mch_id=1317179801&nonce_str="
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
							+ "total_fee=1&" + "trade_type=JSAPI";

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
							+ "<out_trade_no>" + out_trade_no
							+ "</out_trade_no>" + "<spbill_create_ip>"
							+ spbill_create_ip + "</spbill_create_ip>"
							+ "<total_fee>1</total_fee>"
							+ "<trade_type>JSAPI</trade_type>" + "<sign>"
							+ sign + "</sign>" + "</xml>";

					String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

					String xmlresult = HttpRequest.sendPostXml(url, rqestXml);

					Map<String, String> map = XmlUtil.xml2Map(new String(
							xmlresult.toString().getBytes(), "UTF-8"));

					logger.info("支付测试sign=" + sign);
					logger.info("支付测试stringA=" + stringA);
					logger.info("支付测试stringSignTemp=" + stringSignTemp);
					logger.info("支付测试rqestXml=" + rqestXml);
					logger.info("支付测试xmlresult=" + xmlresult);

					if (map.get("xml.return_msg").equals("OK")) {

						float timestamp = System.currentTimeMillis();
						String json = "[{\"timestamp\":\"" + timestamp + "\","
								+ "\"nonceStr\":\"" + map.get("xml.nonce_str")
								+ "\"," + "\"package\":\"" + "prepay_id="
								+ map.get("xml.prepay_id") + "\","
								+ "\"signType\":\"" + "MD5" + "\","
								+ "\"paySign\":\"" + map.get("xml.sign")
								+ "\"}]";
						logger.info("支付测试json");
						out.write(json);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}

			} else if (action.equals("getJsid")) {
				// String json = null;
				String url = URLDecoder.decode(request.getParameter("url"));

				url = url.substring(0, url.indexOf("@"));

				Map<String, String> ret = sign(RedisUtil.get("JSAPI"), url);
				for (Map.Entry entry : ret.entrySet()) {
					System.out
							.println(entry.getKey() + ", " + entry.getValue());
				}

				JSONArray json = JSONArray.fromObject(ret);

				out = response.getWriter();
				out.write(json.toString());

			} else if (action.equals("unifiedorder")) {
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
						+ "total_fee=0.01&" + "trade_type=JSAPI";

				String stringSignTemp = stringA
						+ "&key=192006250b4c09247ec02edce69f6a2d";
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
						+ "</spbill_create_ip>" + "<total_fee>0.01</total_fee>"
						+ "<trade_type>JSAPI</trade_type>" + "<sign>" + sign
						+ "</sign>" + "</xml>";

				String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

				System.out.println(rqestXml);

				HttpRequest.sendPostXml(url, rqestXml);

			} else if (action.equals("pay_suc")) {

				String id = request.getParameter("id");
				String time = request.getParameter("time");
				String service_id = request.getParameter("service_id");
				String money = request.getParameter("money");
				String serial = request.getParameter("serial");
				String key = request.getParameter("key");

				if (key.equals(Md5PasswordEncoder.encodePassword(time
						+ service_id + money + id + "godloveyou", "iso"))) {

					logger.info("充值成功");
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
								logger.info("过期了");
								ConnectionPoolDao.updateServiceNow(id, addyear);
							} else {
								logger.info("还没到");
								ConnectionPoolDao.updateService(id, addyear);
							}
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							logger.info("error:" + e.toString());
							e.printStackTrace();
						}

					} else if (service_id.equals("6")) {
						ConnectionPoolDao.updateServiceliuliang(id, 1);
						String imsi = ConnectionPoolDao.getImsiById(id);
						ConnectionPoolDao.updateSimMadte(imsi, 1);

						logger.info("流量服务id:" + id);
					}
				} else
					logger.info("key验证失败");
			} else if (action.equals("checkbuy")) {
				String did = request.getParameter("did");
				String result = "";
				String imsi = ConnectionPoolDao.getImsiById(did);
				if (imsi == null)
					result = "[{\"res\":'false',\"msc\":\"所选设备imei不存在，设备不支持购买\"}]";
				else {
					Sim sim = ConnectionPoolDao.getPhoneByImsi(imsi);
					if (sim == null)
						result = "[{\"res\":'false',\"msc\":\"所选设备手机卡不支持购买\"}]";
					else if (Integer.parseInt(sim.getBsim_id())==10&&Integer.parseInt(sim.getFlow())<100) {
						result = "[{\"res\":'true',\"msc\":\"phone\"}]";
					}else
					{
						result = "[{\"res\":'go',\"msc\":\"非征卡请去京东购买\"}]";
					}
					// else {
					// DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					// try {
					// Date endTime = fmt.parse(sim.getMdate());
					// endTime.setDate(1);
					//
					// if (endTime.before(new Date())) {
					// result = "[{\"res\":'false',\"msc\":\"卡过期\"}]";
					// } else {
					// result = "[{\"res\":'true',\"msc\":\"phone\"}]";
					// }
					//
					// } catch (ParseException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// result = "[{\"res\":'false',\"msc\":\"无法获取simdate\"}]";
					// }
					//
					// }
				}
				out.write(result);

			}else if (action.equals("checkid")) {
				String did = request.getParameter("did");
				String result = "";
				Boolean imsi = ConnectionPoolDao.isIdisused(did);
					result = "[{\"res\":'"+imsi+"'}]";
				out.write(result);

			}
			
			else if (action.equals("gift")) {
				String did = request.getParameter("did");
				String result = "";

				String time = Sha1Util.getTimeStamp();
				if (Integer.parseInt(ConnectionPoolDao.getGiftById(did)) < 1) {
					ConnectionPoolDao.updateGift(did, time);
					gift(did);
					result = "[{\"res\":'true',\"msc\":\"领取成功\"}]";
				} else
					result = "[{\"res\":'false',\"msc\":\"不在活动范围\"}]";
				System.out.println(result);
				out.write(result);

			} else if (action.equals("errorlog")) {
				logger.info("微信加载失败" + new Date() + "\n:"
						+ request.getParameter("log"));
			} else if (action.equals("changeSelect")) {

				ConnectionPoolDao.updateSelect0(request.getParameter("weid"));
				ConnectionPoolDao.updateSelect1(request.getParameter("did"),
						request.getParameter("weid"));
			} else if (action.equals("removepar")) {

				ConnectionPoolDao.doUnLockCar(request.getParameter("did"));
			} else if (action.equals("send_code")) {

				String res = CommonUtil
						.urlGet("http://webservice.conqueror.cn:8181/sim/smsActivateCode?phone="
								+ request.getParameter("phone"));
				out.write(res);
			} else if (action.equals("loc_iccid")) {
				String res = CommonUtil
						.urlGet("http://webservice.conqueror.cn:8181/sim/smsActivate?phone="
								+ request.getParameter("phone")
								+ "&code="
								+ request.getParameter("code")
								+ "&alias="
								+ request.getParameter("alias")
								+ "&iccid="
								+ request.getParameter("iccid")
								+ "&weid="
								+ request.getParameter("weid"));
				System.out
						.println("http://webservice.conqueror.cn:8181/sim/smsActivate?phone="
								+ request.getParameter("phone")
								+ "&code="
								+ request.getParameter("code")
								+ "&alias="
								+ request.getParameter("alias")
								+ "&iccid="
								+ request.getParameter("iccid")
								+ "&weid="
								+ request.getParameter("weid"));
				System.out.println(res);
				out.write(res);
			} else if (action.equals("getPhoneIccid")) {

				String result = "[]";
				sqlSession = factory.openSession();
				try {
					IUserOperation userInfoMapper = sqlSession
							.getMapper(IUserOperation.class);
					List<User_sim> iccidList = userInfoMapper
							.getIccidList(request.getParameter("phone"));
					JSONArray jsonArray = JSONArray.fromObject(iccidList);
					result = jsonArray.toString();
				} finally {
					sqlSession.close();
				}

				out.write(result);
			} else if (action.equals("getIccidByWeid")) {
				String res = CommonUtil
						.urlGet("http://webservice.conqueror.cn:8181/sim/getIccidByWeid?weid="
								+  request.getParameter("weid"));
				System.out.println(res);
				out.write(res);

			} else if (action.equals("cheak_sim_buy")) {

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		String type = request.getParameter("type");

		String result = "result=";

		/*
		 * File file = new File("e:\\test\\123.gif") MagicMatch match =
		 * Magic.getMagicMatch(file, false, true); String contentType =
		 * match.getMimeType(); System.out.println(contentType);
		 */

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

		} else if (type.equals("saveXGtoken")) {
			String did = request.getParameter("did");
			String xgtoken = request.getParameter("xgtoken");
			RedisUtil.hset("xgtoken", did, xgtoken);
			result = "suc saveXGtoken";
		}
		// else if(type.equals("setOnline")){
		// String deviceId = request.getParameter("deviceId");
		// // String deviceId=DeviceType.testcarid;
		// String status= request.getParameter("registrationId");
		//
		// logger.info("type=setOnline--deviceId=" + deviceId + "--status="
		// + status );
		//
		//
		// RedisUtil.set("WECHATONLINE"+deviceId,status);
		//
		// }
		else if (type.equals("getPicture")) {

			/*
			 * ServletInputStream filein = request.getInputStream(); byte[]
			 * buffer = new byte[1024]; File file = new
			 * File(request.getSession()
			 * .getServletContext().getRealPath("/img/"),"img_"+i+".jpg"); i++;
			 * FileOutputStream fileout = new FileOutputStream(file); int len =
			 * filein.read(buffer, 0, 1024); while( len!=-1){
			 * fileout.write(buffer,0,len); len = filein.read(buffer, 0, 1024);
			 * } fileout.close(); filein.close();
			 * 
			 * logger.info(" 图片下载结束，地址="+request.getSession().getServletContext()
			 * .getRealPath("/img/"));
			 */

			String temp = request.getSession().getServletContext()
					.getRealPath("/")
					+ "temp"; // 临时目录
			System.out.println("temp=" + temp);
			String loadpath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "images"; // 上传文件存放目录
			System.out.println("loadpath=" + loadpath);
			DiskFileUpload fu = new DiskFileUpload();
			fu.setSizeMax(1 * 1024 * 1024); // 设置允许用户上传文件大小,单位:字节
			fu.setSizeThreshold(4096); // 设置最多只允许在内存中存储的数据,单位:字节
			fu.setRepositoryPath(temp); // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录

			// 开始读取上传信息
			// int index = 0;
			List fileItems = null;

			try {
				fileItems = fu.parseRequest(request);
				System.out.println("fileItems=" + fileItems);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Iterator iter = fileItems.iterator(); // 依次处理每个上传的文件
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();// 忽略其他不是文件域的所有表单信息
				if (!item.isFormField()) {
					String name = item.getName();// 获取上传文件名,包括路径
					name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名
					long size = item.getSize();
					if ((name == null || name.equals("")) && size == 0)
						continue;
					int point = name.indexOf(".");
					name = (new Date()).getTime()
							+ name.substring(point, name.length());
					// index++;
					File fNew = new File(loadpath, name);
					try {
						if (!fNew.exists())
							fNew.createNewFile();
						item.write(fNew);
						System.out.println("文件地址：" + loadpath + name);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else// 取出不是文件域的所有表单信息
				{
					// String fieldvalue = item.getString();
					// 如果包含中文应写为：(转为UTF-8编码)
					// String fieldvalue = new
					// String(item.getString().getBytes(),"UTF-8");
				}
			}
			// String text1 = "11";
			// response.sendRedirect("result.jsp?text1=" + text1);

			result = "图片下载完成";

			// 上传服务器
			// url=
			// String token=RedisUtil.get("TOKENCN");
			// String urlStr =
			// "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type=image";
			// String media_msg = formUpload(urlStr,iamgeS[k]);
			// String media_id = null;

		} else if (type.equals("warnimage")) {

		} else if (type.equals("image")) {
			// 请求微信发送图片
			Logger logger = Logger.getLogger(ToolsServlet.class.getName());
			// if (null !=
			// request.getParameter("openId")&&"".equals(request.getParameter("openId")))
			// {
			String openId = request.getParameter("openId");
			logger.info("openIdimage-----=" + openId);
			if (openId.length() > 3) {
				logger.info("请求图片" + new Date() + "\n:"
						+ request.getParameter("openId") + "--url="
						+ request.getParameter("RESULT_STR"));

				Pattern pattern = Pattern
						.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
				Matcher matcher = pattern.matcher(request
						.getParameter("RESULT_STR"));

				if (matcher.find()) {

					if (request.getParameter("openId").equals("android")) {

						String xgtoken = RedisUtil.hget("xgtokenapp",
								request.getParameter("did"));

						if (xgtoken != null) {
							String json = "{\"type\":\"" + "app_command_photo"
									+ "\"," + "\"image_url\":\""
									+ request.getParameter("RESULT_STR")
									+ "\"," + "\"pushTime\":\""
									+ System.currentTimeMillis() + "\"}";
							logger.info("XGUtil-getPhoto=" + "---"
									+ XGUtil.sendMsgApp(xgtoken, json));
						}

					} else {

						// 新版本
						WechatSend.sendNews(request.getParameter("openId"),
								"设备"+request.getParameter("did"),
								"照相回传",
								"http://wechat.conqueror.cn/jsp/image.jsp?url="
										+ request.getParameter("RESULT_STR"),
								request.getParameter("RESULT_STR"));
						
						
						
					}
				} else {

					WechatSend.sendImage(request.getParameter("openId"),
							request.getParameter("RESULT_STR"));
					// 旧版本
					WechatSend.sendText(request.getParameter("openId"),
							"检测到您的设备版本过低，请升级您的设备版本，设备更新完成后会进行一次重启");
				}

			} else {

				List<String> weidlist = ConnectionPoolDao.getweidByNo(request
						.getParameter("did"));

				for (int i = 0; i < weidlist.size(); i++) {
					logger.info("震动报警" + new Date() + "\n:" + weidlist.get(i)
							+ "--url=" + request.getParameter("RESULT_STR"));

					if (null != weidlist.get(i)) {
						if (request.getParameter("RESULT_STR").substring(0, 4)
								.equals("http")) {
							logger.info("new");
							WechatSend
									.sendNews(
											weidlist.get(i),
											"Conqueror",
											"",
											"http://wechat.conqueror.cn/jsp/image.jsp?url="
													+ request
															.getParameter("RESULT_STR"),
											request.getParameter("RESULT_STR"));

						} else {
							logger.info("old");
							WechatSend.sendImage(weidlist.get(i),
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
					System.out.println(HttpRequest.sendPost(url, param));

				}

			}

			result = "{\"success\":\"true\"}";

			// result = "微信发送图片完成";
		} else if (type.equals("video")) {

			Logger logger = Logger.getLogger(ToolsServlet.class.getName());
			logger.info("请求视频" + new Date() + "\n:"
					+ request.getParameter("openId") + "--url="
					+ request.getParameter("RESULT_STR"));

			/*
			 * Pattern pattern = Pattern
			 * .compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
			 * Matcher matcher = pattern.matcher(request
			 * .getParameter("RESULT_STR"));
			 */

			logger.info("openIdvideo-----=" + request.getParameter("openId"));

			if (request.getParameter("RESULT_STR").substring(0, 4)
					.equals("http")) {

				logger.info("videotest1");

				if (request.getParameter("openId").equals("android")) {
					logger.info("videotest2");

					String xgtoken = RedisUtil.hget("xgtokenapp",
							request.getParameter("did"));

					logger.info("xgtoken=" + xgtoken);

					if (xgtoken != null) {
						String json = "{\"type\":\"" + "app_command_video"
								+ "\"," + "\"video_url\":\""
								+ request.getParameter("RESULT_STR") + "\","
								+ "\"pushTime\":\""
								+ System.currentTimeMillis() + "\"}";

						logger.info("json=" + json);

						logger.info("XGUtil-getVideo=" + "---"
								+ XGUtil.sendMsgApp(xgtoken, json));
					}

					logger.info("videotest2-end");

				} else {

					logger.info("new");
					WechatSend
							.sendNews(
									request.getParameter("openId"),
									"设备"+request.getParameter("did"),
									"查看视频回传",
									"http://wechat.conqueror.cn/jsp/video.jsp?url="
											+ request
													.getParameter("RESULT_STR"),
									"http://wechat.conqueror.cn/jsp/images/log_min.jpg");
				}

			} else {
				// 旧版本
				logger.info("old");
				WechatSend.sendText(request.getParameter("openId"),
						"检测到您的后视镜设备版本过低，请升级您的设备版本，设备更新完成后会进行一次重启");
			}

			// result = "微信发送视频完成";
			result = "{\"success\":\"true\"}";
		} else if (type.equals("list")) {

			Logger logger = Logger.getLogger(ToolsServlet.class.getName());
			logger.info("发送list" + new Date() + "\n:"
					+ request.getParameter("count") + "\n"
					+ request.getParameter("path") + "\n"
					+ request.getParameter("namelist") + "\n"
					+ request.getParameter("did"));

			String count = request.getParameter("count");
			String path = request.getParameter("path");
			String namelist = request.getParameter("namelist");
			String id = request.getParameter("did");

			List<String> weidlist = ConnectionPoolDao.getweidByNo(id);

			for (int i = 0; i < weidlist.size(); i++) {
				WechatSend.sendText(weidlist.get(i),
						"<a href='http://wechat.conqueror.cn/jsp/getimage.jsp?&did="
								+ id + "&count=" + count + "&path=" + path
								+ "&namelist=" + namelist + "'"
								+ ">查看震动报警图片</a>");
			}
			// /mnt/external_sdio/DCIM/PIC/
			// IMG_20130121_165230912.jpg,IMGU_20130121_165231059.jpg,IMG_20130121_173438832.jpg,IMG_20130121_173542065.jpg,IMG_20130121_165337898.jpg,IMG_20130121_165440627.jpg,IMG_20130121_165222554.jpg,IMG_20130121_165306121.jpg,IMG_20130121_165433540.jpg,IMG_20130121_171403072.jpg,IMG_20130121_171453529.jpg,IMG_20130121_171620369.jpg,IMG_20130121_171823650.jpg,IMG_20130121_165259811.jpg,IMG_20130121_171654782.jpg,IMG_20130121_165319889.jpg,IMG_20130121_193631182.jpg,IMG_20130121_193753885.jpg,IMG_20160514_140302202.jpg
			// 1380106012591131

			result = "微信发送list完成";
		} else if (type.equals("msg")) {

			Logger logger = Logger.getLogger(ToolsServlet.class.getName());
			logger.info("发送请求" + new Date() + "\n:"
					+ request.getParameter("openId") + "--url="
					+ request.getParameter("RESULT_STR"));

			String id = ConnectionPoolDao.getIdByImei(request
					.getParameter("imei"));

			List<String> weidlist = ConnectionPoolDao.getweidByNo(id);
			String path = request.getParameter("RESULT_STR");
			
			String ver ="i5X_CN";
			try {
				ver=request.getParameter("ver");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				

			for (int i = 0; i < weidlist.size(); i++) {
				logger.info("震动报警" + new Date() + "\n:" + weidlist.get(i)
						+ "--url=" + request.getParameter("RESULT_STR"));

				if ("震动报警文字提示".equals(path)) {
					path = "";
				}
				/*
				 * else {
				 * RedisUtil.hset("warn",weidlist.get(i)+System.currentTimeMillis
				 * (), request.getParameter("RESULT_STR")); }
				 */

				/*
				 * logger.info("接收到震动报警" +
				 * "<a href='http://wechat.conqueror.cn/ToolsServlet?action=getWarn&weid="
				 * + weidlist.get(i) + "&did=" + id+"&path="+path
				 * +"&time="+System.currentTimeMillis() + "'" + ">点击获取图片</a>");
				 */
				// 文字提醒
				WechatSend
						.sendText(
								weidlist.get(i),
								"接收到震动报警"
										+ "<a href='http://wechat.conqueror.cn/ToolsServlet?action=getWarn&weid="
										+ weidlist.get(i) + "&did=" + id + "&ver=" + ver
										+ "&path=" + path + "&time="
										+ System.currentTimeMillis() + "'"
										+ ">点击获取图片</a>");

				SimpleDateFormat df = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");// 设置日期格式

				String url = "http://cloud.conqueror.cn/webservice/sendSMS";
				String msg1 = null;
				try {
					msg1 = URLEncoder.encode(df.format(new Date()) + ",设备" + id
							+ "震动报警", "UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String param = "key=44CQ55Ge5b%2BG56eR5oqA44CRY29ucXVlcm9y&id="
						+ id + "&mess=" + msg1;
				System.out.println(HttpRequest.sendPost(url, param));

			}

			result = "微信发送视频完成";

		} else if (type.equals("bat")) {

			Logger logger = Logger.getLogger(ToolsServlet.class.getName());
			logger.info("电压过低" + new Date() + "\n:"
					+ request.getParameter("openId"));

			List<String> weidlist = ConnectionPoolDao.getweidByNo(request
					.getParameter("did"));

			for (int i = 0; i < weidlist.size(); i++) {
				WechatSend.sendText(weidlist.get(i), "检测到您的后视镜设备电压过低");
			}

			result = "微信发送低电压完成";
		}else if(type.equals("sendTest"))
		{
			String weid=request.getParameter("weid");
			String imgurl=request.getParameter("imgurl");
			
			// 新版本
			WechatSend.sendNews(weid,"Test", "","http://wechat.conqueror.cn/jsp/image.jsp?url="+ imgurl,imgurl);
							
							
		}
		out.print(result);

	}

	/***
	 * 判断文件的类型是否为指定的文件类型
	 * 
	 * @return
	 */
	public boolean filterType() {
		boolean isFileType = false;
		String fileType = getImgContentType();
		System.out.println(fileType);
		for (String type : types) {
			if (type.equals(fileType)) {
				isFileType = true;
				break;
			}
		}
		return isFileType;
	}

	public String getSavePath() {
		String realPath = ServletActionContext.getRequest().getRealPath(
				savePath);
		System.out.println("savePaht -- " + realPath);
		return realPath;
	}

	public File getImg() {
		return img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setSavePath(String value) {
		this.savePath = value;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	/**
	 * 取得文件夹大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public long getFileSize(File f) throws Exception {
		return f.length();
	}

	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/*
	 * private static void initLog4j() { Properties prop = new Properties();
	 * 
	 * prop.setProperty("log4j.rootLogger",
	 * "info, ServerDailyRollingFile, stdout");
	 * prop.setProperty("log4j.appender.ServerDailyRollingFile",
	 * "org.apache.log4j.DailyRollingFileAppender");
	 * 
	 * prop.setProperty("log4j.appender.ServerDailyRollingFile.DatePattern",
	 * "'.'yyyy-MM-dd");
	 * prop.setProperty("log4j.appender.ServerDailyRollingFile.File",
	 * "'.'logs/notify-subscription.log");
	 * 
	 * prop.setProperty("log4j.appender.ServerDailyRollingFile.layout",
	 * "org.apache.log4j.PatternLayout"); prop.setProperty(
	 * "log4j.appender.ServerDailyRollingFile.layout.ConversionPattern",
	 * "%d-%m%n");
	 * prop.setProperty("log4j.appender.ServerDailyRollingFile.Append", "true");
	 * 
	 * prop.setProperty("log4j.appender.stdout",
	 * "org.apache.log4j.ConsoleAppender");
	 * prop.setProperty("log4j.appender.stdout.layout",
	 * "org.apache.log4j.PatternLayout ");
	 * prop.setProperty("log4j.appender.stdout.layout.ConversionPattern",
	 * "%d{yyyy-MM-dd HH:mm:ss} %p [%c] %m%n");
	 * 
	 * PropertyConfigurator.configure(prop); }
	 */

	public static String formUpload(String urlStr, Map<String, String> textMap,
			Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}
			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap()
							.getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	// HttpClient Post;
	public static String sendPost(Map<String, String> paramsMap, String Url) {

		int CHECK_TIMEOUT = 30000;

		HttpPost post = null;
		String resultStr = null;
		try {
			post = new HttpPost(Url);
		} catch (Exception e) {
			e.printStackTrace();
			return resultStr;
		}
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
			params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setSoTimeout(httpParameters, CHECK_TIMEOUT);
		HttpClient httpClient = new DefaultHttpClient(httpParameters);
		try {
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			HttpResponse response = httpClient.execute(post);

			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				String msg = EntityUtils.toString(entity);
				resultStr = msg;

			} else {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
	}

	public static String sendGet(String url, Map<String, String> parameters) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		StringBuffer sb = new StringBuffer();// 存储参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if (parameters.size() == 1) {
				for (String name : parameters.keySet()) {
					sb.append(name)
							.append("=")
							.append(java.net.URLEncoder.encode(
									parameters.get(name), "UTF-8"));
				}
				params = sb.toString();
			} else {
				for (String name : parameters.keySet()) {
					sb.append(name)
							.append("=")
							.append(java.net.URLEncoder.encode(
									parameters.get(name), "UTF-8")).append("&");
				}
				String temp_params = sb.toString();
				params = temp_params.substring(0, temp_params.length() - 1);
			}
			String full_url = url + "?" + params;
			System.out.println(full_url);
			// 创建URL对象
			java.net.URL connURL = new java.net.URL(full_url);
			// 打开URL连接
			java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL
					.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 建立实际的连接
			httpConn.connect();
			// 响应头部获取
			Map<String, List<String>> headers = httpConn.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : headers.keySet()) {
				System.out.println(key + "\t：\t" + headers.get(key));
			}
			// 定义BufferedReader输入流来读取URL的响应,并设置编码方式
			in = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		// 注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 *
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Map<String, String> parameters = new HashMap<String, String>();
	 * parameters.put("origin", "24.648516,118.15641");
	 * parameters.put("destination", "24.648212,118.156096");
	 * parameters.put("key", "AIzaSyAeEMBoxHAF19fSyuNGWC80yQPX9urbp44");
	 * 
	 * try { JSONObject json = new JSONObject(sendGet(
	 * "https://maps.google.cn/maps/api/directions/json", parameters));
	 * org.json.JSONArray routes = json.getJSONArray("routes"); String
	 * routes_str = routes.toString(); // JSONObject json2=new //
	 * JSONObject(routes_str.substring(1,routes_str.length()-2)); //
	 * System.out.println("111"+routes_str.substring(1,routes_str.length()-2));
	 * System.out.println("111" + routes); } catch (JSONException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * }
	 */

	private static void t() {
		try {

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String nonce_str = md5.encodePassword("123456", Math.random());
			long out_trade_no = System.currentTimeMillis();
			String spbill_create_ip = "61.131.68.157";
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

	public String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

//	public static void main(String[] args) {
//		SqlSession sqlSession;
//		sqlSession = factory.openSession();
//		try {
//			IUserOperation userInfoMapper = sqlSession
//					.getMapper(IUserOperation.class);
//			List<User_sim> iccidList = userInfoMapper
//					.getIccidList("13163905721");
//			JSONArray jsonArray = JSONArray.fromObject(iccidList);
//			System.out.println(jsonArray.toString());
//			// out.write(jsonArray.toString());
//		} finally {
//			sqlSession.close();
//		}
//	}

	private static void gift(String id) {
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

	
	
	public static void main(String[] args) {
		String xgtoken="a7c9fd00ffac57cc4e17a66dc939874584bbbb57";
		String json="{\"type\":\"getWarn\",\"openId\":\"oISxbtyBMnICaTLfwBN9rLazGOUQ\",\"path\":\"/mnt/external_sdio/DCIM/PIC/IMG_20161112_085706793\",\"pushTime\":\"1478912410621\"}";
		try {
			XGUtil.sendMsg(xgtoken, json).toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
