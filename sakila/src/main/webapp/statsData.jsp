<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.*" %>
<%
		StatsDataDao statsDataDao = new StatsDataDao();
		//1. 고객별 별 총액 180 이상
		List<Map<String,Object>> amountByCustomer = statsDataDao.amountByCustomer();
		
		//2. rental_rate별 영화 갯수
		List<Map<String, Object>>filmCountByRentalRate= statsDataDao.filmCountByRentalRate();
		
		//3.상영 시간 별 영화 갯수
		List<Map<String,Object>> filmCountByRating = statsDataDao.filmCountByRating();
		
		//4.스테프별 매출
		List<Map<String,Object>> customerByStore= statsDataDao.customerByStore();
		 
		//5. 스토어 별 총매출
		List<Map<String,Object>> amountBystore = statsDataDao.amountBystore();
		
		// 6. 스테프별 년도별 매출
		List<Map<String,Object>> amountByYStaff = statsDataDao.amountByYStaff();
		
		//7.카테고리별 영화
		List<Map<String,Object>> 	filmByCatrgory = statsDataDao.filmByCatrgory();
		
%>
			<!DOCTYPE html>
			<html>
			<head>
			<meta charset="UTF-8">
			<title>Insert title here</title>
			<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
			</head>
			<body>
				<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			 
			   <h1>1.고객 별 총액 180 이상(상위10명)</h1>
			      <table border="1">
			         <tr>
			            <td>고객아이디</td>
			            <td>고객이름</td>
			            <td>총지불액</td>
			         </tr>
			      <%
			         for(Map<String, Object> m : amountByCustomer) {
			      %>
			            <tr>
			               <td><%=m.get("customerId")%></td>
			               <td><%=m.get("name") %></td>
			               <td><%=m.get("total") %></td>
			            </tr> 
			      <%
			         }
			      %>
			      </table>
			      
					<h1>2. rental_rate별 영화 갯수</h1>
					<table border="1">
						<tr>
							<td>상영시간</td>
							<td>COUNT</td>
						</tr>
							  <%
						         for(Map<String, Object> m : filmCountByRentalRate ) {
						      %>
							<tr>
				               <td><%=m.get("rentalrate")%></td>
				               <td><%=m.get("cnt") %></td>
			           		</tr>
			           			<%
									}
								%>
		    		 </table>
		    		 
						<h1>3. 상영시간 별 영화 개수</h1>
						<table border="1">
								<tr>
							<td>등급</td>
							<td>COUNT</td>
						</tr>
							<%
								for(Map<String,Object> m : filmCountByRating) {
							%>
							<tr>
								<td><%=m.get("rating")%></td>
								<td><%=m.get("cnt")%></td>
							</tr>
							<%
								}
							%>
							</table>
							
						<h1>4. 스토어 별 고객 카운트</h1>
						<table border="1">
									<tr>
										<th>스토어</th>
										<th>방문 고객</th>
									</tr>
							<%
								for(Map<String,Object> m :  customerByStore) {
							%>
							<tr>
								<td><%=m.get("storeId")%></td>
								<td><%=m.get("cnt")%></td>
							</tr>
							<%
								}
							%>
							</table>
							
								<h1>5.  스토어 별 총매출e</h1>
						<table border="1">
									<tr>
										<th>스토어</th>
										<th>총 매출</th>
									</tr>
							<%
								for(Map<String,Object> m :  amountBystore) {
							%>
							<tr>
								<td><%=m.get("storeId")%></td>
								<td><%=m.get("total")%></td>
							</tr>
							<%
								}
							%>
						</table>
						
							<h1>6. staff별 년도별 매출</h1>
						<table border="1">
									<tr>
										<th>스테프</th>
										<th>년도</th>
										<th>총 매출</th>
									</tr>
							<%
								for(Map<String,Object> m : amountByYStaff) {
							%>
							<tr>
								<td><%=m.get("staffId")%></td>
								<td><%=m.get("year")%></td>
								<td><%=m.get("total")%></td>
							</tr>
							<%
								}
							%>
						</table>
						
								<h1>7.카테고리별 영화</h1>
						<table border="1">
									<tr>
										<th>카테고리</th>
										<th>종류</th>
										<th>COUNT</th>
									</tr>
							<%
								for(Map<String,Object> m : filmByCatrgory) {
							%>
							<tr>
								<td><%=m.get("catrgoryId")%></td>
								<td><%=m.get("name")%></td>
								<td><%=m.get("cnt")%></td>
							</tr>
							<%
								}
							%>
						</table>
			</body>
	</html>