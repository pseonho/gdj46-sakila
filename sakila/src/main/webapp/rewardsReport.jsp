<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	int minMonthlyPurchase = Integer.parseInt(request.getParameter("minMonthlyPurchase"));
	double minDollarAmountPurchase = Double.parseDouble(request.getParameter("minDollarAmountPurchase"));
	RewardsReportDao rr = new RewardsReportDao();
	
	Map<String, Object> map = rr.rewardsReport(minMonthlyPurchase, minDollarAmountPurchase);
	ArrayList<HashMap<String,Object>> list = (ArrayList<HashMap<String,Object>>)map.get("list"); //h가 저장된 list
	int count = (int) map.get("count"); // 입력 값이 총 몇명인지 count
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rewardReport</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
 <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
	<div class="container" >
         <table class="table">
         	<thead class="thead-dark">
				<form class="form-inline" method="post" action="<%=request.getContextPath()%>/rewardsReport.jsp">
					<h3>rewardReport</h3>
					<div><a class="text-danger">[<%=minMonthlyPurchase%>]</a>회 구매 한  고객 중<a class="text-danger"> [<%=minDollarAmountPurchase%>]</a>달러 이상 구매한 고객은 총 <a class="text-danger">[<%=count%>]</a>명 입니다.</div>
						<table class="table">
						<thead class="thead-dark">
								<th>customerId</th>
								<th>storeId</th>
								<th>firstName</th>
								<th>lastName</th>
								<th>email</th>
								<th>addressId</th>
								<th>active</th>
								<th>createDate</th>
								<th>updateDate</th>
					</thead>
					<tbody>
						<tr>
							<%
								for(Map m : list){
							%>
									<td><%=(Integer)m.get("customerId")%></td>
									<td><%=(Integer)m.get("storeId")%></td>
									<td><%=(String)m.get("firstName")%></td>
									<td><%=(String)m.get("lastName")%></td>
									<td><%=(String)m.get("email")%></td>
									<td><%=(Integer)m.get("addressId")%></td>
									<td><%=(Integer)m.get("active")%></td>
									<td><%=(String)m.get("createDate")%></td>
									<td><%=(String)m.get("updateDate")%></td>
							</tr>
								<%
									}
								%>
					
				</tbody>
			</table>
		</div>
	</body>
</html> 