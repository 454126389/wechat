package com.ruiyi.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.ruiyi.wechat.servlet.ToolsServlet;
import com.ruiyi.wechat.string.DeString;

public class UserSendImageMessageUtil {

	
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
	
	
	
	public static int createMenu(String OPENID,String value,String access_token) throws IOException {
		
		
		String temp[] = value.split(",");
		String lon = null;
		String lat=null;
		if (temp[0]!=null) {
			 lon=temp[0].split("-")[0];
			 lat=temp[0].split("-")[1];
		}
		//谷歌纠偏
		List<String> latlnglist = SignUtil.changegps(lat, lon);
						
		String imaUrl="http://st.map.qq.com/api?size=256*256&center="+latlnglist.get(1)+","+latlnglist.get(0)+"&zoom=16&markers="+latlnglist.get(1)+","+latlnglist.get(0)+",red";
		
		String user_define_menu2="{ \"touser\":\""+OPENID+"\",\"msgtype\":\"news\",\"news\":{\"articles\": [{\"title\":\""+temp[2]+"\",\"description\":\""+temp[1]+"\n"+temp[2]+"\n"+temp[3]+"\",\"picurl\":\""+imaUrl+"\"}]}}";
		
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ access_token;
		
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(user_define_menu2.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);
			
			
			JSONObject  dataJson=new JSONObject(message);
			System.out.println(dataJson.getString("errcode"));
			int code=Integer.parseInt(dataJson.getString("errcode"));
			//if 超24小时没上线
			if(code==45015)
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				//给用户发送信息通知待开发！
				return 1;
			}
			else if(code==0)
			{
				//错误报告
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				return 1;
			}else
			{
				//错误报告
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				
				RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
				return 0;
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	//车机发送图片或视频
	public static int getServiceMsg(String OPENID,String media_id,String access_token,String type) throws IOException {
	
		
		initLog4j();
		
		Logger logger = Logger.getLogger(UserSendImageMessageUtil.class.getName());
		
		String user_define_menu2="{ \"touser\":\""+OPENID+"\",\"msgtype\":\""+type+"\",\""+type+"\":{\"media_id\":\""+media_id+"\"}}";
		
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ access_token;
		
		
		
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(user_define_menu2.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);
			
			
			JSONObject  dataJson=new JSONObject(message);
			System.out.println(dataJson.getString("errcode"));
			int code=Integer.parseInt(dataJson.getString("errcode"));
			//if 超24小时没上线
			if(code==45015)
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				logger.error("45015:"+df.format(new Date())+"OPENID="+OPENID+"---"+message);
				//给用户发送信息通知待开发！
				return 1;
			}
			else if(code==0)
			{
				//错误报告
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				logger.error("suc:"+df.format(new Date())+"OPENID="+OPENID+"---"+message);
				return 1;
			}else
			{
				//错误报告
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				logger.error("error:"+df.format(new Date())+"OPENID="+OPENID+"---"+message);
				RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
				return 0;
			}
			
		
		} catch (Exception e) {
			logger.error("45015:"+"OPENID="+OPENID+"---"+e.toString());
			e.printStackTrace();
			return 0;
		}
	}
	
	//车机发送图片或视频（图文版）
	public static int getServiceMsg2(String OPENID,String RESULT_STR,String access_token,String type) throws IOException {
	
/*		{
		    "touser":"OPENID",
		    "msgtype":"news",
		    "news":{
		        "articles": [
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         },
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         }
		         ]
		    }
		}*/
		String user_define_menu2="{\"touser\":\""+OPENID+"\",\"msgtype\":\""+type+"\", \""+type+"\":{\"articles\": [{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"www.baidu.com\",\"picurl\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png\"}]}}";
//		String user_define_menu2="{\"touser\":\""+OPENID+"\",\"msgtype\":\""+type+"\", \""+type+"\":{\"articles\": [{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"www.baidu.com\",\"picurl\":\"https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}";
		    
		String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
				+ access_token;
		
		
		
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(user_define_menu2.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);
			
			
			JSONObject  dataJson=new JSONObject(message);
			System.out.println(dataJson.getString("errcode"));
			int code=Integer.parseInt(dataJson.getString("errcode"));
			//if 超24小时没上线
			if(code==45015)
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				//给用户发送信息通知待开发！
				return 1;
			}
			else if(code==0)
			{
				//错误报告
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				return 1;
			}else
			{
				//错误报告
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				
				RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
				return 0;
			}
			
		
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
		}
	}

	// 获得ACCESS_TOKEN
	public static String getAccess_token() {

		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ DeString.getAppId() + "&secret=" + DeString.getAppSecret();

		String accessToken = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setConnectTimeout(30 * 1000);

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			System.out.println(message);
			
			JSONObject demoJson = new JSONObject(message);
			accessToken = demoJson.getString("access_token");
			
			

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	//获得jsapi_ticket
	public static String getJsapi_Ticket() {

		
		
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+RedisUtil.get("TOKENCN")+"&type=jsapi";

		String accessToken = null;
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();

			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setConnectTimeout(30 * 1000);

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			System.out.println(message);
			
			JSONObject demoJson = new JSONObject(message);
			accessToken = demoJson.getString("ticket");

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	
	
	//获取微信二维码
	public static String getWeChatTicket(String device_id)
	{
/*		http请求方式: POST
		URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
		POST数据格式：json
		POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
		或者也可以使用以下POST数据创建字符串形式的二维码参数：
		{"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}*/
	
		
		String myUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+RedisUtil.get("TOKENCN");
		
		String action="{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": "+device_id+"}}}";
		
		String ticket = null;
		
		try {
			URL url = new URL(myUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(action.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);
			
			JSONObject  dataJson=new JSONObject(message);
			 ticket=dataJson.getString("ticket");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return ticket;
	}

//个性菜单
	public static int createTypeMenu(String access_token) throws IOException {
		
		
	
//        String user_define_menu2="{\"button\":[{\"type\":\"click\",\"name\":\"我的裝置\",\"key\":\"01_MESSAGE\"},{\"type\":\"click\",\"name\":\"裝置設定\",\"key\":\"02_USERSETTING\"},{\"name\":\"其他服務\",\"sub_button\":[{\"type\":\"click\",\"name\":\"更多服務\",\"key\":\"03_MORE\"},{\"type\":\"click\",\"name\":\"指令模式\",\"key\":\"simple_moden\"}]}],\"matchrule\":{\"group_id\":\"\",  \"sex\":\"\",  \"country\":\"中国\", \"province\":\"台湾\", \"city\":\"\",\"client_platform_type\":\"\" }}";
        String user_define_menu2="{\"button\":[{\"type\":\"click\",\"name\":\"我的设备\",\"key\":\"01_MESSAGE\"},{\"type\":\"click\",\"name\":\"设备设置\",\"key\":\"02_USERSETTING\"},{\"name\":\"其他服务\",\"sub_button\":[{\"type\":\"click\",\"name\":\"更多服务\",\"key\":\"03_MORE\"},{\"type\":\"click\",\"name\":\"指令列表\",\"key\":\"simple_moden\"},{\"type\":\"click\",\"name\":\"教程\",\"key\":\"teach_moden\"}]}]}";
        

        System.out.println(user_define_menu2);
 
	
		String action = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token="
				+ access_token;
		
		try {
			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(user_define_menu2.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);
			
			return 1;
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	

}