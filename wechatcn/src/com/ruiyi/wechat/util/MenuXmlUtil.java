package com.ruiyi.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import com.ruiyi.wechat.string.DeString;

public class MenuXmlUtil {


	// 创建菜单
	public static int createMenu() throws IOException {

		String user_define_menu = "{ \"button\": [{ \"type\": \"click\",\"name\": \"我的设备\", \"key\": \"01_MESSAGE\" }, { \"name\": \"设备设置\", \"sub_button\": [ { \"type\": \"click\", \"name\": \"用户注册\", \"key\": \"01_CLOUD\" }, { \"type\": \"click\", \"name\": \"终端设置\", \"key\": \"02_TERMINAL\" }, { \"type\": \"click\", \"name\": \"车辆绑定\", \"key\": \"02_GOLOCK\"}] }, { \"name\": \"更多服务\", \"sub_button\": [ { \"type\": \"view\", \"name\": \"网上商城\", \"url\": \"http://conqueror.tmall.com/shop/view_shop.htm?user_number_id=1018600512&ali_trackid=2:mm_35592599_0_0:1395105877_3k3_1897779196\" },{ \"type\": \"view\", \"name\": \"违章查询\", \"url\": \"http://light.weiche.me/\" },{ \"type\": \"click\", \"name\": \"使用帮助\", \"key\": \"01_HELP\" }, { \"type\": \"view\", \"name\": \"关于我们\", \"url\": \"http://m.baidu.com/from=2001a/bd_page_type=1/ssid=0/uid=0/pu=usm%400%2Csz%401320_1003%2Cta%40iphone_2_4.2_1_9.4/baiduid=91A1C42EFA42D7523B16B19785EC9289/w=0_10_%E5%BE%81%E6%9C%8D%E8%80%85%E5%AE%98%E7%BD%91/t=iphone/l=3/tc?ref=www_iphone&lid=11870546325552080334&order=4&vit=osres&tj=www_normal_4_0_10&m=8&srd=1&cltj=cloud_title&dict=21&sec=37129&di=a17dfbc7fd86b8d6&bdenc=1&nsrc=IlPT2AEptyoA_yixCFOxXnANedT62v3IIQKC_ytL0CebmFusaLrgHhEsRCTqMjrIBK\" } ] } ] }";

		// 此处改为自己想要的结构体，替换即可
		String access_token = getAccess_token();

		String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
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
			os.write(user_define_menu.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println(message);
			return 1;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
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
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

			http.connect();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");

			JSONObject demoJson = new JSONObject(message);
			accessToken = demoJson.getString("access_token");

			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
	
	

}