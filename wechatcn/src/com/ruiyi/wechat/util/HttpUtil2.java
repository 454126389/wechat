package com.ruiyi.wechat.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author yoUng
 * @description 发送http请求
 * @filename HttpUtil.java
 * @time 2011-6-15 下午05:26:36
 * @version 1.0
 */
public class HttpUtil2 {

	public static String http(String url, Map<String, String> params) {
		long t1=System.currentTimeMillis(); //排序前取得当前时间 
		String result="";
		while(result.equals(""))
		{
			result =getResult(url,params);
			
		}
		long t2=System.currentTimeMillis(); //排序前取得当前时间 
		System.out.println("url="+url);
		System.out.println("t1="+t1);
		System.out.println("t2="+t2);
		System.out.println("t="+(t2-t1));
		
		Calendar c=Calendar.getInstance(); 
		c.setTimeInMillis(t2-t1); 
		
		System.out.println("耗时: " + c.get(Calendar.MINUTE) + "分 " + c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND) + " 微秒"); 
		
		return result;
	}

	public static String getResult(String url, Map<String, String> params)  {
		URL u = null;
		StringBuffer buffer=null;
		HttpURLConnection con = null;
		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}
		System.out.println("send_url:" + url);
		System.out.println("send_data:" + sb.toString());
		// 尝试发送请求
		try {
			
			u = new URL(url);
			con = (HttpURLConnection) u.openConnection();

			con.setConnectTimeout(10000);
			con.setReadTimeout(5000);

			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
/*			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");*/

			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "5000");

//			OutputStreamWriter osw = new OutputStreamWriter(
//					con.getOutputStream(), "UTF-8");
			// if()
//			System.out.println("----" + osw.getEncoding());
//
//			osw.write(sb.toString());
//			osw.flush();
//			osw.close();
			
			
			// 读取返回内容
			buffer = new StringBuffer();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						con.getInputStream(), "UTF-8"));
				String temp;
				while ((temp = br.readLine()) != null) {
					buffer.append(temp);
					buffer.append("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				con.getInputStream().close();
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}



		return buffer.toString();
	}
}
