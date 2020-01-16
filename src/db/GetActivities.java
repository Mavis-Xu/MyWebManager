package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetActivities {
	public static final String DBDRIVER = "com.mysql.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost/backgroundmanagement";
    public static final String DBUSER = "root";
    public static final String DBPASS = "1234";
    
    Connection conn = null; // ���ݿ�����
    PreparedStatement pstmt = null; // ���ݿ�Ԥ�������
    ResultSet rs = null; // ��ѯҪ��������
    
    public GetActivities(){
      
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try{
	    conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	    String sql = "SELECT * FROM activities";
	    pstmt = conn.prepareStatement(sql);
	    
	
	    rs = pstmt.executeQuery(); // ��ѯ
	    if (rs.next()) { // ��������ݣ������ִ��
	    	System.out.println("��ѯ���ˣ�");
	    	int ind=rs.getInt("activityid");
	    	String activityName=rs.getString("activityname");
	    	String activityAddress=rs.getString("activityaddress");
	    	String Date=rs.getString("activitydate");
	    	String collectiveAddress=rs.getString("collectiveaddress");
	    	String collectiveTime=rs.getString("collectivetime");
	    	String type=rs.getString("activittype");
	    	String state=rs.getString("state");
	    	}
	    
	    }catch(SQLException e){
	    	  e.printStackTrace();
	    }finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
            }
        }
    }
    
  
	
}
