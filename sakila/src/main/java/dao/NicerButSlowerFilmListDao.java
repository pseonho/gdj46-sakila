package dao;
import vo.NicerButSlowerFilmList;
import java.util.*;
import java.sql.*;
import util.DBUtil;

public class NicerButSlowerFilmListDao {
		public List<NicerButSlowerFilmList> selectNicerButSlowerFilmListByPage(int beginRow, int rowPerPage){
				List<NicerButSlowerFilmList> list= new ArrayList<NicerButSlowerFilmList>();
					Connection conn = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;
					//static 으로 바꿨기때문에 DBUtil = dbutil(); 객체 생성하지 않아도 된다.
					conn = DBUtil.getConnection(); // db
					
					String sql = "select fid filmId, title, description, category, price, length, rating, actors from nicer_but_slower_film_list order by fid limit ?,?";  
							try {
								stmt = conn.prepareStatement(sql);
								stmt.setInt(1, beginRow);
								stmt.setInt(2, rowPerPage);
								rs = stmt.executeQuery();
								while(rs.next()) {
									NicerButSlowerFilmList n = new	NicerButSlowerFilmList();
									n.setFilmId(rs.getInt("filmId"));
									n.setTitle(rs.getString("title"));
									n.setDiscription(rs.getString("discription"));
									n.setCategory(rs.getString("category"));
									n.setPrice(rs.getDouble("price"));
									n.setLength(rs.getInt("length"));
									n.setRating(rs.getString("rating"));
									n.setActors(rs.getString("actors"));
								}
						} catch (SQLException e) {
							e.printStackTrace();
						}
						return list;
					}
				//전체행을 구하는 메서드
				public int selectNicerButSlowerFilmListTotalRow() {
					int row = 0; //전체 행의 수 변수 초기화
					Connection conn = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;	
					conn=DBUtil.getConnection();
	
				// count 쿼리문
					String sql = "select count(*) cnt from NicerButSlowerFilm_List";
					try {
							stmt = conn.prepareStatement(sql);
							rs = stmt.executeQuery();
							if(rs.next()) {
									row = rs.getInt("cnt");
									System.out.println(row+ " <-- selectNicerButSlowerFilmListTotalRow");
								}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				return row;	
		}
}

