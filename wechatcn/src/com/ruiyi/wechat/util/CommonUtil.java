package com.ruiyi.wechat.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.utils.StringUtils;

import com.google.protobuf.TextFormat.ParseException;



public class CommonUtil {
	//private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

	/**
	 * 发送https请求
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return 返回微信服务器响应的信息
	 */
	public static String httpsRequest(String requestUrl, String requestMethod,
			String outputStr) {
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("content-type",
					"application/x-www-form-urlencoded");
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
		} catch (ConnectException ce) {
			//log.error("连接超时：{}", ce);
		} catch (Exception e) {
			//log.error("https请求异常：{}", e);
		}
		return null;
	}



	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	//GET请求
	public static String urlGet(String url) {

		
		/* 建立HTTP Get对象 */
		HttpGet httpRequest = new HttpGet(url);
		httpRequest.addHeader( "User-Agent", "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");   
		try {
			/* 发送请求并等待响应 */
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读 */
//				String strResult = EntityUtils.toString(httpResponse
//						.getEntity());
				String charset = "UTF-8"; 
				 HttpEntity entity = httpResponse.getEntity();   
		         charset = getContentCharSet(entity);  
                 // 使用EntityUtils的toString方法，传递编码，默认编码是ISO-8859-1   
		         String result = EntityUtils.toString(entity, charset);   
				
				
				/* 去没有用的字符 */
				// strResult = eregi_replace("(\r\n|\r|\n|\n\r)", "",
				// strResult);
				// mTextView1.setText(strResult);

//				JSONObject jsonParser = new JSONObject(strResult);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static String getContentCharSet(final HttpEntity entity)   
            throws ParseException {   
       
            if (entity == null) {   
                throw new IllegalArgumentException("HTTP entity may not be null");   
            }   
            String charset = null;   
            if (entity.getContentType() != null) {    
                HeaderElement values[] = entity.getContentType().getElements();   
                if (values.length > 0) {   
                    NameValuePair param = values[0].getParameterByName("charset" );   
                    if (param != null) {   
                        charset = param.getValue();   
                    }   
                }   
            }   
             
            if(StringUtils.isEmpty(charset)){  
                charset = "UTF-8";  
            }  
            return charset;   
        }  
    
    
    
	public static PushPayload buildPushObject_messageWithExtras(
			String registartion_id, Message msg) {

		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.registrationId(registartion_id))
				.setMessage(msg).build();
	}
    
	public static String sendMsg(String registartion_id, Message msg) {
		
		
		JPushClient jpushClient = new JPushClient("87ef4d1c8a14a7b36a62917c",
				"7f9e6b577ae991b2d706dd98", 3);

		PushPayload payload = buildPushObject_messageWithExtras(
				registartion_id, msg);
		
		
		
		String restr = "";
		try {
			
//			System.out.println(jpushClient.getUserOnlineStatus(registartion_id));
			
			PushResult result = jpushClient.sendPush(payload);
			
			System.out.println("Got result - " + result);
			restr = "" + result;

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			System.out.println("Connection error, should retry later,e=" + e);
			restr = "" + e;

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			System.out.println("Should review the error, and fix the request"
					+ e);
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());

			restr = "" + e;
		}
		return restr;
	}
    
	
	public static String AppsendMsg(String registartion_id, Message msg) {
		JPushClient jpushClient = new JPushClient("34f3ce21bc0d2db3cda5ccf6",
				"6e75c5aa961b0cd97043c617", 3);

		PushPayload payload = buildPushObject_messageWithExtras(
				registartion_id, msg);
		
		
		
		String restr = "";
		try {
			
//			System.out.println(jpushClient.getUserOnlineStatus(registartion_id));
			
			PushResult result = jpushClient.sendPush(payload);
			
			System.out.println("Got result - " + result);
			restr = "" + result;

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			System.out.println("Connection error, should retry later,e=" + e);
			restr = "" + e;

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			System.out.println("Should review the error, and fix the request"
					+ e);
			System.out.println("HTTP Status: " + e.getStatus());
			System.out.println("Error Code: " + e.getErrorCode());
			System.out.println("Error Message: " + e.getErrorMessage());

			restr = "" + e;
		}
		return restr;
	}
	
}