package com.ruiyi.wechat.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ruiyi.wechat.util.MyListener;
import com.ruiyi.wechat.util.RedisUtil;

public class startListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	 	MyListener listener = new MyListener();
        RedisUtil.pool.getResource().subscribe(listener, new String[] {"fenceAlarm"});  
	}

}
