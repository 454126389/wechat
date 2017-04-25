package com.ruiyi.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruiyi.wechat.string.Language;
import com.ruiyi.wechat.util.MainClient;

//import com.baidu.bdt.java.util.json.JSONObject;

/**
 * 核心请求处理类
 * 
 * @author liufeng
 * @date 2013-05-18
 */
public class speekServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// servlet返回的内容不允许客户端缓存
		response.setContentType("text/xml;charset=UTF-8");
		
		 response.addHeader( "Cache-Control", "no-cache" );//浏览器和缓存服务器都不应该缓存页面信息
		 response.addHeader( "Cache-Control", "no-store" );//请求和响应的信息都不应该被存储在对方的磁盘系统中；    
	     response.addHeader( "Cache-Control", "must-revalidate" );//于客户机的每次请求，代理服务器必须想服务器验证缓存是否过时；
		
	     response.flushBuffer();
		PrintWriter out = response.getWriter();
		
		String action = request.getParameter("action");
		
		if (action.equals("speek_news")) {
			
			request.setCharacterEncoding("UTF-8");
			
			String resmsg="";
			String selectlist = request.getParameter("selectlist");
			String nos[]=selectlist.split(",");
//			String content = request.getParameter("message");
			
			String content =new String(request.getParameter("message").getBytes("ISO8859_1"),"UTF-8");
			
			String bm=Language.broadcast+":"+content;
			for (String sn : nos) {
				long n = Long.parseLong(sn);
			    try {
			    	//id,内容
					MainClient.sendNewsMessage(n, bm.getBytes("GBK"));
					resmsg = Language.broadcast_suc;
				} catch (UnsupportedEncodingException e) {
					resmsg = Language.broadcast_fail;
				}
			}

			out.print(resmsg);
			out.close();
			
		}else if (action.equals("take_photo")) {
			
			request.setCharacterEncoding("UTF-8");
			
			String resmsg="";
			String selectlist = request.getParameter("selectlist");
			String nos[]=selectlist.split(",");
			
			for (String sn : nos) {
				long n = Long.parseLong(sn);
			    try {
			    	//id,内容
					MainClient.askImageMessage(n);
					resmsg = "请求拍照成功";
				} catch (Exception e) {
					resmsg = "请求拍照失败";
				}
			}

			out.print(resmsg);
			out.close();
			
		}
		
			
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.write("doGet\r\n");

	}
}
