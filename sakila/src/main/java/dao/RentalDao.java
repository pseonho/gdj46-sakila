package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
//rental 검색  리스트
public class RentalDao {
	//검색 후 리스트  요청 메서드
	public List<Map<String,Object>> selectRentalSearchList(int storeId, String customerName, String beginDate, String endDate, int beginRow, int rowPerPage){
		List<Map<String,Object>> list = new ArrayList<>();
		// DB 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		// rental 검색 SQL 쿼리 
		conn = DBUtil.getConnection();
		String sql = "SELECT rental_id rentalId, rental_date rentalDate, r.customer_id customerId, return_date returnDate, r.staff_id staffId, r.last_update lastUpdate,"
				+ " CONCAT(c.first_name,' ',c.last_name) cName,"
				+ " s.store_id storeId,"
				+ " i.film_id filmId,"
				+ " f.title title"
				+ " FROM rental r"
				+ "	INNER JOIN customer c"
				+ "	ON r.customer_id = c.customer_id "
				+ "	INNER JOIN  staff s"
				+ "	ON r.staff_id = s.staff_id"
				+ "	INNER JOIN inventory i"
				+ "	ON r.inventory_id = i.inventory_id"
				+ "	INNER JOIN film f"
				+ "	ON i.film_id = f.film_id ";

			/*검색 기능 경우의수
			
			 전부 입력 하지 않을 떄
			전부 입력 했을 때
			
			storeID
			customerName
			 beginDate(대여 시작날)
			 endDate(대여 마지막날)
	
		 	storeId+customerName
			storeId+beginDate(대여 시작날)
			storeId+endDate(대여 마지막날)
	
			customerName+beginDate(대여 시작날)
			customerName+endDate(대여 마지막날)
			customerName+beginDate(대여 시작날)+endDate(대여 마지막날)
	
			storeId+customerName+beginDate(대여 시작날)
			storeId+customerName+endDate(대여 마지막날)
			storeId+beginDate(대여 시작날)+endDate(대여 마지막날)
	
			beginDate(대여 시작날)+endDate(대여 마지막날)
			*/
		try {
			//전부 입력 하지 않을 떄
			if(storeId == -1 && customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { 
				sql += " ORDER BY rentalId LIMIT ?,? "; 
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, rowPerPage);
			
			//전부 입력 됬을 때
			} else if(storeId != -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("")) { 
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
				
			//storeID	
			} else if(storeId != -1 && customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { 
				sql += " WHERE s.store_id= ? ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				
			//customerName	
			} else if(storeId == -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { 
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				
				// beginDate(대여 시작날)
			} else if(storeId == -1 && customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) {
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-30','%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, beginDate);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				
				// endDate(대여 마지막날)
			} else if(storeId == -1 && customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) { 
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, endDate);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);
				
				// storeId+customerName
			} else if(storeId != -1 && !customerName.equals("") && beginDate.equals("") && endDate.equals("") ) { 
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				
				// storeId+beginDate(대여 시작날)
			} else if(storeId != -1 && customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { 
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-31','%Y-%m-%d') ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);

				// storeId+endDate(대여 마지막날)
			}  else if(storeId != -1 && customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) {
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,? ";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				
				//customerName+beginDate(대여 시작날)
			} else if(storeId == -1 && !customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { 
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-31','%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				
				//customerName+endDate(대여 마지막날)
			} else if(storeId == -1 && !customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) { 
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
				
			//customerName+beginDate+endDate
			} else if(storeId == -1 && !customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { 
				sql += " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			
				//storeId+customerName+beginDate(대여 시작날)
			} else if(storeId != -1 && !customerName.equals("") && !beginDate.equals("") && endDate.equals("") ) { 
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE('9999-12-31','%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setString(3, beginDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
				
				//storeId+customerName+endDate(대여 마지막날)
			} else if(storeId != -1 && !customerName.equals("") && beginDate.equals("") && !endDate.equals("") ) {
				sql += " WHERE s.store_id= ? AND CONCAT(c.first_name,' ',c.last_name) LIKE ? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, "%"+customerName+"%");
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
				
				//storeId+beginDate(대여 시작날)+endDate(대여 마지막날)
			} else if(storeId != -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { 
				sql += " WHERE s.store_id= ? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, storeId);
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
				
				// beginDate(대여 시작날)+endDate(대여 마지막날)
			} else if(storeId == -1 && customerName.equals("") && !beginDate.equals("") && !endDate.equals("") ) { 
				sql += " WHERE r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d') ORDER BY rentalId LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, beginDate);
				stmt.setString(2, endDate);
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}
			
			System.out.println(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("rentalId", rs.getInt("rentalId"));
				m.put("rentalDate", rs.getString("rentalDate"));
				m.put("customerId", rs.getInt("customerId"));
				m.put("returnDate", rs.getString("returnDate"));
				m.put("staffId", rs.getInt("staffId"));
				m.put("lastUpdate", rs.getString("lastUpdate"));
				m.put("cName", rs.getString("cName"));
				m.put("storeId", rs.getInt("storeId"));
				m.put("filmId", rs.getInt("filmId"));
				m.put("title", rs.getString("title"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// rental의 총 행의 개수구하는 메서드
	public int totalRow (int storeId, String customerName, String beginDate, String endDate){
		int totalRow = 0; //totalRow 변수 초기화
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			 String sql = "SELECT COUNT(*) cnt"
						+ " FROM rental r INNER JOIN customer c"
						+ " ON r.customer_id = c.customer_id"
						+ "	INNER JOIN staff s"
						+ "	ON r.staff_id = s.staff_id"
						+ "	INNER JOIN inventory i "
						+ "	ON i.inventory_id = r.inventory_id "
						+ "	INNER JOIN film f"
						+ "	ON f.film_id = i.film_id"
						+ " WHERE CONCAT(c.first_name,' ',c.last_name) LIKE ?";

			 if(storeId == -1 && beginDate.equals("") && endDate.equals("")){ // customerName
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				
				// endDate
			 } else if (storeId == -1 && beginDate.equals("") && !endDate.equals("")) { 
				 sql += " AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
			 	stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, endDate);
				
				 // beginDate
			} else if(storeId == -1 && !beginDate.equals("") && endDate.equals(""))  {
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				
				// rentalDate
			} else if(storeId == -1 && !beginDate.equals("") && !endDate.equals("")) { 
				sql += " AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setString(2, beginDate);
				stmt.setString(3, endDate);
				
				 // storId
			} else if(storeId != -1 && beginDate.equals("") && endDate.equals("")) {
				sql += " AND s.store_id=?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				
				 // storeId, endDate
			} else if(storeId != -1 && beginDate.equals("") && !endDate.equals("")) {
				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE('0000-01-01','%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, endDate);
				
				 // storeId, beginDate
			} else if(storeId != -1 && !beginDate.equals("") && endDate.equals("")) {
				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND NOW()";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				
				// storeId, beginDate, endDate
			} else if(storeId != -1 && !beginDate.equals("") && !endDate.equals("")) { 
				sql += " AND s.store_id=? AND r.rental_date BETWEEN STR_TO_DATE(?,'%Y-%m-%d') AND STR_TO_DATE(?,'%Y-%m-%d')";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+customerName+"%");
				stmt.setInt(2, storeId);
				stmt.setString(3, beginDate);
				stmt.setString(4, endDate);
			}

				rs = stmt.executeQuery();
				if(rs.next()) {
					totalRow=rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return totalRow;
	}
	
}