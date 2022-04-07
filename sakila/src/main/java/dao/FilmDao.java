package dao;

import java.sql.*;
import java.util.*;
import util.DBUtil;
import vo.FilmList;

public class FilmDao {
	//상세검색
	public List<FilmList> selectFilmSearchList(int beginRow, int rowPerPage,String category, String rating, double price, int length, String title, String actor) {

		List<FilmList> list = new ArrayList<FilmList>();
		
		//db 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();

		//1.아무것도 선택하지 않음
		//2.length만
		//3. price만
		//4.length, category
		//5.length, rating
		//6.length, price,  category 
		//7.length, price,  rating
		//8 length  rating  category
		//9.rating만
		//10 rating , price
		//11.  price, length
		//12. category만
		//13. category price
		//14. category rating 
		//15. category, rating , price
		//16. 전부 다 선택

		/*
		if(length == 0) {
							sql += " AND length<60 ORDER BY fid LIMIT ?,?";
						} else if (length == 1){
							sql += " AND length>=60 ORDER BY fid LIMIT ?,?";
						}  length만
		sql += " AND price=? ORDER BY fid LIMIT ?,?";price만
		sql += " AND rating=? ORDER BY fid LIMIT ?,?"; rating만
		sql += " AND category=? ORDER BY fid LIMIT ?,?";  category만
		*/
		
		try {
			// 동적쿼리
			String sql = "SELECT fid,title,description,category,price,length,rating,actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
			//1.아무것도 선택하지 않음
			if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
				sql += " ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} 
			
			//2.length 입력
			else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { 
				if(length == 0) {
					sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
				} else if(length == 1) {
					sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
				}
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} 
			
			 // 3.  price 선택
			else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
				sql += " AND price=? ORDER BY fid LIMIT ?, ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+title+"%");
				stmt.setString(2, "%"+actor+"%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			}
			
			//4.length, category
				else if (!category.equals("") && rating.equals("") && price ==-1 && length !=-1) { 
					if(length == 0) {
						sql += " AND category=? AND length<60 ORDER BY fid LIMIT ?,?";
					} else if (length == 1) {
						sql += " AND category=? AND length>=60 ORDER BY fid LIMIT ?,?";
					}
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3, category);
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
				}
			
			//5.length, rating
			   else if(category.equals("") && !rating.equals("") && price==-1 && length!=-1) { 
		            if(length == 0) {
		               sql += " AND length<60 AND rating=? ORDER BY fid LIMIT ?, ?";
		            } else if(length == 1) {
		               sql += " AND length>=60 AND rating=? ORDER BY fid LIMIT ?, ?";
		            }
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setString(3, rating);
		            stmt.setInt(4, beginRow);
		            stmt.setInt(5, rowPerPage);
		         } 
			
			//6.length, price,  category 
			   else if(!category.equals("") && rating.equals("") && price!=-1 && length!=-1) { 
		            if(length == 0) {
		               sql += " AND category=? AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
		            } else if(length == 1) {
		               sql += " AND category=? AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
		            }
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setString(3, category);
		            stmt.setDouble(4, price);
		            stmt.setInt(5, beginRow);
		            stmt.setInt(6, rowPerPage);
		         } 
			
			//7.length, price,  rating
			   else if(category.equals("") && !rating.equals("") && price!=-1 && length!=-1) { 
		            if(length == 0) {
		               sql += " AND rating=? AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
		            } else if(length == 1) {
		               sql += " AND rating=? AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
		            }
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setString(3, rating);
		            stmt.setDouble(4, price);
		            stmt.setInt(5, beginRow);
		            stmt.setInt(6, rowPerPage);
		         } 
			
			//8 length  rating  category
			   else if(!category.equals("") && !rating.equals("") && price==-1 && length!=-1) { 
		            if(length == 0) {
		               sql += " AND category=? AND rating=? AND length<60 ORDER BY fid LIMIT ?, ?";
		            } else if(length == 1) {
		               sql += " AND category=? AND rating=? AND length>=60 ORDER BY fid LIMIT ?, ?";
		            }
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setString(3, category);
		            stmt.setString(4, rating);
		            stmt.setInt(5, beginRow);
		            stmt.setInt(6, rowPerPage);
		         } 
		
			//9.rating만
			   else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
		            sql += " AND rating=? ORDER BY fid LIMIT ?, ?";
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setString(3, rating);
		            stmt.setInt(4, beginRow);
		            stmt.setInt(5, rowPerPage);
		         } 
			
			//10 rating , price
			   else if (category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
					sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3,rating);
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
				}
			
			//11.  price, length
			   else if(category.equals("") && rating.equals("") && price!=-1 && length!=-1) { 
		            if(length == 0) {
		               sql += " AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
		            } else if(length == 1) {
		               sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
		            }
		            stmt = conn.prepareStatement(sql);
		            stmt.setString(1, "%"+title+"%");
		            stmt.setString(2, "%"+actor+"%");
		            stmt.setDouble(3, price);
		            stmt.setInt(4, beginRow);
		            stmt.setInt(5, rowPerPage);
		         } 
			
			
			//12. category만
			   else if (!category.equals("") && rating.equals("") && price ==-1 && length ==-1) { 
					sql += " AND category=? ORDER BY fid LIMIT ?,?";
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3, category);
					stmt.setInt(4, beginRow);
					stmt.setInt(5, rowPerPage);
				} 
			
			//13. category, price
			   else if (!category.equals("") && rating.equals("") && price !=-1 && length ==-1) { 
					sql += " AND category=? AND price=? ORDER BY fid LIMIT ?,?";
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3, category);
					stmt.setDouble(4, price);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
			   }
			
			//14. category, rating 
			   else if (!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { 
					sql += " AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					stmt.setInt(5, beginRow);
					stmt.setInt(6, rowPerPage);
			   }
			 
			//15. category, rating , price
			   else if (!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { 
					sql += " AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					stmt.setDouble(5, price);
					stmt.setInt(6, beginRow);
					stmt.setInt(7, rowPerPage);
				}
			
			//16. 전부 다 선택
			   else if (!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { //다입력했을떄
					if (length == 0) {
						sql += " AND category=? AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
					} else if (length == 1) {
						sql += " AND category=? AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
					}
					stmt =conn.prepareStatement(sql);
					stmt.setString(1, "%" +title+"%");
					stmt.setString(2,"%" + actor + "%");
					stmt.setString(3, category);
					stmt.setString(4, rating);
					stmt.setDouble(5, price);
					stmt.setInt(6, beginRow);
					stmt.setInt(7, rowPerPage);
				}
				rs =stmt.executeQuery();
				 while (rs.next()) {
					 FilmList f = new FilmList();
					 f.setFid(rs.getInt("fid"));
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
	//총행
	public int selectFilmSearchTotalRow (String category, String rating, double price, int length, String title, String actor) {
	      int   totalCount = 0;
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      try {
	         // 동적쿼리
	         String sql = "SELECT fid,title,description,category,price,length,rating,actors FROM film_list WHERE title LIKE ? AND actors LIKE ?";
	         //1.아무것도 선택하지 않음
	         if(category.equals("") && rating.equals("") && price==-1 && length==-1) {
	            sql += " ORDER BY fid LIMIT ?, ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "%"+title+"%");
	            stmt.setString(2, "%"+actor+"%");
	         
	         } 
	         
	         //2.length 입력
	         else if(category.equals("") && rating.equals("") && price==-1 && length!=-1) { 
	            if(length == 0) {
	               sql += " AND length<60 ORDER BY fid LIMIT ?, ?";
	            } else if(length == 1) {
	               sql += " AND length>=60 ORDER BY fid LIMIT ?, ?";
	            }
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "%"+title+"%");
	            stmt.setString(2, "%"+actor+"%");
	            
	         } 
	         
	          // 3.  price 선택
	         else if(category.equals("") && rating.equals("") && price!=-1 && length==-1) {
	            sql += " AND price=? ORDER BY fid LIMIT ?, ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "%"+title+"%");
	            stmt.setString(2, "%"+actor+"%");
	            stmt.setDouble(3, price);
	         
	         }
	         //4.length, category
	            else if (!category.equals("") && rating.equals("") && price ==-1 && length !=-1) { 
	               if(length == 0) {
	                  sql += " AND category=? AND length<60 ORDER BY fid LIMIT ?,?";
	               } else if (length == 1) {
	                  sql += " AND category=? AND length>=60 ORDER BY fid LIMIT ?,?";
	               }
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3, category);
	            
	            }
	         
	         //5.length, rating
	            else if(category.equals("") && !rating.equals("") && price==-1 && length!=-1) { 
	                  if(length == 0) {
	                     sql += " AND length<60 AND rating=? ORDER BY fid LIMIT ?, ?";
	                  } else if(length == 1) {
	                     sql += " AND length>=60 AND rating=? ORDER BY fid LIMIT ?, ?";
	                  }
	                  stmt = conn.prepareStatement(sql);
	                  stmt.setString(1, "%"+title+"%");
	                  stmt.setString(2, "%"+actor+"%");
	                  stmt.setString(3, rating);
	              
	               } 
	         
	         //6.length, price,  category 
	            else if(!category.equals("") && rating.equals("") && price!=-1 && length!=-1) { 
	                  if(length == 0) {
	                     sql += " AND category=? AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
	                  } else if(length == 1) {
	                     sql += " AND category=? AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
	                  }
	                  stmt = conn.prepareStatement(sql);
	                  stmt.setString(1, "%"+title+"%");
	                  stmt.setString(2, "%"+actor+"%");
	                  stmt.setString(3, category);
	                  stmt.setDouble(4, price);
	              
	               } 
	         
	         //7.length, price,  rating
	            else if(category.equals("") && !rating.equals("") && price!=-1 && length!=-1) { 
	                  if(length == 0) {
	                     sql += " AND rating=? AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
	                  } else if(length == 1) {
	                     sql += " AND rating=? AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
	                  }
	                  stmt = conn.prepareStatement(sql);
	                  stmt.setString(1, "%"+title+"%");
	                  stmt.setString(2, "%"+actor+"%");
	                  stmt.setString(3, rating);
	                  stmt.setDouble(4, price);
	          
	               } 
	         
	         //8 length  rating  category
	            else if(!category.equals("") && !rating.equals("") && price==-1 && length!=-1) { 
	                  if(length == 0) {
	                     sql += " AND category=? AND rating=? AND length<60 ORDER BY fid LIMIT ?, ?";
	                  } else if(length == 1) {
	                     sql += " AND category=? AND rating=? AND length>=60 ORDER BY fid LIMIT ?, ?";
	                  }
	                  stmt = conn.prepareStatement(sql);
	                  stmt.setString(1, "%"+title+"%");
	                  stmt.setString(2, "%"+actor+"%");
	                  stmt.setString(3, category);
	                  stmt.setString(4, rating);
	               
	               } 
	      
	         //9.rating만
	            else if(category.equals("") && !rating.equals("") && price==-1 && length==-1) {
	                  sql += " AND rating=? ORDER BY fid LIMIT ?, ?";
	                  stmt = conn.prepareStatement(sql);
	                  stmt.setString(1, "%"+title+"%");
	                  stmt.setString(2, "%"+actor+"%");
	                  stmt.setString(3, rating);
	                 
	               } 
	         
	         //10 rating , price
	            else if (category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
	               sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3,rating);
	            
	            }
	         
	         //11.  price, length
	            else if(category.equals("") && rating.equals("") && price!=-1 && length!=-1) { 
	                  if(length == 0) {
	                     sql += " AND length<60 AND price=? ORDER BY fid LIMIT ?, ?";
	                  } else if(length == 1) {
	                     sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?, ?";
	                  }
	                  stmt = conn.prepareStatement(sql);
	                  stmt.setString(1, "%"+title+"%");
	                  stmt.setString(2, "%"+actor+"%");
	                  stmt.setDouble(3, price);
	            
	               } 
	         
	         
	         //12. category만
	            else if (!category.equals("") && rating.equals("") && price ==-1 && length ==-1) { 
	               sql += " AND category=? ORDER BY fid LIMIT ?,?";
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3, category);
	            
	            } 
	         
	         //13. category, price
	            else if (!category.equals("") && rating.equals("") && price !=-1 && length ==-1) { 
	               sql += " AND category=? AND price=? ORDER BY fid LIMIT ?,?";
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3, category);
	               stmt.setDouble(4, price);
	            
	            }
	         
	         //14. category, rating 
	            else if (!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { 
	               sql += " AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3, category);
	               stmt.setString(4, rating);
	            ;
	            }
	          
	         //15. category, rating , price
	            else if (!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { 
	               sql += " AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3, category);
	               stmt.setString(4, rating);
	               stmt.setDouble(5, price);
	            
	            }
	         
	         //16. 전부 다 선택
	            else if (!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { //다입력했을떄
	               if (length == 0) {
	                  sql += " AND category=? AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
	               } else if (length == 1) {
	                  sql += " AND category=? AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
	               }
	               stmt =conn.prepareStatement(sql);
	               stmt.setString(1, "%" +title+"%");
	               stmt.setString(2,"%" + actor + "%");
	               stmt.setString(3, category);
	               stmt.setString(4, rating);
	               stmt.setDouble(5, price);
	            }
	               rs =stmt.executeQuery();
	                if(rs.next()) {
	                   totalCount = rs.getInt("cnt");
	                } 
	            }catch (SQLException e) {
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
	            return totalCount;
	            
	         }
	   
	
	public List<Double> selectfilmPriceList() {
		List<Double> list = new ArrayList<Double>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT DISTINCT price FROM film_list ORDER BY price";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getDouble("price"));
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


	
   public Map<String, Object> filmInStockCall(int filmId, int storeId) {
      Map<String, Object> map = new HashMap<String, Object>();
      Connection conn = null;
      // PreparedStatement : 쿼리를 실행
      // CallableStatement : 프로시저를 실행 
      CallableStatement stmt = null;
      ResultSet rs = null;
      // select inventory_id .... 
      List<Integer> list = new ArrayList<>();
      // select count(inventroy_id) ....
      Integer count = 0;
      conn = DBUtil.getConnection();
      try {
         stmt = conn.prepareCall("{call film_in_stock(?, ?, ?)}");
         stmt.setInt(1, filmId);
         stmt.setInt(2, storeId);
         stmt.registerOutParameter(3, Types.INTEGER);
         rs = stmt.executeQuery();
         while(rs.next()) {
            list.add(rs.getInt(1)); // rs.getInt("inventory_id")
         }
         count = stmt.getInt(3); // 프로시저 3번째 out변수 값
      } catch (SQLException e) {
         e.printStackTrace();
      }
      map.put("list", list);
      map.put("count", count);
      return map;
   }
   public static void main(String[] args) {
      FilmDao fd = new FilmDao();
      int filmId = 10;
      int storeId = 2;
      Map<String, Object> map = fd.filmInStockCall(filmId, storeId);
      List<Integer> list = (List<Integer>)map.get("list");
      
      int count = (Integer)map.get("count");
      
      System.out.println(filmId + "번 영화는 "+ storeId +"번 가게에 "+count+"개 남음");
      for(int id : list) {
         
         System.out.println(id);
      }
   }
}