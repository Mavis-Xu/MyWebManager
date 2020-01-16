package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.Charset;
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

import service.LoginService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String name,password;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		name=request.getParameter("name");
		password=request.getParameter("password");
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
        String pass = "1234";//���ݿ�����
        Connection con;
        try{
            Class.forName(driver);//������������
            con = DriverManager.getConnection(url,user,pass);
            if(!con.isClosed())
                System.out.println("���ݿ����ӳɹ�");
            Statement statement = con.createStatement();//statement����ִ�����ݿ�������
            String sql = "select * from members";
            if(name!=null && password!=null){
            	sql="select * from members where name="+name+"and password="+password;
            }
            ResultSet rs = statement.executeQuery(sql);//ִ��sql��䲢�����������resultset

            //���ݱ���ΪJSON��ʽ
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();//����Arraylist
            while(rs.next()) {
                Map<String,String> map = new HashMap<String,String>();//Ҫÿ�δ���һ���µ�ӳ�����Ȼֻ�ᱣ�����һ�����ݡ�
                map.put("memberid",rs.getString("memberid"));
                map.put("name",rs.getString("name"));
                map.put("password",rs.getString("password"));
                map.put("realname",rs.getString("realname"));
                map.put("sex",rs.getString("sex"));               
                map.put("email",rs.getString("email"));
                map.put("city",rs.getString("city"));
                map.put("idnum",rs.getString("idnum"));
                map.put("qqnum",rs.getString("qqnum"));
                map.put("icon",rs.getString("icon"));
                map.put("phone",rs.getString("phone"));
                map.put("organizationid",rs.getString("organizationid"));
                map.put("group",rs.getString("group"));
                map.put("guarantor",rs.getString("guarantor"));

                list.add(map);//���ݱ�����list��

            }
            //��GSON���еķ������л�json�ַ���
            Gson gson = new Gson();
            String jsonstr = null;
            jsonstr = gson.toJson(list);
            PrintWriter out = response.getWriter();  

            out.write(jsonstr);
            
            
        }catch(SQLException e){
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}   

}
