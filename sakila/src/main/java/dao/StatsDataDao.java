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
	//1. 고객 별 총액 180 이상
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
				m.put("customerId",rs.getInt("customerId"));
				m.put("name",rs.getString("name"));
				m.put("total",rs.getDouble("total"));
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
			
			//4. 스토어 별 손님 카운트
			public List<Map<String,Object>> customerByStore(){		
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
			
				conn = DBUtil.getConnection();
				//Store별  손님 방문 쿼리
				String sql="SELECT s.store_id storeId, COUNT(*) cnt "
						+ " FROM store s "
						+ " INNER JOIN customer c "
						+ " ON s.store_id = c.store_id "
						+ " GROUP BY s.store_id ";
				
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("storeId", rs.getString("storeId"));
						m.put("cnt",rs.getDouble("cnt"));
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
			
			//5. 스토어 별 총매출
			public List<Map<String,Object>> amountBystore(){		
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
			
				conn = DBUtil.getConnection();
				//각 스토어 매출 쿼리
				String sql="SELECT t.storeid storeId, SUM(t.amount)  total "
						+ " FROM (SELECT p.amount amount, s.store_id storeId "
						+ " FROM payment p INNER JOIN staff s"
						+ " ON p.staff_id = s.staff_id) t"
						+ " GROUP BY t.storeid";
				
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("storeId", rs.getString("storeId"));
						m.put("total",rs.getDouble("total"));
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
			
			//6.staff별 년도별 매출
			public List<Map<String,Object>>amountByYStaff(){
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				conn = DBUtil.getConnection();
				//
				String sql = "SELECT staff_id staffId, YEAR(payment_date) year, SUM(amount) total"
						+ "  FROM payment"
						+ "  GROUP BY staff_id, YEAR(payment_date)";
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("staffId", rs.getInt("staffId"));
						m.put("year",rs.getInt("year"));
						m.put(" total",rs.getDouble("total"));
						list.add(m);
						System.out.println(m);
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
			//7.카테고리별 영화
			public List<Map<String,Object>>filmByCatrgory(){
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				conn = DBUtil.getConnection();
				String sql = "SELECT t.category_id catrgoryId, c.name name, t.cnt "
						+ " FROM (SELECT category_id, COUNT(*) cnt "
						+ "      FROM film_category "
						+ "      GROUP BY category_id "
						+ "      ORDER BY COUNT(*) DESC) t "
						+ "      INNER JOIN  "
						+ "      category c "
						+ "      ON t.category_id = c.category_id "
						+ " ORDER BY t.cnt DESC ";
				try {
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					while(rs.next()) {
						
						Map<String,Object> m = new HashMap<String, Object>();
						m.put("catrgoryId", rs.getInt("catrgoryId"));
						m.put("name",rs.getString("name"));
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
  		}
			
			
			
			