package servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class LoginCheck extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	    super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

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
	   
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	            String sql = "SELECT * FROM users where name=? and password=?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, req.getParameter("name"));
	            pstmt.setString(2, req.getParameter("password")); 
	            
				
	            rs = pstmt.executeQuery(); // ��ѯ
	            if (rs.next()) { // ��������ݣ������ִ��
	                flag = true; //  ��ʾ��½�ɹ�
	                identity=rs.getString("identity");
	             	icon=rs.getString("icon");
	             	organizationid=rs.getInt("organizationid");
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
	    if (flag) { 
	        req.getSession().setAttribute("identity", identity);  
	        req.getSession().setAttribute("icon", icon); 
	        req.getSession().setAttribute("organizationid", organizationid); 
	        if(identity.equals("normal")){
	        	 
	        	 resp.sendRedirect("login.jsp");
	             JOptionPane.showMessageDialog(null, "�����ǹ���Ա���޷������̨����");				
	             }else{
	            	 resp.sendRedirect("/MyWebManager/index.jsp?organizationid="+organizationid);
	      	}       
	        // ��½�ɹ�
	   
	  
	    		
	        } else { // ��½ʧ��
	        resp.sendRedirect("login.jsp");
	        /*  response.sendRedirect("login.jsp"); */
	        	JOptionPane.showMessageDialog(null, "�û������������!");
	        	
	  }
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
	
	
	
}
