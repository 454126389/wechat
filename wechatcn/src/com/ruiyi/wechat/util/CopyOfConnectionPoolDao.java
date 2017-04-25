package com.ruiyi.wechat.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruiyi.wechat.model.Batch;
import com.ruiyi.wechat.model.Car;
import com.ruiyi.wechat.model.CarIdWeid;
import com.ruiyi.wechat.model.CarInfo;
import com.ruiyi.wechat.model.Mileage;
import com.ruiyi.wechat.model.Parameter;
import com.ruiyi.wechat.model.RegisterDevice;
import com.ruiyi.wechat.model.Service;
import com.ruiyi.wechat.model.SettingInfo;
import com.ruiyi.wechat.model.User;
import com.ruiyi.wechat.model.Voiture;

public class CopyOfConnectionPoolDao {

//	parameter_select
	
	

	

	// 获取绑定车辆数量
	public static String getLockCarNum(String wechatid) {
		// TODO Auto-generated method stub

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT COUNT(ID) FROM terminal_wechat WHERE WECHAT_ID='"
				+ wechatid + "'";
		String num = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			rs.next();
			num = rs.getString("COUNT(ID)");
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆数量失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return num;
	}
	
	
	
	
	

	// 获取绑定车辆的设置
	public static List<SettingInfo> getLockCarSetting(String wechatid) {
		// TODO Auto-generated method stub

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM parameter as p,terminal_wechat as t WHERE t.ID=p.ID AND t.WECHAT_ID ='"
				+ wechatid + "'";
		List<SettingInfo> carlistsetting = new ArrayList<SettingInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				
/*				STZS = sTZS;
				CGPS = ;
				MPHN = mPHN;
				FPHN = fPHN;
				REST = rEST;*/
				
				SettingInfo car = new SettingInfo(rs.getString("ID"), rs
						.getString("ALIAS"), rs.getString("FORTIRY"), rs
						.getString("SGPS"), rs.getString("TR_T"),rs.getString("EMAIL_OPEN"),rs.getInt("WIFI"),"",rs.getString("SOS_OPEN"),rs.getString("STZS"),rs.getString("CGPS"),rs.getString("MPHN"),rs.getString("FPHN"),rs.getString("REST"),rs.getString("VATIME"),rs.getString("LSEN"));
				carlistsetting.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆设置失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlistsetting;
	}

	// 获取天气状态
	public static int getLockCarWeather(String ID) {
		// TODO Auto-generated method stub

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		int weather_state = 0;
		
		String sql = "SELECT STATE FROM service_parameter WHERE SERVICE_ID='1' AND PARAMETER_ID = '"
				+ ID + "'";
//		List<SettingInfo> carlistsetting = new ArrayList<SettingInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				weather_state=rs.getInt("STATE");
			}
		} catch (Exception ex) {
			System.out.println("获取天气状态失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return weather_state;
	}
	
	
	// 获取天气状态
	public static int getLockCarNews(String ID) {
		// TODO Auto-generated method stub

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		int weather_state = 0;
		
		String sql = "SELECT STATE FROM service_parameter WHERE SERVICE_ID='7' AND PARAMETER_ID = '"
				+ ID + "'";
//		List<SettingInfo> carlistsetting = new ArrayList<SettingInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				weather_state=rs.getInt("STATE");
			}
		} catch (Exception ex) {
			System.out.println("获取天气状态失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return weather_state;
	}
	
	
	// 获取精简版类型 0不精简，1型精简
	public static int getCutType(String ID) {
		// TODO Auto-generated method stub

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		int cutType = 0;
		
		
		String sql = "SELECT CUT FROM product WHERE ID= '"+ ID.substring(0, 3) + "'";
				
//		List<SettingInfo> carlistsetting = new ArrayList<SettingInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				cutType=rs.getInt("CUT");
			}
		} catch (Exception ex) {
			System.out.println("获取天气状态失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return cutType;
	}
	
	
	
	// 查找已绑定车辆列表：
	public static List<CarInfo> getLockCarList(String wechatid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,ALIAS FROM parameter WHERE WECHAT_ID='"
				+ wechatid + "'";
		List<CarInfo> carlist = new ArrayList<CarInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"), rs
						.getString("ALIAS"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}
	
	
	// 查找已绑定车辆列表：
	public static List<CarInfo> getCarListOnMap(String wechatid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,CHOICE,ALIAS,FENCE FROM parameter WHERE WECHAT_ID='"
				+ wechatid + "'";
				/*	String sql = "SELECT ID,ALIAS FROM parameter WHERE WECHAT_ID='"+wechatid+"' union select ID,ALIAS from hsjfrz where wechat_id ='"+wechatid+"'";*/
				
				
		
		
		
		List<CarInfo> carlist = new ArrayList<CarInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"),rs.getString("CHOICE"), rs
						.getString("ALIAS"),rs.getString("FENCE"));
				
				if(carlist.size()>0)
				if(rs.getString("CHOICE").equals("1"))
				{
					CarInfo t=carlist.get(0);
					carlist.set(0, car);
					car=t;
				}
				carlist.add(car);
				
			}
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}
	
	
	
	
	
	public static Boolean updateChoice1(String wechatid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isTrue = false;


		String sql ="UPDATE parameter SET CHOICE =0 WHERE WECHAT_ID='"+wechatid+"'";
		int rs;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("绑定车辆失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isTrue;
	}
	
		public static Boolean updateChoice2(String id) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			Boolean isTrue = false;


			String sql ="UPDATE parameter SET CHOICE =1 WHERE ID="+id;
			int rs;
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);

				rs = ps.executeUpdate();
				conn.commit();

				System.out.println(rs);

				isTrue = true;
			} catch (Exception ex) {
				isTrue = false;
				System.out.println("绑定车辆失败：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			return isTrue;
		}
	
	
	
	public static List<CarInfo> getCarInfo(String did) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,ALIAS,FENCE FROM parameter WHERE ID='"
				+ did + "'";
				/*	String sql = "SELECT ID,ALIAS FROM parameter WHERE WECHAT_ID='"+wechatid+"' union select ID,ALIAS from hsjfrz where wechat_id ='"+wechatid+"'";*/
				
				
		
		
		
		List<CarInfo> carlist = new ArrayList<CarInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"), rs
						.getString("ALIAS"),rs.getString("FENCE"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}
	
/*	
	public static List<CarInfo> getCarListOnMap2(String wechatid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,ALIAS,FENCE FROM parameter WHERE WECHAT_ID='"
				+ wechatid + "'";
		String sql = "select ID,ALIAS,FENCE from hsjfrz where wechat_id ='"+wechatid+"'";
				
				
		
		
		
		List<CarInfo> carlist = new ArrayList<CarInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"), rs
						.getString("ALIAS"),rs.getString("FENCE"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}*/
	
	
	// 查找已绑定车辆列表：
	public static List<CarInfo> getLockCarListByPhone(String phone) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,ALIAS FROM parameter WHERE ZH_NO='"
				+ phone + "'";
		List<CarInfo> carlist = new ArrayList<CarInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"), rs
						.getString("ALIAS"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获取用户绑定车辆列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}
	
	
	
	
	// 获得wechat用户列表
	public static List<String> getWechatUserList() {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT distinct WECHAT_ID FROM parameter WHERE WECHAT_ID is not null";
		
		List<String> userList = new ArrayList<String>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				userList.add(rs.getString("WECHAT_ID"));
			}
		} catch (Exception ex) {
			System.out.println("获得wechat用户列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return userList;
	}
	

	
	

	// 获得wechat用户列表
	public static List<CarIdWeid> getWechatList() {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,WECHAT_ID FROM  parameter WHERE LENGTH(WECHAT_ID)>1";

		List<CarIdWeid> userList = new ArrayList<CarIdWeid>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarIdWeid car = new CarIdWeid(rs.getString("ID"), rs
						.getString("WECHAT_ID"));
				userList.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获得wechat用户列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return userList;
	}
	
	
	
	
	
	// 查找未绑定车辆列表：
	public static List<CarInfo> getUnLockCarList(String phone) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		List<CarInfo> carlist = new ArrayList<CarInfo>();
		String sqlphone = phone.trim();
		if (sqlphone.equals(""))
			return carlist;
//		String sql = "SELECT ID,ALIAS FROM parameter WHERE ZH_NO='" + phone
//				+ "' AND WECHAT_ID is null";
		String sql = "SELECT ID,ALIAS FROM parameter WHERE ZH_NO='" + phone
		+ "' AND (WECHAT_ID is null OR WECHAT_ID='')";

		ResultSet rs = null;

		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"), rs
						.getString("ALIAS"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("获取未绑定车辆列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}

	// 绑定车辆：
	public static Boolean doLockCar(String wechatid, String[] carnolist) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isTrue = false;
		StringBuffer nolist = new StringBuffer();
		for (int i = 0; i < carnolist.length; i++) {
			nolist.append("ID=" + carnolist[i] + " OR ");
		}

		if (nolist.length() == 0) {
			isTrue = false;
		}

		String sql = "UPDATE parameter SET WECHAT_ID = '" + wechatid
				+ "' WHERE " + nolist.substring(0, nolist.length() - 4);
		int rs;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("绑定车辆失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isTrue;
	}

	
	// 绑定车辆：
	public static Boolean doLockCar(String wechatid, String pid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isTrue = false;


		String sql = "UPDATE parameter SET WECHAT_ID = '" + wechatid
				+ "' WHERE  ID =" + pid;
		int rs;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("绑定车辆失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isTrue;
	}
	
	// 解绑车辆：
	public static Boolean doUnLockCar(String[] carnolist) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSuccese = false;
		StringBuffer nolist = new StringBuffer();
		for (int i = 0; i < carnolist.length; i++) {
			nolist.append("ID=" + carnolist[i] + " OR ");
		}

		if (nolist.length() == 0) {
			isSuccese = false;
		}

		String sql = "UPDATE parameter SET WECHAT_ID = null WHERE "
				+ nolist.substring(0, nolist.length() - 4);
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			int rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isSuccese = true;
		} catch (Exception ex) {
			System.out.println("绑定车辆失败：" + ex.getMessage());
			isSuccese = false;
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSuccese;
	}
	
	// 解绑车辆：
	public static Boolean doUnLockCar(String pid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSuccese = false;


		String sql = "UPDATE parameter SET WECHAT_ID = null WHERE ID ="
				+ pid;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			int rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isSuccese = true;
		} catch (Exception ex) {
			System.out.println("绑定车辆失败：" + ex.getMessage());
			isSuccese = false;
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSuccese;
	}


	
	// 一键绑定车辆：
	public static Boolean doLockCarone(String wechatid, String pid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isTrue = false;

		String sql = "UPDATE parameter m set m.WECHAT_ID = '"+wechatid+"' WHERE m.ID IN(SELECT temp.PARAMETER_ID FROM (SELECT * FROM user_parameter WHERE USER_ID='"+pid+"') temp)";
	
		int rs;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("一键绑定车辆：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isTrue;
	}
	
	
	// 一键解绑车辆：
	public static Boolean doUnLockCarone(String pid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isTrue = false;

		String sql = "UPDATE parameter m set m.WECHAT_ID = NULL WHERE m.ID IN(SELECT temp.PARAMETER_ID FROM (SELECT * FROM user_parameter WHERE USER_ID='"+pid+"') temp)";
	
		int rs;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeUpdate();
			conn.commit();

			System.out.println(rs);

			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("一键绑定车辆：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isTrue;
	}
	

	// 改变设备设置
	public static Boolean updatDeviceSetting(SettingInfo setting) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE parameter SET TR_T = ? , SGPS = ? , FORTIRY= ?,SOS_OPEN=? ,EMAIL_OPEN=? WHERE ID='"
				+ setting.getId() + "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, setting.getTr_t());
			ps.setString(2, setting.getSgps());
			ps.setString(3, setting.getFortiry());
			ps.setInt(4, Integer.parseInt(setting.getSos_open()));
			ps.setInt(5, Integer.parseInt(setting.getEmail_open()));
			
			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			System.out.println("改变设备设置失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	
	
	// 改变设备设置定位器
	public static Boolean updatDeviceSetting2(String id,String stzs,String cpgps,String mphnlist,String fphnlist,String restswch,String vatime) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE parameter SET STZS = ? , CGPS = ? , MPHN= ?,FPHN=? ,REST=?,VATIME=? WHERE ID='"
				+ id + "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, stzs);
			ps.setString(2, cpgps);
			ps.setString(3, mphnlist);
			ps.setString(4,fphnlist);
			ps.setInt(5, Integer.parseInt(restswch));
			ps.setString(6, vatime);
			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			System.out.println("改变设备设置失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	
	// 改变天气设置
	public static Boolean updatWeatherState(String state,String ID) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE service_parameter SET STATE = ? WHERE SERVICE_ID='1' AND PARAMETER_ID='"
				+ ID+ "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, Integer.parseInt(state));
			
			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			System.out.println("改变天气设置失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	
	// 改变新闻设置
	public static Boolean updatNewsState(String state,String ID) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE service_parameter SET STATE = ? WHERE SERVICE_ID='7' AND PARAMETER_ID='"
				+ ID+ "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, Integer.parseInt(state));
			
			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			System.out.println("改变新闻设置：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	

	// 查找车辆类型列表
	public static List<Voiture> getCarTypeList() {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM voiture";
		List<Voiture> carlist = new ArrayList<Voiture>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				Voiture cartype = new Voiture(rs.getString("ID"), rs
						.getString("TYPE"));
				carlist.add(cartype);
			}
		} catch (Exception ex) {
			System.out.println("获取车辆类型列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}

	// 判断用户名是否存在
	public static Boolean isUsername(String username) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM user WHERE USERNAME = '" + username + "'";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next())
				isTrue = true;

		} catch (Exception ex) {
			System.out.println("判断用户名是否存在失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}

	// 判断是否是设备id
	public static Boolean isId(String id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM terminal WHERE ID = '" + id + "'";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next())
				isTrue = true;

		} catch (Exception ex) {
			System.out.println("判断是否是设备id失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}

	// 判断设备id是否被使用
	public static Boolean isIdisused(String id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM parameter WHERE ID = '" + id + "'";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next())
				isTrue = true;

		} catch (Exception ex) {
			System.out.println("判断设备id是否被使用失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}

	// 判断是否key和id对应
	public static Boolean isKey(String id, String key) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM terminal WHERE ID = '" + id
				+ "' AND TKEY = '" + key + "' AND ACTIVATE ='0'";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next())
				isTrue = true;

		} catch (Exception ex) {
			System.out.println("判断是否是设备id失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	
	
//	注册查询
	public static RegisterDevice RegisterDeviceSelect(String id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "select pt.CUT,LANGUAGE,ZH_NO,F_NO,ALIAS,FENCE,EMAIL_OPEN,SMS_OPEN,FORTIRY,SGPS,RMAP,TR_T,SPRU,NEWSOPEN,VATIME from parameter as p,product as pt where pt.ID=LEFT(p.ID,3) and p.ID="+id;
		ResultSet rs = null;
		Boolean isTrue = false;
		RegisterDevice registerDevice=null;
		
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				registerDevice=new RegisterDevice(rs.getString("CUT"), rs.getString("LANGUAGE"), rs.getString("ZH_NO"), rs.getString("F_NO"),
						rs.getString("ALIAS"), rs.getString("FENCE"), rs.getString("EMAIL_OPEN"), rs.getString("SMS_OPEN"),
						rs.getString("FORTIRY"), rs.getString("SGPS"), rs.getString("RMAP"), rs.getString("TR_T"), 
						rs.getString("SPRU"), rs.getString("NEWSOPEN"), rs.getString("VATIME"));
			}

		} catch (Exception ex) {
			System.out.println("判断是否是设备id失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return registerDevice;
	}
	
	
	

	// 添加用户
	public static Boolean addUser(User user) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO user (USERNAME,PASSWORD,ACTIVATE,EMAIL,PHONE,CREATION_DATE,CREATION_IP,BALANCE,SIMNUM) VALUES (?,?,?,?,?,?,?,?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setInt(3, 1);
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getCreation_date());
			ps.setString(7, user.getCreation_ip());
			ps.setFloat(8, user.getBalance());
			ps.setString(9, user.getSimnum());

			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加用户失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}

	// 添加用户
	public static Boolean addUser2(User user) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO user (USERNAME,PASSWORD,CREATION_DATE,CREATION_IP) VALUES (?,?,?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
		
			ps.setString(3, user.getCreation_date());
			ps.setString(4, user.getCreation_ip());
	

			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加用户失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}

	
	
	
	
	
	// 查找用户id
	public static String getUserId(String username) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID FROM user where USERNAME= '" + username + "'";
		String id = null;
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getString("ID");
			}
		} catch (Exception ex) {
			System.out.println("查找用户id失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return id;
	}

	// 查找设备batchid
	public static String getBatchId(String pid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT BATCH_ID FROM terminal where ID= '" + pid + "'";
		String batchid = null;
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				batchid = rs.getString("BATCH_ID");
			}
		} catch (Exception ex) {
			System.out.println("查找设备batchid失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return batchid;
	}
	
	// 查找SimTime
	public static String getSimTime(String pid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT adate FROM sim where imsi= '" + pid + "'";
		String batchid = null;
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				batchid = rs.getString("adate");
			}
		} catch (Exception ex) {
			System.out.println("查找SimTime失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return batchid;
	}
	
	
	
	// 查找设备IMSI
	public static String getIMSI(String pid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT IMSI FROM terminal where ID= '" + pid + "'";
		String batchid = null;
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				batchid = rs.getString("IMSI");
			}
		} catch (Exception ex) {
			System.out.println("查找设备batchid失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return batchid;
	}
	

	// 查找设备batch
	public static Batch getBatch(String bachtid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM batch where ID= '" + bachtid + "'";
		Batch batchid = null;
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				batchid = new Batch(rs.getString("ID"), rs
						.getString("TIME_ZONE"), rs.getString("VALIDITY"), rs
						.getString("TIME"), rs.getString("NUM"), rs
						.getString("LANGUAGE"), rs.getString("AGENT_ID"), rs
						.getString("PRODUCT_ID"));
			}
		} catch (Exception ex) {

			System.out.println("查找设备batch失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return batchid;
	}

	
	// 查找phone
	public static String getTel(String username) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT PHONE FROM user where USERNAME= '" + username + "'";
		Batch batchid = null;
		ResultSet rs = null;
		String tel=null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				tel=rs.getString("PHONE");
			}
		} catch (Exception ex) {

			System.out.println("查找设备batch失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return tel;
	}

	
	// 添加用户角色
	public static Boolean addUserRole(String id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO user_role (USER_ID,ROLE_ID) VALUES (?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setInt(2, 2);

			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加用户角色失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	
	
	// 添加设备
	public static Boolean addTerminal(String id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO terminal (ID,TKEY) VALUES (?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, "12345678");
		
			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加terminal：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	
	
	
	//激活设备
	
	

	// 添加设备
	public static Boolean addParameter(Parameter parameter) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO parameter (ID,LANGUAGE,ZH_NO,ALIAS,PRODUCT_ID,VOITURE_ID,WECHAT_ID) VALUES (?,?,?,?,?,?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, parameter.getId());
			ps.setString(2, parameter.getLanguge());
			ps.setString(3, parameter.getZh_no());
			ps.setString(4, parameter.getAlias());
			ps.setString(5, parameter.getProduct_id());
			ps.setString(6, parameter.getVoiture_id());
			ps.setString(7, parameter.getWechat_id());

			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加设备失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}

	
	// 添加设备
	public static Boolean addParameter2(Parameter parameter) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO parameter (ID,PRODUCT_ID,WECHAT_ID,ALIAS) VALUES (?,?,?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, parameter.getId());

			ps.setString(2, parameter.getProduct_id());

			ps.setString(3, parameter.getWechat_id());

			ps.setString(4, "车机"+parameter.getId());
			
			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加设备失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	

	// 添加设备
		public static Boolean UpdateParameter2(Parameter parameter) {
			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();
			String sql = "UPDATE parameter SET PRODUCT_ID=?,WECHAT_ID=?,ALIAS=? WHERE ID=?";
			ResultSet rs = null;
			Boolean isTrue = false;
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
				

				ps.setString(1, parameter.getProduct_id());

				ps.setString(2, parameter.getWechat_id());

				ps.setString(3, "车机"+parameter.getId());
				
				ps.setString(4, parameter.getId());
				
				ps.executeUpdate();
				conn.commit();
				isTrue = true;
			} catch (Exception ex) {
				isTrue = false;
				System.out.println("添加设备失败：" + ex.getMessage());
			} finally {
				connPool.closeConnection(rs, ps, conn);
			}
			return isTrue;
		}
	
	
	// 添加用户设备
	public static Boolean addUserParameter(String id, String pid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO user_parameter (USER_ID,PARAMETER_ID) VALUES (?,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pid);

			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加用户设备失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	
	// 添加service状态表
	public static Boolean addServiceParameter(String PARAMETER_ID,String sdate,String edate,String edate2) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO service_parameter (SERVICE_ID,STATE,STARTTIME,ENDTIME,PARAMETER_ID)VALUES (1,1,NULL,NULL,?),(3,1,NULL,NULL,?),(4,1,?,?,?),(5,1,NULL,NULL,?),(6,1,?,?,?),(7,1,NULL,NULL,?)";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, PARAMETER_ID);
			ps.setString(2, PARAMETER_ID);
			ps.setString(3, sdate);
			ps.setString(4, edate);
			ps.setString(5, PARAMETER_ID);
			ps.setString(6, PARAMETER_ID);
			ps.setString(7, sdate);
			ps.setString(8, edate2);
			ps.setString(9, PARAMETER_ID);
			ps.setString(10, PARAMETER_ID);

			ps.executeUpdate();
			conn.commit();
			isTrue = true;
		} catch (Exception ex) {
			isTrue = false;
			System.out.println("添加service状态表：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	

	// 改变设备状态
	public static Boolean updateActivate(int status, String id) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE terminal SET ACTIVATE = ? WHERE ID='" + id + "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);

			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			isSucc = false;
			System.out.println("改变设备状态失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	
	
	// 改变registrationId
		public static Boolean addTerminalRegistrationId(String id, String registrationId,String imei) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			Boolean isSucc = false;                    
			String sql = "UPDATE terminal SET IMEI = ?,REGISTRATION_ID= ? WHERE ID='" + id + "'";
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, imei);
				ps.setString(2, registrationId);
				ps.executeUpdate();
				conn.commit();
				isSucc = true;
			} catch (Exception ex) {
				isSucc = false;
				System.out.println("改变设registrationId失败：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			return isSucc;
		}
	
	//获取registrationId
		public static String getTerminalRegistrationId(String id) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			String registration_id = null;                    
			String sql = "SELECT REGISTRATION_ID FROM terminal WHERE ID='" + id + "'";
			ResultSet rs = null;
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);

				rs = ps.executeQuery();
				while (rs.next()) {
					registration_id=rs.getString("REGISTRATION_ID");
			
				}
			} catch (Exception ex) {
				
				System.out.println("获取registrationId失败：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			return registration_id;
			
			
	
			
		}

	// 是否是用户
	public static Boolean isUser(String username, String psw) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM user WHERE USERNAME = '" + username
				+ "' AND PASSWORD = '" + psw + "'";
		ResultSet rs = null;
		Boolean isTrue = false;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next())
				isTrue = true;

		} catch (Exception ex) {
			System.out.println("判断是否是用户失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return isTrue;
	}
	
	
	
	// 获取用户信息
	public static User getUserInfo(String username) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM user WHERE USERNAME = '" + username
				+ "'";
		ResultSet rs = null;
		User user = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("USERNAME"),"","",rs
						.getString("EMAIL"), rs.getString("PHONE"),"","",rs.getFloat("BALANCE"),"",rs.getString("QQ"),rs.getString("ID"));
			}

		} catch (Exception ex) {
			System.out.println("获取用户信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return user;
	}
	
	
	// 改变用户信息
	public static Boolean updataUserInfo(String id,String tel,String qq,String email) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE user SET EMAIL = ? , PHONE = ? , QQ = ? WHERE ID ='"
				+ id+ "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, email);
			ps.setString(2, tel);
			ps.setString(3,	qq);
			
			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			System.out.println("改变用户信息失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	
	// 改变用户信息
	public static Boolean updataParmterPhone(String id,String tel) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		Boolean isSucc = false;
		String sql = "UPDATE parameter SET ZH_NO = ?  WHERE ID ='"
				+ id+ "'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, tel);
			
			ps.executeUpdate();
			conn.commit();
			isSucc = true;
		} catch (Exception ex) {
			System.out.println("改变用户信息失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return isSucc;
	}
	
	
	// 查找设备名称
	public static String selectParmterNameByPid(String pid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String alias="";
		ResultSet rs = null;
		String sql = "SELECT ALIAS FROM `parameter` WHERE ID='"+pid+"'";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				alias=rs.getString("ALIAS");
			}
		} catch (Exception ex) {
			System.out.println("改变用户信息失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
		return alias;
	}
	
	
	
	// 获得用户的所有设备ID
	public static ArrayList<String> getUserParamterById(String id) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		
//		SELECT * FROM mileage WHERE TERMINAL_ID = '1000104010951201' AND (TIME >= '2014-05-07') AND  (TIME <= '2014-05-14')

		
//		String sql = "SELECT * FROM mileage WHERE TERMINAL_ID ='"+did +"' AND (TIME >'" +stime+"')AND  (TIME <='"+etime+"')";
		String sql = "SELECT temp.PARAMETER_ID FROM (SELECT * FROM user_parameter WHERE USER_ID='"+id+"') temp";
				
		ResultSet rs = null;
		Mileage mileage = null;
		 ArrayList<String> mileageList=new ArrayList<String>();
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				mileageList.add(rs.getString("PARAMETER_ID"));
			}

		} catch (Exception ex) {
			System.out.println("获得用户的所有设备ID：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return mileageList;
	}
	
	
	
	
	// 获得用户已绑定设备数量
	public static int getUserParmaterNum(String uid) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		

		
		String sql = "SELECT COUNT(*) TOTAL FROM (  SELECT * FROM parameter P WHERE ID IN (SELECT temp.PARAMETER_ID FROM (SELECT * FROM user_parameter WHERE USER_ID='"+uid+"') temp) AND (WECHAT_ID IS NOT null AND WECHAT_ID !=''	) ) d";
				
		ResultSet rs = null;
		int mileageList=0;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				mileageList=rs.getInt("TOTAL");
			}

		} catch (Exception ex) {
			System.out.println("获得用户的所有设备ID：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return mileageList;
	}
	
	
	
	
	
	
	
	// 获取里程记录
	public static List<Mileage> getMileage(String did,String stime,String etime) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		
//		SELECT * FROM mileage WHERE TERMINAL_ID = '1000104010951201' AND (TIME >= '2014-05-07') AND  (TIME <= '2014-05-14')

		
		String sql = "SELECT * FROM mileage WHERE TERMINAL_ID ='"+did +"' AND (TIME >'" +stime+"')AND  (TIME <='"+etime+"')";
				
		ResultSet rs = null;
		Mileage mileage = null;
		List<Mileage> mileageList=new ArrayList<Mileage>();
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				mileage = new Mileage(rs.getString("TIME"),rs.getString("MILES"));
				
		
				
				mileageList.add(mileage);
			}

		} catch (Exception ex) {
			System.out.println("获取里程记录：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return mileageList;
	}
	
	
	
	
	// 查找车辆类型列表
	public static String getCarTypeById(String id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT TYPE FROM voiture WHERE ID='"+id+"'";
		ResultSet rs = null;
		String carType=null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				carType=rs.getString("TYPE");
			}
		} catch (Exception ex) {
			System.out.println("获取车辆类型列表失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carType;
	}
	

	// 获得用户车辆列表
	public static List<Car> getCarListByUserName(String username) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

//		String sql = "SELECT * FROM parameter WHERE ZH_NO=(SELECT PHONE FROM user WHERE USERNAME ='"
//				+ username + "')";
		
		String sql ="SELECT * FROM parameter WHERE ID =any(SELECT PARAMETER_ID FROM user_parameter WHERE USER_ID =(SELECT ID FROM user WHERE USERNAME ='"+username+"'))";
		
		ResultSet rs = null;

		List<Car> carList = new ArrayList<Car>();

		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				Car temp = new Car();
				temp.setName(rs.getString("ALIAS"));
				if (rs.getString("WECHAT_ID") == null||rs.getString("WECHAT_ID").equals(""))
					temp.setHasWeChat("否");
				else
					temp.setHasWeChat("是");
				temp.setNo(rs.getString("ID"));
				temp.setCarType(getCarTypeById(rs.getString("VOITURE_ID")));
				carList.add(temp);
			}
		} catch (Exception ex) {
			System.out.println("查找设备batchid失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carList;
	}
	
	// 谷歌纠偏
	public static Map<String,Double> getOffset(Double lng,Double lat) {
		Map<String,Double> offsetMap=new HashMap<String,Double>();
	
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT OFFSET_LNG,OFFSET_LAT FROM offset WHERE LNG="
				+ lng + " AND LAT="+lat;
		
		
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				offsetMap.put("offset_lng", rs.getDouble("OFFSET_LNG"));
				offsetMap.put("offset_lat", rs.getDouble("OFFSET_LAT"));
						
			}
		} catch (Exception ex) {
			System.out.println("获取谷歌纠偏失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return offsetMap;
	}

//	public static Object updataLangType(String wechatid, String langType) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
	
//	// 记录
//	public static Map<String,Double> getWemsg(String msg) {
//		
//		Map<String,Double> offsetMap=new HashMap<String,Double>();
//	
//		ConnectionPool connPool = null;
//		Connection conn = null;
//		PreparedStatement ps = null;
//		connPool = ConnectionPool.getPool();
//
//		String sql = "SELECT OFFSET_LNG,OFFSET_LAT FROM offset WHERE LNG="
//				+ lng + " AND LAT="+lat;
//		
//		
//		ResultSet rs = null;
//		try {
//			conn = connPool.getConnection();
//			ps = conn.prepareStatement(sql);
//
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				offsetMap.put("offset_lng", rs.getDouble("OFFSET_LNG"));
//				offsetMap.put("offset_lat", rs.getDouble("OFFSET_LAT"));
//						
//			}
//		} catch (Exception ex) {
//			System.out.println("获取谷歌纠偏失败：" + ex.getMessage());
//		} finally {
//			connPool.closeConnection(rs, ps, conn);
//		}
//		return offsetMap;
//	}
	
	
	// 查詢餘額：
	public static List<CarInfo> getBalance(String wechatid) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT ID,ALIAS FROM parameter WHERE WECHAT_ID='"
				+ wechatid + "'";
		List<CarInfo> carlist = new ArrayList<CarInfo>();
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				CarInfo car = new CarInfo(rs.getString("ID"), rs
						.getString("ALIAS"));
				carlist.add(car);
			}
		} catch (Exception ex) {
			System.out.println("查詢餘額失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return carlist;
	}
	
	
	// 查詢餘額方式二：
	public static int getBalanceByNo(String no) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT BALANCE FROM `user` WHERE ID =(SELECT USER_ID FROM user_parameter WHERE PARAMETER_ID='"+no+"')";
		ResultSet rs = null;
		
		int BALANCE=0;
		
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {

				BALANCE=rs.getInt("BALANCE");
			}
		} catch (Exception ex) {
			System.out.println("查詢餘額方式二失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return BALANCE;
	}
	
	// 改变餘額：
	public static void updateBalanceByNo(float balance,String no) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		
		
		String sql = "UPDATE `user` SET BALANCE =? WHERE ID =(SELECT USER_ID FROM user_parameter WHERE PARAMETER_ID=?)";
		
		
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setFloat(1, balance);
			ps.setString(2, no);
			
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			System.out.println("改变餘額失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
	}
	
	
	
	// 获取用户信息
	public static User getUserInfoByNo(String no) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM `user` WHERE ID =(SELECT USER_ID FROM user_parameter WHERE PARAMETER_ID=?)";
		
		
		
		
		ResultSet rs = null;
		User user = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, no);

			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("USERNAME"),"","",rs
						.getString("EMAIL"), rs.getString("PHONE"),"","",rs.getFloat("BALANCE"),"",rs.getString("QQ"),rs.getString("ID"));
			}

		} catch (Exception ex) {
			System.out.println("获取用户信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return user;
	}
	
	
	
//	SELECT ID FROM parameter WHERE WECHAT_ID='oISxbt5xiz3bBNA0mhnaVsaO2UCY'
	
	// 添加购买记录
	public static void addConsume(String user_id,String consume_time,int consume_fee,int service_id,String parameter_id) {
		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "INSERT INTO consume (user_id,consume_time,consume_fee,service_id,parameter_id)VALUES (?,?,?,?,?)";
		ResultSet rs = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			
			ps.setString(2, consume_time);
			ps.setInt(3, consume_fee);
			ps.setInt(4, service_id);
			ps.setString(5, parameter_id);

			ps.executeUpdate();
			conn.commit();
			
		} catch (Exception ex) {
			System.out.println("添加购买记录：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
	}
	
	
	// 获取service信息
	public static Service getService(int SERVICE_ID,String no) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * FROM `service_parameter` WHERE SERVICE_ID=? AND PARAMETER_ID=?";
		
		
		ResultSet rs = null;
		Service service = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			
			
			ps.setInt(1, SERVICE_ID);
			ps.setString(2, no);

			rs = ps.executeQuery();
			while (rs.next()) {
				service = new Service(rs.getInt("STATE"),rs.getString("STARTTIME"),rs.getString("ENDTIME"));
			}

		} catch (Exception ex) {
			System.out.println("获取service信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return service;
	}
	
	
	
	
	
	// 改变service设置
	public static void updatServiceState(int state,String sdate,String edate,int SERVICE_ID,String ID) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "UPDATE service_parameter SET STATE = ?,STARTTIME=?,ENDTIME=?  WHERE SERVICE_ID=? AND PARAMETER_ID=?";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, state);
			ps.setString(2, sdate);
			ps.setString(3, edate);
			ps.setInt(4, SERVICE_ID);
			ps.setString(5, ID);
			
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			System.out.println("改变service设置失败：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
	}
	
	
	// 改变短信數量
	public static void updateSimNum(int num,String no) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "UPDATE `user` SET SIMNUM=SIMNUM+? WHERE ID =(SELECT USER_ID FROM user_parameter WHERE PARAMETER_ID=?)";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setInt(1, num);
			ps.setString(2, no);
			
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			System.out.println("改变短信數量失敗：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
	}

	// 改变SIM信息
	public static void updateSimInfo(String mdate,String imsi) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "UPDATE `SIM` SET mdate=? WHERE imsi=?";
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, mdate);
			ps.setString(2, imsi);
			
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			System.out.println("改变SIM信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
	}
	
	
	// 获取service信息
	public static String getImsi(String no) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT * IMSI `terminal` WHERE ID=?";
		
		
		ResultSet rs = null;
		String IMSI = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, no);

			rs = ps.executeQuery();
			while (rs.next()) {
				IMSI = rs.getString("IMSI");
			}

		} catch (Exception ex) {
			System.out.println("获取service信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return IMSI;
	}
	
	
	//添加围栏
	public static void addroundrail(String no,String latlng) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "UPDATE parameter SET FENCE=? WHERE ID=?";

		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
	
			ps.setString(1, latlng);
			ps.setString(2, no);
			
			ps.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			System.out.println("改变围栏信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(ps, conn);
		}
	}
	
	

	
	
	//获取围栏
	public static String getroundrail(String no) {

		ConnectionPool connPool = null;
		Connection conn = null;
		PreparedStatement ps = null;
		connPool = ConnectionPool.getPool();

		String sql = "SELECT FENCE from parameter WHERE ID=?";
		
		
		ResultSet rs = null;
		String roundrail = null;
		try {
			conn = connPool.getConnection();
			ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, no);

			rs = ps.executeQuery();
			while (rs.next()) {
				roundrail = rs.getString("FENCE");
			}

		} catch (Exception ex) {
			System.out.println("获取取围栏信息：" + ex.getMessage());
		} finally {
			connPool.closeConnection(rs, ps, conn);
		}
		return roundrail;
	}
	
	
	
		//删除围栏
		public static void removeroundrail(String no) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			String sql = "UPDATE parameter SET FENCE=NULL WHERE ID=?";

			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);

				ps.setString(1, no);
				
				ps.executeUpdate();
				conn.commit();
			} catch (Exception ex) {
				System.out.println("删除围栏：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
		}
		
		//获取weid
		public static String getweidByNo(String no) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			String sql = "SELECT WECHAT_ID from parameter WHERE ID=?";
			
			
			ResultSet rs = null;
			String roundrail = null;
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
				
				
				ps.setString(1, no);

				rs = ps.executeQuery();
				while (rs.next()) {
					roundrail = rs.getString("WECHAT_ID");
				}

			} catch (Exception ex) {
				System.out.println("获取weid信息：" + ex.getMessage());
			} finally {
				connPool.closeConnection(rs, ps, conn);
			}
			return roundrail;
		}
		
		
		//更新ticket
		public static Boolean UPDATEticket(String device_id,String ticket) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			Boolean b=false;
			
			//String sql = "INSERT INTO parameter(ID, ticket) SELECT '"+device_id +"', '"+ticket+"' FROM DUAL WHERE NOT EXISTS(SELECT * FROM parameter WHERE ID = '"+device_id+"')";
			String sql ="UPDATE terminal SET TICKET=? WHERE ID=?";
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
		
				ps.setString(1, ticket);
				ps.setString(2, device_id);
				
				ps.executeUpdate();
				conn.commit();
				b=true;
			} catch (Exception ex) {
				b=false;
				System.out.println("更新ticket：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			return b;
		}
		
		//添加后视镜信息
		public static Boolean ADDticket(String device_id,String ticket) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			Boolean b=false;
			
			String sql = "INSERT INTO parameter (TICKET,ID) VALUES (?,?)";
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
		
				ps.setString(1, ticket);
				ps.setString(2, device_id);
				
				ps.executeUpdate();
				conn.commit();
				b=true;
			} catch (Exception ex) {
				b=false;
				System.out.println("添加后视镜信息：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			return b;
		}
		
		
		public static String get_ticket(String device_id) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			
			
			ResultSet rs = null;
			String tickey = null;
			
			String sql = "SELECT * FROM terminal WHERE ID='"+device_id+"'";

			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
				
				rs = ps.executeQuery();
				if (rs.next()) 
					tickey = rs.getString("TICKET");
				
		
				
			} catch (Exception ex) {
				System.out.println("getticket信息：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			System.out.println("tickey="+tickey);
			return tickey;
		}
		
		
		public static String getWeid(String did,String key1) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();

			
			
			ResultSet rs = null;
			String tickey = null;
			
			String sql = "SELECT "+key1+" FROM parameter WHERE ID="+did;

			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				
				if (rs.next()) 
					tickey = rs.getString(key1);
				
		
				
			} catch (Exception ex) {
				System.out.println("getticket信息：" + ex.getMessage());
			} finally {
				connPool.closeConnection(ps, conn);
			}
			System.out.println("tickey="+tickey);
			return tickey;
		}
		
	
		

		
		
		
		public static Boolean checkUser(String device_id) {

			ConnectionPool connPool = null;
			Connection conn = null;
			PreparedStatement ps = null;
			connPool = ConnectionPool.getPool();
			String sql = "SELECT * FROM parameter WHERE ID = '" + device_id+"'";
			ResultSet rs = null;
			Boolean isTrue = false;
			try {
				conn = connPool.getConnection();
				ps = conn.prepareStatement(sql);

				rs = ps.executeQuery();
				if (rs.next())
					isTrue = true;

			} catch (Exception ex) {
				System.out.println("判断是否是设备id失败：" + ex.getMessage());
			} finally {
				connPool.closeConnection(rs, ps, conn);
			}
			return isTrue;
		}
		

		
		
		
		
		
}
