<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page import ="dao.ActorInfoDao" %>
<%@ page import ="vo.ActorInfo" %>
<%@ page import ="java.util.*" %>
<%
		ActorInfoDao actorInfoDao = new ActorInfoDao();
		// 페이징
		int currentPage = 1; // 현재 페이지 초기 값 1
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println(currentPage + "<-- actorInfoList currentPage");
		}
		int rowPerPage = 5; //초기 페이지  수 5
		int beginRow = (currentPage - 1) * rowPerPage;
		List<ActorInfo> list = actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage); // 페이징 메서드
		int lastPage = 0; // 마지막 페이지 변수 초기화
		int totalCount = actorInfoDao.selectActorInfoTotalRow(); // 총 개수
		
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
	<title>ActorInfoList</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
	<div class="container" >
	<table class="table">
	<thead class="thead-dark">
	<h3>ActorInfo_List</h3>
			<tr>
				<th>ActorId</th>
				<th>firstName</th>
				<th>lastName</th>
				<th>filmInfo</th>
			</tr>
		<tbody>
				<%
					for(ActorInfo a : list){
				%>
			<tr>
				<td><%=a.getActorId()%></td>
				<td><%=a.getFirstName()%></td>	
				<td><%=a.getLastName()%></td>
				<td><%=a.getFilmInfo()%></td>
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
			<a href="<%=request.getContextPath()%>/actorInfoList.jsp?currentPage=<%=currentPage - 1%>">이전</a>
		<%
			}
		%>
		<%
			if(currentPage < lastPage){
		%>
			<a href="<%=request.getContextPath()%>/actorInfoList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
			</div>
	</body>
</html> 
