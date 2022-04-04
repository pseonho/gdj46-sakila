<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
	SalesByStoreDao salesByStoreDao = new SalesByStoreDao();
	List<SalesByStore> list = salesByStoreDao.selectSalesByStoreListByPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>salesByStore</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
			<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			<div class="container" >
			<table class="table">
			<thead class="thead-dark">
			<h3>salesByStore_View</h3>
							<tr>
								<th>store</th>
								<th>manager</th>
								<th>totalSales</th>
						</tr>
					</thead>
					<%
					for(SalesByStore s : list){
					%>
							<tr>
								<td><%=s.getStore()%></td>
								<td><%=s.getManager()%></td>
								<td><%=s.getTotalSales()%></td>
							</tr>
					<%
						}	
					%>
		</table>
		</body>
	</div>
</html>