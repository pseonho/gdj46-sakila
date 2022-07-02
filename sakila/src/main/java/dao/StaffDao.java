package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class StaffDao {
	public List<Map<String, Object>> selectStaffList() {
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","mariadb1234");
			String sql = "SELECT"
							+ "		s1.manager_staff_id staffId,"
							+ "		concat(s2.first_name,' ',s2.last_name) staffName, "
							+ "		CONCAT(a.address, IFNULL(a.address2, ' '), district) staffAddress, "
							+ "		s1.store_id storeId, "
							+ "		s1.address_id addressId, "
							+ "		s1.last_update lastUpdate, "
							+ "		s2.email "
							+ " FROM staff s2"
							+ " INNER JOIN store  s1"
							+ " INNER JOIN address a"
							+ " ON s1.manager_staff_id = s2.staff_id"
							+ " AND s1.address_id = a.address_id;";		
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>(); // 다형성
				map.put("storeId", rs.getInt("storeId"));
				map.put("staffId", rs.getInt("staffId"));
				map.put("staffName", rs.getString("staffName"));
				map.put("addressId", rs.getInt("addressId"));
				map.put("staffAddress", rs.getString("staffAddress"));
				map.put("lastUpdate", rs.getString("lastUpdate"));
				map.put("email", rs.getString("email"));
				list.add(map);
			}
	} catch (Exception e) { // ClassNotFoundException, SQLException두개의 예외를 부모타입 Exception으로 처리 -> 다형성
		e.printStackTrace();
		System.out.println("예외발생");
	} finally {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return list;
}
	public static void main(String[] args) {
		StaffDao dao = new StaffDao();
		List<Map<String, Object>> list = dao.selectStaffList();
		for(Map m : list) {
			System.out.print(m.get("storeId")+", ");
			System.out.print(m.get("staffId")+", ");
			System.out.print(m.get("staffName")+", ");
			System.out.print(m.get("addressId")+", ");
			System.out.print(m.get("staffAddress")+", ");
			System.out.print(m.get("email")+", ");
			System.out.print(m.get("lastUpdate"));
		}
		
	}
	
	
}
