package com.ruiyi.wechat.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ruiyi.wechat.model.Batch;
import com.ruiyi.wechat.model.Car;
import com.ruiyi.wechat.model.GprsParameter;
import com.ruiyi.wechat.model.Parameter;
import com.ruiyi.wechat.model.RegisterDevice;
import com.ruiyi.wechat.model.User;
import com.ruiyi.wechat.model.Voiture;
import com.ruiyi.wechat.string.DeString;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.Md5PasswordEncoder;
import com.ruiyi.wechat.util.NetUtil;
import com.ruiyi.wechat.util.RedisUtil;

//import com.baidu.bdt.java.util.json.JSONObject;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class userRegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// servlet返回的内容不允许客户端缓存
		response.setContentType("application/json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("userRegisterServlet");
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		System.out.println("action=" + action);

		if (action.equals("getcartypelist")) {
			String weid = request.getParameter("weid");
			String username = null;

				List<Voiture> cartypelist = ConnectionPoolDao.getCarTypeList();
				request.setAttribute("cartypelist", cartypelist);

				request.getRequestDispatcher(
						"jsp/userRegister.jsp?weid=" + weid).forward(request,
						response);
//			}

		} else if (action.equals("checkusername")) {
			String username = request.getParameter("username");
			System.out.println("checkusername="
					+ ConnectionPoolDao.isUsername(username));
			if (ConnectionPoolDao.isUsername(username))
				out.print("isTrue");
			else
				out.print("isFalse");

		} else if (action.equals("checkid")) {
			String id = request.getParameter("id");
			System.out.println(ConnectionPoolDao.isId(id));
			if (ConnectionPoolDao.isId(id))
				out.print("isTrue");
			else
				out.print("isFalse");

		} else if (action.equals("checkidisused")) {
			String id = request.getParameter("id");
			System.out.println("checkidisused="
					+ ConnectionPoolDao.isIdisused(id));
			if (ConnectionPoolDao.isIdisused(id))
				out.print("isTrue");
			else
				out.print("isFalse");
		} else if (action.equals("checkkey")) {
			String key = request.getParameter("key");
			String id = request.getParameter("id");
			System.out.println(id + "---" + key);
			if (ConnectionPoolDao.isKey(id, key))
				out.print("isTrue");
			else
				out.print("isFalse");

		} else if (action.equals("isuser")) {
			String username = request.getParameter("username");
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String pswmd5 = md5.encodePassword(request.getParameter("psw"),
					request.getParameter("username"));

			if (ConnectionPoolDao.isUser(username, pswmd5))
				out.print("isTrue");
			else
				out.print("isFalse");

		} else if (action.equals("gettwoselect")) {
			String s1 = new String(request.getParameter("s1").getBytes(
					"ISO8859_1"), "UTF-8");

			String path = getServletContext().getRealPath("/jsp/text");
			List<String> citylist = new ArrayList<String>();
			File file = new File(path + "/QCPProvinceCityConfig.txt");

			if (file.isFile() && file.exists()) { // 判断文件是否存在

				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), "UTF-8");// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					if (s1.equals(lineTxt.split(",")[0])) {
						citylist.add(lineTxt.split(",")[2]);
					}
				}
				String jsonstr = JSONSerializer.toJSON(citylist).toString();
				System.out.println("jsonstr=" + jsonstr);
				out.print(jsonstr);

			} else
				System.out.println("找不到指定的文件");

		} else if (action.equals("changeUserInfo")) {
			// 修改USER表
			String tel = request.getParameter("tel");
			String qq = request.getParameter("qq");
			String email = request.getParameter("email");
			String id = request.getParameter("id");

			System.out.println("tel=" + tel);
			System.out.println("qq=" + qq);
			System.out.println("email=" + email);
			System.out.println("id=" + id);

			// 修改用户表
			ConnectionPoolDao.updataUserInfo(id, tel, qq, email);

			// 修改用户所有设备的tel
			//ArrayList<String> list = ConnectionPoolDao.getUserParamterById(id);
			
			User user = ConnectionPoolDao.getUserInfo(id);
			
			List<Car> carlist = ConnectionPoolDao
					.getUserParamterById(user.getId());
			
			for (Car tempid : carlist) {
				ConnectionPoolDao.updataParmterPhone(tempid.getNo(), tel);
				// 空号不存
				if (!tel.equals("")) {
					String key = "PAR" + tempid;// 终端参数KEY
					String sp = RedisUtil.get(key);
					// 缓存设备存在
					if (sp != null) {
						GprsParameter gp = GprsParameter.stringToBean(sp);
						gp.setZh_number(tel);
						sp = GprsParameter.beanToString(gp);

						RedisUtil.set(key, sp);
					}
				}
			}

		} else if (action.equals("getUserCarListView")) {

			String username = request.getParameter("username");
			User user = ConnectionPoolDao.getUserInfo(username);
			System.out.println("username=" + username);

			// 车辆类型列表
			List<Voiture> cartypelist = ConnectionPoolDao.getCarTypeList();
			request.setAttribute("cartypelist", cartypelist);

			// 车辆列表
			List<Car> carlist = ConnectionPoolDao
					.getUserParamterById(user.getId());
			request.setAttribute("carlist", carlist);

			request.getRequestDispatcher("jsp/logincarlist.jsp").forward(
					request, response);

		} else if (action.equals("login")) {

			String weid = request.getParameter("weid");
			String username = request.getParameter("username");
			User user = ConnectionPoolDao.getUserInfo(username);

			request.setAttribute("user", user);
			request.setAttribute("weid", weid);

		

			request.setAttribute("wenum",
					ConnectionPoolDao.getLockCarNum(weid) + "");

			//获取所有设备列表
			List<Car> carlist = ConnectionPoolDao
					.getUserParamterById(user.getId());
			
			request.setAttribute("totalnum", carlist.size());
			
			
			request.setAttribute("carlist", carlist);

			// 车辆类型列表
			List<Voiture> cartypelist = ConnectionPoolDao.getCarTypeList();
			request.setAttribute("cartypelist", cartypelist);

			request.getRequestDispatcher("jsp/loginuser.jsp").forward(request,
					response);

		} else if (action.equals("lock")) {

			String pid = request.getParameter("pid");
			String weid = request.getParameter("weid");


			Boolean resmsg = ConnectionPoolDao.doLockCar(weid, pid);

			out.print(resmsg);
			out.close();
		} else if (action.equals("unlock")) {

			String pid = request.getParameter("pid");
			Boolean resmsg = ConnectionPoolDao.doUnLockCar(pid);
			out.print(resmsg);
			out.close();
		} else if (action.equals("lockone")) {

			String username = request.getParameter("username");
			User user = ConnectionPoolDao.getUserInfo(username);
			String weid = request.getParameter("weid");

			String[] idlist=ConnectionPoolDao.getPidByUid(user.getId());
			
			Boolean resmsg = ConnectionPoolDao.doLockCar(weid,idlist);

			out.print(resmsg);
			out.close();
		} else if (action.equals("unlockone")) {

			String username = request.getParameter("username");
			User user = ConnectionPoolDao.getUserInfo(username);

			 String weid = request.getParameter("weid");
			
			String[] idlist=ConnectionPoolDao.getPidByUid(user.getId());
			

			Boolean resmsg = ConnectionPoolDao.doUnLockCar(idlist,weid);
			out.print(resmsg);
			out.close();
		} else if (action.equals("oneregister")) {
			
			String weid = request.getParameter("weid");
			String id = request.getParameter("id");

			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			String pswmd5 = md5.encodePassword(
					id.substring(id.length() - 6, id.length()), id);
			System.out.println("帐号：" + id);
			System.out.println("密码："
					+ id.substring(id.length() - 6, id.length()));

			// 获得注册时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Calendar calender = Calendar.getInstance();
			String time = simpleDateFormat.format(calender.getTime())
					.toString();

			// 添加用户
			User user = new User(id, pswmd5, time,
					request.getHeader("X-Real-IP"));

			if (!ConnectionPoolDao.addUser2(user))
				out.write("添加用户失败");

			// 取得用户id
			String userid = ConnectionPoolDao.getUserId(id);
			// 添加用户角色
			if (!ConnectionPoolDao.addUserRole(userid))
				out.write("添加用户角色失败");
			// 添加设备
			if (!ConnectionPoolDao.addTerminal(userid))
				out.write("添加t设备失败");

			// 获得设备批次号
//			String batchid = ConnectionPoolDao.getBatchId(id);
			// 获得设置批次信息
//			Batch batch = ConnectionPoolDao.getBatch(batchid);
			// 增加激活设备
			
			ConnectionPoolDao.updateActivate(1, id);
			

			Parameter parameter = new Parameter(id,id.substring(0, 3), weid);
			if (!ConnectionPoolDao.addParameter2(parameter))
				out.write("添加p设备失败");
			
			
			ConnectionPoolDao.doLockCar(weid, id);

			// 添加设备用户对应表
			if (!ConnectionPoolDao.addUserParameter(userid, id))
				out.write("添加设备用户对应表失败");
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			Calendar c = Calendar.getInstance();
			String starttime = df.format(c.getTime()); // 返回String型的时间
			
/*			int addmonth=1;
			if(ConnectionPoolDao.getCutType(id)==3)*/
			int	addmonth=3;
				if(ConnectionPoolDao.getCutType(id)==4||ConnectionPoolDao.getCutType(id)==3||ConnectionPoolDao.getCutType(id) == 6)
				addmonth=12;
			c.add(Calendar.MONTH, addmonth); // 将当前日期加一个月
			String endtime1 = df.format(c.getTime()); // 返回String型的时间
			c.add(Calendar.MONTH, -1); // 将当前日期减一个月
			c.add(Calendar.YEAR, 1); // 将当前日期加一个月
			String endtime2 = df.format(c.getTime()); // 返回String型的时间
	
			
			// 添加服务状态表
			if (!ConnectionPoolDao.addServiceParameter(
					id, starttime, endtime1, endtime2)) 
				out.write("添加服务状态应表失败");

			// 放入缓存
			String key = "PAR" + id;// 终端参数KEY
			String sp = RedisUtil.get(key);
//			if (sp != null) {
				GprsParameter p = new GprsParameter();
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
//			}
		/*	else
			{
				
				
				RegisterDevice d=ConnectionPoolDao.RegisterDeviceSelect(id);
				GprsParameter p = GprsParameter.stringToBean(sp);
				p.setDeviceType(Short.parseShort(d.getCUT()));
				p.setLanguage(d.getLANGUAGE());
				
				p.setZh_number(d.getZH_NO());
				p.setFu_number(d.getF_NO());
				p.setLicence(d.getALIAS());
				
				p.setRoundRail(d.getFENCE());
				p.setEmailTag(Boolean.parseBoolean(d.getEMAIL_OPEN()));
				p.setSmsTag(Boolean.parseBoolean(d.getSMS_OPEN()));
				p.setFortiry(Short.parseShort(d.getFORTIRY()));
				
				String[] s = d.getSGPS().split(",");
				
				p.setGprsOpen(Boolean.parseBoolean(s[0]));
				p.setMoveInterval(Short.parseShort(s[1]));
				p.setStopInterval(Short.parseShort(s[2]));
				
				p.setRmap(Short.parseShort(d.getRMAP()));
				p.setTr_r(Short.parseShort(d.getTR_T()));
				p.setSpru(Short.parseShort(d.getSPRU()));
				
				p.setNewsOpen(Boolean.parseBoolean(d.getNEWSOPEN()));
				p.setVaTime(d.getVATIME());
				
				sp = GprsParameter.beanToString(p);
				RedisUtil.set(key, sp);
				String nkey = "NOT" + id;
				RedisUtil.del(nkey);
				
			}*/
			
			
			
			
			

		}

		out.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		// System.out.println("username=" + request.getParameter("username"));
		// System.out.println("psw" + request.getParameter("psw"));

		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String pswmd5 = md5.encodePassword(request.getParameter("psw"),
				request.getParameter("username"));

		String action = request.getParameter("action");
		if (action.equals("register")) {
			String[] arr = request.getParameterValues("cartype");

			// 获得注册时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			Calendar calender = Calendar.getInstance();
			String time = simpleDateFormat.format(calender.getTime())
					.toString();

			// 添加用户
			User user = new User(request.getParameter("username"), pswmd5, "1",
					request.getParameter("email"), request.getParameter("tel"),
					time, request.getHeader("X-Real-IP"), 0.00f, "5", "", "");

			if (!ConnectionPoolDao.addUser(user)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=添加用户失败")
						.forward(request, response);
				return;
			}

			// 取得用户id
			String userid = ConnectionPoolDao.getUserId(request
					.getParameter("username"));
			// 添加用户角色
			if (!ConnectionPoolDao.addUserRole(userid)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=添加用户角色失败")
						.forward(request, response);
				return;
			}

			// 获得设备批次号
			String batchid = ConnectionPoolDao.getBatchId(request.getParameter("id"));
					
			// 获得设置批次信息
			Batch batch = ConnectionPoolDao.getBatch(batchid);
			// 增加激活设备
			
			
			

			Parameter parameter = new Parameter(request.getParameter("id"),
					batch.getLanguage(), request.getParameter("tel"),
					request.getParameter("carno"), (request.getParameter("id")).substring(0, 3),
					arr[0], "0", "0", "0", "1,30,1800", "50", "30",
					request.getParameter("weid"), "0");
			if (!ConnectionPoolDao.addParameter(parameter)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=增加激活设备失败")
						.forward(request, response);
				return;
			}
			
			
			ConnectionPoolDao.doLockCar(request.getParameter("weid"), request.getParameter("id"));
			

			// 激活标志
			if (!ConnectionPoolDao
					.updateActivate(1, request.getParameter("id"))) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=激活标志失败")
						.forward(request, response);
				return;
			}
			// 添加设备用户对应表
			if (!ConnectionPoolDao.addUserParameter(userid,
					request.getParameter("id"))) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=添加设备用户对应表失败")
						.forward(request, response);
				return;
			}

			// 获得设备IMIS
			String imsi = ConnectionPoolDao.getIMSI(request.getParameter("id"));
			// 获得sim时间
			String simtime = ConnectionPoolDao.getSimTime(imsi);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			System.out.println("simtime=" + simtime);

			Calendar c = Calendar.getInstance();
			String starttime = df.format(c.getTime()); // 返回String型的时间

			System.out.println("starttime=" + starttime);

		//	c.add(Calendar.MONTH, 1); // 将当前日期加一个月
			
			
/*			int addmonth=1;
			if(ConnectionPoolDao.getCutType(request.getParameter("id"))==3)*/
			int	addmonth=3;
				if(ConnectionPoolDao.getCutType(request.getParameter("id"))==4||ConnectionPoolDao.getCutType(request.getParameter("id"))==3||ConnectionPoolDao.getCutType(request.getParameter("id"))==6)
				addmonth=12;
			c.add(Calendar.MONTH, addmonth); // 将当前日期加一个月

			String endtime1 = df.format(c.getTime()); // 返回String型的时间

		//	System.out.println("endtime1=" + endtime1);
			
			

			

			c.add(Calendar.MONTH, -1); // 将当前日期减一个月
			c.add(Calendar.YEAR, 1); // 将当前日期加一个月

			String endtime2 = df.format(c.getTime()); // 返回String型的时间
			Date dt1 = null;
			Date dt2 = null;
			try {
				dt1 = df.parse(endtime2);
				if (null != simtime)
					dt2 = df.parse(simtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 如果simt不为空且小于当前时间-1年则取小的值
			if (null != simtime) {
				if (dt2.getTime() < dt1.getTime())
					endtime2 = simtime;
			}

			System.out.println("endtime2=" + endtime2);

			// 添加服务状态表
			if (!ConnectionPoolDao.addServiceParameter(
					request.getParameter("id"), starttime, endtime1, endtime2)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=添加服务状态表")
						.forward(request, response);
				return;
			}

			user = ConnectionPoolDao.getUserInfo(request
						.getParameter("username"));
			// 车辆列表
			List<Car> carlist = ConnectionPoolDao.getUserParamterById(user.getId());
			request.setAttribute("carlist", carlist);

			// 车辆类型列表
			List<Voiture> cartypelist = ConnectionPoolDao.getCarTypeList();
			request.setAttribute("cartypelist", cartypelist);

			request.getRequestDispatcher("jsp/logincarlist.jsp").forward(
					request, response);

			// 放入缓存
			String key = "PAR" + request.getParameter("id");// 终端参数KEY
			String sp = RedisUtil.get(key);
//			if (sp != null) {
				GprsParameter p =new GprsParameter();
				p.setLicence(request.getParameter("carno"));
				if(ConnectionPoolDao.getCutType(request.getParameter("id"))==4)
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
				// flag
				String nkey = "NOT" + request.getParameter("id");
				RedisUtil.del(nkey);
				// flag
				// System.out.println(RedisUtil.get(key));
//			}else
//			{
				
	/*			select pt.CUT,LANGUAGE,ZH_NO,F_NO,ALIAS,
				FENCE,EMAIL_OPEN,SMS_OPEN,FORTIRY,SGPS,
				RMAP,TR_T,SPRU,NEWSOPEN,VATIME 
				from parameter as p,product as pt where pt.ID=LEFT(p.ID,3) and p.ID=?*/
				
//				RegisterDevice d=ConnectionPoolDao.RegisterDeviceSelect(request
//						.getParameter("id"));
//				GprsParameter p = GprsParameter.stringToBean(sp);
//				p.setDeviceType(Short.parseShort(d.getCUT()));
//				p.setLanguage(d.getLANGUAGE());
//				
//				p.setZh_number(d.getZH_NO());
//				p.setFu_number(d.getF_NO());
//				p.setLicence(d.getALIAS());
//				
//				p.setRoundRail(d.getFENCE());
//				p.setEmailTag(Boolean.parseBoolean(d.getEMAIL_OPEN()));
//				p.setSmsTag(Boolean.parseBoolean(d.getSMS_OPEN()));
//				p.setFortiry(Short.parseShort(d.getFORTIRY()));
//				
//				String[] s = d.getSGPS().split(",");
//				
//				p.setGprsOpen(Boolean.parseBoolean(s[0]));
//				p.setMoveInterval(Short.parseShort(s[1]));
//				p.setStopInterval(Short.parseShort(s[2]));
//				
//				p.setRmap(Short.parseShort(d.getRMAP()));
//				p.setTr_r(Short.parseShort(d.getTR_T()));
//				p.setSpru(Short.parseShort(d.getSPRU()));
//				
//				p.setNewsOpen(Boolean.parseBoolean(d.getNEWSOPEN()));
//				p.setVaTime(d.getVATIME());
//				
//				sp = GprsParameter.beanToString(p);
//				RedisUtil.set(key, sp);
//				String nkey = "NOT" + request
//						.getParameter("id");
//				RedisUtil.del(nkey);
//				
//			}

		} else if (action.equals("login")) {
			String username = request.getParameter("username");

			System.out.println("username=" + username);

			User user = ConnectionPoolDao.getUserInfo(username);
			request.setAttribute("user", user);

			request.setAttribute("totalnum", ConnectionPoolDao
					.getUserParamterById(user.getId()).size());

			request.setAttribute("wenum",
					ConnectionPoolDao.getUserParmaterNum(user.getId()) + "");

			
			user = ConnectionPoolDao.getUserInfo(request
					.getParameter("username"));
		// 车辆列表
		List<Car> carlist = ConnectionPoolDao.getUserParamterById(user.getId());
			
			
			request.setAttribute("carlist", carlist);

			// 车辆类型列表
			List<Voiture> cartypelist = ConnectionPoolDao.getCarTypeList();
			request.setAttribute("cartypelist", cartypelist);

			request.getRequestDispatcher("jsp/loginuser.jsp").forward(request,
					response);

		} else if (action.equals("login_add")) {

			// 取得用户id
			User user = ConnectionPoolDao.getUserInfo(request
					.getParameter("username"));
			// 取得用户id
			String userid = ConnectionPoolDao.getUserId(request
					.getParameter("username"));
			// 获得设备批次号
			String batchid = ConnectionPoolDao.getBatchId(userid);
			// 获得设置批次信息
			Batch batch = ConnectionPoolDao.getBatch(batchid);
			// 增加激活设备
			
			
			RedisUtil.set("LBS",request.getParameter("id"));
			

			Parameter parameter = new Parameter(request.getParameter("id"),
					batch.getLanguage(), user.getPhone(), "未命名设备",
					batch.getProduct_id(), "1", "0", "0", "0", "1,30,1800",
					"50", "30", request.getParameter("weid"), "0");
			if (!ConnectionPoolDao.addParameter(parameter)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=增加激活设备失败")
						.forward(request, response);
				return;
			}

			
			ConnectionPoolDao.doLockCar(request.getParameter("weid"), request.getParameter("id"));
			
			// 激活标志
			if (!ConnectionPoolDao
					.updateActivate(1, request.getParameter("id"))) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=激活标志失败")
						.forward(request, response);
				return;
			}

			// 添加设备用户对应表
			if (!ConnectionPoolDao.addUserParameter(userid,
					request.getParameter("id"))) {
				request.getRequestDispatcher(
						"jsp/errortip.jsp?tip=添加设备用户对应表失败" + userid + "_"
								+ request.getParameter("id")).forward(request,
						response);
				return;
			}

			
			user = ConnectionPoolDao.getUserInfo(request
					.getParameter("username"));
			// 车辆列表
			List<Car> carlist = ConnectionPoolDao.getUserParamterById(user.getId());
			
			request.setAttribute("carlist", carlist);

			// 车辆类型列表
			List<Voiture> cartypelist = ConnectionPoolDao.getCarTypeList();
			request.setAttribute("cartypelist", cartypelist);

			request.getRequestDispatcher("jsp/logincarlist.jsp").forward(
					request, response);

		} else if (action.equals("add_device")) {

			String username = request.getParameter("username");

			String[] arr = request.getParameterValues("cartype");

			// 获得设备批次号
			String batchid = ConnectionPoolDao.getBatchId(request
					.getParameter("id"));
			// 获得设置批次信息
			Batch batch = ConnectionPoolDao.getBatch(batchid);
			// 增加激活设备

			String tel = ConnectionPoolDao.getTel(username);
			// 增加激活设备

			System.out.println("tel=" + tel);

			Parameter parameter = new Parameter(request.getParameter("id"),
					batch.getLanguage(), tel, request.getParameter("carno"),
					batch.getProduct_id(), arr[0], "0", "0", "0", "1,30,1800",
					"50", "30", request.getParameter("weid"), "0");
			if (!ConnectionPoolDao.addParameter(parameter)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=增加激活设备失败")
						.forward(request, response);
				return;
			}

			
			ConnectionPoolDao.doLockCar(request.getParameter("weid"), request.getParameter("id"));
			
			// 激活标志
			if (!ConnectionPoolDao
					.updateActivate(1, request.getParameter("id"))) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=激活标志失败")
						.forward(request, response);
				return;
			}

			// 取得用户id
			String userid = ConnectionPoolDao.getUserId(request
					.getParameter("username"));

			// 添加设备用户对应表
			if (!ConnectionPoolDao.addUserParameter(userid,
					request.getParameter("id"))) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=添加设备用户对应表失败")
						.forward(request, response);
				return;
			}

			// 获得设备IMIS
			String imsi = ConnectionPoolDao.getIMSI(request.getParameter("id"));
			// 获得sim时间
			String simtime = ConnectionPoolDao.getSimTime(imsi);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			System.out.println("simtime=" + simtime);

			Calendar c = Calendar.getInstance();
			String starttime = df.format(c.getTime()); // 返回String型的时间

			System.out.println("starttime=" + starttime);

//			c.add(Calendar.MONTH, 1); // 将当前日期加一个月
			
			
			
//			int addmonth=1;
//			if(ConnectionPoolDao.getCutType(request.getParameter("id"))==3)
				int	addmonth=3;
				if(ConnectionPoolDao.getCutType(request.getParameter("id"))==4||ConnectionPoolDao.getCutType(request.getParameter("id"))==3||ConnectionPoolDao.getCutType(request.getParameter("id"))==6)
				addmonth=12;
			c.add(Calendar.MONTH, addmonth); // 将当前日期加一个月


			String endtime1 = df.format(c.getTime()); // 返回String型的时间

			System.out.println("endtime1=" + endtime1);

			c.add(Calendar.MONTH, -1); // 将当前日期减一个月
			c.add(Calendar.YEAR, 1); // 将当前日期加一个月

			String endtime2 = df.format(c.getTime()); // 返回String型的时间
			Date dt1 = null;
			Date dt2 = null;
			try {
				dt1 = df.parse(endtime2);
				if (null != simtime)
					dt2 = df.parse(simtime);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 如果simt不为空且小于当前时间-1年则取小的值
			if (null != simtime) {
				if (dt2.getTime() < dt1.getTime())
					endtime2 = simtime;
			}


			// 添加服务状态表
			if (!ConnectionPoolDao.addServiceParameter(
					request.getParameter("id"), starttime, endtime1, endtime2)) {
				request.getRequestDispatcher("jsp/errortip.jsp?tip=添加服务状态表")
						.forward(request, response);
				return;
			}

			// 放入缓存
			String key = "PAR" + request.getParameter("id");// 终端参数KEY
			String sp = RedisUtil.get(key);
			if (sp != null) {
				GprsParameter p = GprsParameter.stringToBean(sp);
				p.setMoveInterval(Short.parseShort("30"));
				p.setStopInterval(Short.parseShort("1800"));
				p.setTr_r(Short.parseShort("30"));
				p.setFortiry((short) (Short.parseShort("16")));
				p.setWeatherTag(true);
				p.setNewsOpen(true);
				p.setEmailTag(true);

				sp = GprsParameter.beanToString(p);
				RedisUtil.set(key, sp);
				System.out.println(RedisUtil.get(key));
			}else
			{
				
	/*			select pt.CUT,LANGUAGE,ZH_NO,F_NO,ALIAS,
				FENCE,EMAIL_OPEN,SMS_OPEN,FORTIRY,SGPS,
				RMAP,TR_T,SPRU,NEWSOPEN,VATIME 
				from parameter as p,product as pt where pt.ID=LEFT(p.ID,3) and p.ID=?*/
				
				RegisterDevice d=ConnectionPoolDao.RegisterDeviceSelect(request
						.getParameter("id"));
				GprsParameter p = GprsParameter.stringToBean(sp);
				p.setDeviceType(Short.parseShort(d.getCUT()));
				p.setLanguage(d.getLANGUAGE());
				
				p.setZh_number(d.getZH_NO());
				p.setFu_number(d.getF_NO());
				p.setLicence(d.getALIAS());
				
				p.setRoundRail(d.getFENCE());
				p.setEmailTag(Boolean.parseBoolean(d.getEMAIL_OPEN()));
				p.setSmsTag(Boolean.parseBoolean(d.getSMS_OPEN()));
				p.setFortiry(Short.parseShort(d.getFORTIRY()));
				
				String[] s = d.getSGPS().split(",");
				
				p.setGprsOpen(Boolean.parseBoolean(s[0]));
				p.setMoveInterval(Short.parseShort(s[1]));
				p.setStopInterval(Short.parseShort(s[2]));
				
				p.setRmap(Short.parseShort(d.getRMAP()));
				p.setTr_r(Short.parseShort(d.getTR_T()));
				p.setSpru(Short.parseShort(d.getSPRU()));
				
				p.setNewsOpen(Boolean.parseBoolean(d.getNEWSOPEN()));
				p.setVaTime(d.getVATIME());
				
				sp = GprsParameter.beanToString(p);
				RedisUtil.set(key, sp);
				String nkey = "NOT" + request
						.getParameter("id");
				RedisUtil.del(nkey);
				
			}

			request.getRequestDispatcher("userRegisterServlet?action=login")
					.forward(request, response);

		}

	}

	private StringBuffer urlConnectionPost(String tourl, StringBuffer data) {
		StringBuffer sb = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;
		URL url;
		try {
			url = new URL(tourl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			// 当存在post的值时，才打开OutputStreamWriter
			if (data != null && data.toString().trim().length() > 0) {
				wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				wr.write(data.toString());
				wr.flush();
			}

			// Get the response
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF-8"));
			sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb;
	}
	

}
