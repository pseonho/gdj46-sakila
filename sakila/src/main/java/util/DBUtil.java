package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//dbutil에는 필드가 없음-  This라는 메서드를 안씀--static으로 바꿈- 객체를 만들필요가 없음
public class DBUtil {
	public static Connection getConnection() {
		Connection conn =null;
		try {
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mariadb://13.125.175.213:80/sakila","root","mariadb1234");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	return conn;
	}
}