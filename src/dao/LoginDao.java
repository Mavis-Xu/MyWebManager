package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.User;

public class LoginDao {
	private static PreparedStatement stmt=null;
	private static Connection connection=null;
	private static ResultSet re=null;
	private static int count=-1;
	public static String identity,icon;
	public static int organizationid;
	
	public static int login(User user){
		int flage=-1;
		String URL="jdbc:mysql://localhost:3306/backgroundmanagement";
		String USENAME="root";
		String PWD="1234";
		

		try{
		Class.forName("com.mysql.jdbc.Driver");//加载具体的驱动类(新的驱动类)
		//b.与数据库建立连接
		connection=DriverManager.getConnection(URL,USENAME,PWD);
		//c.发送sql,执行命令
		//String sql="insert into class(number,name) value('16','nmj');";
		
		String sql="SELECT * FROM users where name=? and password=?";
		stmt=connection.prepareStatement(sql);
		stmt.setString(1, user.getName());
		stmt.setString(2, user.getPassword());
		
		
		re=stmt.executeQuery();
		
		if(re.next()){
			count=1;
			identity=re.getString("identity");
         	icon=re.getString("icon");
         	organizationid=re.getInt("organizationid");
		}
		
		 if(count>0){//登录成功
			 flage=1;
		}
		 else {
			flage=0;
		}
		 }
		catch(Exception e){
			e.printStackTrace();
			flage=-1;
		}finally{
			try{
				if(re!=null){re.close();}
				if(stmt!=null){stmt.close();}
				if(connection!=null){connection.close();}
			}catch(Exception e){
				flage=-1;
				e.printStackTrace();
			}
		}
		return flage;
		
		 
	}

}
