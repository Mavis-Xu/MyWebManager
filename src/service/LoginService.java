package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
	
	// �������ɸ����ݿ�����ӳ���
    final String DBDRIVER = "com.mysql.jdbc.Driver";
    final String DBURL = "jdbc:mysql://localhost/backgroundmanagement";
    final String DBUSER = "root";
    final String DBPASS = "1234";
    
        Connection conn = null; // ���ݿ�����
        PreparedStatement pstmt = null; // ���ݿ�Ԥ�������
        ResultSet rs = null; // ��ѯҪ��������
        boolean flag = false; // ������
        String identity = null; // �������
        int organizationid=0;
        String icon = null;
        
	public Boolean login(String username, String password) {

        // ��ȡSql��ѯ���
        String logSql = "select * from user where name ='" + username
                + "' and password ='" + password + "'";

        // ��ȡDB����
        link(logSql);

        
        return flag;
    }

    /*public Boolean register(String username, String password) {
    
        // ��ȡSql��ѯ���
        String regSql = "insert into student values('"+ username+ "','"+ password+ "') ";

        // ��ȡDB����
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
	           
	            
				
	            rs = pstmt.executeQuery(); // ��ѯ
	            if (rs.next()) { // ��������ݣ������ִ��
	                flag = true; //  ��ʾ��½�ɹ�
	                
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
