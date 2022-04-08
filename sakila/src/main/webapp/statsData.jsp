<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.*" %>
<%@ page import ="dao.*" %>
<%
		StatsDataDao statsDataDao = new StatsDataDao();
		//1. customer 별 총 amount
		List<Map<String,Object>> amountByCustomer = statsDataDao.amountByCustomer();
		
		//2. rental_rate별 영화 갯수
		List<Map<String, Object>>filmCountByRentalRate= statsDataDao.filmCountByRentalRate();
		
		//3.rating별 영화 갯수
		List<Map<String,Object>> filmCountByRating = statsDataDao.filmCountByRating();
		
		//4.스토어별 매출
		List<Map<String,Object>> amountBystaff = statsDataDao.amountBystaff();
		
		
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
			 
			   <h1>1.amountByCoustomer (상위10명)</h1>
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
		    		 
						<h1>3. filmCountByRating</h1>
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
							
							
						<h1>4. amountBystaff</h1>
						<table border="1">
									<tr>
										<th>스테프<th>
										<th>총액</th>
									</tr>
							<%
								for(Map<String,Object> m :  amountBystaff) {
							%>
							<tr>
								<td><%=m.get("staffId")%></td>
								<td><%=m.get("sum")%></td>
							</tr>
							<%
								}
							%>
						</table>
			</body>
	</html>