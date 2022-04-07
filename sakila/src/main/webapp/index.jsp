<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>Index</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<div class="container">
<body>
	<h2>INDEX</h2><br>
		<h4>table LIST</h4>
			<ol>
				<li><a href="<%=request.getContextPath()%>/storeList.jsp" class="btn btn-outline-dark" role="button">Store List</a></li>
				<li><a href="<%=request.getContextPath()%>/staffList.jsp" class="btn btn-outline-dark" role="button">Staff List</a></li>
			</ol>
		<!-- view 7개 리스트 -->
		<h4>view LIST</h4>
			<ol>
				<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp" class="btn btn-outline-dark" role="button">actorInfo List</a></li>
				<li><a href="<%=request.getContextPath()%>/customerList.jsp" class="btn btn-outline-dark" role="button">customerList</a></li>
				<li><a href="<%=request.getContextPath()%>/filmList.jsp" class="btn btn-outline-dark" role="button">filmList</a></li>
				<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp" class="btn btn-outline-dark" role="button">nicerButSLowerFilmList</a></li>
				<li><a href="<%=request.getContextPath()%>/salesByStore.jsp" class="btn btn-outline-dark" role="button">salesByStore</a></li>
				<li><a href="<%=request.getContextPath()%>/salesByFilmCategory.jsp" class="btn btn-outline-dark" role="button">salesByFilmCategory</a></li>
				<li><a href="<%=request.getContextPath()%>/staffListView.jsp" class="btn btn-outline-dark" role="button">staffListView</a></li>
			</ol>
		<!-- procedure 3개 결과 화면 -->
		<h4>procedure LIST</h4>
			<ol>
				<li><a href="<%=request.getContextPath()%>/filmInStockForm.jsp" class="btn btn-outline-dark" role="button">filmInStockform</a></li>
				<li><a href="<%=request.getContextPath()%>/filmNotInStockForm.jsp" class="btn btn-outline-dark" role="button">filmnotInStockform</a></li>
				<li><a href="<%=request.getContextPath()%>/rewardsReportForm.jsp" class="btn btn-outline-dark" role="button">rewardsReport</a></li>
			</ol>
		<h4>detailed search LIST</h4>
		<ol>
				<li><a href="<%=request.getContextPath()%>/filmSearchForm.jsp" class="btn btn-outline-dark" role="button">filmSearchForm</a></li>
				<li><a href="<%=request.getContextPath()%>/rentalSearchForm.jsp" class="btn btn-outline-dark" role="button">rentalSearchForm</a></li>
		</ol>
</body>
</div>
</html> 