package com.ruiyi.wechat.util;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MainClient {

	private static String HOST = "192.168.2.113";//192.168.2.117
	private static int PORT = 8124;//8124

	/**
	 * @param message
	 *            发送信息
	 * @param tag
	 *            标识
	 * @param no
	 *            终端编号
	 * @param code
	 *            语言编码
	 */
	private static void sendMessage(byte[] message, long no, int tag) {
		// 创建一个非阻塞的客户端程序
		IoConnector connector = new NioSocketConnector();
		// 添加业务逻辑处理器类
		connector.setHandler(new MainClientHandler());
		// 设置链接超时时间
		connector.setConnectTimeoutMillis(3000l);
		// 设置日志过滤器
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		IoSession session = null;
		try {
			ConnectFuture future = connector.connect(new InetSocketAddress(
					HOST, PORT));// 创建连接
			future.awaitUninterruptibly();// 等待连接创建完成
			session = future.getSession();// 获得session
			byte[] mess = encoderMess(message, tag, no);
			session.write(IoBuffer.wrap(mess));// 发送消息
		} catch (Exception e) {
		}
		if (session != null) {
			session.getCloseFuture().awaitUninterruptibly();// 等待连接断开
		}
		connector.dispose();
	}

	private static byte[] encoderMess(byte[] message, int tag, long no) {
		byte[] mess = null;
		try {
			int len =17;
			if(message!=null)len+=message.length;
			byte a = (byte) ((len >> 8 & 0xff) | 0x80);
			byte b = (byte) (len & 0xff);
			mess = new byte[len];
			mess[0] = 0x23;
			mess[1] = 0x52;
			mess[2] = 0x59;
			mess[3] = (byte) (tag >> 8 & 0xff);
			mess[4] = (byte) (tag & 0xff);
			mess[5] = a;
			mess[6] = b;
			mess[7] = (byte) (no >> 56 & 0xff);
			mess[8] = (byte) (no >> 48 & 0xff);
	
		mess[9] = (byte) (no >> 40 & 0xff);
			mess[10] = (byte) (no >> 32 & 0xff);
			mess[11] = (byte) (no >> 24 & 0xff);
			mess[12] = (byte) (no >> 16 & 0xff);
			mess[13] = (byte) (no >> 8 & 0xff);
			mess[14] = (byte) (no & 0xff);
			if(message!=null)System.arraycopy(message, 0, mess, 15, len - 17);
			mess[len - 2] = 0x24;
			mess[len - 1] = 0x24;
		} catch (Exception e) {
		}
		return mess;
	}

	// WEB终端广播
	public static void sendNewsMessage(long no, byte[] message) {
		sendMessage(message, no, 0x3001);
	}
	
	
	// 请求拍照
	public static void askImageMessage(long no) {
		sendMessage(null,no, 0x3023);
	}
	

	// WEB修改终端参数
	public static void sendTerminalParameterUpdate(long no, byte[] message) {
		sendMessage(message, no, 0x3002);
	}
	
	//WIFI参数请求
	public static void getWIFI(long no,byte[] message){
		sendMessage(message, no, 0x3003);
	}
	//WIFI参数设置
	public static void sendWIFI(long no,byte[] message){
		sendMessage(message, no, 0x3004);
	}
	
}
