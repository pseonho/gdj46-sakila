<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="vo.*"%>
<%@ page import="dao.*" %>
<%
	StaffListDao staffListDao = new StaffListDao();
	List<StaffList> list = staffListDao.selectStaffListByPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StaffList_view</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
		<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			<div class="container" >
			<table class="table">
			<thead class="thead-dark">
					<h3>staffList_View</h3>
						<tr>
								<th>staffId</th>
								<th>name</th>
								<th>address</th>
								<th>phone</th>
								<th>city</th>
								<th>country</th>
								<th>storeId</th>
						</tr>		
				</thead>
	
							<%
								for(StaffList s : list){
							%>
								<tr>
									<td><%=s.getStaffId()%></td>
									<td><%=s.getName()%></td>
									<td><%=s.getAddress()%></td>
									<td><%=s.getPhone()%></td>
									<td><%=s.getCity()%></td>
									<td><%=s.getCountry()%></td>
									<td><%=s.getStoreId()%></td>
								</tr>
				<%
					}	
				%>
					</table>
				</div>
		</body>
</html>