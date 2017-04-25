package com.ruiyi.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCursor;
import com.mongodb.util.JSON;
import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.model.GprsParameter;
import com.ruiyi.wechat.model.GpsPoi;
import com.ruiyi.wechat.model.Mileage;
import com.ruiyi.wechat.model.Positions;
import com.ruiyi.wechat.model.Service;
import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.Gps;
import com.ruiyi.wechat.util.HttpUtil2;
import com.ruiyi.wechat.util.MongoDBManager;
import com.ruiyi.wechat.util.MongoDBManager2;
import com.ruiyi.wechat.util.PositionUtil;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.SignUtil;

//import com.baidu.bdt.java.util.json.JSONObject;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class carLocationServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public static String getHistoru(String sdate, String edate, String no) {
	
		
		JSONArray jsonArray = null;

		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

			Date nsDate = sdf.parse(sdate);
			Date neDate = sdf.parse(edate);
		

			long st=nsDate.getTime()/1000;
			long et=neDate.getTime()/1000;
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
			


			double offsetLat = 0.0, offsetLng = 0.0;
			double oldLat = 0.0, oldLng = 0.0;
			boolean flag = false;
			boolean stopFlag = false;
			double speed = 0;
			
			long startStopTime=0l;
			long endStopTime;
			List<DBObject> stop = new ArrayList<DBObject>();
			List<DBObject> over = new ArrayList<DBObject>();
			List<DBObject> nor = new ArrayList<DBObject>();
			DBObject dbo=null;
			
			for (int i = (list.size() - 1); i >= 0; i--) {
				
				double slat = Double.parseDouble(list.get(i).get("lat")
						.toString());
				double slng = Double.parseDouble(list.get(i).get("lng")
						.toString());
				
				double nlat = Math.round(slat * 10) / 10.0;
				double nlng = Math.round(slng * 10) / 10.0;
				if (Math.abs(slat - oldLat) > 0.1
						|| Math.abs(slng - oldLng) > 0.1) {
					flag = true;
					oldLat = slat;
					oldLng = slng;
				}

				if (flag) {
					if (18.2 <= slat && slat <= 53.5 && 73.6 <= slng
							&& slng <= 134.7) {
						Map<String, Double> offsetMap = ConnectionPoolDao
								.getOffset(nlng, nlat);
						// 118.2 24.7
						if (null != offsetMap) {
							try {
								offsetLng = offsetMap.get("offset_lng");
								offsetLat = offsetMap.get("offset_lat");
							} catch (Exception e) {
								// TODO: handle exception
								System.out.println("offsetLng_null");
							}

						}
					}
					flag = false;
				}
//				1560106041961041
				if(no.equals("1560106041961041")||no.equals("1560106041971042")||no.equals("1560107000811419"))
				{
					list.get(i).put("lat", slat );
					list.get(i).put("lng", slng );
				}else{
					list.get(i).put("lat", slat + offsetLat);
					list.get(i).put("lng", slng + offsetLng);
				}
				
				
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
				if (speed < 0.02&&i!=0) {
					startStopTime = Long.parseLong(list.get(i).get("time").toString());
					stopFlag = true;
					dbo=list.get(i);
					
				} else {
//					System.out.println(list.get(i).toString());
					if (stopFlag) {
						endStopTime = Long.parseLong(list.get(i).get("time").toString());
						long time = endStopTime - startStopTime;
						
						System.out.println(time);
						
						if (time > 300) {
//							list.get(i).put("stopTime", time);
							dbo.put("stopTime", time);
							stop.add(dbo);
//							stop.add(list.get(i));
						}
					}
					stopFlag = false;
				}
				
				

	

				int limitSpeed = 0;
				String overSpeed = "0";
				if (null != list.get(i).get("limitSpeed"))
					limitSpeed = Integer.parseInt(list.get(i).get("limitSpeed")
							.toString());

				if (null != list.get(i).get("overSpeed"))
				{
					overSpeed = list.get(i).get("overSpeed").toString();
					over.add(list.get(i));
				}
		
				
				
				


			}
			
			List<Object> objectList=new ArrayList<Object>();
			objectList.add(nor);
			objectList.add(stop);
			objectList.add(over);
			
			 jsonArray = JSONArray.fromObject(objectList);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return jsonArray.toString();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");

		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();

		// 轨迹回放
		if (action.equals("getHistroy")) {
			String didhis = request.getParameter("didhis");
			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");
			
			System.out.println("start_time="+start_time);
			System.out.println("end_time="+end_time);
			System.out.println(didhis);

			Service s = ConnectionPoolDao.getService(4, didhis);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dtnow = null, dtend = null;
			try {

				dtend = df.parse(s.getENDTIME());
				dtnow = new Date();
			} catch (Exception e) {
				// TODO: handle exception
			}

			String result = null;
			if (dtnow.getTime() < dtend.getTime())
				result = getHistoru(start_time, end_time, didhis);

			out.print(result);
			out.close();

		}

		if (action.equals("getNearRank")) {
			String appcaptcha = "";
			try {
				appcaptcha = request.getParameter("appcaptcha");
			} catch (Exception e) {
				// TODO: handle exception
				appcaptcha = "appcaptcha=" + appcaptcha + "&appattach=null";
			}
			String url = "http://app.eclicks.cn/violation2/dataif?action=near&lng=118.156196&lat=24.648412&limit=20&start=&appid=10"
					+ appcaptcha;
			String responseMsg = HttpUtil2.http(url, null);
			out.print(responseMsg);
			out.close();

		}

		if (action.equals("getCarList")) {

			String weid = request.getParameter("weid");
			List<CarInfo> carlist = ConnectionPoolDao.getLockCarList(weid);
			StringBuilder mess = new StringBuilder();
			String gpskeys;
			String sgps;
			for (int i = 0; i < carlist.size(); i++) {

				gpskeys = "GPS" + carlist.get(i).getId();
				sgps = RedisUtil.get(gpskeys);
				if (i > 0 && mess.length() > 0)
					mess.append(";");
				if (sgps == null)
					sgps = "{ 'no' :"
							+ carlist.get(i).getId()
							+ " ,'lat' : null , 'lng' : null , 'alt' : null , 'speed' : null , 'dir' : null , 'mile' : null , 'info' : null , 'sims' : null , 'time' : null , 'count' : null}";
				mess.append(sgps);
			}

			List<Positions> carPosiList = new ArrayList<Positions>();
			String[] carstrs = mess.toString().split(";");
			JSONObject jsonObject;
			for (int i = 0; i < carstrs.length; i++) {

				try {
					jsonObject = new JSONObject(carstrs[i]);
					Positions position = new Positions();
					position.setName(carlist.get(i).getAlias());
					position.setNo(jsonObject.getString("no"));

					if (jsonObject.getString("lng").equals("null")
							&& jsonObject.getString("lat").equals("null")) {
						position.setStatus(Language.never_open);
					} else {

						List<String> latlnglist = SignUtil.changegps(
								jsonObject.getString("lat"),
								jsonObject.getString("lng"));
						position.setGpsPosiLat(latlnglist.get(0));
						position.setGpsPosiLon(latlnglist.get(1));

						position.setSpeed(jsonObject.getString("speed"));
						position.setDeriction(jsonObject.getString("dir"));
						position.setTime(jsonObject.getString("time"));

						Long hours = System.currentTimeMillis()
								- Long.parseLong(jsonObject.getString("time"))
								* 1000;

						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						String sb = simpleDateFormat
								.format(Long.parseLong(jsonObject
										.getString("time")) * 1000);

						position.setLasttime(sb);

						if (hours >= 1000 * 60 * 60 * 2)
							position.setStatus(Language.out_line);
						else
							position.setStatus(Language.on_line);
					}
					carPosiList.add(position);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			request.setAttribute("carPosiList", carPosiList);
			JSONArray carPosiListJson = JSONArray.fromObject(carPosiList);
			request.setAttribute("carPosiListJson", carPosiListJson);

			request.getRequestDispatcher("jsp/carlocationlast3.jsp").forward(
					request, response);
			return;
		}

		if (action.equals("getCarNow")) {

			String car_id = request.getParameter("car_id");
			String pname = ConnectionPoolDao.selectParmterNameByPid(car_id);

			// StringBuilder mess = new StringBuilder();
			String gpskeys;
			String sgps;
			// car_id="1010104410141163";

			gpskeys = "GPS" + car_id;
			sgps = RedisUtil.get(gpskeys);
			// mess.append(sgps);

			JSONObject jsonObject = null;
			Positions position = null;
			try {
				jsonObject = new JSONObject(sgps);
				position = new Positions();
				position.setName(pname);
				position.setNo(jsonObject.getString("no"));

				if (jsonObject.getString("lng").equals("null")
						&& jsonObject.getString("lat").equals("null")) {
					position.setStatus(Language.never_open);
				} else {

					List<String> latlnglist = SignUtil.changegps(
							jsonObject.getString("lat"),
							jsonObject.getString("lng"));
					position.setGpsPosiLat(latlnglist.get(0));
					position.setGpsPosiLon(latlnglist.get(1));

					position.setSpeed(jsonObject.getString("speed"));
					position.setDeriction(jsonObject.getString("dir"));
					position.setTime(jsonObject.getString("time"));

					Long hours = System.currentTimeMillis()
							- Long.parseLong(jsonObject.getString("time"))
							* 1000;

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					String sb = simpleDateFormat.format(Long
							.parseLong(jsonObject.getString("time")) * 1000);

					position.setLasttime(sb);

					if (hours >= 1000 * 60 * 60 * 2)
						position.setStatus(Language.out_line);
					else
						position.setStatus(Language.on_line);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.write("" + JSONArray.fromObject(position));
			// request.setAttribute("sgps", sgps);
			return;

		}

		// weidc查找设备列表
		if (action.equals("getCarListByWeid")) {
			String weid = request.getParameter("weid");
			List<CarInfo> carlist = ConnectionPoolDao.getLockCarList(weid);

			JSONArray carPosiListJson = JSONArray.fromObject(carlist);

			out.write(carPosiListJson.toString());
		}

		if (action.equals("getMileage")) {

			String weid = request.getParameter("weid");

			List<CarInfo> carlist = ConnectionPoolDao.getLockCarList(weid);

			StringBuilder mess = new StringBuilder();
			String gpskeys;
			String sgps;
			for (int i = 0; i < carlist.size(); i++) {

				gpskeys = "GPS" + carlist.get(i).getId();
				sgps = RedisUtil.get(gpskeys);
				if (i > 0 && mess.length() > 0)
					mess.append(";");
				mess.append(sgps);
			}

			List<Positions> carPosiList = new ArrayList<Positions>();
			if (null == mess) {
				for (int i = 0; i < carlist.size(); i++) {
					Positions position = new Positions();
					position.setName(carlist.get(i).getAlias());
					position.setNo(carlist.get(i).getId());
					position.setStatus(Language.never_open);
					carPosiList.add(position);
				}
			} else {

				String[] carstrs = mess.toString().split(";");
				JSONObject jsonObject;
				for (int i = 0; i < carstrs.length; i++) {

					try {
						jsonObject = new JSONObject(carstrs[i]);
						Positions position = new Positions();
						position.setName(carlist.get(i).getAlias());
						position.setNo(jsonObject.getString("no"));

						List<String> latlnglist = SignUtil.changegps(
								jsonObject.getString("lat"),
								jsonObject.getString("lng"));

						position.setGpsPosiLat(latlnglist.get(0));
						position.setGpsPosiLon(latlnglist.get(1));
						position.setSpeed(jsonObject.getString("speed"));
						position.setDeriction(jsonObject.getString("dir"));
						position.setTime(jsonObject.getString("time"));

						Long hours = System.currentTimeMillis()
								- Long.parseLong(jsonObject.getString("time"))
								* 1000;

						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");

						String sb = simpleDateFormat
								.format(Long.parseLong(jsonObject
										.getString("time")) * 1000);

						position.setLasttime(sb);

						if (hours >= 1000 * 60 * 60 * 2)
							position.setStatus(Language.out_line);
						else
							position.setStatus(Language.on_line);
						carPosiList.add(position);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			request.setAttribute("carPosiList", carPosiList);

			request.getRequestDispatcher("jsp/mileage.jsp").forward(request,
					response);
			return;
		} else if (action.equals("addroundrail"))// 添加围栏
		{
			String didhis = request.getParameter("didhis");
			String latLng = request.getParameter("latLng");
			String rail = request.getParameter("rail");
			String roundtime = request.getParameter("roundtime");
			
			
			String mylat[]=latLng.substring(1, latLng.length() - 2).split(",");
			Gps gps=PositionUtil.gcj_To_Gps84(Double.parseDouble(mylat[0]),Double.parseDouble(mylat[1]));

			ConnectionPoolDao.addroundrail(didhis,
					gps.getWgLat()+","+gps.getWgLon() + "," + rail + ","
							+ roundtime);

			String key = "PAR" + didhis;// 终端参数KEY
			String sp = RedisUtil.get(key);
			GprsParameter p = null;
			if (sp == null)
				p = new GprsParameter();
			else
				p = GprsParameter.stringToBean(sp);

			p.setRoundRail(gps.getWgLat()+","+gps.getWgLon() + ","
					+ rail);
			sp = GprsParameter.beanToString(p);
			RedisUtil.set(key, sp);

		} else if (action.equals("getroundrail"))// 添加围栏
		{
			String didhis = request.getParameter("didhis");


			String fence= ConnectionPoolDao.getroundrail(didhis);
			Gps g2=PositionUtil.gps84_To_Gcj02(Double.parseDouble(fence.split(",")[0]), Double.parseDouble(fence.split(",")[1]));

			out.print(g2.getWgLat()+","+g2.getWgLon()+","+fence.split(",")[2]+","+fence.split(",")[3]);
			out.close();

		} else if (action.equals("removeroundrail"))// 添加围栏
		{
			String didhis = request.getParameter("didhis");

			// String table;
			// if (DeviceType.getCutType(didhis.substring(0, 3)))
			// table="hsjfrz";
			// else
			// table="parameter";

			ConnectionPoolDao.removeroundrail(didhis);

			String key = "PAR" + didhis;// 终端参数KEY
			String sp = RedisUtil.get(key);

			GprsParameter p = null;
			if (sp == null)
				p = new GprsParameter();
			else
				p = GprsParameter.stringToBean(sp);

			// GprsParameter p = GprsParameter.stringToBean(sp);
			p.setRoundRail(null);
			sp = GprsParameter.beanToString(p);
			RedisUtil.set(key, sp);

		} else if (action.equals("getCarOnMap")) {
			
			String weid = request.getParameter("weid"); // 微信号

			List<CarInfo> carlist = ConnectionPoolDao.getCarListOnMap(weid);
			
			CarInfo temp;
			String selectid = ConnectionPoolDao.getSelect(weid);
			for (int i = 0; i < carlist.size(); i++) {
				if (carlist.get(i).getId().equals(selectid)) {
					temp = carlist.get(i);
					carlist.remove(i);
					carlist.add(0, temp);
				}
			}
			

			List<Positions> carPosiList = new ArrayList<Positions>();

			getgps(carlist, carPosiList);

			JSONArray carPosiListJson = JSONArray.fromObject(carPosiList);

			out.write(carPosiListJson.toString());

		} else if (action.equals("getCarGps")) {
			String id = request.getParameter("did");
			String gpskeys = "GPS" + id;
			String sgps = RedisUtil.get(gpskeys);

			Positions position = new Positions();
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(sgps);
				List<String> latlnglist = SignUtil.changegps(
						jsonObject.getString("lat"),
						jsonObject.getString("lng"));
				position.setGpsPosiLat(latlnglist.get(0));
				position.setGpsPosiLon(latlnglist.get(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<Positions> carPosiList = new ArrayList<Positions>();
			carPosiList.add(position);
			JSONArray jsonArray = JSONArray.fromObject(carPosiList);

			out.write(jsonArray.toString());
		} else if (action.equals("getLocStart")) {
			String did = request.getParameter("did"); // 微信号
			String time = request.getParameter("time"); // 微信号
			RedisUtil.set("LOC" + did, time);
		} else if (action.equals("getCarInfo")) {
			String did = request.getParameter("did");

			List<CarInfo> carlist = ConnectionPoolDao.getCarInfo(did);

			List<Positions> carPosiList = new ArrayList<Positions>();

			getgps(carlist, carPosiList);

			JSONArray carPosiListJson = JSONArray.fromObject(carPosiList);

			out.write(carPosiListJson.toString());

		} else if(action.equals("getLastPoi"))
		{
			String did = request.getParameter("did");
			String gpskeys;
			gpskeys = "GPS" + did;
			String sgps = RedisUtil.get(gpskeys);
			
			JSONObject jsonObject;
			List<String> latlnglist = null;
			try {
				jsonObject = new JSONObject(sgps);
				latlnglist = SignUtil.changegps(
						jsonObject.getString("lat"),
						jsonObject.getString("lng"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String result = null;
			if(latlnglist!=null)
			 result="{\"lat\" : "+latlnglist.get(0)+" , \"lng\" : "+latlnglist.get(1)+"}";

			
			System.out.println(result);
			
			out.write(result);
		}
			else if (action.equals("searchMileage")) {

			String stime = request.getParameter("start_time");
			String etime = request.getParameter("end_time");
			String didhis = request.getParameter("didhis");

			/*
			 * if (DeviceType.getCutType(didhis)) {
			 * MongoDBManager2.getInstance() .find("gps", obj, obj1); }else {
			 * MongoDBManager2 }
			 */

			Long todayTimeS = 0l;
			Long todayTimeE = 0l;
			try {
				todayTimeS = new SimpleDateFormat("yyyy-MM-dd").parse(
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
						.getTime();
				todayTimeE = todayTimeS + 86400000l;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {

				List<Mileage> resmsg = ConnectionPoolDao.getMileage(didhis,
						stime, etime);

				double today_miles = 0;
				/*
				 * if (DeviceType.getCutType(didhis.substring(0, 3))) { Document
				 * dbo21 = new Document(); Document dbo22 = new Document();
				 * Document dbo1 = new Document(); Document dbo2 = new
				 * Document(); Document dbo3 = new Document(); Document dbo4 =
				 * new Document(); dbo3.append("$max", "$odometer");
				 * dbo4.append("$min", "$odometer"); dbo2.append("_id", "$no");
				 * dbo2.append("maxMile", dbo3); dbo2.append("minMile", dbo4);
				 * dbo1.append("$group", dbo2); dbo22.append("no",
				 * Long.parseLong(didhis));
				 * 
				 * dbo22.append("time", new Document("$gt", todayTimeS)
				 * .append("$lt", todayTimeE)); dbo21.append("$match", dbo22);
				 * List<Document> test = new ArrayList<Document>();
				 * test.add(dbo21); test.add(dbo1); MongoCursor<Document> list =
				 * MongoDBManager2.aggregate( "gps", test); while
				 * (list.hasNext()) { Document doc = (list.next()); // double
				 * maxMile = doc.getDouble("maxMile"); // double minMile =
				 * doc.getDouble("minMile"); //
				 * System.out.println("今日行程为"+(maxMile-minMile)); today_miles =
				 * doc.getDouble("maxMile") - doc.getDouble("minMile"); } } else
				 * {
				 */
				DBObject dbo21 = new BasicDBObject();
				DBObject dbo22 = new BasicDBObject();
				DBObject dbo1 = new BasicDBObject();
				DBObject dbo2 = new BasicDBObject();
				DBObject dbo3 = new BasicDBObject();
				DBObject dbo4 = new BasicDBObject();
				dbo3.put("$max", "$mile");
				dbo4.put("$min", "$mile");
				dbo2.put("_id", "$no");
				dbo2.put("maxMile", dbo3);
				dbo2.put("minMile", dbo4);
				dbo1.put("$group", dbo2);
				dbo22.put("no", Long.parseLong(didhis));
				dbo22.put("time", new BasicDBObject("$gt", todayTimeS).append(
						"$lt", todayTimeE));
				dbo21.put("$match", dbo22);

				try {

					Iterator<DBObject> it = MongoDBManager.aggregate("gps",
							dbo21, dbo1);
					while (it.hasNext()) {
						DBObject d = it.next();
						/*
						 * double maxMile = Double.parseDouble(d.get("maxMile")
						 * .toString()); double minMile =
						 * Double.parseDouble(d.get("minMile") .toString());
						 */
						today_miles = Double.parseDouble(d.get("maxMile")
								.toString())
								- Double.parseDouble(d.get("minMile")
										.toString());

					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				/* } */

				// 今日里程
				resmsg.add(new Mileage("今日里程", "" + today_miles));

				JSONArray s = JSONArray.fromObject(resmsg);

				out.print(s.toString());
				out.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 支持多功能的设备
		} else if (action.equals("getOnlineDevice")) {
			String weid = request.getParameter("weid"); // 微信号

			List<CarInfo> carlist = ConnectionPoolDao.getCarListOnMap(weid);

			List<CarInfo> carlist2 = new ArrayList<CarInfo>();

			for (int i = 0; i < carlist.size(); i++)
				if (DeviceType.getCutType((carlist.get(i).getId()).substring(0,
						3)) == 3
						|| DeviceType.getCutType((carlist.get(i).getId())
								.substring(0, 3)) == 2||DeviceType.getCutType((carlist.get(i).getId())
										.substring(0, 3)) == 5)
					carlist2.add(carlist.get(i));

			List<Positions> carPosiList = new ArrayList<Positions>();

			getgps(carlist2, carPosiList);

			Positions temp;
			String selectid = ConnectionPoolDao.getSelect(weid);
			for (int i = 0; i < carPosiList.size(); i++) {
				if (carPosiList.get(i).getNo().equals(selectid)) {
					temp = carPosiList.get(i);
					carPosiList.remove(i);
					carPosiList.add(0, temp);
				}
			}

			JSONArray carPosiListJson = JSONArray.fromObject(carPosiList);
			out.write(carPosiListJson.toString());
		} else if (action.equals("check_pick_up")) {
			String weid = request.getParameter("weid"); // 微信号

			List<CarInfo> carlist = ConnectionPoolDao.getCarListOnMap(weid);

			List<CarInfo> carlist2 = new ArrayList<CarInfo>();
			for (int i = 0; i < carlist.size(); i++)
				if (DeviceType.getCutType((carlist.get(i).getId()).substring(0,
						3)) == 3)
					carlist2.add(carlist.get(i));

			JSONArray carPosiListJson = JSONArray.fromObject(carlist2);
			out.write(carPosiListJson.toString());
		}

	}

	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + " days " + hours + " hours " + minutes + " minutes "
				+ seconds + " seconds ";
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void getgps(List<CarInfo> carlist, List<Positions> carPosiList) {

		StringBuilder mess = new StringBuilder();

		String gpskeys;
		String sgps;
		for (int i = 0; i < carlist.size(); i++) {
			
			
			String fence=carlist.get(i).getFence();
			if(fence!=null&&!fence.equals(""))
			{
				Gps g2=PositionUtil.gps84_To_Gcj02(Double.parseDouble(fence.split(",")[0]), Double.parseDouble(fence.split(",")[1]));
				if(fence.split(",").length>3)
					carlist.get(i).setFence(g2.getWgLat()+","+g2.getWgLon()+","+fence.split(",")[2]+","+fence.split(",")[3]);
				else
					carlist.get(i).setFence(g2.getWgLat()+","+g2.getWgLon()+","+fence.split(",")[2]);
			}
			

			gpskeys = "GPS" + carlist.get(i).getId();
			sgps = RedisUtil.get(gpskeys);

			if (sgps == null)
				sgps = "{ \"no\" :"
						+ carlist.get(i).getId()
						+ " ,\"lat\" : null , \"lng\" : null , \"alt\" : null , \"speed\" : null , \"dir\" : null , \"mile\" : null , \"info\" : null , \"sims\" : null , \"time\" : null ,\"bat\":null,\"count\" : null}";

			mess.append(sgps);

			if (i < carlist.size() - 1)
				mess.append(";");

		}

		String[] carstrs = new String[] {};
		if (mess.length() > 1)
			carstrs = mess.toString().split(";");

		JSONObject jsonObject;
		for (int i = 0; i < carstrs.length; i++) {
			try {
				jsonObject = new JSONObject(carstrs[i]);
				Positions position = new Positions();
				position.setName(carlist.get(i).getAlias());
				position.setFence(carlist.get(i).getFence());

				try {
					position.setBat(jsonObject.getString("bat"));
					position.setElec(jsonObject.getString("elec"));
				} catch (Exception e) {
					// TODO: handle exception
					position.setBat(null);
					position.setElec(null);
				}

				position.setNo(jsonObject.getString("no"));

				if (jsonObject.getString("lng").equals("null")
						&& jsonObject.getString("lat").equals("null")) {
					position.setStatus(Language.never_open);
					position.setType(""
							+ ConnectionPoolDao.getCutType(jsonObject
									.getString("no")));
				} else {

					List<String> latlnglist = SignUtil.changegps(
							jsonObject.getString("lat"),
							jsonObject.getString("lng"));
					position.setGpsPosiLat(latlnglist.get(0));
					position.setGpsPosiLon(latlnglist.get(1));

					position.setSpeed(jsonObject.getString("speed"));
					position.setDeriction(jsonObject.getString("dir"));

					position.setTime(jsonObject.getString("time"));

					Service st = ConnectionPoolDao.getService(4, position.getNo());
					
					position.setService_tinme(st.getENDTIME());
					
					Long hours = System.currentTimeMillis()
							- Long.parseLong(jsonObject.getString("time"))
							* 1000;

					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");

					String sb = simpleDateFormat.format(Long
							.parseLong(jsonObject.getString("time")) * 1000);

					position.setLasttime(sb);

					// 2小时
					// if (hours >= 1000 * 60 * 60 * 2)
					if (!DeviceType.isOnline(jsonObject.getString("no")))
						position.setStatus(Language.out_line);
					else
						position.setStatus(Language.on_line);

					position.setType(""
							+ ConnectionPoolDao.getCutType(jsonObject
									.getString("no")));

				}
				carPosiList.add(position);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	public static void main(String[] args) {
		
		
		String stime = "2017-04-03";
		String etime = "2017-04-10";
		String didhis = "352544072210389";

		Long todayTimeS = 0l;
		Long todayTimeE = 0l;
		try {
			todayTimeS = new SimpleDateFormat("yyyy-MM-dd").parse(
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
					.getTime();
			todayTimeE = todayTimeS + 86400000l;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			List<Mileage> resmsg = ConnectionPoolDao.getMileage(didhis,
					stime, etime);

			double today_miles = 0;
			/*
			 * if (DeviceType.getCutType(didhis.substring(0, 3))) { Document
			 * dbo21 = new Document(); Document dbo22 = new Document();
			 * Document dbo1 = new Document(); Document dbo2 = new
			 * Document(); Document dbo3 = new Document(); Document dbo4 =
			 * new Document(); dbo3.append("$max", "$odometer");
			 * dbo4.append("$min", "$odometer"); dbo2.append("_id", "$no");
			 * dbo2.append("maxMile", dbo3); dbo2.append("minMile", dbo4);
			 * dbo1.append("$group", dbo2); dbo22.append("no",
			 * Long.parseLong(didhis));
			 * 
			 * dbo22.append("time", new Document("$gt", todayTimeS)
			 * .append("$lt", todayTimeE)); dbo21.append("$match", dbo22);
			 * List<Document> test = new ArrayList<Document>();
			 * test.add(dbo21); test.add(dbo1); MongoCursor<Document> list =
			 * MongoDBManager2.aggregate( "gps", test); while
			 * (list.hasNext()) { Document doc = (list.next()); // double
			 * maxMile = doc.getDouble("maxMile"); // double minMile =
			 * doc.getDouble("minMile"); //
			 * System.out.println("今日行程为"+(maxMile-minMile)); today_miles =
			 * doc.getDouble("maxMile") - doc.getDouble("minMile"); } } else
			 * {
			 */
			DBObject dbo21 = new BasicDBObject();
			DBObject dbo22 = new BasicDBObject();
			DBObject dbo1 = new BasicDBObject();
			DBObject dbo2 = new BasicDBObject();
			DBObject dbo3 = new BasicDBObject();
			DBObject dbo4 = new BasicDBObject();
			dbo3.put("$max", "$mile");
			dbo4.put("$min", "$mile");
			dbo2.put("_id", "$no");
			dbo2.put("maxMile", dbo3);
			dbo2.put("minMile", dbo4);
			dbo1.put("$group", dbo2);
			dbo22.put("no", Long.parseLong(didhis));
			dbo22.put("time", new BasicDBObject("$gt", todayTimeS).append(
					"$lt", todayTimeE));
			dbo21.put("$match", dbo22);

			try {

				Iterator<DBObject> it = MongoDBManager.aggregate("gps",
						dbo21, dbo1);
				while (it.hasNext()) {
					DBObject d = it.next();
					/*
					 * double maxMile = Double.parseDouble(d.get("maxMile")
					 * .toString()); double minMile =
					 * Double.parseDouble(d.get("minMile") .toString());
					 */
					today_miles = Double.parseDouble(d.get("maxMile")
							.toString())
							- Double.parseDouble(d.get("minMile")
									.toString());

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			/* } */

			// 今日里程
			resmsg.add(new Mileage("今日里程", "" + today_miles));

			JSONArray s = JSONArray.fromObject(resmsg);

			System.out.println(s.toString());
//			out.print(s.toString());
//			out.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
