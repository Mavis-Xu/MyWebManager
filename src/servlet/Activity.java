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
		String driver = "com.mysql.jdbc.Driver";//��������
        String url = "jdbc:mysql://localhost/backgroundmanagement";//���ݿ��ַ
        String user = "root";//���ݿ��ʺ�
        String password = "1234";//���ݿ�����
        Connection con;
        try{
            Class.forName(driver);//������������
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("���ݿ����ӳɹ�");
            Statement statement = con.createStatement();//statement����ִ�����ݿ�������
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
            ResultSet rs = statement.executeQuery(sql);//ִ��sql��䲢�����������resultset

            //���ݱ���ΪJSON��ʽ
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();//����Arraylist
            while(rs.next()) {
                Map<String,String> map = new HashMap<String,String>();//Ҫÿ�δ���һ���µ�ӳ�����Ȼֻ�ᱣ�����һ�����ݡ�
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

                list.add(map);//���ݱ�����list��

            }
            //��GSON���еķ������л�json�ַ���
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
