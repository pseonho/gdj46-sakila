<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="dao.*"%>
<%@ page import ="java.util.*"%>
<%
		//요청값 받기
		//storeId
		int storeId =-1; // -1 : 선택안된 기본값
		if(request.getParameter("storeId")!=null&&!request.getParameter("storeId").equals("")){
			storeId = Integer.parseInt(request.getParameter("storeId"));
			System.out.println(storeId+"<--storeId");
		}
		
		//customerName
		String customerName ="";
		if(request.getParameter("customerName")!=null){
			customerName = request.getParameter("customerName");
			System.out.println(customerName+"<--customerName");
		}
		
		//beginDate
		String beginDate ="";
		if(request.getParameter("beginDate")!=null){
			beginDate = request.getParameter("beginDate");
			System.out.println(beginDate+"<--beginDate");
		}
		
		//endDate
		String endDate ="";
		if(request.getParameter("endDate")!=null){
			endDate = request.getParameter("endDate");
			System.out.println(endDate+"<--endDate");
		}
		
		// 페이징 코드
		int currentPage = 1; // 현재 페이지 초기 값 1
		if(request.getParameter("currentPage")!=null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			System.out.println(currentPage+"<--currentPage");
		}
		
		int rowPerPage = 10; //초기 페이지 당 행의 수 10 - 수에 따라 임의 변경 가능
		if(request.getParameter("rowPerPage")!=null){
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
			System.out.println(rowPerPage+"<--rowPerPage");
		}
		
		int beginRow = (currentPage-1)*rowPerPage; //Page 처음 행의 값 연산식
			System.out.println(beginRow+"beginRow");
		int minPage = 1;
			if(request.getParameter("minPage")!= null){
				minPage = Integer.parseInt(request.getParameter("minPage"));
				currentPage=minPage;
			}
			System.out.println(minPage+"<--minPage");
			// 마지막 페이지 변수 값 저장 코드
			int lastPage = 0;
			
		//dao 호출
		RentalDao rentalDao = new RentalDao();
		
		// Map 데이터 저장 
		List<Map<String,Object>> rentalList = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);
		
		//검색후 전체 게시물 수
		int totalRow =rentalDao.totalRow(storeId, customerName, beginDate, endDate);
		System.out.println(totalRow+"<--totalRow");
		
		//마지막 페이지
		lastPage = ((totalRow - 1) / rowPerPage + 1); 
		System.out.println(lastPage+"<--lastPage");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>rentalSearchAction</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
	</head>
	<body>	
			<a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-outline-dark" role="button">index</a>
			<div class="container" >
			<table class="table">
			<thead class="thead-dark">
		 	<div style ="TEXT-ALIGN:center"> <a href="<%=request.getContextPath()%>/rentalSearchForm.jsp"  class=" btn-outline-light text-dark"role="button"><h1>rentalSearchAction</h1></a><br>
			<form method="get" action="<%=request.getContextPath()%>/rentalSearchAction.jsp"></div>
				<tr>
					<th>rentalId</th>
					<th>rentalDate</th>
					<th>returnDate</th>
					<th>customerId</th>
					<th>storeId</th>
					<th>cName</th>
					<th>title</th>
				</tr>	
				</thead>
			<%
				for(Map m : rentalList) {
			%>
				<tr>
					<td><%=m.get("rentalId") %></td>
					<td><%=m.get("rentalDate") %></td>
					<td><%=m.get("returnDate") %></td>
					<td><%=m.get("customerId") %></td>
					<td><%=m.get("storeId") %></td>
					<td><%=m.get("cName") %></td>
					<td><%=m.get("title") %></td>
				</tr>
			<%
				}
			%>
		</table>
		
		<!-- 검색 정보에서 목록 이동 -->
				<input type="hidden" name =storeId value =<%=storeId%>>
				<input type="hidden" name =customerName value =<%=customerName%>>
				<input type="hidden" name =beginDate value =<%=beginDate%>>
				<input type="hidden" name =endDate value =<%=endDate%>>
		
		<!-- 이전 currentPage-1 -->
		<div style ="TEXT-ALIGN:center">
				<%
					if(currentPage>1){
				%>
				 		<button type = "submit" value ="<%=currentPage-1%>" name = "currentPage" class="btn btn-outline-dark" >이전</button>
				<%
					}
				%>
				<!-- 목록 사이 번호 표시 -->
				<%
					for(int i = minPage; i<minPage+10; i=i+1){
						if(i<=lastPage){
							if(currentPage==i){
				%>
							<button type = "submit" value ="<%=i%>" name = "currentPage" class="btn btn-outline-dark"><%=i%></button>
				<%
							}else{
				%>
							<button type = "submit" value ="<%=i%>" name = "currentPage" class="btn btn-light"><%=i%></button>
				<%
							}
						}
					}	
				%>
				<!-- 다음 부분 currentPage+1 -->
				<%
					if(currentPage<lastPage){
				%>
			 		<button type = "submit" value ="<%=currentPage+1%>" name = "currentPage" class="btn btn-outline-secondary" >다음</button>
				<%
					}
				%>
			</div>
		 </form>
	</body>
</html> 