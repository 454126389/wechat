package com.ruiyi.wechat.util;

import java.util.List;
import java.util.Map;

import com.ruiyi.wechat.model.News;

public class ReMsg {
	// 文字消息
	public static String reText(Map<String, String> map, String msg) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		sb.append("<xml><ToUserName><![CDATA[")
				.append(map.get("xml.FromUserName"))
				.append("]]></ToUserName><FromUserName><![CDATA[")
				.append(map.get("xml.ToUserName"))
				.append("]]></FromUserName><CreateTime>")
				.append(map.get("xml.CreateTime"))
				.append("</CreateTime><MsgType><![CDATA[text]]></MsgType>")
				.append("<Content><![CDATA[")
				// .append("对不起，没有查到您想要的信息！" + map.get("xml.Content"))
				.append(msg).append("]]></Content>")
				.append("<FuncFlag>0</FuncFlag></xml>");
		return sb.toString();

	}
	
	
	// 客服消息
	public static String reKf(Map<String, String> map) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		sb.append("<xml><ToUserName><![CDATA[")
				.append(map.get("xml.FromUserName"))
				.append("]]></ToUserName><FromUserName><![CDATA[")
				.append(map.get("xml.ToUserName"))
				.append("]]></FromUserName><CreateTime>")
				.append(map.get("xml.CreateTime"))
				.append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType>")
				.append("</xml>");
		return sb.toString();

	}
	

	// 链接消息
	public static String reLink(Map<String, String> map, String url) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		sb.append("<xml><ToUserName><![CDATA[")
				.append(map.get("xml.FromUserName"))
				.append("]]></ToUserName><FromUserName><![CDATA[")
				.append(map.get("xml.ToUserName"))
				.append("]]></FromUserName><CreateTime>")
				.append(map.get("xml.CreateTime"))
				.append("</CreateTime><MsgType><![CDATA[link]]></MsgType>")
				.append("<Title><![CDATA[厦门瑞忆科技有限公司]]></Title>")
				.append("<Description><![CDATA[厦门瑞忆科技有限公司]]></Description>")
				.append("<Url><![CDATA[").append(url).append("]]></Url>")
				.append("<FuncFlag>0</FuncFlag></xml>");
		return sb.toString();

	}

	// 图文消息
	public static String reNews(Map<String, String> map, List<News> newsList) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();

		sb.append("<xml><ToUserName><![CDATA[")
				.append(map.get("xml.FromUserName"))
				.append("]]></ToUserName><FromUserName><![CDATA[")
				.append(map.get("xml.ToUserName"))
				.append("]]></FromUserName><CreateTime>")
				.append(map.get("xml.CreateTime"))
				.append("</CreateTime><MsgType><![CDATA[news]]></MsgType>")
				.append("<ArticleCount>" + newsList.size() + "</ArticleCount>")

				.append("<Articles>");

		for (int i = 0; i < newsList.size(); i++) {

			sb.append("<item>")
					.append("<Title><![CDATA[" + newsList.get(i).getTitle()
							+ "]]></Title>")
					.append("<Description><![CDATA["
							+ newsList.get(i).getDescription()
							+ "]]></Description>")
					.append("<PicUrl><![CDATA[" + newsList.get(i).getPicUrl()
							+ "]]></PicUrl>")
					.append("<Url><![CDATA[" + newsList.get(i).getUrl()
							+ "]]></Url>").append("</item>");
		}

		sb.append("</Articles></xml>");

		return sb.toString();

	}

}
