package dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

		public class RewardsReportDao {//우수고객 프로시저
				public Map<String,Object> rewardsReport(int minMonthlyPurchase, double minDollarAmountPurchase){
					Map<String, Object> map = new HashMap<String,Object>();
					Connection conn = null;
					 // PreparedStatement : 쿼리를 실행
				      // CallableStatement : 프로시저를 실행
					CallableStatement stmt = null;
					ResultSet rs = null;
			
					ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>(); // ArrayList ->map->h 저장
					HashMap<String,Object> h = null; //  h 저장
					Integer count = 0; // count(*)를 저장할 변수
		
					conn = DBUtil.getConnection();
					try {
						stmt = conn.prepareCall("{CALL rewards_report(?,?,?)}");
						stmt.setInt(1, minMonthlyPurchase);
						stmt.setDouble(2, minDollarAmountPurchase);
						stmt.registerOutParameter(3,Types.INTEGER);
						rs = stmt.executeQuery();
						while(rs.next()) {
							h = new HashMap<String,Object>();
							h.put("customerId",rs.getInt(1));
							h.put("storeId",rs.getInt(2));
							h.put("firstName",rs.getString(3));
							h.put("lastName",rs.getString(4));
							h.put("email",rs.getString(5));
							h.put("addressId",rs.getInt(6));
							h.put("active",rs.getInt(7));
							h.put("createDate",rs.getString(8));
							h.put("updateDate",rs.getString(9));
		
							System.out.println(rs.getInt(1) + " ★customerId");
							System.out.println(rs.getInt(2) + " ★storeId");
							System.out.println(rs.getString(3) + "★-firstName");
							System.out.println(rs.getString(4) + " ★lastName");
							System.out.println(rs.getString(5) + " ★email");
							System.out.println(rs.getInt(6) + " ★addressId");
							System.out.println(rs.getInt(7) + " ★-active");
							System.out.println(rs.getString(8) + "★createDate");
							System.out.println(rs.getString(9) + " ★updateDate");
							list.add(h); //리스트 추가
						}
						count = stmt.getInt(3); // 프로시저 3번째 out변수 값
						System.out.println(count+ "★count");
					} catch (SQLException e) {
						e.printStackTrace();
					}
		
					map.put("list", list);
					map.put("count", count); // 총 개수
					return map;
					
				}
				// ?구매 , ?달러 이상 구매, ?명 count
				public static void main(String[] args) {
					RewardsReportDao rr = new RewardsReportDao();
					int minMonthlyPurchase = 7;
					double minDollarAmountPurchase = 50.00;
					Map<String,Object> map = rr.rewardsReport(minMonthlyPurchase, minDollarAmountPurchase);
					List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
					  int count = (Integer)map.get("count");
					System.out.println(minMonthlyPurchase + "회 구매 한  고객 중" + minDollarAmountPurchase+ "달러 이상 구매한 고객은"+count+"총 명 입니다.");
				}
		}
