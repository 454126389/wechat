package com.ruiyi.wechat.util;

import org.apache.log4j.Logger;

import com.ruiyi.wechat.model.AccessJsapiTicket;
import com.ruiyi.wechat.model.AccessToken;

public class RedisThread implements Runnable {
	private static Logger log = Logger.getLogger(RedisThread.class.getName());

	public void run() {
		while (true) {
			try {

				log.info("定时重启");
				MyListener listener = new MyListener();
				RedisUtil.pool.getResource().subscribe(listener,
						new String[] { "fenceAlarm", "image", "sim.expire" });
				// 休眠7000秒
				Thread.sleep(7000);

			} catch (Exception e) {
				// TODO: handle exception
				log.info("定时重启error");
			}
		}

	}
}