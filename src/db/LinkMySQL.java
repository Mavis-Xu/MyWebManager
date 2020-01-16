package db;

import java.sql.*;

public class LinkMySQL {
	public static final String DBURL = "jdbc:mysql://localhost/backgroundmanagement";
    public static final String DBUSER = "root";
    public static final String DBPASS = "1234";
	 public  Connection getConn() 
	    {
	        Connection con = null;
	        try  
	        {  ////������������
	            Class.forName("com.mysql.jdbc.Driver");  
	        }  
	        catch (ClassNotFoundException e)  
	        {  
	            e.printStackTrace();  
	        }  
	        try  
	        {  
	            con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);//ע������������ 


	        }  
	        catch (SQLException e)  
	        {  
	            e.printStackTrace();
	        }
	        return con;
	    }

	 public void select(String table) throws SQLException
	    {  
	        String sql = "select * from "+table;  
	        Connection con = getConn();//�˴�Ϊͨ���Լ�д�ķ���getConn()�������  
	        try{

	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(sql); //ResultSet�࣬������Ż�ȡ�Ľ��������

	            System.out.println("ִ�н��������ʾ:");  
	            System.out.println("-----------------");  
	            System.out.println("id" + "\t" + "����" + "\t" +"��ϵ��ʽ");  
	            System.out.println("-----------------");  

	            String id = null;
	            String name = null,cell = null;
	            while(rs.next()){

	                //��ȡid��������
	                id = rs.getString("id");
	                //��ȡname��������
	                name = rs.getString("name");
	                cell = rs.getString("cell");
	                //������
	                System.out.println(id + "\t" + name+"\t"+cell);
	            }
	            rs.close();
	            con.close();
	        }
	        catch(SQLException e)
	        {      
	            e.printStackTrace();   
	        } 
	    }
	
    
	 public int insert(String table) 
	    {

	        int i = 0;
	        Connection con = getConn();
	        String sql = "insert into "+table+" (id, name, cell) values (?, ?, ?)";
	        try {
	            //����ִ��SQL���
	            PreparedStatement pst = con.prepareStatement(sql); 
	            pst.setLong(1,4);
	            pst.setString(2, "����");
	            pst.setString(3, "123344"); 
	            i = pst.executeUpdate();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(i);
	        return i; //����Ӱ���������1Ϊִ�гɹ�
	    }
	 public int update() 
	    {
	        int i=0;
	        String sql = "update table_test set id=?,name=?,cell=? where id =?";
	        Connection con=getConn();
	        try {
	            //����ִ��SQL���
	            PreparedStatement pst = con.prepareStatement(sql); 
	            pst.setLong(1,1);
	            pst.setString(2, "С��");
	            pst.setString(3, "45654");
	            pst.setLong(4, 1);
	            i = pst.executeUpdate();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(i);
	        return i;
	    }
	 /*
	     * ɾ������
	     */
	    public int delete() {

	        String sql = "delete from table_test where id=1";
	        int i=0;
	        Connection con = getConn();
	        try {

	            Statement stmt = con.createStatement();
	            i = stmt.executeUpdate(sql);
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	        return i;
	    }

    
	     

}
