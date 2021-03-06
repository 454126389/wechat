package com.ruiyi.wechat.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;

import com.ruiyi.wechat.util.Md5PasswordEncoder;
import com.utils.CommonUtil;
import com.utils.GetWxOrderno;
import com.utils.RequestHandler;
import com.utils.Sha1Util;
import com.utils.TenpayUtil;
import com.utils.WeixinOauth2Token;
import com.utils.http.HttpResponse;


public class TopayServletForKF extends HttpServlet {

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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Logger logger = Logger.getLogger(TopayServletForKF.class.getName());
		
		//网页授权后获取传递的参数
		String userId = request.getParameter("userId"); 	
		String orderNo = request.getParameter("orderNo"); 
		String money = request.getParameter("money");
		String code = request.getParameter("code");
		String did = request.getParameter("did");
		
		String weid = request.getParameter("weid");
		
		String service_id = null;
//		String dec = null;
		
		if(money.equals("10")||money.equals("20")||money.equals("50"))
			{
			service_id="3";
			}
		else if(money.equals("120")||money.equals("200")||money.equals("260"))
		{
			service_id="4";
		}else if(money.equals("60"))
			service_id="6";
		

		
		
		//金额转化为分为单位
		float sessionmoney = Float.parseFloat(money);
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		
		//商户相关资料 
		String appid = "wx71460f3d267fcd41";
		String appsecret = "687b30e3fd3c1c911758fc4afb255205";
		String partner = "1317179801";
		String partnerkey = "11111111222222223333333344444444";
		
		
		String openId =weid;
//		String URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+appsecret+"&code="+code+"&grant_type=authorization_code";
//		
//		logger.info("支付url："+URL);
		
//		JSONObject jsonObject = CommonUtil.httpsRequest(URL, "GET", null);
//		if (null != jsonObject) {
//			openId = jsonObject.getString("openid");
//		}
		
		logger.info("支付openId："+openId);
		
		//获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
				String currTime = TenpayUtil.getCurrTime();
				//8位日期
				String strTime = currTime.substring(8, currTime.length());
				//四位随机数
				String strRandom = TenpayUtil.buildRandom(4) + "";
				//10位序列号,可以自行调整。
				String strReq = strTime + strRandom;
				
				
				//商户号
				String mch_id = partner;
				//子商户号  非必输
				//String sub_mch_id="";
				//设备号   非必输
				String device_info="";
				//随机数 
				String nonce_str = strReq;
				//商品描述
				//String body = describe;
				
				//商品描述根据情况修改
				String body = "征服者增值服务";
				//附加数据
				String attach = userId;
				//商户订单号
				String out_trade_no = orderNo;
				int intMoney = Integer.parseInt(finalmoney);
				
				//总金额以分为单位，不带小数点
				int total_fee = intMoney;
				//订单生成的机器 IP
				String spbill_create_ip = request.getRemoteAddr();
				//订 单 生 成 时 间   非必输
//				String time_start ="";
				//订单失效时间      非必输
//				String time_expire = "";
				//商品标记   非必输
//				String goods_tag = "";
				
				//这里notify_url是 支付完成后微信发给该链接信息，可以判断会员是否支付成功，改变订单状态等。
				String notify_url ="http://wechat.conqueror.cn/NotifyServlet";
				
				String trade_type = "NATIVE";
				String openid = openId;
				//非必输
//				String product_id = "";
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("appid", appid);  
				packageParams.put("mch_id", mch_id);  
				packageParams.put("nonce_str", nonce_str);  
				packageParams.put("body", body);  
				packageParams.put("attach", attach);  
				packageParams.put("out_trade_no", out_trade_no);  
				packageParams.put("product_id", did);  
				
				//这里写的金额为1 分到时修改
		/*		if(money.equals("0.01"))
					finalmoney="1";*/
				
				finalmoney="1";
				
				packageParams.put("total_fee", finalmoney);  
				packageParams.put("spbill_create_ip", spbill_create_ip);  
				packageParams.put("notify_url", notify_url);  
				
				packageParams.put("trade_type", trade_type);  
				packageParams.put("openid", openid);  

				RequestHandler reqHandler = new RequestHandler(request, response);
				reqHandler.init(appid, appsecret, partnerkey);
				
				String sign = reqHandler.createSign(packageParams);
				String xml="<xml>"+
						"<appid>"+appid+"</appid>"+
						"<mch_id>"+mch_id+"</mch_id>"+
						"<nonce_str>"+nonce_str+"</nonce_str>"+
						"<sign>"+sign+"</sign>"+
						"<body><![CDATA["+body+"]]></body>"+
						"<attach>"+attach+"</attach>"+
						"<out_trade_no>"+out_trade_no+"</out_trade_no>"+
						"<product_id>"+did+"</product_id>"+
						//金额，这里写的1 分到时修改
//						"<total_fee>"+1+"</total_fee>"+
						"<total_fee>"+finalmoney+"</total_fee>"+
						"<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>"+
						"<notify_url>"+notify_url+"</notify_url>"+
						"<trade_type>"+trade_type+"</trade_type>"+
						"<openid>"+openid+"</openid>"+
						"</xml>";
				
				
				
				String allParameters = "";
				try {
					allParameters =  reqHandler.genPackage(packageParams);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
				String prepay_id="";
				try {
					prepay_id = new GetWxOrderno().getErCode(createOrderURL, xml);
					
					
					logger.info("支付结果："+prepay_id);
					if(prepay_id.equals("")){
						
						logger.info("支付失败："+xml);
						
//						request.setAttribute("ErrorMsg", "统一支付接口获取预支付订单出错");
						response.sendRedirect("jsp/error.jsp");
					}else
					{
						
						logger.info("支付成功："+prepay_id);
						
//						SortedMap<String, String> finalpackage = new TreeMap<String, String>();
//						String appid2 = appid;
//						String timestamp = Sha1Util.getTimeStamp();
//						String nonceStr2 = nonce_str;
//						String prepay_id2 = "prepay_id="+prepay_id;
//						String packages = prepay_id2;
//						finalpackage.put("appId", appid2);  
//						finalpackage.put("timeStamp", timestamp);  
//						finalpackage.put("nonceStr", nonceStr2);  
//						finalpackage.put("package", packages);  
//						finalpackage.put("signType", "MD5");
//						String finalsign = reqHandler.createSign(finalpackage);
//						
//						String key=Md5PasswordEncoder.encodePassword(timestamp+service_id+money+did+"godloveyou", "iso");
//						
//						logger.info("Topay值="+"jsp/pay.jsp?appid="+appid2+"&timeStamp="+timestamp+"&nonceStr="+nonceStr2+"&package="+packages+"&sign="+finalsign+"&did="+did+"&money="+money+"&service_id="+service_id+"&orderNo="+orderNo+"&weid="+weid+"&key="+key);
//						
//						response.sendRedirect("jsp/pay.jsp?appid="+appid2+"&timeStamp="+timestamp+"&nonceStr="+nonceStr2+"&package="+packages+"&sign="+finalsign+"&did="+did+"&money="+money+"&service_id="+service_id+"&orderNo="+orderNo+"&weid="+weid+"&key="+key);
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				
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

	public static void main(String[] args) {
		
		String currTime = TenpayUtil.getCurrTime();
		//8位日期
		String strTime = currTime.substring(8, currTime.length());
		//四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		//10位序列号,可以自行调整。
		String strReq = strTime + strRandom;
		
		
		String timestamp = Sha1Util.getTimeStamp();
		String stringA="appid=wx71460f3d267fcd41&mch_id=1317179801&time_stamp="+timestamp+"&product_id=001&nonce_str="+strReq; 
		String stringSignTemp="stringA&key=11111111222222223333333344444444";
	}
	
}
