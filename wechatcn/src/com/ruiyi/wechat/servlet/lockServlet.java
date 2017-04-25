package com.ruiyi.wechat.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ruiyi.wechat.model.Batch;
import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.model.GprsParameter;
import com.ruiyi.wechat.model.Mileage;
import com.ruiyi.wechat.model.SettingInfo;
import com.ruiyi.wechat.model.User;
import com.ruiyi.wechat.po.Parameter;
import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.MainClient;
import com.ruiyi.wechat.util.Md5PasswordEncoder;
import com.ruiyi.wechat.util.NetUtil;
import com.ruiyi.wechat.util.ReMsg;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.WechatSend;

//import com.baidu.bdt.java.util.json.JSONObject;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class lockServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml;charset=UTF-8");

		// response.addHeader( "Cache-Control", "no-cache"
		// );//浏览器和缓存服务器都不应该缓存页面信息
		// response.addHeader( "Cache-Control", "no-store"
		// );//请求和响应的信息都不应该被存储在对方的磁盘系统中；
		// response.addHeader( "Cache-Control", "must-revalidate"
		// );//于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
		// response.flushBuffer();
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		System.err.println("action=" + action);

		if (action.equals("qrsacn")) {

			String uid = "";
			Cookie mycookies[] = request.getCookies();
			if (mycookies != null) {
				for (int i = 0; i < mycookies.length; i++) {
					if ("uid".equalsIgnoreCase(mycookies[i].getName())) {
						uid = mycookies[i].getValue();
					}
				}
			} else {
				String host = request.getHeader("host");
				uid = "123";
				Cookie mycookie = new Cookie("uid", uid);
				mycookie.setDomain(host);
				mycookie.setMaxAge(93312000);// 三年
				mycookie.setPath("/*/");
				response.addCookie(mycookie);
			}
			System.out.println("uid is>" + uid);

		} else if (action.equals("test")) {
			Cookie[] cookies = request.getCookies();
			Cookie cookie = null;
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("username")) {

					// out.print("用户名"+cookie.getValue());
					// out.close();
					System.out.println(cookie.getValue());

				}
			}
		}

		if (action.equals("getUnLockList")) {

			String phone = request.getParameter("phone");

			List<CarInfo> carlist = ConnectionPoolDao.getUnLockCarList(phone);

			StringBuilder xml = new StringBuilder();
			xml.append("<items>");
			for (CarInfo o : carlist) {
				xml.append("<item>");
				xml.append("<itemno>").append(o.getId()).append("</itemno>");
				xml.append("<itemname>").append(o.getAlias())
						.append("</itemname>");
				xml.append("</item>");
			}
			xml.append("</items>");
			out.print(xml.toString());
			out.close();
		} else if (action.equals("getLockListSetting")) {
			String weid = request.getParameter("weid");

			// ParameterDaoImp pdi=new ParameterDaoImp();
			// List<Parameter> parameterlist =pdi.findByParameterWechatid(weid);

			// List<SettingInfo> settingInfolist=new ArrayList<SettingInfo>();

			List<SettingInfo> carlist = ConnectionPoolDao
					.getLockCarSetting(weid);
			for (int i = 0; i < carlist.size(); i++) {

				carlist.get(i).setWeather_state(
						ConnectionPoolDao.getLockCarWeather(carlist.get(i)
								.getId()) + "");
				carlist.get(i).setNews_state(
						ConnectionPoolDao
								.getLockCarNews(carlist.get(i).getId()) + "");
				// 有wifi
				if (carlist.get(i).getWifi() > 0) {
					long n = Long.parseLong(carlist.get(i).getId());

					MainClient.getWIFI(n, null);

					String wifi_msg = RedisUtil.get("WIFI" + n);

					// String s="1:" +
					// "1,ST_F3D076,aaaaaaaaaaaaaa,0,0,0;" +
					// "1,nqueror02,conqueror02,0,0,0;" +
					// "1,conqueror02,conqueror02,1,3,1;" +
					// "1,conqueror01,,0,0,0;0,SEVEN45,,1,2,0;" +
					// "0,8F-AP,,1,1,0;0,visitor,,1,1,0;" +
					// "0,TP-LINK_XM,,1,3,0;" +
					// "0,weifer_TPLINK,,1,2,0;" +
					// "0,360WiFi-382A,,0,1,0;" +
					// "0,猎豹免费WiFi577,,1,1,0";
					carlist.get(i).setWifimsg(wifi_msg);
				}
			}

			StringBuilder xml = new StringBuilder();
			xml.append("<items>");

			for (SettingInfo o : carlist) {
				// 后视镜不参与
				if (DeviceType.getCutType(o.getId()) != 3) {
					xml.append("<item>");
					xml.append("<itemno>").append(o.getId())
							.append("</itemno>");
					xml.append("<itemwifi>").append(o.getWifi())
							.append("</itemwifi>");
					xml.append("<itemmsg>").append(o.getWifimsg())
							.append("</itemmsg>");
					xml.append("<itemtype>")
							.append(ConnectionPoolDao.getCutType(o.getId()
									.substring(0, 3))).append("</itemtype>");

					xml.append("<itemname>").append(o.getAlias())
							.append("</itemname>");
					xml.append("<itemfortiry>").append(o.getFortiry())
							.append("</itemfortiry>");
					xml.append("<itemsgps>").append(o.getSgps())
							.append("</itemsgps>");
					xml.append("<itemtr_t>").append(o.getTr_t())
							.append("</itemtr_t>");
					xml.append("<itememail>").append(o.getEmail_open())
							.append("</itememail>");
					xml.append("<itemweather>").append(o.getWeather_state())
							.append("</itemweather>");
					xml.append("<itemnews>").append(o.getNews_state())
							.append("</itemnews>");
					xml.append("<itemsos>").append(o.getSos_open())
							.append("</itemsos>");

					if (DeviceType.getCutType(o.getId()) == 4) {
						xml.append("<itemstzs>").append(o.getSTZS())
								.append("</itemstzs>");
						xml.append("<itemcgps>").append(o.getCGPS())
								.append("</itemcgps>");
						xml.append("<itemmphn>").append(o.getMPHN())
								.append("</itemmphn>");
						xml.append("<itemfphn>").append(o.getFPHN())
								.append("</itemfphn>");
						xml.append("<itemrest>").append(o.getREST())
								.append("</itemrest>");
						xml.append("<itemvatime>").append(o.getVATIME())
								.append("</itemvatime>");
						xml.append("<itemlsen>").append(o.getLSEN())
								.append("</itemlsen>");
					}

					xml.append("</item>");
				}
			}
			xml.append("</items>");

			out.print(xml.toString());
			out.close();

		} else if (action.equals("getLockList")) {
			String weid = request.getParameter("weid");

			List<CarInfo> carlist = ConnectionPoolDao.getLockCarList(weid);

			StringBuilder xml = new StringBuilder();
			xml.append("<items>");

			for (CarInfo o : carlist) {
				xml.append("<item>");
				xml.append("<itemno>").append(o.getId()).append("</itemno>");
				xml.append("<itemtype>")
						.append(ConnectionPoolDao.getCutType(o.getId()
								.substring(0, 3))).append("</itemtype>");
				xml.append("<itemname>").append(o.getAlias())
						.append("</itemname>");
				xml.append("</item>");
			}
			xml.append("</items>");

			out.print(xml.toString());
			out.close();

		} else if (action.equals("setLoctList")) {

			String selectlist = request.getParameter("selectlist");

			String weid = request.getParameter("weid");

			String nolist[] = selectlist.split(",");
			Boolean resmsg = ConnectionPoolDao.doLockCar(weid, nolist);

			out.print(resmsg);
			out.close();

		} else if (action.equals("setunLockList")) {

			String selectlist = request.getParameter("selectlist");
			String weid = request.getParameter("weid");
			System.out.println("selectlist=" + selectlist);

			/*
			 * String url =
			 * "http://cloud.conqueror.cn/wechat/delBoundParameter.action?wechatID="
			 * +weid+"&paramters=" + selectlist;
			 */

			String nolist[] = selectlist.split(",");
			Boolean resmsg = ConnectionPoolDao.doUnLockCar(nolist, weid);

			out.print(resmsg);
			out.close();

		} else if (action.equals("firstreques")) {
			String username = request.getParameter("username");
			System.out.println("username=" + username);
			request.setAttribute("username", username);
			request.getRequestDispatcher("jsp/lockdevice.jsp").forward(request,
					response);
		} else if (action.equals("golock")) {
			String weid = request.getParameter("weid");
			request.setAttribute("weid", weid);
			request.getRequestDispatcher("jsp/lockdevice.jsp").forward(request,
					response);
		} else if (action.equals("changeWifi")) {

			System.out.println("" + Long.parseLong(request.getParameter("no")));
			System.out.println("" + request.getParameter("wifimsg"));

			// long n = Long.parseLong(request.getParameter("no"));

			try {
				MainClient.sendWIFI(Long.parseLong(request.getParameter("no")),
						request.getParameter("wifimsg").getBytes("GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (action.equals("getWechatUser")) {
			String result = "";
			String code = request.getParameter("code");
			try {
				URL U = new URL(
						"https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx71460f3d267fcd41&secret=a9df5f8486bb29fea09af0b2c6e0a4a8&code="
								+ code + "&grant_type=authorization_code");
				URLConnection connection = U.openConnection();
				connection.connect();
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream(), "UTF-8"));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
				in.close();

			} catch (Exception e) {
				e.printStackTrace();
				result = "error";
			}
			out.print(result);
			out.close();

		} else if (action.equals("addPar")) {

			String str = "设备不存在";
			String weid = request.getParameter("weid");
			String did = request.getParameter("did");

			if (ConnectionPoolDao.checkDevice(did))

			{
				if (!ConnectionPoolDao.checkUser(did))
					str = oneRegister(did, weid);
				else
					str = updatUser(did, weid);
			}

			out.print(str);
			out.close();

		}
		else if (action.equals("changename")) {

			String str = "设备不存在";
			String name = new String(request.getParameter("name").getBytes("ISO8859_1"),"UTF-8");
			String did = request.getParameter("did");

			ConnectionPoolDao.changeName(name,did);

			out.print(str);
			out.close();

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		// 添加设备 设备出厂时需要添加T表
		/*
		 * if (!ConnectionPoolDao.addTerminal(id)) sbstr.append("添加t设备失败");
		 */

		// 获得设备批次号
//		String batchid = ConnectionPoolDao.getBatchId(id);
		// 获得设置批次信息
//		Batch batch = ConnectionPoolDao.getBatch(batchid);
		// 增加激活设备

		// 激活标志
		if (!ConnectionPoolDao.updateActivate(1, id)) {
			sbstr.append("激活标志失败" + id);
		}

		com.ruiyi.wechat.model.Parameter parameter = new com.ruiyi.wechat.model.Parameter(
				id, id.substring(0, 3), weid);
		// String tic=ConnectionPoolDao.get_ticket(id);

		/*
		 * if(ConnectionPoolDao.checkUser(id)) { if
		 * (!ConnectionPoolDao.UpdateParameter2(parameter))
		 * sbstr.append("更新p设备失败"); }else
		 */
		// if(tic.equals("add"))

//		RedisUtil.set("LBS", id);

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
		

		if (sbstr.length() == 0)
			sbstr.append("设备" + id
					+ "扫码注册成功+\n帐号为id，密码为id后六位。\n为了您的帐号安全请及时登录修改密码");
		return sbstr.toString();
	}

	private String updatUser(String id, String weid) {
		String s = null;
		if (ConnectionPoolDao.doLockCar(weid, id))
			s = "设备绑定成功";
		else
			s = "您已经绑定该设备";
		return s;
	}
}
