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

public class StatsDataDao {
	//1_amountByCoustome 고객 별 총액
	public List<Map<String,Object>> amountByCustomer(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT p.customer_id customerId, CONCAT(c.first_name,'',c.last_name) name, SUM(amount) total"
				+ "		FROM payment p INNER JOIN customer c "
				+ "		ON p.customer_id = c.customer_id"
				+ "		GROUP BY p.customer_id"
				+ " 		HAVING sum(p.amount) >= 180"
				+ "		ORDER BY SUM(p.amount) DESC ";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				
				Map<String,Object> m = new HashMap<String, Object>();
				m.put("customerId", rs.getInt("customerId"));
				m.put("name",rs.getString("name"));
				m.put("total", rs.getDouble("total"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		//2_ rental_rate별 영화 갯수
	public List<Map<String,Object>> filmCountByRentalRate(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		      Connection conn = null;
		      PreparedStatement stmt = null;
		      ResultSet rs = null;
		      conn = DBUtil.getConnection();
		      
		      //RentalRate 쿼리
				  	String sql=" SELECT rental_rate rentalRate, COUNT(*) cnt"
							+ " FROM film"
							+ " GROUP BY rental_rate"
							+ " ORDER BY COUNT(*) desc";
							      	  try {
							 	         stmt = conn.prepareStatement(sql);
							 	         rs = stmt.executeQuery();
							 	         while(rs.next()) {			      		
									 	        Map<String, Object> m = new HashMap<>();
									 	       m.put("rentalrate", rs.getString("rentalrate"));
									 	       m.put("cnt",rs.getInt("cnt"));
									 	       list.add(m);
								         }
								      } catch (SQLException e) {
								         e.printStackTrace();
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
	
		//3. Rating 별 영화 개수(filmCount)
			public List<Map<String,Object>> filmCountByRating(){		
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
		
				conn = DBUtil.getConnection();
				//Rating  쿼리
				String sql=" SELECT rating, COUNT(*) cnt"
						+ " FROM film"
						+ " GROUP BY rating";
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("rating", rs.getString("rating"));
						m.put("cnt",rs.getInt("cnt"));
						list.add(m);
					}
				} catch (SQLException e) {
					e.printStackTrace();
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
			
			//4. staff별 매출
			public List<Map<String,Object>> amountBystaff(){		
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
			
				conn = DBUtil.getConnection();
				// staff별 매출 쿼리
				String sql=" SELECT staff_id, SUM(amount)\r\n"
						+ "FROM payment\r\n"
						+ "GROUP BY staff_id";
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						// 값 넣기
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("staffId", rs.getString("staffId"));
						m.put("sum",rs.getDouble("sum"));
						
						list.add(m);
					}
				} catch (SQLException e) {
					e.printStackTrace();
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
			
		}
			