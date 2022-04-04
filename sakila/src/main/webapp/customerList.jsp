<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="dao.*"  %>
<%@ page import="vo.*"  %>
<%@ page import="java.util.*" %>
<% 
			CustomerListDao customerListDao = new  CustomerListDao();
			// 페이징
			int currentPage = 1; // 현재 페이지 초기 값 1
			if(request.getParameter("currentPage") != null){
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				System.out.println(currentPage + "<-- customerListList currentPage");
			}
			int rowPerPage = 5; //초기 페이지  수 5
			int beginRow = (currentPage - 1) * rowPerPage;
			List<CustomerList> list = customerListDao.selectCustomerListByPage(beginRow, rowPerPage); // 페이징 메서드
			int lastPage = 0; // 마지막 페이지 변수 초기화
			int totalCount = customerListDao.selectCustomerListTotalRow(); // 총 개수
			
			// 마지막 페이지 구하는 식
			lastPage = totalCount / rowPerPage;
			if(totalCount % rowPerPage != 0){
				lastPage++;
			}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>CustomerList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	</head>
<body>	
			<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			<div class="container" >
			<table class="table">
			<thead class="thead-dark">
				<h3>Customer_List</h3>
					<tr>	
						<th>customerId</th>
						<th>name</th>
						<th>address</th>
						<th>phone</th>
						<th>city</th>
						<th>country</th>
						<th>notes</th>
						<th>storeId</th>
					</tr>
						</thead>
							<tbody>
											<%
												for(CustomerList c : list){
											%>
												<tr>
													<td><%=c.getCustomerId()%></td>
													<td><%=c.getName()%></td>
													<td><%=c.getAddress()%></td>
													<td><%=c.getPhone()%></td>
													<td><%=c.getCity()%></td>
													<td><%=c.getCountry()%></td>
													<td><%=c.getNotes()%></td>
													<td><%=c.getStoreId()%></td>
												</tr>
											<%
												}
											%>
								</tbody>
							</table>
						</div>
			<!-- 페이지 목록 표시 부분 -->
			<div class="container" >
		<%
			if(currentPage > 1){
		%>
			<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage - 1%>">이전</a>
		<%
			}
		%>
		<%
			if(currentPage < lastPage){
		%>
			<a href="<%=request.getContextPath()%>/customerList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
				</div>
	</body>
</html> 
