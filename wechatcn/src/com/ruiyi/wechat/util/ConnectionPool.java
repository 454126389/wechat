package com.ruiyi.wechat.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionPool {
    //持有一个静态的数据库连接池对象
    private static ComboPooledDataSource cpds=null; 
    private static ConnectionPool pool;

    
    private static ComboPooledDataSource cpdsTw=null;
    private static ConnectionPool poolTw;
    
    private ConnectionPool() {
    }

    public synchronized static ConnectionPool getPool() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    public static void initDataSource() {
        try {
        	System.out.println("数据库连接中......\r\n");
//            loadData.jTextArea1.append("数据库连接中......\r\n");
            cpds=new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.jdbc.Driver"); // 驱动器
      /*      cpds.setJdbcUrl("jdbc:mysql:loadbalance://192.168.2.81:3306,192.168.2.82:3306/cloud?roundRobinLoadBalance=true&characterEncoding=UTF-8"); 
            cpds.setUser("cloud"); // 用户名
            cpds.setPassword("ry315conqueror"); */
            
            cpds.setJdbcUrl("jdbc:mysql://192.168.2.105:3306/cloud?characterEncoding=UTF-8"); 
            cpds.setUser("wechat"); // 用户名
            cpds.setPassword("wechat123"); 
            
            cpds.setInitialPoolSize(5); // 初始化连接池大小
            cpds.setMinPoolSize(5); // 最少连接数
            cpds.setMaxPoolSize(15); // 最大连接数
            cpds.setAcquireIncrement(2); // 连接数的增量
            cpds.setMaxIdleTime(120);//最大空闲时间
            cpds.setIdleConnectionTestPeriod(120); // 测连接有效的时间间隔
            cpds.setTestConnectionOnCheckout(true); // 每次连接验证连接是否可用
            
            System.out.println("数据库连接完成....\r\n");
//            loadData.jTextArea1.append("数据库连接完成....\r\n");
        } catch (Exception ex) {
        	System.out.println("数据库连接错误:"+ex.getMessage()+"\r\n");
//            loadData.jTextArea1.append("数据库连接错误:"+ex.getMessage()+"\r\n");
        }
    }
    
    


 
    
    /**
     * 获得一个数据库连接 return 返回一个可用连接
     */
    public synchronized static Connection getConnection() {

        Connection con = null;
        if (cpds == null) {
				 initDataSource();
        }
        try {
            con = cpds.getConnection();
            //将数据库连接的事物设置为不默认为自动Commit
            con.setAutoCommit(false);
        } catch (Exception e) {
        }
        //回收数据库连接时，直接使用con.close()即可
        return con;

    }
    
   
    
    

    /**
     * 关闭一个数据库连接
     */
    public  void closeConnection(PreparedStatement st, Connection con) {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 关闭一个数据库连接
     */
    public  void closeConnection(ResultSet rs, PreparedStatement st, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 回收数据库所有连接资源
     */
    protected static void shutdownDataSource() throws SQLException {
        ComboPooledDataSource bds = cpds;
        bds.close();
    }
}
