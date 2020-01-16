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
	        {  ////加载驱动程序
	            Class.forName("com.mysql.jdbc.Driver");  
	        }  
	        catch (ClassNotFoundException e)  
	        {  
	            e.printStackTrace();  
	        }  
	        try  
	        {  
	            con = DriverManager.getConnection(DBURL,DBUSER,DBPASS);//注意是三个参数 


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
	        Connection con = getConn();//此处为通过自己写的方法getConn()获得连接  
	        try{

	            Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery(sql); //ResultSet类，用来存放获取的结果集！！

	            System.out.println("执行结果如下所示:");  
	            System.out.println("-----------------");  
	            System.out.println("id" + "\t" + "名字" + "\t" +"联系方式");  
	            System.out.println("-----------------");  

	            String id = null;
	            String name = null,cell = null;
	            while(rs.next()){

	                //获取id这列数据
	                id = rs.getString("id");
	                //获取name这列数据
	                name = rs.getString("name");
	                cell = rs.getString("cell");
	                //输出结果
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
	            //用来执行SQL语句
	            PreparedStatement pst = con.prepareStatement(sql); 
	            pst.setLong(1,4);
	            pst.setString(2, "许娜");
	            pst.setString(3, "123344"); 
	            i = pst.executeUpdate();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println(i);
	        return i; //返回影响的行数，1为执行成功
	    }
	 public int update() 
	    {
	        int i=0;
	        String sql = "update table_test set id=?,name=?,cell=? where id =?";
	        Connection con=getConn();
	        try {
	            //用来执行SQL语句
	            PreparedStatement pst = con.prepareStatement(sql); 
	            pst.setLong(1,1);
	            pst.setString(2, "小例");
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
	     * 删除操作
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
