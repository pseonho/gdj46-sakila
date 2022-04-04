package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.FilmList;

public class FilmListDao {
			public List<FilmList> selectCustomerListByPage(int beginRow, int rowPerPage){
				List<FilmList> list = new ArrayList<FilmList>();
				
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				//static 으로 바꿨기때문에 DBUtil = dbutil(); 객체 생성하지 않아도 된다.
				conn = DBUtil.getConnection(); // db
	
				String sql = "select fid filmId, title, description, category, price, length, rating, actors from film_list order by fid limit ?,?";
						try {
							stmt = conn.prepareStatement(sql);
							stmt.setInt(1, beginRow);
							stmt.setInt(2, rowPerPage);
							rs = stmt.executeQuery();
							while(rs.next()) {
												FilmList f = new FilmList();
												f.setFilmId(rs.getInt("filmId"));
												f.setTitle(rs.getString("title"));
												f.setDescription(rs.getString("description"));
												f.setCategory(rs.getString("category"));
												f.setPrice(rs.getDouble("price"));
												f.setLength(rs.getInt("length"));
												f.setRating(rs.getString("rating"));
												f.setActors(rs.getString("actors"));
												list.add(f);
								
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
