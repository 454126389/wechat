package com.ruiyi.wechat.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.utils.Sha1Util;

public class MainServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	//网页授权获取用户信息
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Logger logger = Logger.getLogger(TopayServlet.class.getName());
		
		
		String did = request.getParameter("did");
		String money = request.getParameter("money");
		
		String weid = request.getParameter("weid");
		
		String type=request.getParameter("type");
		if(type!=null)
		{
//			money="0.01";
			
			logger.info("支付授权：test");
			
			//共账号及商户相关参数
			String appid = "wx71460f3d267fcd41";
			String backUri = "http://wechat.conqueror.cn/TopayServletForKF";
			//授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
			//最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
			//比如 Sign = %3D%2F%CS% 
			String orderNo=appid+Sha1Util.getTimeStamp();
//			backUri = backUri+"?userId=b88001&orderNo="+orderNo+"&describe=test&money="+money+"&did="+did+"&weid="+weid+"&type="+request.getParameter("type");
			backUri = backUri+"?userId=b88001&orderNo="+orderNo+"&describe=test&money="+money+"&did="+did+"&weid="+weid+"&type="+request.getParameter("type");
			//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
			backUri = URLEncoder.encode(backUri);
			//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
					"appid=" + appid+
					"&redirect_uri=" +
					 backUri+
					"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
			response.sendRedirect(url);
			
		}else
		{
		
		
		logger.info("支付授权：MainServlet");
		
		//共账号及商户相关参数
		String appid = "wx71460f3d267fcd41";
		String backUri = "http://wechat.conqueror.cn/TopayServlet";
		//授权后要跳转的链接所需的参数一般有会员号，金额，订单号之类，
		//最好自己带上一个加密字符串将金额加上一个自定义的key用MD5签名或者自己写的签名,
		//比如 Sign = %3D%2F%CS% 
		String orderNo=appid+Sha1Util.getTimeStamp();
		backUri = backUri+"?userId="+did+"&orderNo="+orderNo+"&describe=test&money="+money+"&did="+did+"&weid="+weid+"&type="+request.getParameter("type");
//		backUri = backUri+"?userId=b88001&orderNo="+orderNo+"&describe=test&money="+money+"&did="+did+"&weid="+weid+"&type="+request.getParameter("type");
		//URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
		backUri = URLEncoder.encode(backUri);
		//scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
				"appid=" + appid+
				"&redirect_uri=" +
				 backUri+
				"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		response.sendRedirect(url);
		}
		return;
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
