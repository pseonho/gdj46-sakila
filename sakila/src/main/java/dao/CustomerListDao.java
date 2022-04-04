package dao;
import vo.CustomerList;
import java.util.*;
import java.sql.*;
import util.DBUtil;

		public class CustomerListDao {
			public List<CustomerList> selectCustomerListByPage(int beginRow, int rowPerPage){
				List<CustomerList> list = new ArrayList<CustomerList>();
				
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				//static 으로 바꿨기때문에 DBUtil = dbutil(); 객체 생성하지 않아도 된다.
				conn = DBUtil.getConnection(); // db
				
				String sql = "select id customerId, name, address, phone, city, country, notes, sid storeId from customer_list order by id limit ?,?";
						try {
							stmt = conn.prepareStatement(sql);
							stmt.setInt(1, beginRow);
							stmt.setInt(2, rowPerPage);
							rs = stmt.executeQuery();
							while(rs.next()) {
								CustomerList c = new  CustomerList();
								c.setCustomerId(rs.getInt("customerId"));
								c.setName(rs.getString("name"));
								c.setAddress(rs.getString("address"));
								c.setPhone(rs.getString("phone"));
								c.setCity(rs.getString("city"));
								c.setCountry(rs.getString("country"));
								c.setNotes(rs.getString("notes"));
								c.setStoreId(rs.getInt("storeId"));
								list.add(c);
				
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

						return list;
					}
						
						//전체행을 구하는 메서드
						public int selectCustomerListTotalRow() {
							int row = 0; //전체 행의 수 변수 초기화
							Connection conn = null;
							PreparedStatement stmt = null;
							ResultSet rs = null;	
							conn=DBUtil.getConnection();
			
						// count 쿼리문
						String sql = "select count(*) cnt from Customer_List";
						try {
								stmt = conn.prepareStatement(sql);
								rs = stmt.executeQuery();
								if(rs.next()) {
										row = rs.getInt("cnt");
										System.out.println(row+ " <-- selectCustomerListTotalRow");
									}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					return row;	
				}
}
		
