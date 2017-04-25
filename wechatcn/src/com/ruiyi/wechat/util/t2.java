package com.ruiyi.wechat.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import com.ruiyi.wechat.model.DeviceType;

import cn.jpush.api.push.model.Message;
	
public class t2 {
	public static void main(String[] args) {

//		 String id=ConnectionPoolDao.get_ticket("1234567890000003");
//		System.out.println(id);
	//	System.out.println(null==RedisUtil.get("WECHATONLINE13163905721"));;
		
		
	/*    JPushClient jpushClient = new JPushClient("87ef4d1c8a14a7b36a62917c", "7f9e6b577ae991b2d706dd98");
	    try {
	        ReceivedsResult result = jpushClient.getReportReceiveds("1942377665");
	        System.out.println("Got result - " + result);

	    } catch (APIConnectionException e) {
	        // Connection error, should retry later
	    	System.out.println("Connection error, should retry later"+ e);

	    } catch (APIRequestException e) {
	        // Should review the error, and fix the request
	    	System.out.println("Should review the error, and fix the request"+ e);
	    	System.out.println("HTTP Status: " + e.getStatus());
	    	System.out.println("Error Code: " + e.getErrorCode());
	    	System.out.println("Error Message: " + e.getErrorMessage());
	    }*/
		
		
//deviceId=--registrationId=00065d353b5--imei=359338054807137
//2015-12-02 16:59:06,539-添加registrationId失败
		//ConnectionPoolDao.addTerminalRegistrationId("13163905721","00065d353b5","359338054807137");
		

		
		//String resultPhoto = formUpload(url, textMap, fileMap);
		
//		System.out.println(ConnectionPoolDao.getTerminalRegistrationId("13163905721"));
		
		
	/*	
		try {
			UserSendImageMessageUtil.getServiceMsg("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ","giU-Wx2EfvgeOtJfTawGbvwADl8K1HKszZwA6oOJG8GRsdlKTJ9nquRAAf4HbQ-e","nAbKnsAYghCz0IC0FXJipO7iWJNjyxVvEhhFwyioaItbl3QNPOiNQEDfkSIhklxteMrni2bn-V_dUxAxq4Nl9lEQmA-dPkIGRKeOQoA_C1ACIBaAIANTM","video");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		*/
//		oneRegister("1234567890000003","oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");
//		UserSendImageMessageUtil.getWeChatTicket("123");
//		System.out.println(RedisUtil.get("TOKENCN"));
//		System.out.println(UserSendImageMessageUtil.getJsapi_Ticket());
		
//		RedisUtil.set("JSAPI",  UserSendImageMessageUtil.getJsapi_Ticket());
//		System.out.println(RedisUtil.get("JSAPI"));
//		
//		
//
//        // 注意 URL 一定要动态获取，不能 hardcode
//        String url = "http://wechat.conqueror.cn/";
//        Map<String, String> ret = sign("sM4AOVdWfPE4DxkXGEs8VC1NFMtrWwbpZ5sc9FKDOYuCmoDXjOGMzyixL2uEDgrWF_WesFGrMaANCp14mbvDLA", url);
//        for (Map.Entry entry : ret.entrySet()) {
//            System.out.println(entry.getKey() + ", " + entry.getValue());
//        }
		
//		System.out.println(ConnectionPoolDao.getweidByNo("1234567890000003")==null);
		
//		RedisUtil.set("TOKENCN",  UserSendImageMessageUtil.getAccess_token());
//		System.out.println( RedisUtil.get("TOKENCN"));
		/*String jsonurl="{\"time\":null,\"count\":null,\"dir\":null,\"speed\":null,\"alt\":null,\"no\":1330105050121288,\"mile\":null,\"sims\":null,\"lng\":null,\"lat\":null,\"info\":null}";
		JSONObject jsonObject = null;
	
			try {
				jsonObject = new JSONObject(jsonurl);
				String noobject = jsonObject.getString("no");
				String timeobject = jsonObject.getString("time");
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
		
//		System.out.println(ConnectionPoolDao.getWeid("3579410528924802015"));
//		System.out.println(RedisUtil.get("TOKENCN"));
//		System.out.println(UserSendImageMessageUtil.getJsapi_Ticket());
		
//		doSendWay("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ","1340105050111294","我要去厦门北站");
		
/*		obj.append("_id", Long.parseLong(no));
		obj.append("time", new BasicDBObject("$gte", nsDate.getTime()));
		Document obj1 = new Document();
		obj1.append("_id", 0);
		obj1.append("time", 0);
		MongoDBManager2.getInstance().find("sos", obj, obj1)*/
		
		//电子狗
//		System.out.println(RedisUtil.isNotField("online.cloud","1010104410161161"));
		//车机
//		System.out.println(RedisUtil2.isNotField("online","1330105050101281"+"_M"));
		
//		System.out.println(DeviceType.isOnline("1010104410161161"));
		//电子狗
	/*	DBObject dbo21=new BasicDBObject();
    	DBObject dbo22=new BasicDBObject();
    	DBObject dbo1=new BasicDBObject();
    	DBObject dbo2=new BasicDBObject();
    	DBObject dbo3=new BasicDBObject();
    	DBObject dbo4=new BasicDBObject();
    	dbo3.put("$max", "$mile");
    	dbo4.put("$min", "$mile");
    	dbo2.put("_id", "$no");
    	dbo2.put("maxMile", dbo3);
    	dbo2.put("minMile", dbo4);
    	dbo1.put("$group",dbo2);
    	dbo22.put("no", 2010105139381060l);
    	dbo22.put("time", new BasicDBObject("$gt",1447411360).append("$lt",1447413362));
    	dbo21.put("$match", dbo22);
    	
    	try {
    		
    		

    		
    		Iterator<DBObject> it=MongoDBManager.aggregate("gps",dbo21,dbo1);
    		while(it.hasNext()){
    			DBObject d=it.next();
    			System.out.println(d.toString());
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		
		
/*			 Date dt=new Date();
		     try {
		    	 Long etime=new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getTime();
		    	 Long stime=etime-86400000l;
				System.out.println("etime="+etime+"\nstime="+stime);
				
					 
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		     
		
	     
	     
/*		String str = "2015-12-26";
		String str2 = "2015-12-27";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			long millionSeconds = sdf.parse(str).getTime();
			long millionSeconds2 = sdf.parse(str2).getTime();
			System.out.println("millionSeconds="+millionSeconds+"--millionSeconds2="+millionSeconds2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//毫秒
*/	
		
			
		
	/*	Date date = new Date();
		date.getDate()
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("UTC+8"));
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.SECOND, 1);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		System.out.println("today zero : " + cal.getTimeInMillis());
		*/
		
		
/*		Document dbo21=new Document();
		Document dbo22=new Document();
		Document dbo1=new Document();
		Document dbo2=new Document();
		Document dbo3=new Document();
		Document dbo4=new Document();
    	dbo3.append("$max", "$odometer");
    	dbo4.append("$min", "$odometer");
    	dbo2.append("_id", "$no");
    	dbo2.append("maxMile", dbo3);
    	dbo2.append("minMile", dbo4);
    	dbo1.append("$group",dbo2);
    	dbo22.append("no", "1340105050101293");
    	dbo22.append("time", new Document("$gt",1451059200000l).append("$lt",1451145600000l));
    	dbo21.append("$match", dbo22);
    	List<Document> test=new ArrayList<Document>();
    	test.add(dbo21);
    	test.add(dbo1);
    	MongoCursor<Document> list=MongoDBManager2.aggregate("gps",test);
    	while (list.hasNext()) 
    	{
			Document doc = (list.next());
			double maxMile = doc.getDouble("maxMile");
			double minMile = doc.getDouble("minMile");
			System.out.println("今日行程为"+(maxMile-minMile));
    	}*/
		
		/*System.out.println(RedisUtil2.get("GPS" +"1330105050121288"));
		System.out.println(RedisUtil2.get("GPS" +"1340105050101293"));*/
		
//		System.out.println(RedisUtil.get("TOKENCN"));
		
/*	try {
			UserSendImageMessageUtil.getServiceMsg2(
					"oISxbtyMDjzmDSX7Mn3VSvQKdAMQ",
					"1",
					RedisUtil.get("TOKENCN"), "news");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//doSendWay("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ","1330105050101281","我要去广东");
		
		
/*		Pattern pattern=Pattern.compile("http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*");
		Matcher matcher=pattern.matcher("safasff");
			System.out.println(matcher.find());*/
	
		//旧版本
//		WechatSend.sendText("oISxbtyMDjzmDSX7Mn3VSvQKdAMQ", "你好");
		
		
		System.out.println(DeviceType.getCutType("1380106000931215"));
		
		
	}
	

	
	
	private static String doSendWay(String weid,String did,String code) {
		// TODO Auto-generated method stub
		
		//获取用户位置
		String token = RedisUtil.get("TOKENCN");
		String uriAPI = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		uriAPI = uriAPI.replace("ACCESS_TOKEN", token);
		uriAPI = uriAPI.replace("OPENID", weid);
		String resultGet=CommonUtil.urlGet(uriAPI);
		JSONObject jsonParser=null;
		 String city=null;
		
		try {
			 jsonParser = new JSONObject(resultGet);
			 city=jsonParser.getString("city");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//语意解析
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("appid", "wx71460f3d267fcd41");
		paramsMap.put("uid", "oISxbtyMDjzmDSX7Mn3VSvQKdAMQ");
		paramsMap.put("category", "map");
		paramsMap.put("city", city);
		paramsMap.put("query", code);

		String requestUrl = "https://api.weixin.qq.com/semantic/semproxy/search?access_token=YOUR_ACCESS_TOKEN";
		requestUrl = requestUrl.replace("YOUR_ACCESS_TOKEN", token);

		String result = CommonUtil
				.httpsRequest(requestUrl, "POST",
						net.sf.json.JSONObject.fromObject(paramsMap)
								.toString());
		
		
		System.out.println("result="+result);
		
		JSONObject jsonParserReq=null;
		String loc_ori=null;
		org.json.JSONObject end_loc=null;
		try {
			jsonParserReq = new JSONObject(result);
			org.json.JSONObject semantic=jsonParserReq.getJSONObject("semantic");
			org.json.JSONObject details=semantic.getJSONObject("details");
			String intent=semantic.getString("intent");
			if("SEARCH".equals(intent))
				 end_loc=details.getJSONObject("location");
			else
				 end_loc=details.getJSONObject("end_loc");
		
		
			loc_ori=end_loc.getString("loc_ori");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//位置经纬度解析
		String geoUrl="http://api.map.baidu.com/telematics/v3/geocoding?keyWord="+loc_ori+"&cityName="+city+"&out_coord_type=gcj02&ak=nHRgHTN4F1Gv1lznGoOOXnqy&output=json";
		System.out.println(geoUrl);
		String resultGeo=CommonUtil.urlGet(geoUrl);
		JSONObject jsonParserGeo=null;
		String lat="";
		String lng="";
		try {
			jsonParserGeo = new JSONObject(resultGeo);
			org.json.JSONObject results=jsonParserGeo.getJSONObject("results");
			org.json.JSONObject location=results.getJSONObject("location");
			lat=location.getString("lat");
			lng=location.getString("lng");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//发送车机指令
		String registartion_id = ConnectionPoolDao
				.getTerminalRegistrationId(did);
		Message msg = Message.newBuilder().setMsgContent("")
				.addExtra("type", "remoteCommand")
				.addExtra("lat", lat)
				.addExtra("lon", lng)
				.addExtra("text", loc_ori).build();
		CommonUtil.sendMsg(registartion_id, msg);
		
		
		
		
		return "正在为您执行指令《"+code+"》，请确保您的设备已经开机且联网。";
	}

	
}
