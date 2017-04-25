package com.ruiyi.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruiyi.wechat.model.DeviceType;
import com.ruiyi.wechat.model.GprsParameter;
import com.ruiyi.wechat.model.SettingInfo;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.MainClient;
import com.ruiyi.wechat.util.RedisUtil;

//import com.baidu.bdt.java.util.json.JSONObject;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class settingServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String no = request.getParameter("no");
		//停车开关
		String fortiry = request.getParameter("fortiry");
		//sgps
		String sgps = request.getParameter("sgps");
		//路况发送间隔(分)
		String tr_r = request.getParameter("tr_r");
		//邮件开关
		String email_open=request.getParameter("email_open");
		
		String weather_state=request.getParameter("weather_state");
		String news_state=request.getParameter("news_state");
		
		String sosval=request.getParameter("sosval");
		
		String lsen ;
		
		if(no.substring(0,3).equals("352"))
		{
			if(fortiry.equals("0")||fortiry.equals("16")||fortiry.equals("32"))
				 RedisUtil.publish("gps.instruct", no+":MOTION,0,0#");
			else if(fortiry.equals("1"))
				 RedisUtil.publish("gps.instruct", no+":MOTION,1,3#");
			else if(fortiry.equals("17"))
				 RedisUtil.publish("gps.instruct", no+":MOTION,5,3#");
			else if(fortiry.equals("33"))
				 RedisUtil.publish("gps.instruct", no+":MOTION,7,3#");
		}
		
		if(DeviceType.getCutType(no)==4)
		{
			
			if (request.getParameter("restswch").equals("1")) 
			{
				sgps="1,17280,0";
				fortiry="16";
			}
		}
		
		
		SettingInfo settingInfo = new SettingInfo(no, fortiry, sgps, tr_r,email_open,sosval);
		ConnectionPoolDao.updatDeviceSetting(settingInfo);
		ConnectionPoolDao.updatWeatherState(weather_state, no);
		ConnectionPoolDao.updatNewsState(news_state, no);
		
		
		if(DeviceType.getCutType(no)==4)
		{
			
			lsen=request.getParameter("lsenswch");
			
			if (request.getParameter("restswch").equals("1")) 
				ConnectionPoolDao.updatDeviceSetting2(no,"8","30","","","1","22-06",lsen);
			else
				ConnectionPoolDao.updatDeviceSetting2(no,request.getParameter("stzs"),request.getParameter("cgps"),request.getParameter("mphnlist"),request.getParameter("fphnlist"),request.getParameter("restswch"),request.getParameter("vatime"),lsen);
		}
		
		
		
		
		String key = "PAR" + no;// 终端参数KEY
		String sp = RedisUtil.get(key);
		if (sp != null) {
			GprsParameter p = GprsParameter.stringToBean(sp);
			p.setMoveInterval(Short.parseShort(sgps.split(",")[1]));
			p.setStopInterval(Short.parseShort(sgps.split(",")[2]));
			p.setTr_r(Short.parseShort(tr_r));
			p.setFortiry((short) (Short.parseShort(fortiry)));
			
			if(weather_state.equals("0"))
				p.setWeatherTag(false);
			else
				p.setWeatherTag(true);
			
			if(email_open.equals("0"))
				p.setEmailTag(false);
			else
				p.setEmailTag(true);
			
			if(news_state.equals("0"))
				p.setNewsOpen(false);
			else
				p.setNewsOpen(true);
			
			if(sosval.equals("0"))
				p.setSosOpen(false);
			else
				p.setSosOpen(true);
			
			

			if(DeviceType.getCutType(no)==4)
			{
				
				if(request.getParameter("lsenswch").equals("1"))
					p.setLsen(true);
				else
					p.setLsen(false);
				
				if (request.getParameter("restswch").equals("1")) 
				{
					p.setStzs(Short.parseShort("8"));
					p.setCgps(Short.parseShort("30"));
					p.setMphn("");
					p.setFphn("");	
					p.setRest(true);
				}
				else
				{
				p.setStzs(Short.parseShort(request.getParameter("stzs")));
				p.setCgps(Short.parseShort(request.getParameter("cgps")));
				p.setMphn(request.getParameter("mphnlist"));
				p.setFphn(request.getParameter("fphnlist"));
				p.setVaTime(request.getParameter("vatime"));
				
				if(request.getParameter("restswch").equals("0"))
					p.setRest(false);
				else
					p.setRest(true);
				}
			}
			
			
			
			
			sp = GprsParameter.beanToString(p);
			RedisUtil.set(key, sp);
			
			
			//修改设备
			StringBuilder s=new StringBuilder();
			
			if(DeviceType.getCutType(no)==4)
			{
				if (request.getParameter("restswch").equals("1"))
					s.append("SGPS:"+sgps+";RMAP:"+5+";TR_T:"+tr_r+";DEFE:"+fortiry+";STZS:"+"8"+";CGPS:"+"30"+";MPHN:"+""+";FPHN:"+""+";REST:"+request.getParameter("restswch"));
				
				else
					s.append("SGPS:"+sgps+";RMAP:"+5+";TR_T:"+tr_r+";DEFE:"+fortiry+";STZS:"+request.getParameter("stzs")+";CGPS:"+request.getParameter("cgps")+";MPHN:"+request.getParameter("mphnlist")+";FPHN:"+request.getParameter("fphnlist")+";REST:"+request.getParameter("restswch"));
				
			}else
			{
				s.append("SGPS:"+sgps+";RMAP:"+5+";TR_T:"+tr_r+";DEFE:"+fortiry);
				
			}
			
		//	s.append("SGPS:"+sgps+";RMAP:"+5+";TR_T:"+tr_r+";DEFE:"+fortiry+";STZS:"+request.getParameter("stzs")+";CGPS:"+request.getParameter("cgps")+";MPHN:"+request.getParameter("mphnlist")+";FPHN:"+request.getParameter("fphnlist")+";REST:"+request.getParameter("restswch"));
			MainClient.sendTerminalParameterUpdate(Long.parseLong(no), s.toString().getBytes());
			
			System.out.println(RedisUtil.get(key));
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("doGet\r\n");

	}
}
