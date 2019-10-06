package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	private static Connection conn = null; 
	
	public static Connection getConnect(){
		PropertiesUtil.loadFile("jdbc.properties");
		String driver = PropertiesUtil.getValue("driver");
		String url = PropertiesUtil.getValue("url");
		String username = PropertiesUtil.getValue("username");
		String password = PropertiesUtil.getValue("password");
		
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(url,username,password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
			close();
		}
		return conn;
	}
	
	public static void close(){
		try{
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
