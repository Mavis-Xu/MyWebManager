package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.LoginDao;

public class LoginServlet extends HttpServlet {
	public String icon=LoginDao.icon;
	public String identity=LoginDao.identity;
	public int organizationid=LoginDao.organizationid;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	}
	//获取用户提交的信息
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//处理登录请求。
				
				request.setCharacterEncoding("utf-8");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}


		

}
