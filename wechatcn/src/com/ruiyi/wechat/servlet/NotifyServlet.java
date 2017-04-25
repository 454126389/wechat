package com.ruiyi.wechat.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ruiyi.wechat.util.CommonUtil;
import com.ruiyi.wechat.util.ConnectionPoolDao;
import com.ruiyi.wechat.util.RedisUtil;
import com.utils.GetWxOrderno;

public class NotifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Logger logger = Logger.getLogger(TopayServletForKF.class.getName());

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		GetWxOrderno getWxOrderno = new GetWxOrderno();
		String return_code = getWxOrderno.getKey(sb.toString(), "return_code");
		String out_trade_no = getWxOrderno.getKey(sb.toString(),"out_trade_no");
		
		if(RedisUtil.hget("out_trade_no",out_trade_no)==null)
		{
			
			logger.info("支付结果：" + sb);
			if (return_code.equals("SUCCESS")) {
				logger.info("支付结果-成功：" + sb);

				String did = getWxOrderno.getKey(sb.toString(), "attach");
				String money = getWxOrderno.getKey(sb.toString(), "total_fee");
//				String out_trade_no = getWxOrderno.getKey(sb.toString(),"out_trade_no");
				String time_end = getWxOrderno.getKey(sb.toString(), "time_end");
				
				
			    SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmss");  
			    Date date;
				try {
					date = format.parse(time_end);
					time_end=""+(date.getTime()/1000);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				
				logger.info("did="+did);
				logger.info("money="+money);
				logger.info("out_trade_no="+out_trade_no);
				logger.info("time_end="+time_end);
				
				// 添加记录
				int num = 0;
				int addyear = 0;
				DateFormat fmt;
				switch (money) {
				case "6000":
					ConnectionPoolDao.add_payrec(did, time_end, "6", "60",
							out_trade_no);

					ConnectionPoolDao.updateServiceliuliang(did, 1);
					String imsi = ConnectionPoolDao.getImsiById(did);
					ConnectionPoolDao.updateSimMadte(imsi, 1);
					
					String iccid=ConnectionPoolDao.getICCIDById(did);
					logger.info("iccid="+iccid);
					CommonUtil.urlGet("http://139.224.221.22:8181/sim/updateSimEndDate?iccid="+iccid+"&month=12");
					
					break;
				case "1000":
					ConnectionPoolDao.add_payrec(did, time_end, "3", "10",
							out_trade_no);

					num = 100;
					ConnectionPoolDao.updateSimNum(did, num);
					break;
				case "2000":
					ConnectionPoolDao.add_payrec(did, time_end, "3", "20",
							out_trade_no);

					num = 200;
					ConnectionPoolDao.updateSimNum(did, num);
					break;
				case "5000":
					ConnectionPoolDao.add_payrec(did, time_end, "3", "50",
							out_trade_no);

					num = 500;
					ConnectionPoolDao.updateSimNum(did, num);
					break;
				case "12000":
					ConnectionPoolDao.add_payrec(did, time_end, "4", "120",
							out_trade_no);

					addyear = 1;
					fmt = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date endTime = fmt.parse(ConnectionPoolDao
								.getServiceEndTime(did));
						if (endTime.before(new Date())) {
							ConnectionPoolDao.updateServiceNow(did, addyear);
						} else {
							ConnectionPoolDao.updateService(did, addyear);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						logger.info("充值异常"+e.toString());
					}
					
					logger.info("充值异常");
					
					break;
				case "20000":
					ConnectionPoolDao.add_payrec(did, time_end, "4", "200",
							out_trade_no);

					addyear = 2;
					fmt = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date endTime = fmt.parse(ConnectionPoolDao
								.getServiceEndTime(did));
						if (endTime.before(new Date())) {
							ConnectionPoolDao.updateServiceNow(did, addyear);
						} else {
							ConnectionPoolDao.updateService(did, addyear);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case "26000":
					ConnectionPoolDao.add_payrec(did, time_end, "4", "260",
							out_trade_no);

					addyear = 3;
					fmt = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date endTime = fmt.parse(ConnectionPoolDao
								.getServiceEndTime(did));
						if (endTime.before(new Date())) {
							ConnectionPoolDao.updateServiceNow(did, addyear);
						} else {
							ConnectionPoolDao.updateService(did, addyear);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				default:
					break;
				}

			} else {
				logger.info("支付结果-失败：" + sb);
			}

			
			RedisUtil.hset("out_trade_no",out_trade_no,"true");
		}else
		{
			logger.info("支付结果-失败：" + "重复请求out_trade_no="+out_trade_no);
		}
		
		// sb为微信返回的xml
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
