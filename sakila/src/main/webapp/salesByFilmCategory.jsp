<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
			SalesByFilmCategoryDao salesByFilmCategoryDao = new SalesByFilmCategoryDao();
			List<SalesByFilmCategory> list = salesByFilmCategoryDao.selectSalesByFilmCategoryByPage(); // 페이징 메서드
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
			<div class="container" >
			<table class="table">
			<thead class="thead-dark">
				<h3>salesByFilmCategory_View</h3>
					<tr>
						<th>category</th>
						<th>totalSales</th>
					</tr>	
			</thead>
				<%
					for(SalesByFilmCategory s : list ){
				%>
					<tr>
						<td><%=s.getCategory()%></td>
						<td><%=s.getTotalSales()%></td>
					</tr>
				<%
					}	
				%>
			
				</table>
		</body>
	</div>
</html>