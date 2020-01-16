package db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToJson {
	/*public static void main(String[] agrs){
		toJson("activities", "activities.json");
	}
*/
	public static void toJson(String tableName,String fileName){
		
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/backgroundmanagement";
		String user = "root";
		String pwd = "1234"; 
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,pwd);
			Statement stet = con.createStatement();


			String sql = "select * from "+tableName;
			ResultSet rs = stet.executeQuery(sql);
			ResultSetMetaData metaData =rs.getMetaData(); 
			int columnCount= metaData.getColumnCount();
			JSONArray array = new JSONArray();
			while(rs.next()){ 
				JSONObject jsonObj = new JSONObject();
				for(int i = 1; i <= columnCount;i++){ 
					String columnName = metaData.getColumnLabel(i); 
					String value =rs.getString(columnName); 
					jsonObj.put(columnName, value); 
				} 
				array.add(jsonObj);
			}	
			System.out.println("转换JSON数据：");  
            System.out.println(array.toString());  
			//File file=new File("E:/毕业设计/服务器端/MyWebManager/"+fileName);
            //File file=new File("C:/Users/Administrator/Workspaces/MyEclipse 2017/MyWebManager/WebRoot/"+fileName);
			File file=new File(fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			String s = array.toString();
			byte[] b = s.getBytes();
			FileWriter fw = new FileWriter(file);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(s);
			out.close();			
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	} 

}
