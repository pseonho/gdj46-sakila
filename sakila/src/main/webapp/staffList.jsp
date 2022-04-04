<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="dao.*"%>    
<%
	StaffDao staffDao = new StaffDao();
	List<Map<String, Object>> list = staffDao.selectStaffList();
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff_List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>

	<div class="container">
	<table class="table">
	<thead class="thead-dark">
	<h3>Staff_List</h3>
			<tr>
				<th>storeId</th>
				<th>staffId</th>
				<th>staffName</th>
				<th>addressId</th>
				<th>staffAddress</th>
				<th>lastUpdate</th>
				<th>email</th>
			</tr>
			<tbody>
			<%
				for(Map m : list) {
			%>
					<tr>
						<td><%=m.get("storeId")%></td>
						<td><%=m.get("staffId")%></td>
						<td><%=m.get("staffName")%></td>
						<td><%=m.get("addressId")%></td>
						<td><%=m.get("staffAddress")%></td>
						<td><%=m.get("email")%></td>
						<td><%=m.get("lastUpdate")%></td>
					</tr>
			<%
				}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>