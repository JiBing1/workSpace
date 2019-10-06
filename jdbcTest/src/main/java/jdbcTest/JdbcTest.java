package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.JdbcUtil;

public class JdbcTest {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public void printUserInfo(){
		conn = JdbcUtil.getConnect();
		String sql = "select * from user";
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getString("User"));
			}
			//System.out.println(rs);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new JdbcTest().printUserInfo();
	}
}
