package com.ruiyi.wechat.util;

import org.apache.log4j.Logger;

import com.ruiyi.wechat.model.AccessJsapiTicket;
import com.ruiyi.wechat.model.AccessToken;

public class TokenThread implements Runnable {
	private static Logger log = Logger.getLogger(TokenThread.class.getName());

	// 第三方用户唯一凭证
	public static String appid = "";
	// 第三方用户唯一凭证密钥
	public static String appsecret = "";
	public static AccessToken accessToken = null;
	public static AccessJsapiTicket accessJsapiTicket = null;

	public void run() {
		while (true) {
			try {
				accessToken = WeixinUtil.getAccessToken(appid, appsecret);
				if (null != accessToken) {
					log.info("获取access_token成功，有效时长"
							+ accessToken.getExpiresIn() + "秒 token:"
							+ accessToken.getToken());

					try {
						accessJsapiTicket = WeixinUtil
								.getAccessJsapiTicket(accessToken.getToken());
						if (null != accessToken) {
							log.info("获取accessJsapiTicket成功，有效时长"
									+ accessJsapiTicket.getExpires_in()
									+ "秒 Ticket:"
									+ accessJsapiTicket.getTicket());
							
							RedisUtil.set("TOKENCN",  accessToken.getToken());
							RedisUtil.set("JSAPI",  accessJsapiTicket.getTicket());
							
						} else {
							Thread.sleep(60 * 1000);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					

					// 休眠7000秒
					Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);
				} else {
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					log.error("{}", e1);
				}
				log.error("{}", e);
			}
		}
	}
}