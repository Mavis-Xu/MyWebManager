package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class Activity
 */
@WebServlet("/Activity")
public class Activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String str ;
	String activityid,activityname,activitytype,target,state;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Activity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		activityid=request.getParameter("activityid");
		activityname=request.getParameter("activityname");
		activitytype=request.getParameter("activitytype");
		target=request.getParameter("target");
		state=request.getParameter("state");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8"); 
		String driver = "com.mysql.jdbc.Driver";//驱动名称
        String url = "jdbc:mysql://localhost/backgroundmanagement";//数据库地址
        String user = "root";//数据库帐号
        String password = "1234";//数据库密码
        Connection con;
        try{
            Class.forName(driver);//加载驱动程序
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("数据库连接成功");
            Statement statement = con.createStatement();//statement对象，执行数据库操作语句
            String sql = "select * from activities ";
            
            if(activityid!=null){
            	 sql="select * from activities where activityid="+activityid;
            }
            if(activityname!=null){
           	 sql="select * from activities where activityname="+activityname;
           }
            if(activitytype!=null){
           	 sql="select * from activities where activitytype="+activitytype;
           }
            if(target!=null){
              	 sql="select * from activities where target="+target;
              }
            if(state!=null){
           	 sql="select * from activities where state="+state;
           }
            ResultSet rs = statement.executeQuery(sql);//执行sql语句并将结果保存在resultset

            //数据保存为JSON格式
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();//创建Arraylist
            while(rs.next()) {
                Map<String,String> map = new HashMap<String,String>();//要每次创建一个新的映射表，不然只会保存最后一组数据。
                map.put("activityid",rs.getString("activityid"));
                map.put("activityname",rs.getString("activityname"));
                map.put("activityaddress",rs.getString("activityaddress"));
                map.put("activitydate",rs.getString("activitydate"));
                map.put("collectiveaddress",rs.getString("collectiveaddress"));               
                map.put("collectivetime",rs.getString("collectivetime"));
                map.put("activitytype",rs.getString("activitytype"));
                map.put("target",rs.getString("target"));
                map.put("state",rs.getString("state"));
                map.put("activityicon",rs.getString("activityicon"));

                list.add(map);//数据保存在list中

            }
            //用GSON包中的方法序列化json字符串
            Gson gson = new Gson();
            String str = null;
            str = gson.toJson(list);
            PrintWriter out = response.getWriter();  
            
            out.write(str);

            
        }catch(SQLException e){
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
        }
        
	}

	
	

}
