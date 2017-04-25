package com.ruiyi.wechat.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class t1 {
	
	public static String access_token = "";  
	
	
	public static byte getXor(byte[] datas){  
		  
	    byte temp=datas[0];  
	          
	    for (int i = 1; i <datas.length; i++) {  
	        temp ^=datas[i];  
	    }  
	  
	    return temp;  
	}  
	
	
//	void DataEnDecrypt(int rnd,int datalen,int datainout)
//	{
//	    for( int i = 0;i < datalen;i++)
//	    {
//	        datainout[i] = datainout[i] ^ TableCrypto[(rnd + i) & 0xff];
//	    }
//	}
	
    public static String string2Json(String s) {        
        StringBuffer sb = new StringBuffer();        
        for (int i=0; i<s.length(); i++) {  
            char c = s.charAt(i);    
             switch (c){  
             case '\"':        
                 sb.append("\\\"");        
                 break;        
             case '\\':        
                 sb.append("\\\\");        
                 break;        
             case '/':        
                 sb.append("\\/");        
                 break;        
             case '\b':        
                 sb.append("\\b");        
                 break;        
             case '\f':        
                 sb.append("\\f");        
                 break;        
             case '\n':        
                 sb.append("\\n");        
                 break;        
             case '\r':        
                 sb.append("\\r");        
                 break;        
             case '\t':        
                 sb.append("\\t");        
                 break;        
             default:        
                 sb.append(c);     
             }  
         }      
        return sb.toString();     
        } 
	
	
	public static void main(String[] args) {
		
		
		
		RedisUtil.publish("gps.instruct", "352544072205181:MOTION,1,2#");
		  
		/*String id="352544072210546";
		String id2=id.substring(0,3);
		System.out.println(id2);
		if(id2.equals("352"))
			System.out.println("1");
		else
			System.out.println("2");
		*/
		
//		String result_str="/sdcard/1.png";
//		String change=result_str.replace("/", "%");
//		System.out.println(change);
		
		
//		RedisUtil.hdel("xgtoken","1560106041901043");
//		System.out.println("xgtoken="+RedisUtil.hget("xgtoken","1560106041901043"));
		
	/*	
		try {
			
			
			
			JSONObject jsonParser = new JSONObject("{\"ticket\":\"gQF78TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyeTNxb2dMUF9hNlUxMDAwMGcwN0YAAgTg/JtYAwQAAAAA\"}");
			
			System.out.println(jsonParser.getString("ticket"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
//		System.out.println("xgtoken="+RedisUtil.hget("xgtoken","6240106050001065"));
		
//		RedisUtil.set("TOKENCN",  "test");
		
//		System.out.println(RedisUtil.get("TOKENCN"));
//		WechatSend.sendNews("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "Conqueror", "test","http://webservice.conqueror.cn:8181/pics/21d78703-8410-406e-851b-d47c8735dae9.jpg","http://webservice.conqueror.cn:8181/pics/21d78703-8410-406e-851b-d47c8735dae9.jpg");
		
//		MyListener listener = new MyListener();
//		RedisUtil.pool.getResource().subscribe(listener,
//				new String[] { "fenceAlarm", "image","sim.expire" });
		
		
//		String a2="A0840400000000010000000100000014000000010000000100000000000000140000000200000001000000010000001400000003000000010000000000000014000000";
//		
//		 DataEnDecrypt(rnd, srcLenth, src); 
//		
//			int CHK;
//		  for(int i = 0; i < srcLenth; i++)               /*DATA*/
//		    {
//		        CHK ^= src[i];
//		        dst[prt++] = src[i];
//		    }
		
//		byte b=getXor(a2.getBytes());
//		System.out.println(b);
		
		
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		String pswmd5 = md5.encodePassword("911201", "1330106304911201");
//		System.out.println("pswmd5="+pswmd5);
		
//		pswmd5=3b4293e82cc6b76b77c868ca6e371597

/*		Boolean ticket;
		
		Parameter parameter=new Parameter("1234567890123456", "101", "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");
		
		ConnectionPoolDao.get_ticket("12345678901234561");*/
//		System.out.println(get_ticket);
		
//		ticket=ConnectionPoolDao.addParameterNew(parameter);
//		System.out.println(""+ticket);
		
		// 添加设备
/*		if (!ConnectionPoolDao.addTerminal("1234567890123456"))
			System.out.println("添加t设备失败");
		else
			System.out.println("suc");*/

		
/*		if(ConnectionPoolDao.get_ticket("1")==null)
			System.out.println("null");
		else
			System.out.println(ConnectionPoolDao.get_ticket("1"));*/
		
//		StringBuffer s =getHistoru("2014/12/9 1:29:0","2015/04/09 15:29","1010103050201004");
		
//		long st=1429073195000L+300000L;
//		System.out.println(st);
		
//		
//		String sd="1429073495000";  
//		 Date dat=new Date(Long.parseLong(sd));  
//         GregorianCalendar gc = new GregorianCalendar();   
//         gc.setTime(dat);  
//         java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");  
//         String sb=format.format(gc.getTime());  
//         System.out.println(sb);  
//		
		
		
		// String s="闽";
		// System.out.println(URLEncoder.encode(s));
		// System.out.println(URLEncoder.encode("闽"));

		 //取报警信息
//		 String[] nos={"1000103050271020"};
//		 StringBuilder mess = new StringBuilder();
//		 String value=null;
//		 for (int i = 0; i < nos.length; i++) {
//		 long no = Long.parseLong(nos[i]);
//		 String key = "CSMS" + no;// 终端参数KEY
//		  String sms = RedisUtil.get(key);
		//		
//		 Jedis jedis = new Jedis("192.168.2.105");
		// String keys = "CSMS" + "1000103050271020";
		// // 取数据
		// value = jedis.get(keys);
		//
		// System.out.println(value);
		// }
		//	
		// try {
		// UserSendMessageUtil.createMenu("oISxbt9lspismVm5cfp19zp1fsM8",value);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// Timer timer = new Timer();
		// timer.schedule(new
		// DateTask("oISxbt9lspismVm5cfp19zp1fsM8"),1000,1000*5);

		// List<CarInfo> list =
		// ConnectionPoolDao.getLockCarList("oISxbt9lspismVm5cfp19zp1fsM8");
		// for (int i = 0; i < list.size(); i++) {
		// System.out.println(list.get(i).getId());
		//		
		// }
//
//		 RedisUtil.set("CSMS1010104410131164", "1;2;3;4;5;***");
		
		
//		System.out.println("i" + urlConnectionPost("http://webkitui.youyuan.com/(1DFC5207D01B4B4F657F36DD56BDDFED167650763)/write_msg_b.jwml?uid=165452223&re=4325934&getcard=1&content=aaaaaaaa&m=1xVFWJQ",null));
//		System.out.println("i" + urlConnectionPost("http://webkitui.youyuan.com/(971D39444610797FF483FF5E252CF7f2167908511)/write_msg_b.jwml?uid=152710496&re=4582484&getcard=1&content=又是假号吗。。。给个机会啊啊&m=1oHtlfQ",null));
//		System.out.println("i" + urlConnectionPost("http://www.52pojie.cn/member.php?mod=LCG_register&PJ52username=123456&PJ52password=123456",null));
		
//		 
//		 System.out.println("a");
//		 RedisUtil.set("CSMS1010104410131192", "http://img30.360buyimg.com/popWaterMark/g6/M05/04/04/rBEGC1C8OlwIAAAAAAHoZWxTcIAAAAznQNw3jwAAeh9026.jpg");
//		 RedisUtil.set("CSMS1010104410141163", "333333333");
//		 RedisUtil.set("CSMS1010104410161161", "4444444444");
//		1	  RedisUtil.set("CSMS1000104010991206", "111111111211");
//		
//		 RedisUtil.set("CSMS1010104410161161", "118.150909-24.65209,2014-04-15 11:05:57,123456报警,福建省厦门市同安区海翔大道");
//		 System.out.println("a");
//			 System.out.println(RedisUtil.get("CSMS1010104410161161"));
			 
		
//		模拟调试 DateTask的 	 UserSendImageMessageUtil.createMenu(openid, temp[j],t1.access_token);替换
	
		

		
//		String url = DeString.url+"/coreServlet?action=putweid&weid=oISxbt9lspismVm5cfp19zp1fsM8";
//		urlConnectionPost(url,null);
		 

		 
//		
		
//		 Timer timer2 = new Timer();
//		 timer2.schedule(new MyTask(), 0);
//		 Timer timer = new Timer();
//		 timer.schedule(new
//		 DateTask("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"),1000,1000*10);

		// Map<String,Double> offsetMap=ConnectionPoolDao.getOffset(73.6,39.3);
		
//		 Timer timer = new Timer();
//		 timer.schedule(new DateTask("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ"),0,1000*10);
		
		
		
//		 RedisUtil.set("CSMS1010104410131164", "118.150909-24.65209,2014-04-15 11:05:57,1000104010981207报警,福建省厦门市同安区海翔大道");
//		 System.out.println("a");
		 
		 
		 
		 
//		System.out.println(RedisUtil.get("CSMS1010104410131164"));

		
		
		
		
//		try {
//			URL url = new URL(action);
//			HttpURLConnection http = (HttpURLConnection) url.openConnection();
//
//			http.setRequestMethod("POST");
//			http.setRequestProperty("Content-Type",
//					"application/x-www-form-urlencoded");
//			http.setDoOutput(true);
//			http.setDoInput(true);
//			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
//			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
//
//			http.connect();
//			OutputStream os = http.getOutputStream();
//			os.write(user_define_menu2.getBytes("UTF-8"));// 传入参数
//			os.flush();
//			os.close();
//
//			InputStream is = http.getInputStream();
//			int size = is.available();
//			byte[] jsonBytes = new byte[size];
//			is.read(jsonBytes);
//			String message = new String(jsonBytes, "UTF-8");
//			System.out.println(message);
//			
//			
//			JSONObject  dataJson=new JSONObject(message);
//			System.out.println(dataJson.getString("errcode"));
//			int code=Integer.parseInt(dataJson.getString("errcode"));
//			//if 超24小时没上线
//			if(code==45015)
//			{
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//				logger.error("45015:"+df.format(new Date())+"OPENID="+OPENID+"---"+message);
//				//给用户发送信息通知待开发！
//				return 1;
//			}
//			else if(code==0)
//			{
//				//错误报告
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//				logger.error("suc:"+df.format(new Date())+"OPENID="+OPENID+"---"+message);
//				return 1;
//			}else
//			{
//				//错误报告
//				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//				
//				logger.error("error:"+df.format(new Date())+"OPENID="+OPENID+"---"+message);
//				RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
//				return 0;
//			}
//			
//		
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//			return 0;
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		String url = DeString.url+"/coreServlet?action=putweid&weid=oISxbt9lspismVm5cfp19zp1fsM8";
//		urlConnectionPost(url,null);
		
//		 开启所有停车监控
		
		//激活
//		String starturl = "http://wechat.conqueror.cn/coreServlet?action=start";
//		System.out.println("i" + urlConnectionPost(starturl,null));
//		
//		
//		List<String> list = ConnectionPoolDao.getWechatUserList();
//		for (int i = 0; i < list.size(); i++) {
//				String url = "http://wechat.conqueror.cn/coreServlet?action=putweid&weid="+ list.get(i);
//						
//			System.out.println(i+"i" + urlConnectionPost(url,null));
//		}
		
		
		
		
		
		
//		String starturl = "http://wechat.conqueror.cn/coreServlet?action=start";
//		System.out.println("i" + urlConnectionPost(starturl,null));
//		String url = "http://wechat.conqueror.cn/coreServlet?action=putweid&weid=oISxbt9lspismVm5cfp19zp1fsM8";
//		System.out.println(""+ urlConnectionPost(url,null));
		
		
//		String gpskeys;
//		String sgps; 
//		List<CarInfo> carlist = ConnectionPoolDao.getLockCarList("oISxbt6XjQrYtfSEWIJ8tjWY8uyI");
//		gpskeys = "GPS" + carlist.get(0).getId();
//		sgps = DBHelper.get(gpskeys);
//		System.out.println(carlist.get(0).getAlias()+sgps);

//		String url = "http://wechat.conqueror.cn/coreServlet?action=putweid&weid=oISxbt9lspismVm5cfp19zp1fsM8";
//		System.out.println("i" + urlConnectionPost(url,null));
		
//		 RedisUtil.set("CSMS1140104021591096", "113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033震动报警,广东省佛山市禅城区x507");
//		 RedisUtil.get("CSMS1140104021591096");
//		 RedisUtil.set("CSMS1000104010981207", "113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033震动报警,广东省佛山市禅城区x507;113.1344-22.9866,2014-05-14 08:42:55,粤EXQ033震动报警,广东省佛山市禅城区x507;113.1344-22.9866,2014-05-14 13:51:09,粤EXQ033震动报警,广东省佛山市禅城区x507;113.1344-22.9866,2014-05-14 14:15:57,粤EXQ033震动报警,广东省佛山市禅城区x507;113.1344-22.9866,2014-05-14 14:19:27,粤EXQ033震动报警,广东省佛山市禅城区x507;113.1344-22.9866,2014-05-14 14:22:58,粤EXQ033震动报警,广东省佛山市禅城区x507");
//		 System.out.println("a");
		
		
//		 Timer timer2 = new Timer();
//		 timer2.schedule(new MyTask(), 0);
//	 RedisUtil.set("CSMS1000104010981207", "118.150909-24.65209,2014-04-15 11:05:57,123456报警,福建省厦门市同安区海翔大道");
//	
//	 Timer timer = new Timer();
//	 timer.schedule(new
//	 DateTask("oISxbt9lspismVm5cfp19zp1fsM8"),1000,1000*5);
		 
//		String string = "中国\u6211\u7231\u5317\u4EAC";
//        byte[] utf8;
//		try {
//			utf8 = string.getBytes("UTF-8");
//		     string = new String(utf8, "UTF-8");
//		        System.out.println(string);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ConnectionPoolDao.getLockCarList("oISxbt9lspismVm5cfp19zp1fsM8");
		
		
		
//		try {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//	
//		 Date d= sdf.parse("1982-12-12T03:45");
//		String formattedTime = output.format(d);
//		System.out.println(formattedTime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		String start_time = "2014-04-15T00:00";
//		String end_time = "2014-05-15T23:59";
//		String didhis="1000104010951201"; 
//		
//		StringBuffer s = DBHelper.getHistoru(start_time, end_time,didhis);
//		System.out.println("s="+s);
//		
		
//		String stime="1982-12-12";
//		String etime="2014-05-16";
//		String didhis="1000104010951201";
//		
//		List<Mileage> resmsg=ConnectionPoolDao.getMileage(didhis, stime, etime);
//		System.out.println(resmsg.size());
//		System.out.println(resmsg.get(0).getMiles());
//		System.out.println(resmsg.get(0).getTime());
		
		
		
		 
//		 
//		System.out.println(DBHelper.get("GPS" + "1050104316731077"));
		
		
//		rzwdlw
		
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		String pswmd5 = md5.encodePassword("123456", "rzwdlw");
//		System.out.println("pswmd5="+pswmd5);
		
		
		//获得设备IMIS
//		String imsi=ConnectionPoolDao.getIMSI("1010104410131192");
//		
//		
//		//获得sim时间
//		String simtime=ConnectionPoolDao.getSimTime(imsi);
//		System.out.println("simtime="+simtime);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//		if(null==simtime)
//		{
//			System.out.println("系统时间="+df.format(new Date()));// new Date()为获取当前系统时间
//			simtime=df.format(new Date());
//		}
		
		
		
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd"); //制定日期格式
//		Calendar c=Calendar.getInstance();
//		Date date=new Date();
		
//		Date daystart = null;
//		try {
//			daystart = df.parse(simtime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		c.setTime(daystart);
//		c.add(Calendar.MONTH,1); //将当前日期加一个月
//		String validityDate=df.format(c.getTime());  //返回String型的时间
//		System.out.println("edate="+validityDate);
//		
//		
//		System.out.println(ConnectionPoolDao.addServiceParameter("1010104410131192", simtime, validityDate));
		
		
		//放入缓存
//		String key = "PAR" + "1010104410131192";// 终端参数KEY
//		String sp = RedisUtil.get(key);
//		if (sp != null) {
//			GprsParameter p = GprsParameter.stringToBean(sp);
//			p.setMoveInterval(Short.parseShort("30"));
//			p.setStopInterval(Short.parseShort("1800"));
//			p.setTr_r(Short.parseShort("30"));
//			p.setFortiry((short) (Short.parseShort("16")));
//			p.setWeatherTag(true);
//			p.setNewsOpen(true);
//			p.setEmailTag(true);
//			
//			sp = GprsParameter.beanToString(p);
//			RedisUtil.set(key, sp);
//			System.out.println(RedisUtil.get(key));
//		}
		
//		
//		//获得设备IMIS
//		String imsi=ConnectionPoolDao.getIMSI("1140104021591096");
//		//获得sim时间
//		String simtime=ConnectionPoolDao.getSimTime(imsi);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//		System.out.println("simtime="+simtime);
//		//获得注册时间
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		
//		Calendar c=Calendar.getInstance();
//		String starttime=df.format( c.getTime());  //返回String型的时间
//		
//		System.out.println("starttime="+starttime);
//		
//		c.add(Calendar.MONTH,1); //将当前日期加一个月
//		
//		String endtime1=df.format(c.getTime());  //返回String型的时间
//		
//		System.out.println("endtime1="+endtime1);
//		
//		
//		c.add(Calendar.MONTH,-1); //将当前日期减一个月
//		c.add(Calendar.YEAR,1); //将当前日期加一个月
//		
//		String endtime2=df.format(c.getTime());  //返回String型的时间
//        Date dt1 = null;
//        Date dt2 = null;
//		try {
//			dt1 = df.parse(endtime2);
//		      dt2 = df.parse(simtime);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//   
//		//如果simt不为空且小于当前时间-1年则取小的值
//        if(null!=simtime)
//        {
//        	if(dt2.getTime()<dt1.getTime())
//        		endtime2=simtime;
//        }
//		
//        System.out.println("endtime2="+endtime2);
//		
		
//		String number="13163905721";
//		ArrayList<String> list= ConnectionPoolDao.getUserParamterById("166");
//		for (int i = 0; i < list.size(); i++) {
//			System.out.println("list="+list.get(i));
//			
//			
//			String key = "PAR" + list.get(i);// 终端参数KEY
//			String sp = RedisUtil.get(key);
//			if (sp != null) {
//				GprsParameter gp = GprsParameter.stringToBean(sp);
//				gp.setZh_number(number);
//				sp = GprsParameter.beanToString(gp);
//				RedisUtil.set(key, sp);
//			}
//			
//		}
		
		
//		String key = "PAR" + "1010104410131164";// 终端参数KEY
//		String sp = RedisUtil.get(key);
//		System.out.println("sp="+sp);
		

//		 ConnectionPoolDao.getUserParmaterNum("166");
//		 
//		 System.out.println("num-"+ ConnectionPoolDao.getUserParmaterNum("166"));
		
		
//		System.out.println(ConnectionPoolDao.getLockCarList("oISxbt9lspismVm5cfp19zp1fsM8"));
		
		 
		 
		 
//		 System.out.println(DBHelper.get("GPS1030104012831178"));
		
		   //发送 GET 请求
//        String s=HttpRequest.sendGet("http://www.tuling123.com/openapi/api","key=42d0d4ee8c5495abbd09e71e354a1c91&info=你好");
//        System.out.println(s);
        
        
        //发送 POST 请求
//        String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
//        System.out.println(sr);
		
		
//		 String url = "http://wechat.conqueror.cn/coreServlet?action=putweid";
//		 System.out.println("i" + urlConnectionPost(url,null));
		
//		
//		 RedisUtil.set("CSMS1010103050231007", "118.150909-24.65209,2014-04-15 11:05:57,开发测试2,福建省厦门市同安区海翔大道");
		 
//		 System.out.println(RedisUtil.get("TOKENCN"));
		
//		Date date = (new SimpleDateFormat("yyyy-MM-dd")).parse(startdate);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		System.out.println((new SimpleDateFormat("yyyy-MM-dd hh:mm")).format(cal.getTime()));
//		cal.add(Calendar.HOUR_OF_DAY, 48);
//		System.out.println((new SimpleDateFormat("yyyy-MM-dd hh:mm")).format(cal.getTime()));
		
		
//		SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd hh:mm");
//		Calendar cal = Calendar.getInstance();
//		if(null!=RedisUtil.get("LASTTIME"))
//		{
////			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(RedisUtil.get("LASTTIME"));
////			cal.setTime(date);
//		}
	
		 
		
		
		
//		 System.out.println( RedisUtil.get("CSMS1000104010981207"));
		 
//		 System.out.println(UserSendImageMessageUtil.getAccess_token());
		 
//		
		
		
//		 System.out.println( RedisUtil.get("CSMS1000104010981207"));
		
//		StringBuffer s=DBHelper.getHistoru("2014/7/22 0:0:0", "2014/07/23 00:00","1050104211701072");
//		System.out.println(s);
		
//		2369e05054afc8abd26504f933384cd7
//		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//		String pswmd5 = md5.encodePassword("123456", "18665753383");
//		System.out.println("pswmd5="+pswmd5);
		
		
//		String message="{'errcode':45015,'errmsg':'response out of time limit'}";
//		JSONObject dataJson;
//		try {
//			dataJson = new JSONObject(message);
//			
//			System.out.println(dataJson.getString("errcode"));
//			int code=Integer.parseInt(dataJson.getString("errcode"));
//			//if 超24小时没上线
//			if(code==45015)
//			{
////				logger.error("it is the debug info---------------");
//				//删除信息
//				System.out.println("1");
//			}
//			else if(code==0)
//			{
//				System.out.println("1");
//			}else
//			{
//				RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
//				System.out.println("0");
//			}
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		[wifi switch]:[type],[ssid],[password],[signal strength],[privacy],[state]
//		long n = Long.parseLong("286331158");

//		
//		long n = Long.parseLong("1431655765");
//		MainClient.getWIFI(n,null);
//		
//		System.out.println(RedisUtil.get("WIFI1431655765"));
		
//        String s = "我爱你";  
//        System.out.println("转换前：" + s);  
//          
//        String encode = base64Encode(s.getBytes());  
//        System.out.println("转换后：" + encode);  
          
//        System.out.println("解码后：" + new String(base64Decode(encode)));
		
		
//		Date date=new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String time=formatter.format(date);
//		System.out.println(time);
//		ConnectionPoolDao.addConsume(166,time, 1, 1, "1010104410131164");
		
		
		
//		User u=ConnectionPoolDao.getUserInfoByNo("1010104410131164");
//		System.out.println("u="+u);
		
//		Service service=ConnectionPoolDao.getService(4,"1010104410131164");
//		System.out.println(serv);
		
		
//		Date date=new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String time=formatter.format(date);
//		System.out.println(time);
		
		
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				Date sdate = formatter.parse("2015-03-10");
//				
//				
//				
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
		

//		
//		System.out.println(getTime(Calendar.YEAR, "2015-03-10", 1));;
//		System.out.println(getTime(Calendar.MONTH, "2015-03-10", 3));;
		
		
		
//		ConnectionPoolDao.updateSimNum(1, "1010104410131164");
//	    
//		int sortList[]={6,1,0,5,3,2};
		
		
//		 int temp=sortList[0];
//		 sortList[0]=sortList[1];
//		 sortList[1]=temp;
//		 
//		 System.out.println(sortList[0]);

//	    int point=0;
//	    int temp;
//	    
//	    for (int j = 0; j < sortList.length-1; j++) {
//	    	for (int i = point+1; i < sortList.length; i++) {
//				if (sortList[point]>sortList[i]) 
//				{
//					
//					 temp=sortList[point];
//					 sortList[point]=sortList[i];
//					 sortList[i]=temp;
//					
//				}
//					
//					
//			}
//	    	point++;
//		}
//	    
//	    for (int i = 0; i < sortList.length; i++) {
//			System.out.println(sortList[i]);
//		}
		
//	    int ia[]=new int[] {4,18,6,10,25,3};
//	    Arrays.sort(ia);
//	    for (int i=0;i<ia.length;i++)
//	    System.out.print(ia[i]+" ");
//		
		
		
//		"carLocationServlet?action=getHistroy&didhis=2010104047942695&start_time=2014/12/13 9:12:0&end_time=2014/12/19 09:12"
//		1010104410171196
//		System.out.println(getHistoru("2014/12/13 9:12:0", "2014/12/19 09:12", "1010104410171196"));
//		System.out.println(getHistoru("2014/12/13 9:12:0", "2014/12/19 09:12", "2010104047942695"));
		
		
		
		
//		Date nowTime=new Date(); 
//		System.out.println(nowTime); 
//		SimpleDateFormat time=new SimpleDateFormat("MM/dd"); 
//		System.out.println(time.format(nowTime)); 
		
//	String car_id="1010104410141163";
//	String gpskeys = "GPS" + car_id;
//	String sgps = RedisUtil.get(gpskeys);
//	System.out.println("sgps="+sgps);
		
		
//		String a = ConnectionPoolDao.selectParmterNameByPid("1010104410141163");
//		System.out.println("a="+a);
		
		
//		String JsonString =   
//		"{" +  
//		    "\"phone\" : [\"12345678\", \"87654321\"]," +  
//		    "\"name\" : \"yuanzhifei89\"," +  
//		    "\"age\" : 100," +  
//		    "\"address\" : { \"country\" : \"china\", \"province\" : \"jiangsu\" }," +  
//		    "\"married\" : false," +  
//		"}";
//		
//		try {
//			JSONObject jsonObject=new JSONObject(JsonString);
//			System.out.println(jsonObject.get("name"));
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(JsonString);
		
		
//		String igs = "GPS" + "1010104410141163";
//		String s=RedisUtil.get(igs);
//		System.out.println("1:"+s);
//		1:{ "no" : 1000104013931206 , "lat" : 24.6516 , "lng" : 118.1511 , "alt" : -36.0 , "speed" : 0.0 , "dir" : 60 , "mile" : 0.13 , "info" : 0 , "sims" : 100 , "time" : 1439365722 , "count" : 27}
			
//		JSONObject jsonObject=
//		System.out.println(System.currentTimeMillis());//与上面的相同
		
		
//		JSONObject jsonObject;
//		try {
//			jsonObject = new JSONObject(s);
//			Long time=jsonObject.getLong("time");
//			
//			long leaveTime = System.currentTimeMillis();
//			
//			int a=(int) ((leaveTime-time*1000)/(1000*60*60));
//			System.out.println(a);
//			
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		1439365722000
//		1439366957383
//		Base64  base64 = new Base64 (); 
//		byte[] a= base64.decode(s);
//		System.out.println(a);
//		
//		try {
//		File file = new File("d:/" + "t" + ".jpg");
//		if (!file.exists()) {
//			
//				file.createNewFile();
//		
//		}
//		FileOutputStream output = new FileOutputStream(file);
//		output.write(a);
//		output.flush();
//		output.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		String token=RedisUtil.get("TOKENCN");
//		System.out.println(token);
//        String urlStr = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type=image";  
//     		String igs = "IMAGE" + "1000104013931206";
//    		String s=RedisUtil.get(igs);
//    		System.out.println(s);
//    		
//    		
////    		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
//          String ret = formUpload(urlStr,s);  
//          System.out.println(ret);  
//		

//		 String sadd="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";
//		 RedisUtil.set(igs, sadd);
//		 
//		 System.out.println("2:"+s);
//		 RedisUtil.get("CSMS1140104021591096");
          
          
//		 RedisUtil.set("CSMS1140104021591096", "113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033震动报警,广东省佛山市禅城区x507");
       
//		System.out.println("3:"+RedisUtil.get("IMAGE1140104021591096"));
		
		
		
//		
//		Timer timer;
//		RedisUtil.set("TOKENCN", UserSendImageMessageUtil
//				.getAccess_token());
//		timer = new Timer();
//		timer.schedule(new DateTask2(), 0, 3 * 1000);
//		System.out.print("大陆版微信报警启动！");
		
		
		
//		System.out.println(RedisUtil.get("TOKENCN"));
		
		
 /*       String json;
		try {
			json = NetUtil.readParse("http://wechat.conqueror.cn/ToolsServlet?action=getWechatPic");
	
//        JSONObject jsonParser = new JSONObject(json);
        System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
//		System.out.println(RedisUtil.get("TOKENCN"));
//		RedisUtil.publish("fenceAlarm", "1010104410161161$$113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033围栏报警,广东省佛山市禅城区x507");
		// RedisUtil.set("CSMS1140104021591096", "113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033震动报警,广东省佛山市禅城区x507");
		
		//oISxbt1hPzivS2ajEimDYFVknjFo

		
//		String s="113.1344-22.9866,2014-05-14 20:39:29,粤EXQ033围栏报警,广东省佛山市禅城区x507";
//		String []t=s.split(",");
//		System.out.println(t[1]);
//		
//		
//	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制 
//		
//	       Date date = null; 
//	        try 
//	        { 
//	            date = format.parse(t[1]); 
//	        }     
//	        catch (Exception ex)    
//	        { 
//	            ex.printStackTrace(); 
//	        } 
//	        
//	        
//	        System.out.println(date.getHours());
//	        
//	        
//	        if(date.getHours()>5&&date.getHours()<22)
//	        {
//	        	
//	        }
	        
	        
		
//		String message="1140104021591096$$113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033围栏报警,广东省佛山市禅城区x507";
//		  String carMess[] = message.split("\\$\\$");
//	        
//	  System.out.println(carMess[0]);
		
//		
//		String s="113.1344-22.9866,2014-05-14 08:39:29,粤EXQ033围栏报警,广东省佛山市禅城区x507";
//		String weid=ConnectionPoolDao.getweidByNo("1140104021591096");
//    	try {
//			UserSendImageMessageUtil.createMenu(weid, s,RedisUtil.get("TOKENCN"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println("s");
//		String s=RedisUtil.get("GPS1140104021591096");
//		System.out.println("t="+s);
		
		
       
//        System.out.println("s");	
		
//	  JPushClient jpushClient = new JPushClient("87ef4d1c8a14a7b36a62917c", "7f9e6b577ae991b2d706dd98", 3);
//
//	        // For push, all you need do is to build PushPayload object.
//	        PushPayload payload = buildPushObject_messageWithExtras();
//
//	        try {
//	            PushResult result = jpushClient.sendPush(payload);
//	            System.out.println("Got result - " + result);;
//
//	        } catch (APIConnectionException e) {
//	            // Connection error, should retry later
//	        	System.out.println("Connection error, should retry later,e="+ e);
//
//	        } catch (APIRequestException e) {
//	            // Should review the error, and fix the request
//	        	System.out.println("Should review the error, and fix the request"+e);
//	        	System.out.println("HTTP Status: " + e.getStatus());
//	        	System.out.println("Error Code: " + e.getErrorCode());
//	        	System.out.println("Error Message: " + e.getErrorMessage());
//	        
//	        }
	        
	        
	        
	        
		
		
	/*String myUrl = "http://wechat.conqueror.cn/ToolsServlet";
		
		String action="{\"type\": \"video\"}";
		
		String ticket = null;
		
		try {
			URL url = new URL(myUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			//http.setRequestProperty("Content-Type",
			//		"application/x-www-form-urlencoded");
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
		}*/
	
		
		
//		ConnectionPoolDao.addhsjfrz("444","33","1");
		
/*		
		
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();
		String pswmd5 = md5.encodePassword("123456", "lishuiye");
		System.out.println("pswmd5="+pswmd5);
		
		
		
		

String s="{ \"longitude\" : 118.15137, \"latitude\" : 24.651056, \"course\" : 352, \"time\" : { \"$numberLong\" : \"1447726628000\" }, \"speed\" : 0.0, \"odometer\" : 1.35791, \"alt\" : 41.102768, \"overspeedType\" : 0, \"speedLimit\" : 0, \"overSpeed\" : 0.0, \"no\" : { \"$numberLong\" : \"1234567890123456\" } }";
try {
	JSONObject jsonObject = new JSONObject(s);
	JSONObject noobject=jsonObject.getJSONObject("no");
	//String no=noobject.getString("$numberLong");
	//String lat=jsonObject.getString("latitude");
	
	System.out.println(noobject);
} catch (JSONException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/


	
		
	}
	
	/*  public static PushPayload buildPushObject_all_all_alert() {
		  	String json="[{\"registration_id\": 04034dc44ef,\"type\": \"getPhoto\",\"openId\": \"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ\"}]";
	        return PushPayload.alertAll(json);
	    }*/
	  
	  
/*	    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.registrationId("00065d353b5"))
	                .setNotification(Notification.android("测试", "内容", null))
	                     .setMessage(Message.newBuilder()..addPlatformNotification(IosNotification.newBuilder()
                        .setMsgContent("")
                        .addExtra("from", "JPush")
                        .build())
	                .build();
	    }*/
	
	
 /*   public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId("00065d353b5"))
                .setMessage(Message.newBuilder()
                        .setMsgContent("")
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }*/
    
/*    public static PushPayload buildPushObject_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId("070a8d03b29"))
                     
                        
                        
                .setMessage(Message.newBuilder()
                        .setMsgContent("111")
                        .addExtra("type", "getVideo")
                        .addExtra("openId", "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ")
                        .addExtra("pushTime", "0")
                        .build())
                .build();
    }*/
/*    public static PushPayload buildPushObject_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId("070a8d03b29"))
                     
                        
                        
                .setMessage(Message.newBuilder()
                        .setMsgContent("111")
                        .addExtra("type", "showWay")
                        .addExtra("lat", "24.27")
                        .addExtra("lon", "118.06")
                        .build())
                .build();
    }*/
    
    public static PushPayload buildPushObject_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId("070a8d03b29"))
                     
                        
                        
                .setMessage(Message.newBuilder()
                        .setMsgContent("111")
                        .addExtra("type", "closeSystem")
           
                        .build())
                .build();
    }
	
	
    public static String formUpload(String urlStr,String imageBase64) {  
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("User-Agent",  
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type",  
                    "multipart/form-data; boundary=" + BOUNDARY);  
  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            // text  
//            if (textMap != null) {  
//                StringBuffer strBuf = new StringBuffer();  
//                Iterator iter = textMap.entrySet().iterator();  
//                while (iter.hasNext()) {  
//                    Map.Entry entry = (Map.Entry) iter.next();  
//                    String inputName = (String) entry.getKey();  
//                    String inputValue = (String) entry.getValue();  
//                    if (inputValue == null) {  
//                        continue;  
//                    }  
//                    strBuf.append("\r\n").append("--").append(BOUNDARY).append(  
//                            "\r\n");  
//                    strBuf.append("Content-Disposition: form-data; name=\""  
//                            + inputName + "\"\r\n\r\n");  
//                    strBuf.append(inputValue);  
//                }  
//                out.write(strBuf.toString().getBytes());  
//            }  
  
            // file  
//            if (fileMap != null) {  
//                Iterator iter = fileMap.entrySet().iterator();  
//                while (iter.hasNext()) {  
//                    Map.Entry entry = (Map.Entry) iter.next();  
//                    String inputName = (String) entry.getKey();  
//                    String inputValue = (String) entry.getValue();  
//                    if (inputValue == null) {  
//                        continue;  
//                    }  
//                    File file = new File(inputValue);  
//                    String filename = file.getName();  
//                    String contentType = new MimetypesFileTypeMap()  
//                            .getContentType(file);  
//                    if (filename.endsWith(".png")) {  
//                        contentType = "image/png";  
//                    }  
//                    if (contentType == null || contentType.equals("")) {  
//                        contentType = "application/octet-stream";  
//                    }  
  
            
            		String inputName="userfile";
            		String filename="temp.jpg";
            		String contentType="application/octet-stream";
            
                    StringBuffer strBufWrite = new StringBuffer();  
                    strBufWrite.append("\r\n").append("--").append(BOUNDARY).append(  
                            "\r\n");  
                    strBufWrite.append("Content-Disposition: form-data; name=\""  
                            + inputName + "\"; filename=\"" + filename  
                            + "\"\r\n");  
                    strBufWrite.append("Content-Type:" + contentType + "\r\n\r\n");  
                    
//                    userfile
//                    tt.jpg
//                    application/octet-stream
         
            		Base64  base64 = new Base64 (); 
            		byte[] a= base64.decode(imageBase64);
            	
                    
                    
                    
  
                    out.write(strBufWrite.toString().getBytes());  
  
//                    DataInputStream in = new DataInputStream(  
//                            new FileInputStream(file));  
//                    int bytes = 0;  
//                    byte[] bufferOut = new byte[1024];  
//                    while ((bytes = in.read(bufferOut)) != -1) {  
//                        out.write(bufferOut, 0, bytes);  
//                    }  
                    
                    out.write(a);
                    
//                    in.close();  
//                }  
//            }  
  
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  
  
            // 读取返回数据  
            StringBuffer strBuf = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            res = strBuf.toString();  
            reader.close();  
            reader = null;  
        } catch (Exception e) {  
            System.out.println("发送POST请求出错。" + urlStr);  
            e.printStackTrace();  
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  
        return res;  
    }  
	
	
	
	
	
	
	
	
	public static StringBuffer getHistoru(String sdate, String edate,
			String no) {
		StringBuffer str=new StringBuffer();
		str.append("[");
		try {
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
			 Date nsDate= sdf.parse(sdate);
			 Date neDate= sdf.parse(edate);
			 
			 
			 
			
			DBObject obj = new BasicDBObject();
			obj.put("no", Long.parseLong(no));
			obj.put("time", new BasicDBObject("$gte", nsDate.getTime() / 1000)
					.append("$lte", neDate.getTime() / 1000));
			DBObject obj1 = new BasicDBObject();
			obj1.put("_id", 0);
			obj1.put("no", 0);
			obj1.put("alt", 0);
			obj1.put("mile", 0);
			obj1.put("info", 0);
			obj1.put("sims", 0);
			obj1.put("time", 0);
			List<DBObject> list = MongoDBManager.getInstance().find("gps", obj,
					obj1);
			double offsetLat = 0.0, offsetLng = 0.0;
			double oldLat = 0.0, oldLng = 0.0;
			boolean flag = false;
			boolean stopFlag = false;
			for (int i = (list.size() - 1); i >= 0; i--) {
				double speed = Double.parseDouble(list.get(i).get("speed")
						.toString());
				if (stopFlag && speed == 0.0)
					continue;
				double slat = Double.parseDouble(list.get(i).get("lat")
						.toString());
				double slng = Double.parseDouble(list.get(i).get("lng")
						.toString());
				double nlat = Math.round(slat * 10) / 10.0;
				double nlng = Math.round(slng * 10) / 10.0;
				if (Math.abs(slat - oldLat) > 0.1
						|| Math.abs(slng - oldLng) > 0.1){
					flag = true;
					oldLat = slat;
					oldLng = slng;
				}
			
				if (flag) {
					if (18.2 <= slat && slat <= 53.5 && 73.6 <= slng && slng <= 134.7) {
						Map<String, Double> offsetMap = ConnectionPoolDao.getOffset(nlng,
								nlat);
//						118.2 24.7
						if (offsetMap != null) {
							 offsetLng = offsetMap.get("offset_lng");
							 offsetLat = offsetMap.get("offset_lat");
						}
					}
					flag = false;
				}
				list.get(i).put("lat", slat + offsetLat);
				list.get(i).put("lng", slng + offsetLng);
				String co = JSON.serialize(list.get(i));
//				coordinatesList.add(co);
				if (i<(list.size() - 1))
					str.append(",");
				str.append(co);
				if (speed == 0.0) {
					stopFlag = true;
				} else {
					stopFlag = false;
				}
			}
			str.append("]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	

	private static void sort(int point,int[] sortList,int[] newSortList)
	{
		int min=sortList[point];
	    for (int i = point+1; i < sortList.length; i++) {
	    	if (sortList[i]<min) 
	    		min=sortList[i];
		}
//	    0
	    
	    newSortList[point]=min;
	    
//	    int sortList[]={1,0,5,3,2};
	    if(point<sortList.length-1)
	    {
	    	point++;
	    	sort(point, sortList, newSortList);
	    }
	    	
		
	};
	
	static String getTime(int type, String time,int adjuctNum) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String val = null;
		try {
			Date sdate = formatter.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdate);
			calendar.set(type,calendar.get(type)+adjuctNum);
			Date date=calendar.getTime();
			
			val=new SimpleDateFormat("yyyy-MM-dd").format(date);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}
	
//	 public static String base64Encode(byte[] bytes){  
//	        return new BASE64Encoder().encode(bytes);  
//	    }  
	 
//	  public static byte[] base64Decode(String base64Code) throws Exception{  
//	        return t1.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);  
//	    }  
	      
	
	
    static class MyTask extends java.util.TimerTask{ 
        @Override
        public void run() { 
//            // TODO Auto-generated method stub
//        	access_token = UserSendMessageUtil.getAccess_token();
//        	System.out.println("access_token="+access_token);
        }
    }
	

	private static StringBuffer urlConnectionPost(String tourl, StringBuffer data) {
		StringBuffer sb = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;
		URL url;
		try {
			url = new URL(tourl);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			// 当存在post的值时，才打开OutputStreamWriter
			if (data != null && data.toString().trim().length() > 0) {
				wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
				wr.write(data.toString());
				wr.flush();
			}

			// Get the response
			reader = new BufferedReader(new InputStreamReader(conn
					.getInputStream(), "UTF-8"));
			sb = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (wr != null) {
					wr.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return sb;
	}

}
