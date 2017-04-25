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

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.ruiyi.wechat.model.Sim;
import com.ruiyi.wechat.model.User;
import com.ruiyi.wechat.mybatis.inter.IUserOperation;
import com.ruiyi.wechat.mybatis.model.Parameter;
import com.ruiyi.wechat.string.DeString;
import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.CommonUtil;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.Gps;
import com.ruiyi.wechat.util.HttpRequest;
import com.ruiyi.wechat.util.MD5;
import com.ruiyi.wechat.util.Md5PasswordEncoder;
import com.ruiyi.wechat.util.MongoDBManager;
import com.ruiyi.wechat.util.MongoDBManager2;
import com.ruiyi.wechat.util.MongoDBManager3;
import com.ruiyi.wechat.util.PositionUtil;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.UserSendImageMessageUtil;
import com.ruiyi.wechat.util.WechatSend;
import com.ruiyi.wechat.util.XGUtil;
import com.ruiyi.wechat.util.XmlUtil;
import com.ruiyi.wechat.util.t3;
import com.utils.Sha1Util;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class AppServlet extends HttpServlet {
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
	
	Logger logger = Logger.getLogger(ToolsServlet.class.getName());
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		SqlSession sqlSession;
		// SqlSession session=initSql();

		String action = request.getParameter("action");
		String json;

		String xgtokenservice;
		String xgtokenapp;
		String pid;
		JSONObject jsonboj = null;
		String weid;
		
		
		
		switch (action) {
		case "getPhoto":

			xgtokenapp = request.getParameter("xgtoken");
			pid = request.getParameter("pid");
			weid = request.getParameter("weid");

			RedisUtil.hset("xgtokenapp", pid, xgtokenapp);

			xgtokenservice = RedisUtil.hget("xgtoken", pid);
			if (xgtokenservice == null) {
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(pid);
				Message msg = Message.newBuilder().setMsgContent("")
						.addExtra("type", "getPhoto").addExtra("openId", weid)
						.addExtra("pushTime", System.currentTimeMillis())
						.build();

				CommonUtil.sendMsg(registartion_id, msg);
				try {
					jsonboj = new JSONObject(
							"{\"ret_code\":0,\"err_msg\":\"jpush\"}");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				json = "{\"type\":\"" + "getPhoto" + "\"," + "\"openId\":\""
						+ weid + "\"," + "\"pushTime\":\""
						+ System.currentTimeMillis() + "\"}";
				jsonboj = XGUtil.sendMsg(xgtokenservice, json);
			}

			out.write(jsonboj.toString());

			break;
		case "getVideo":
			// 请求车机发送视频
			/*
			 * String registartion_id = ConnectionPoolDao
			 * .getTerminalRegistrationId(request.getParameter("did"));
			 * 
			 * Message msg = Message.newBuilder().setMsgContent("")
			 * .addExtra("type", "getVideo") .addExtra("openId",
			 * request.getParameter("weid")) .addExtra("pushTime", "0").build();
			 * 
			 * CommonUtil.sendMsg(registartion_id, msg);
			 */

			xgtokenapp = request.getParameter("xgtoken");
			pid = request.getParameter("pid");
			weid = request.getParameter("weid");

			RedisUtil.hset("xgtokenapp", pid, xgtokenapp);

			xgtokenservice = RedisUtil.hget("xgtoken", pid);
			if (xgtokenservice == null) {
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(pid);
				Message msg = Message.newBuilder().setMsgContent("")
						.addExtra("type", "getPhoto").addExtra("openId", weid)
						.addExtra("pushTime", System.currentTimeMillis())
						.build();

				CommonUtil.sendMsg(registartion_id, msg);
				try {
					jsonboj = new JSONObject(
							"{\"ret_code\":0,\"err_msg\":\"jpush\"}");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				json = "{\"type\":\"" + "getVideo" + "\"," + "\"openId\":\""
						+ weid + "\"," + "\"pushTime\":\""
						+ System.currentTimeMillis() + "\"}";
				jsonboj = XGUtil.sendMsg(xgtokenservice, json);
			}

			out.write(jsonboj.toString());

			break;
		case "sendWord":
			
		
	
			
			xgtokenapp = request.getParameter("xgtoken");
			pid = request.getParameter("pid");
			weid = request.getParameter("weid");
			// String word=request.getParameter("word");

			String word = new String(request.getParameter("word").getBytes(
					"ISO8859_1"), "UTF-8");

			RedisUtil.hset("xgtokenapp", pid, xgtokenapp);
			xgtokenservice = RedisUtil.hget("xgtoken", pid);
			
			
			
			
			

			if (xgtokenservice == null) {
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(pid);
				Message msg = Message.newBuilder().setMsgContent("")
						.addExtra("type", "speakWords")
						.addExtra("words", "广播消息:" + word).build();

				CommonUtil.sendMsg(registartion_id, msg);
				try {
					jsonboj = new JSONObject(
							"{\"ret_code\":0,\"err_msg\":\"jpush\"}");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				json = "{\"type\":\"" + "speakWords" + "\"," + "\"words\":\""
						+ "广播消息:" + word + "\"}";
				jsonboj = XGUtil.sendMsg(xgtokenservice, json);
			}

			out.write(jsonboj.toString());

			break;
		case "sendWay":

			logger.info("sendWay");
			
			xgtokenapp = request.getParameter("xgtoken");
			pid = request.getParameter("pid");
			weid = request.getParameter("weid");

			String applat = request.getParameter("lat");
			String applng = request.getParameter("lng");
			String address = new String(request.getParameter("address")
					.getBytes("ISO8859_1"), "UTF-8");


			Gps gps = PositionUtil.gcj02_To_Bd09(Double.parseDouble(applat), Double.parseDouble(applng));

			RedisUtil.hset("xgtokenapp", pid, xgtokenapp);
			xgtokenservice = RedisUtil.hget("xgtoken", pid);
			
			
			logger.info("xgtokenapp="+xgtokenapp);
			logger.info("pid="+pid);
			logger.info("weid="+weid);
			logger.info("applat="+applat);
			logger.info("applng="+applng);
			logger.info("xgtokenservice="+xgtokenservice);
			

			if (xgtokenservice == null) {

				// 发送车机指令
				String registartion_id = ConnectionPoolDao
						.getTerminalRegistrationId(pid);

				Message msg1 = Message.newBuilder().setMsgContent("")
						.addExtra("type", "showWay")
						.addExtra("lat", gps.getWgLat())
						.addExtra("lon", gps.getWgLon())
						.addExtra("pushTime", "0").build();

				Message msg2 = Message.newBuilder().setMsgContent("")
						.addExtra("type", "remoteCommand")
						.addExtra("lat", gps.getWgLat())
						.addExtra("lon", gps.getWgLon())
						.addExtra("text", address).build();

				CommonUtil.sendMsg(registartion_id, msg1);
				CommonUtil.sendMsg(registartion_id, msg2);

				try {
					jsonboj = new JSONObject(
							"{\"ret_code\":0,\"err_msg\":\"jpush\"}");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String json1 = "{\"type\":\"" + "showWay" + "\","
						+ "\"lat\":\"" + gps.getWgLat() + "\"," + "\"lon\":\""
						+ gps.getWgLon() + "\"," + "\"pushTime\":\""
						+ System.currentTimeMillis() + "\"}";

				String json2 = "{\"type\":\"" + "remoteCommand" + "\","
						+ "\"lat\":\"" + gps.getWgLat() + "\"," + "\"lon\":\""
						+ gps.getWgLon() + "\"," + "\"maptype\":\"" + "Bd09"
						+ "\"," + "\"text\":\"" + address + "\","
						+ "\"pushTime\":\"" + System.currentTimeMillis()
						+ "\"}";

				
				
				XGUtil.sendMsg(xgtokenservice, json1);
				XGUtil.sendMsg(xgtokenservice, json2);
				
				try {
					jsonboj = new JSONObject(
							"{\"ret_code\":0,\"err_msg\":\"jpush\"}");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						

			}

			out.write(jsonboj.toString());

			break;
		case "login":

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String pswmd5 = md5.encodePassword(password, username);

			sqlSession = factory.openSession();
			try {
				IUserOperation userInfoMapper = sqlSession
						.getMapper(IUserOperation.class);
				com.ruiyi.wechat.mybatis.model.User user = userInfoMapper
						.login(username, pswmd5);
				JSONArray jsonArray = JSONArray.fromObject(user);
				out.write(jsonArray.toString());
			} finally {
				sqlSession.close();
			}
			break;
		case "getParameterList":

			String user_id = request.getParameter("userid");

			sqlSession = factory.openSession();
			try {
				IUserOperation userInfoMapper = sqlSession
						.getMapper(IUserOperation.class);
				List<Parameter> parameterList = userInfoMapper
						.getParameterList(user_id);
				JSONArray jsonArray = JSONArray.fromObject(parameterList);
				out.write(jsonArray.toString());
			} finally {
				sqlSession.close();
			}
			break;
		case "addFence":
		
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lng = Double.parseDouble(request.getParameter("lng"));
			pid = request.getParameter("pid");
			String mile = request.getParameter("mile");

			double[] back_gpsresult;
			try {
				back_gpsresult = back_changegps(lat, lng);
				ConnectionPoolDao.addroundrail(pid, back_gpsresult[0] + ","
						+ back_gpsresult[1] + "," + mile + "," + "22-06");

				json = "[{\"res\":true}]";
				out.write(json);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		case "getHistory":
			try {

				// Gps gps = PositionUtil.gcj02_To_Bd09(slat, slng);
				long stime = Long.parseLong(request.getParameter("stime"));
				long etime = Long.parseLong(request.getParameter("etime"));
				String no = request.getParameter("pid");
				long st = stime / 1000;
				long et = etime / 1000;

				System.out.println("" + st);
				System.out.println("" + et);

				long vt = 1463760000;// 2016-05-21 00:00:00

				DBObject obj = new BasicDBObject();
				obj.put("no", Long.parseLong(no));
				obj.put("time",
						new BasicDBObject("$gte", st).append("$lte", et));

				DBObject obj1 = new BasicDBObject();
				obj1.put("_id", 0);
				obj1.put("no", 0);
				obj1.put("alt", 0);
				// obj1.put("mile", 0);
				obj1.put("info", 0);
				obj1.put("sims", 0);
				// obj1.put("time", 0);

				List<DBObject> list;
				if (et < vt) {
					list = MongoDBManager.getInstance().find("gps", obj, obj1);// 2.128
				} else if (st > vt) {
					list = MongoDBManager2.getInstance().find("gps", obj, obj1);// 2.64
				} else {
					list = MongoDBManager2.getInstance().find("gps", obj, obj1);// 2.64
					list.addAll(MongoDBManager.getInstance().find("gps", obj,
							obj1));// 2.128
				}

				boolean stopFlag = false;
				double speed = 0;

				long startStopTime = 0l;
				long endStopTime;
				List<DBObject> stop = new ArrayList<DBObject>();
				List<DBObject> over = new ArrayList<DBObject>();
				List<DBObject> nor = new ArrayList<DBObject>();
				List<DBObject> geofence = new ArrayList<DBObject>();
				List<DBObject> now = new ArrayList<DBObject>();
				DBObject dbo = null;

				for (int i = (list.size() - 1); i >= 0; i--) {

					double slat = Double.parseDouble(list.get(i).get("lat")
							.toString());
					double slng = Double.parseDouble(list.get(i).get("lng")
							.toString());

					double[] result = changegps(slat, slng);

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
					if (stopFlag && speed == 0.0 && i != 0)
						continue;
					nor.add(list.get(i));
					if (speed < 0.2) {
						startStopTime = Long.parseLong(list.get(i).get("time")
								.toString());
						stopFlag = true;
						dbo = list.get(i);

					} else {
						if (stopFlag) {
							endStopTime = Long.parseLong(list.get(i)
									.get("time").toString());
							long time = endStopTime - startStopTime;

							System.out.println(time);

							if (time > 300) {
								dbo.put("stopTime", time);
								stop.add(dbo);
							}
						}
						stopFlag = false;
					}

					if (null != list.get(i).get("overSpeed")) {
						over.add(list.get(i));
					}

				}

				// 围栏
				String fence = ConnectionPoolDao.getroundrail(no);
				// 39.825413103424786, 116.44409179687,0.5,22-06

				if (fence != null) {
					double[] offsetMap = changegps(
							Double.parseDouble(fence.split(",")[0]),
							Double.parseDouble(fence.split(",")[1]));
					DBObject fen = new BasicDBObject();
					fen.put("lat", "" + offsetMap[0]);
					fen.put("lng", "" + offsetMap[1]);
					fen.put("mile", "" + fence.split(",")[2]);
					geofence.add(fen);
				}

				String sgps = RedisUtil.get("GPS" + no);
				DBObject gpsnow = (DBObject) com.mongodb.util.JSON.parse(sgps);
				double[] gpsresult = changegps(
						Double.parseDouble(gpsnow.get("lat").toString()),
						Double.parseDouble(gpsnow.get("lng").toString()));
				gpsnow.put("lat", gpsresult[0]);
				gpsnow.put("lng", gpsresult[1]);
				now.add(gpsnow);

				List<Object> objectList = new ArrayList<Object>();
				objectList.add(nor);
				objectList.add(stop);
				objectList.add(over);
				objectList.add(geofence);
				objectList.add(now);

				JSONArray jsonArray = JSONArray.fromObject(objectList);
				out.write(jsonArray.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}

		/*
		 * String action = request.getParameter("action"); PrintWriter out =
		 * null; out = response.getWriter();
		 * 
		 * if (action.equals("locdev")) { String id =
		 * request.getParameter("id"); String alias
		 * =ConnectionPoolDao.selectParmterNameByPid(id); String json =
		 * "{\"alias\":"+alias+"}"; out.write(json); }
		 */

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

		out.print(result);

	}

	// private static SqlSession initSql()
	// {
	// String resource = "conf.xml";
	// InputStream is = t3.class.getClassLoader().getResourceAsStream(resource);
	// SqlSessionFactory sessionFactory = new
	// SqlSessionFactoryBuilder().build(is);
	// SqlSession session = sessionFactory.openSession();
	//
	// return session;
	// }

	public static void main(String[] args) {

		/*
		 * String resource = "conf.xml"; Reader reader = null; try { reader =
		 * Resources.getResourceAsReader(resource); } catch (IOException e) {
		 * e.printStackTrace(); } SqlSessionFactory factory = new
		 * SqlSessionFactoryBuilder() .build(reader);
		 * factory.getConfiguration().addMapper(IUserOperation.class);
		 * SqlSession sqlSession = factory.openSession(); try { IUserOperation
		 * userInfoMapper = sqlSession .getMapper(IUserOperation.class);
		 * com.ruiyi.wechat.mybatis.model.User user =
		 * userInfoMapper.login("kjx123","fa2b7dea341d377407826895feb74fdf");
		 * System.out.println(user.getPassword()); } finally {
		 * sqlSession.close(); }
		 */

		String resource = "conf.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory factory = new SqlSessionFactoryBuilder()
				.build(reader);
		factory.getConfiguration().addMapper(IUserOperation.class);
		SqlSession sqlSession = factory.openSession();
		try {
			IUserOperation userInfoMapper = sqlSession
					.getMapper(IUserOperation.class);
			List<Parameter> list = userInfoMapper.getParameterList("166");
			System.out.println(list.size());
		} finally {
			sqlSession.close();
		}

	}

	public double[] changegps(double slat, double slng) throws Exception {
		double[] result = new double[2];
		;
		boolean flag = false;
		double offsetLat = 0.0, offsetLng = 0.0;
		double oldLat = 0.0, oldLng = 0.0;

		if (Math.abs(slat - oldLat) > 0.1 || Math.abs(slng - oldLng) > 0.1) {
			flag = true;
			oldLat = slat;
			oldLng = slng;
		}

		if (flag) {
			if (18.2 <= slat && slat <= 53.5 && 73.6 <= slng && slng <= 134.7) {
				// double[] offsetMap = changegps(slat, slng);

				double[] latlng = new double[2];
				double nlat = Math.round(slat * 100) / 100.0;
				double nlng = Math.round(slng * 100) / 100.0;
				DBObject obj = new BasicDBObject();
				obj.put("lng", nlng);
				obj.put("lat", nlat);
				DBObject obj1 = new BasicDBObject();
				obj1.put("_id", 0);
				obj1.put("offsetlng", 1);
				obj1.put("offsetlat", 1);
				List<DBObject> list = MongoDBManager3.getInstance().find(
						"offset", obj, obj1);
				double offset_lng = 0, offset_lat = 0;
				if (list.size() > 0) {
					offset_lng = Double.parseDouble(list.get(0)
							.get("offsetlng").toString());
					;

					offset_lat = Double.parseDouble(list.get(0)
							.get("offsetlat").toString());
				}
				latlng[0] = offset_lat;
				latlng[1] = offset_lng;

				// 118.2 24.7
				if (null != latlng) {
					try {
						offsetLat = latlng[0];
						offsetLng = latlng[1];

					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("offsetLng_null");
					}

				}
			}
			flag = false;
		}

		result[0] = slat + offsetLat;
		result[1] = slng + offsetLng;

		return result;
	}

	public double[] back_changegps(double slat, double slng) throws Exception {
		double[] result = new double[2];
		;
		boolean flag = false;
		double offsetLat = 0.0, offsetLng = 0.0;
		double oldLat = 0.0, oldLng = 0.0;

		if (Math.abs(slat - oldLat) > 0.1 || Math.abs(slng - oldLng) > 0.1) {
			flag = true;
			oldLat = slat;
			oldLng = slng;
		}

		if (flag) {
			if (18.2 <= slat && slat <= 53.5 && 73.6 <= slng && slng <= 134.7) {
				// double[] offsetMap = changegps(slat, slng);

				double[] latlng = new double[2];
				double nlat = Math.round(slat * 100) / 100.0;
				double nlng = Math.round(slng * 100) / 100.0;
				DBObject obj = new BasicDBObject();
				obj.put("lng", nlng);
				obj.put("lat", nlat);
				DBObject obj1 = new BasicDBObject();
				obj1.put("_id", 0);
				obj1.put("offsetlng", 1);
				obj1.put("offsetlat", 1);
				List<DBObject> list = MongoDBManager3.getInstance().find(
						"offset", obj, obj1);
				double offset_lng = 0, offset_lat = 0;
				if (list.size() > 0) {
					offset_lng = Double.parseDouble(list.get(0)
							.get("offsetlng").toString());
					;

					offset_lat = Double.parseDouble(list.get(0)
							.get("offsetlat").toString());
				}
				latlng[0] = offset_lat;
				latlng[1] = offset_lng;

				// 118.2 24.7
				if (null != latlng) {
					try {
						offsetLat = latlng[0];
						offsetLng = latlng[1];

					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("offsetLng_null");
					}

				}
			}
			flag = false;
		}

		result[0] = slat - offsetLat;
		result[1] = slng - offsetLng;

		return result;
	}

}
