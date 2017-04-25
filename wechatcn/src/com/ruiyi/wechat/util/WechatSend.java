package com.ruiyi.wechat.util;

import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.ruiyi.wechat.servlet.ToolsServlet;

public class WechatSend {
	private static void send(String url, String json)
    {
		
		Logger logger = Logger.getLogger(WechatSend.class.getName());
	
		logger.info("doSend2");
		
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        try
        {
//            StringEntity s = new StringEntity(json);
            
            StringEntity s = new StringEntity(json, "UTF-8");
            
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
 
            HttpResponse res = client.execute(post);
            

        	logger.info("doSend3");
            
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
            	
            	logger.info("doSend4");
            	
                HttpEntity entity = res.getEntity();
                System.out.println(EntityUtils.toString(entity, "UTF-8"));
                logger.info("发送结果"+new Date()+"\n"+EntityUtils.toString(entity, "UTF-8"));
            }
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
            throw new RuntimeException(e);
//            java.io.IOException: Attempted read from closed stream.
        }
    }
	
	
	private static void doSend(String strJson)
	{
		Logger logger = Logger.getLogger(WechatSend.class.getName());
		
		logger.info("doSend1");
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?&body=0&access_token=" + RedisUtil.get("TOKENCN");
		System.out.println( RedisUtil.get("TOKENCN"));
		send(url,strJson);
	}
	
	//发送文字消息
	public static void sendText(String opid,String text)
	{
		
				String strJson = "{\"touser\" :\""+opid+"\",";
				strJson += "\"msgtype\":\"text\",";
				strJson += "\"text\":{";
				strJson += "\"content\":\""+text+"\"";
				strJson += "}}";
				
				
				doSend(strJson);
	}
	
	//发送图片消息
	public static void sendImage(String opid,String MEDIA_ID)
	{
				String strJson = "{\"touser\" :\""+opid+"\",";
				strJson += "\"msgtype\":\"image\",";
				strJson += "\"image\":{";
				strJson += "\"media_id\":\""+MEDIA_ID+"\"";
				strJson += "}}";
				
				doSend(strJson);
	}
	
	
	//发送news
	public static void sendNews(String opid,String title,String description,String url,String picurl)
	{
		
				String strJson = "{\"touser\" :\""+opid+"\",";
				strJson += "\"msgtype\":\"news\",";
				strJson += "\"news\":{";
				strJson += "\"articles\": [";
				strJson += " {";
				strJson += "\"title\":\""+title+"\",";
				strJson += "\"description\":\""+description+"\",";
				strJson += "\"url\":\""+url+"\",";
				strJson += "\"picurl\":\""+picurl+"\"";
				strJson += "}";
				strJson += "]";
				strJson += "}}";
				
				doSend(strJson);
	}
	
	
}
