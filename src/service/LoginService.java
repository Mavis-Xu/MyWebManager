package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
	
	// 定义若干个数据库的连接常量
    final String DBDRIVER = "com.mysql.jdbc.Driver";
    final String DBURL = "jdbc:mysql://localhost/backgroundmanagement";
    final String DBUSER = "root";
    final String DBPASS = "1234";
    
        Connection conn = null; // 数据库连接
        PreparedStatement pstmt = null; // 数据库预处理操作
        ResultSet rs = null; // 查询要处理结果集
        boolean flag = false; // 保存标记
        String identity = null; // 保存身份
        int organizationid=0;
        String icon = null;
        
	public Boolean login(String username, String password) {

        // 获取Sql查询语句
        String logSql = "select * from user where name ='" + username
                + "' and password ='" + password + "'";

        // 获取DB对象
        link(logSql);

        
        return flag;
    }

    /*public Boolean register(String username, String password) {
    
        // 获取Sql查询语句
        String regSql = "insert into student values('"+ username+ "','"+ password+ "') ";

        // 获取DB对象
       link(regSql);

        int ret = sql.executeUpdate(regSql);
        if (ret != 0) {
            sql.closeDB();
            return true;
        }
        sql.closeDB();
        
        return false;
    }*/
    
    public void link(String sql){
    	
	   
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	            //String sql = "SELECT * FROM members where name=? and password=?";
	            pstmt = conn.prepareStatement(sql);
	           
	            
				
	            rs = pstmt.executeQuery(); // 查询
	            if (rs.next()) { // 如果有数据，则可以执行
	                flag = true; //  表示登陆成功
	                
	            }
	            	
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                rs.close();
	                pstmt.close();
	                conn.close();
	            } catch (Exception e) {
	            }
	        }
    }
}
