<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*"  %>
<%@ page import="vo.*"  %>
<%@ page import="java.util.*" %>
<%   
			NicerButSlowerFilmListDao  nicerButSlowerFilmListDao = new  NicerButSlowerFilmListDao();
		//페이징
			int currentPage = 1;
			if(request.getParameter("currentPage")!= null){
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				System.out.println(currentPage + "<-- nicerButSlowerFilmList currentPage");
			}
			int rowPerPage = 5; //초기 페이지  수 5
			int beginRow = (currentPage - 1) * rowPerPage;
			List<NicerButSlowerFilmList> list =  nicerButSlowerFilmListDao.selectNicerButSlowerFilmListByPage(beginRow, rowPerPage); // 페이징 메서드
			int lastPage = 0; // 마지막 페이지 변수 초기화
			int totalCount =  nicerButSlowerFilmListDao.selectNicerButSlowerFilmListTotalRow(); // 총 개수
			
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
<title>NicerButSlowerFilmList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	</head>
	<body>	
			<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			<div class="container" >
			<table class="table">
			<thead class="thead-dark">
				<h3>NicerButSlowerFilm_List</h3>
							<tr>
									<th>filmId</th>
									<th>title</th>
									<th>description</th>
									<th>category</th>
									<th>price</th>
									<th>length</th>
									<th>rating</th>
									<th>actors</th>
							</tr>
		</div>
								<%
										for(NicerButSlowerFilmList n  : list){
								%>
									<tr>
												<td><%=n.getFilmId()%></td>		
												<td><%=n.getTitle()%></td>	
												<td><%=n.getDescription()%></td>			
												<td><%=n.getCategory()%></td>			
												<td><%=n.getPrice()%></td>			
												<td><%=n.getLength()%></td>			
												<td><%=n.getRating()%></td>			
												<td><%=n.getActors()%></td>			
									</tr>
								<%
									}
								%>
								
						<!-- 페이지 목록 표시 부분 -->
							<div class="container" >
						<%
							if(currentPage > 1){
						%>
							<a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage - 1%>">이전</a>
						<%
							}
						%>
						<%
							if(currentPage < lastPage){
						%>
							<a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage+1%>">다음</a>
						<%
							}
						%>
					</div>
					
		</body>
</html> 