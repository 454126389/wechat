package com.ruiyi.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.Positions;
import com.ruiyi.wechat.model.Service;
import com.ruiyi.wechat.model.User;
import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.RedisUtil;
import com.ruiyi.wechat.util.SignUtil;

@SuppressWarnings("serial")
public class TotalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html; charset=utf-8");
		
//		String weid = request.getParameter("weid");
		
		
		String action = request.getParameter("action");
		
		if(action.equals("buyservers"))
		{
			int pt=Integer.parseInt(request.getParameter("pt"));
			int ll=Integer.parseInt(request.getParameter("ll"));
			int dx=Integer.parseInt(request.getParameter("dx"));
			String no = request.getParameter("no");
			
//			獲取當前時間
			String nowtime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			
			
			//获得余额
//			int balance=ConnectionPoolDao.getBalanceByNo(no);
			User user=ConnectionPoolDao.getUserInfoByNo(no);
			
			if(pt<=0||ll<=0||dx<=0)
				log("cost error");
			if(user.getBalance()<(pt+ll+dx))
				log("金额不足");
			else
			{
				//修改余额
				ConnectionPoolDao.updateBalanceByNo((user.getBalance()-(pt+ll+dx)), no);
			
				//平台服務4
				if(pt>0)
				{
					//进行记录
					ConnectionPoolDao.addConsume(user.getId(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), pt, 4, no);
					Service service=ConnectionPoolDao.getService(4,no);
					
					//已經激活的話，修改結束時間
					if(service.getSTATE()>0)
						switch (pt) {
						case 120:
							ConnectionPoolDao.updatServiceState(service.getSTATE(), service.getSTARTTIME(), getTime(Calendar.YEAR, service.getENDTIME(), 1), 4, no);
							break;
						case 200:
							ConnectionPoolDao.updatServiceState(service.getSTATE(), service.getSTARTTIME(), getTime(Calendar.YEAR, service.getENDTIME(), 2), 4, no);
							break;
						case 260:
							ConnectionPoolDao.updatServiceState(service.getSTATE(), service.getSTARTTIME(), getTime(Calendar.YEAR, service.getENDTIME(), 3), 4, no);
							break;

						default:
							break;
						}
						
					else//未激活：激活，當前時間為起始，結束時間。
						
						switch (pt) {
						case 120:
							ConnectionPoolDao.updatServiceState(1, nowtime, getTime(Calendar.YEAR, nowtime, 1), 4, no);
							break;
						case 200:
							ConnectionPoolDao.updatServiceState(1, nowtime, getTime(Calendar.YEAR, nowtime, 2), 4, no);
							break;
						case 260:
							ConnectionPoolDao.updatServiceState(1, nowtime, getTime(Calendar.YEAR, nowtime, 3), 4, no);
							break;

						default:
							break;
						}
					
				}
				
				if(ll>0)
				{
					ConnectionPoolDao.addConsume(user.getId(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), ll, 6, no);
					Service service=ConnectionPoolDao.getService(6,no);
					
					//已經激活的話，修改結束時間
					if(service.getSTATE()>0)
						switch (ll) {
						case 15:
							ConnectionPoolDao.updatServiceState(service.getSTATE(), service.getSTARTTIME(), getTime(Calendar.MONTH, service.getENDTIME(), 3), 6, no);
							break;
						case 60:
							ConnectionPoolDao.updatServiceState(service.getSTATE(), service.getSTARTTIME(), getTime(Calendar.YEAR, service.getENDTIME(), 1), 6, no);
							break;

						default:
							break;
						}
					else//未激活：激活，當前時間為起始，結束時間。
							switch (pt) {
							case 15:
								ConnectionPoolDao.updatServiceState(1, nowtime, getTime(Calendar.MONTH, nowtime, 3), 6, no);
								break;
							case 60:
								ConnectionPoolDao.updatServiceState(1, nowtime, getTime(Calendar.YEAR, nowtime, 1), 6, no);
								break;
							default:
								break;
							}
					
				}
				
				if(dx>0)
				{
					//添加記錄
					ConnectionPoolDao.addConsume(user.getId(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), dx, 3, no);
					//激活服務
					ConnectionPoolDao.updatServiceState(1,null,null, 6, no);
					//增加短信數量
					ConnectionPoolDao.updateSimNum(no,100);
					
					//更新sim
					ConnectionPoolDao.updateSimInfo(nowtime, ConnectionPoolDao.getImsi(no));
					
					
				}
					
				
				
				
				
				
			}
					return;
		}
		

		
		
	}
	
	
	
	String getTime(int type, String time,int adjuctNum) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String val = null;
		try {
			Date sdate = formatter.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdate);
			calendar.set(type,calendar.get(type)+adjuctNum);
			Date date=calendar.getTime();
			
			val=new SimpleDateFormat("yyyy-MM-dd").format(date);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
		
		
		
	}
}
